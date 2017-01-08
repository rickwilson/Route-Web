package core.services;

import core.Application;
import org.javamoney.moneta.Money;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.money.*;
import javax.money.convert.CurrencyConversion;
import javax.money.convert.ExchangeRateProvider;
import javax.money.convert.MonetaryConversions;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;

@Service
public class CurrencyService {

    private final HashMap<String, String> countryCodeCurrencyCodeMap;
    private final HashMap<String, String> currencyCodeCurrencyNameMap;
    private final HashMap<String, Locale> currencyCodeLocalMap;
    private final HashMap<String, Integer> currencyCodeFractionDigitsMap;
    private final HashMap<String, String> currencyCodeSymbolMap;

//    @Autowired
    public CurrencyService() {
        countryCodeCurrencyCodeMap = new HashMap<>();
        currencyCodeCurrencyNameMap = new HashMap<>();
        currencyCodeLocalMap = new HashMap<>();
        currencyCodeFractionDigitsMap = new HashMap<>();
        currencyCodeSymbolMap = new HashMap<>();
        for (Locale locale : Locale.getAvailableLocales()) {
            try {
                Currency currency = Currency.getInstance(locale);
                countryCodeCurrencyCodeMap.put(locale.getISO3Country(), currency.getCurrencyCode());
                currencyCodeCurrencyNameMap.put(currency.getCurrencyCode(),currency.getDisplayName());
                currencyCodeLocalMap.put(currency.getCurrencyCode(),locale);
                currencyCodeFractionDigitsMap.put(currency.getCurrencyCode(),currency.getDefaultFractionDigits());
                currencyCodeSymbolMap.put(currency.getCurrencyCode(),currency.getSymbol());
            } catch (Exception e) {
            }
        }
    }

    @Cacheable("exchangeRate1")
    public String exchangeToUSDWithCountryCode(String termCountryCode, BigDecimal amount) {
        return exchangeToUSDWithCurrencyCode(countryCodeCurrencyCodeMap.get(termCountryCode),amount);
    }

    @Cacheable("exchangeRate2")
    public String exchangeToUSDWithCurrencyCode(String termCurrencyCode, BigDecimal amount) {
        CurrencyUnit currencyUSD = Monetary.getCurrency("USD");
        CurrencyUnit termCurrency = Monetary.getCurrency(termCurrencyCode);

        CurrencyConversion usdConversion = MonetaryConversions.getConversion(currencyUSD);

        MonetaryAmount termDollars = Money.of(amount, termCurrency);
        MonetaryAmount usDollar = termDollars.with(usdConversion);

        MonetaryRounding rounding = Monetary.getRounding(currencyUSD);
        MonetaryAmount roundedUSD = usDollar.with(rounding);
        return roundedUSD.getNumber().toString();
    }

    @Cacheable("exchangeRate3")
    public String getExchangeRate(String termCurrencyCode) {
        ExchangeRateProvider ecbExchangeRateProvider = MonetaryConversions.getExchangeRateProvider();
        return ecbExchangeRateProvider.getExchangeRate("USD",termCurrencyCode).getFactor().toString();
    }

    @CacheEvict(value = { "exchangeRate1","exchangeRate2","exchangeRate3" }, allEntries = true)
    public void evictExchangeRateCaches() {
    }

    @Scheduled(fixedRate = 21600000) // 6hrs
    public void runEvict() {
        evictExchangeRateCaches();
    }


    public String formatCurrencyForCurrencyCode(BigDecimal amount, String currencyCode) {
        amount = trimTrailingDigitsForCurrency(amount, currencyCode);
        return currencyCodeSymbolMap.get(currencyCode)+amount.toString();
    }

    public BigDecimal trimTrailingDigitsForCurrency(BigDecimal amount, String currencyCode) {
        return amount.setScale(currencyCodeFractionDigitsMap.get(currencyCode), BigDecimal.ROUND_HALF_UP);
    }

    public String changeCurrencyString(String currencyCodeFrom, String currencyCodeTo, BigDecimal amount) {
        return changeCurrencyBigDecimal(currencyCodeFrom,currencyCodeTo,amount).toString();
    }

    public BigDecimal changeCurrencyBigDecimal(String currencyCodeFrom, String currencyCodeTo, BigDecimal amount) {

        CurrencyUnit currencyFrom = Monetary.getCurrency(currencyCodeFrom);
        CurrencyUnit currencyTo = Monetary.getCurrency(currencyCodeTo);

        CurrencyConversion toConversion = MonetaryConversions.getConversion(currencyTo);

        MonetaryAmount fromDollars = Money.of(amount, currencyFrom);
        MonetaryAmount toDollar = fromDollars.with(toConversion);

        return new BigDecimal(toDollar.getNumber().doubleValueExact());
    }

    public String changeToUsdAndFormatCurrency(String currencyCodeFrom, BigDecimal amount) {
        BigDecimal changedAndFormatted = changeCurrencyBigDecimal(currencyCodeFrom,"USD",amount);
        changedAndFormatted = trimTrailingDigitsForCurrency(changedAndFormatted, "USD");
        return changedAndFormatted.toString();
    }

    public String changeAndFormatCurrency(String currencyCodeFrom, String currencyCodeTo, BigDecimal amount) {
        BigDecimal changedAndFormatted = changeCurrencyBigDecimal(currencyCodeFrom,"USD",amount);
        changedAndFormatted = trimTrailingDigitsForCurrency(changedAndFormatted, "USD");
        return changedAndFormatted.toString();
    }

    @Cacheable("isCurrencyCode")
    public boolean isCurrencyCode(String testCurranceCode) {
        return currencyCodeCurrencyNameMap.containsKey(testCurranceCode);
    }

    public HashMap<String, String> getCurrencyCodeCurrencyNameMap() {
        return currencyCodeCurrencyNameMap;
    }

}
