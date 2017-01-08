package core.services;

import core.thirdParty.aftership.enums.ISO3Country;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Service
public class IsoCountryService {
    @Cacheable("iso3Codes")
    public ISO3Country getISO3Country(String country) {
        if(country == null || country.trim().length() < 2) {
            return null;
        } else {
            if(country.trim().length() == 3 && getISO3CountryByCode(country.toUpperCase()) != null) {
                return getISO3CountryByCode(country.toUpperCase());
            } else if (country.trim().length() == 2 &&
                    iso2CountryCodeToIso3CountryCode(country.toUpperCase()) != null &&
                    getISO3CountryByCode(iso2CountryCodeToIso3CountryCode(country.toUpperCase())) != null) {
                return getISO3CountryByCode(country.toUpperCase());
            }
            ISO3Country iso3Country = getISO3CountryByName(country);
            if(iso3Country != null) {
                return iso3Country;
            }
        }
        return null;
    }

    private ISO3Country getISO3CountryByCode(String country) {
        try {
            return ISO3Country.valueOf(country.toUpperCase());
        } catch (Exception e) {}
        return null;
    }

    private ISO3Country getISO3CountryByName(String country) {
        for(ISO3Country iso3Country : ISO3Country.values()) {
            if(iso3Country.getName().equalsIgnoreCase(country)) {
                return iso3Country;
            }
        }
        return null;
    }

    private String iso2CountryCodeToIso3CountryCode(String iso2CountryCode){
        try {
            Locale locale = new Locale("", iso2CountryCode);
            return locale.getISO3Country();
        } catch (Exception e) {}
        return null;
    }

    @Cacheable("iso2Codes")
    public String getISO2CountryCode(String country) {
        String[] countries = Locale.getISOCountries();
        Map<String, Locale> localeMap = new HashMap<String, Locale>(countries.length);
        for (String countryCode : countries) {
            Locale locale = new Locale("", countryCode);
            localeMap.put(locale.getISO3Country().toUpperCase(), locale);
        }
        if(country == null || country.trim().length() < 2) {
            return null;
        } else {
            if(country.trim().length() == 3 && localeMap.get(country) != null) {
                return localeMap.get(country).getCountry();
            } else if (country.trim().length() == 2 && new Locale("", country.toUpperCase()) != null) {
                return country.toUpperCase();
            }
            ISO3Country iso3Country = getISO3CountryByName(country);
            if(iso3Country != null) {
                return localeMap.get(iso3Country.toString()).getCountry();
            }
        }
        return null;
    }
}
