package core.controllers;

import core.entities.*;
import core.services.TransactionService;
import core.util.PrettyDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.WeekFields;
import java.util.*;

import static java.time.temporal.ChronoUnit.MONTHS;

@Controller
@RequestMapping(value="/analytics/")
public class AnalyticsController {

    private final AccountDAO accountDAO;
    private final TransactionDAO transactionDAO;
    private final TransactionService transactionService;

    @Autowired
    public AnalyticsController(AccountDAO accountDAO, TransactionDAO transactionDAO,
                               TransactionService transactionService) {
        Assert.notNull(accountDAO, "AccountDAO must not be null!");
        Assert.notNull(transactionDAO, "TransactionDAO must not be null!");
        Assert.notNull(transactionService, "TransactionService must not be null!");
        this.accountDAO = accountDAO;
        this.transactionDAO = transactionDAO;
        this.transactionService = transactionService;
    }

    @RequestMapping(value = "weekly")
    public String weeklyDashboard(ModelMap model) {
        ArrayList<AnalyticsTransaction> last50Trans = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        Timestamp nowTimestamp = Timestamp.valueOf(now);
        LocalDate today = LocalDate.now();
        LocalDateTime todayMidnight = LocalDateTime.of(today, LocalTime.MIDNIGHT);
        LocalDateTime firstOfThisWeek = todayMidnight.with(WeekFields.of(Locale.US).dayOfWeek(), 1);
        Timestamp firstOfThisWeekTimeStamp = Timestamp.valueOf(firstOfThisWeek);
        LocalDateTime firstOfLastWeek = todayMidnight.with(WeekFields.of(Locale.US).dayOfWeek(), 1).minus(1, ChronoUnit.WEEKS);
        Timestamp firstOfLastWeekTimeStamp = Timestamp.valueOf(firstOfLastWeek);

        firstOfLastWeek = firstOfLastWeek.minusWeeks(1);
        Timestamp firstOfTwoWeeksAgoTimeStamp = Timestamp.valueOf(firstOfLastWeek);

        firstOfLastWeek = firstOfLastWeek.minusWeeks(1);
        Timestamp firstOfThreeWeeksAgoTimeStamp = Timestamp.valueOf(firstOfLastWeek);

        LocalDateTime oneMonthAgo = todayMidnight.minus(1, MONTHS);
        Timestamp oneMonthAgoTimeStamp = Timestamp.valueOf(oneMonthAgo);

        Long transactionsThisWeek = transactionDAO.countByCreatedAfterAndTransactionStateNotIn(firstOfThisWeekTimeStamp,transactionService.getExcludedTransactionStates());
        Long transactionsLastWeek = transactionDAO.countByCreatedBetweenAndTransactionStateNotIn(firstOfLastWeekTimeStamp,firstOfThisWeekTimeStamp,transactionService.getExcludedTransactionStates());
        Long transactionsTwoWeeksAgo = transactionDAO.countByCreatedBetweenAndTransactionStateNotIn(firstOfTwoWeeksAgoTimeStamp,firstOfLastWeekTimeStamp,transactionService.getExcludedTransactionStates());
        Long transactionsThreeWeeksAgo = transactionDAO.countByCreatedBetweenAndTransactionStateNotIn(firstOfThreeWeeksAgoTimeStamp,firstOfTwoWeeksAgoTimeStamp,transactionService.getExcludedTransactionStates());
        Long accountsThisWeek = accountDAO.countByCreatedAfter(firstOfThisWeekTimeStamp);
        Long accountsLastWeek = accountDAO.countByCreatedBetween(firstOfLastWeekTimeStamp,firstOfThisWeekTimeStamp);
        Long accountsTwoWeeksAgo = accountDAO.countByCreatedBetween(firstOfTwoWeeksAgoTimeStamp,firstOfLastWeekTimeStamp);
        Long accountsThreeWeeksAgo = accountDAO.countByCreatedBetween(firstOfThreeWeeksAgoTimeStamp,firstOfTwoWeeksAgoTimeStamp);

        HashMap<Long,String> accountNames = new HashMap<>();
        for(Transaction transaction : transactionDAO.findFirst50ByOrderByCreatedDesc()) {
            if(!accountNames.containsKey(transaction.getAccountId())) {
                Account account = accountDAO.findOne(transaction.getAccountId());
                accountNames.put(transaction.getAccountId(),account.getName());
            }
            AnalyticsTransaction analyticsTransaction = new AnalyticsTransaction(accountNames.get(transaction.getAccountId()),transaction.getId(),transaction.getTransactionState().toString(), PrettyDate.makeItPretty(transaction.getCreated()));
            last50Trans.add(analyticsTransaction);
        }
        long transactionsSHORTThisWeek = 0;
        if(transactionsThisWeek < 12500) {
            transactionsSHORTThisWeek = 12500-transactionsThisWeek;
        }
        long transactionsSHORTLastWeek = 0;
        if(transactionsLastWeek < 12500) {
            transactionsSHORTLastWeek = 12500-transactionsLastWeek;
        }
        long transactionsSHORTTwoWeeksAgo = 0;
        if(transactionsTwoWeeksAgo < 12500) {
            transactionsSHORTTwoWeeksAgo = 12500-transactionsTwoWeeksAgo;
        }
        long transactionsSHORTThreeWeeksAgo = 0;
        if(transactionsThreeWeeksAgo < 12500) {
            transactionsSHORTThreeWeeksAgo = 12500-transactionsThreeWeeksAgo;
        }


        long accountsSHORTThisWeek = 0;
        if(accountsThisWeek < 4) {
            accountsSHORTThisWeek = 4-accountsThisWeek;
        }
        long accountsSHORTLastWeek = 0;
        if(accountsLastWeek < 4) {
            accountsSHORTLastWeek = 4-accountsLastWeek;
        }
        long accountsSHORTTwoWeeksAgo = 0;
        if(accountsTwoWeeksAgo < 4) {
            accountsSHORTTwoWeeksAgo = 4-accountsTwoWeeksAgo;
        }
        long accountsSHORTThreeWeeksAgo = 0;
        if(accountsThreeWeeksAgo < 4) {
            accountsSHORTThreeWeeksAgo = 4-accountsThreeWeeksAgo;
        }

        model.addAttribute("lastUpdated",PrettyDate.currentDateTimeUtah());
        model.addAttribute("last50Trans",last50Trans);
        model.addAttribute("transactionsThisWeek",transactionsThisWeek);
        model.addAttribute("transactionsSHORTThisWeek",transactionsSHORTThisWeek);
        model.addAttribute("transactionsLastWeek",transactionsLastWeek);
        model.addAttribute("transactionsSHORTLastWeek",transactionsSHORTLastWeek);
        model.addAttribute("transactionsTwoWeeksAgo",transactionsTwoWeeksAgo);
        model.addAttribute("transactionsSHORTTwoWeeksAgo",transactionsSHORTTwoWeeksAgo);
        model.addAttribute("transactionsThreeWeeksAgo",transactionsThreeWeeksAgo);
        model.addAttribute("transactionsSHORTThreeWeeksAgo",transactionsSHORTThreeWeeksAgo);

        model.addAttribute("accountsThisWeek",accountsThisWeek);
        model.addAttribute("accountsSHORTThisWeek",accountsSHORTThisWeek);
        model.addAttribute("accountsLastWeek",accountsLastWeek);
        model.addAttribute("accountsSHORTLastWeek",accountsSHORTLastWeek);
        model.addAttribute("accountsTwoWeeksAgo",accountsTwoWeeksAgo);
        model.addAttribute("accountsSHORTTwoWeeksAgo",accountsSHORTTwoWeeksAgo);
        model.addAttribute("accountsThreeWeeksAgo",accountsThreeWeeksAgo);
        model.addAttribute("accountsSHORTThreeWeeksAgo",accountsSHORTThreeWeeksAgo);

        model.addAttribute("newAccounts",accountDAO.countByCreatedBetween(oneMonthAgoTimeStamp,nowTimestamp));
        Set<Long> activeAccounts = new HashSet<>();
        transactionDAO.findByCreatedAfterAndTransactionStateNotIn(oneMonthAgoTimeStamp,transactionService.getExcludedTransactionStates()).forEach(transaction->activeAccounts.add(transaction.getAccountId()));
        model.addAttribute("activeAccounts",activeAccounts.size());
        return "analytics/weeklyProgress";
    }
}
