package core.services;

import core.Application;
import core.entities.*;
import core.entities.client.ClientDashboardObject;
import core.entities.enums.TransactionState;
import core.util.CheckData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class TransactionService {
    private final TransactionDAO transactionDAO;
    private ArrayList<TransactionState> excludedTransactionStates;

    @Autowired
    public TransactionService(TransactionDAO transactionDAO) {
        Assert.notNull(transactionDAO, "TransactionDAO must not be null!");
        this.transactionDAO = transactionDAO;

        excludedTransactionStates = new ArrayList<>();
        excludedTransactionStates.add(TransactionState.TEST);
        excludedTransactionStates.add(TransactionState.ERROR);
        excludedTransactionStates.add(TransactionState.FAILED);
    }

    public void populateClientDashboard(ClientDashboardObject clientDashboardObject) {
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int month = today.getMonthValue();
        LocalDate yearAgoLocalDate = today.minusYears(1);
        LocalDateTime YearAgoLocalDateTime = LocalDateTime.of(yearAgoLocalDate.getYear(),yearAgoLocalDate.getMonth(),1,1,1);
        Timestamp yearAgo = Timestamp.valueOf(YearAgoLocalDateTime);
        HashMap<String,Integer> revPoints = new HashMap<>();
        for(Transaction transaction : transactionDAO.findByAccountIdAndCreatedAfterAndTransactionStateNotIn(clientDashboardObject.getAccountId(),yearAgo,excludedTransactionStates)) {
            LocalDate transDate =  transaction.getCreated().toLocalDateTime().toLocalDate();
            boolean isUpTake = false;
            if(transaction.getTransactionState().equals(TransactionState.NEW) ||
                    transaction.getTransactionState().equals(TransactionState.WAITING_FOR_SHIPPED) ||
                            transaction.getTransactionState().equals(TransactionState.SHIPPED_WAITING_FOR_ROUTE_ORDER) ||
                                    transaction.getTransactionState().equals(TransactionState.COMPLETE_BILLED) ||
                                            transaction.getTransactionState().equals(TransactionState.COMPLETE_UNBILLED) ||
                                                transaction.getTransactionState().equals(TransactionState.COMPLETE) ||
                                                    transaction.getTransactionState().equals(TransactionState.PAID)) {
                isUpTake = true;
            }
            int revShare = CheckData.getRevShare(transaction.getRevShare(), transaction.getRevSharePercent(), transaction.getInsPrice());
            if(revShare>0){
                updateRevPoint(revPoints,transDate.getMonth().name(),revShare);
            }
            if(transDate.equals(today)) {
                clientDashboardObject.addTranToday();
                if(isUpTake) {
                    clientDashboardObject.addUptakeCountToday();
                }
                clientDashboardObject.addRevToday(revShare);
            }
            if(transDate.getMonthValue() == month) {
                clientDashboardObject.addTranMonth();
                if(isUpTake) {
                    clientDashboardObject.addUptakeCountMonth();
                }
                clientDashboardObject.addRevMonth(revShare);
            }
            clientDashboardObject.addTranYear();
            if(isUpTake) {
                clientDashboardObject.addUptakeCountYear();
            }
            clientDashboardObject.addRevYear(revShare);
        }
        setMonthsAndRev(revPoints, clientDashboardObject);
    }

    private void setMonthsAndRev(HashMap<String,Integer> revPoints, ClientDashboardObject clientDashboardObject) {
        LocalDate rollingDate = LocalDate.now();
        clientDashboardObject.setMonth1Name(rollingDate.getMonth().name().substring(0,3));
        if(revPoints.containsKey(rollingDate.getMonth().name())) {
            clientDashboardObject.setMonth1Value(CheckData.removeCentsFromInt(revPoints.get(rollingDate.getMonth().name())));
        }
        rollingDate = rollingDate.minusMonths(1);
        clientDashboardObject.setMonth2Name(rollingDate.getMonth().name().substring(0,3));
        if(revPoints.containsKey(rollingDate.getMonth().name())) {
            clientDashboardObject.setMonth2Value(CheckData.removeCentsFromInt(revPoints.get(rollingDate.getMonth().name())));
        }
        rollingDate = rollingDate.minusMonths(1);
        clientDashboardObject.setMonth3Name(rollingDate.getMonth().name().substring(0,3));
        if(revPoints.containsKey(rollingDate.getMonth().name())) {
            clientDashboardObject.setMonth3Value(CheckData.removeCentsFromInt(revPoints.get(rollingDate.getMonth().name())));
        }
        rollingDate = rollingDate.minusMonths(1);
        clientDashboardObject.setMonth4Name(rollingDate.getMonth().name().substring(0,3));
        if(revPoints.containsKey(rollingDate.getMonth().name())) {
            clientDashboardObject.setMonth4Value(CheckData.removeCentsFromInt(revPoints.get(rollingDate.getMonth().name())));
        }
        rollingDate = rollingDate.minusMonths(1);
        clientDashboardObject.setMonth5Name(rollingDate.getMonth().name().substring(0,3));
        if(revPoints.containsKey(rollingDate.getMonth().name())) {
            clientDashboardObject.setMonth5Value(CheckData.removeCentsFromInt(revPoints.get(rollingDate.getMonth().name())));
        }
        rollingDate = rollingDate.minusMonths(1);
        clientDashboardObject.setMonth6Name(rollingDate.getMonth().name().substring(0,3));
        if(revPoints.containsKey(rollingDate.getMonth().name())) {
            clientDashboardObject.setMonth6Value(CheckData.removeCentsFromInt(revPoints.get(rollingDate.getMonth().name())));
        }
        rollingDate = rollingDate.minusMonths(1);
        clientDashboardObject.setMonth7Name(rollingDate.getMonth().name().substring(0,3));
        if(revPoints.containsKey(rollingDate.getMonth().name())) {
            clientDashboardObject.setMonth7Value(CheckData.removeCentsFromInt(revPoints.get(rollingDate.getMonth().name())));
        }
        rollingDate = rollingDate.minusMonths(1);
        clientDashboardObject.setMonth8Name(rollingDate.getMonth().name().substring(0,3));
        if(revPoints.containsKey(rollingDate.getMonth().name())) {
            clientDashboardObject.setMonth8Value(CheckData.removeCentsFromInt(revPoints.get(rollingDate.getMonth().name())));
        }
        rollingDate = rollingDate.minusMonths(1);
        clientDashboardObject.setMonth9Name(rollingDate.getMonth().name().substring(0,3));
        if(revPoints.containsKey(rollingDate.getMonth().name())) {
            clientDashboardObject.setMonth9Value(CheckData.removeCentsFromInt(revPoints.get(rollingDate.getMonth().name())));
        }
        rollingDate = rollingDate.minusMonths(1);
        clientDashboardObject.setMonth10Name(rollingDate.getMonth().name().substring(0,3));
        if(revPoints.containsKey(rollingDate.getMonth().name())) {
            clientDashboardObject.setMonth10Value(CheckData.removeCentsFromInt(revPoints.get(rollingDate.getMonth().name())));
        }
        rollingDate = rollingDate.minusMonths(1);
        clientDashboardObject.setMonth11Name(rollingDate.getMonth().name().substring(0,3));
        if(revPoints.containsKey(rollingDate.getMonth().name())) {
            clientDashboardObject.setMonth11Value(CheckData.removeCentsFromInt(revPoints.get(rollingDate.getMonth().name())));
        }
        rollingDate = rollingDate.minusMonths(1);
        clientDashboardObject.setMonth12Name(rollingDate.getMonth().name().substring(0,3));
        if(revPoints.containsKey(rollingDate.getMonth().name())) {
            clientDashboardObject.setMonth12Value(CheckData.removeCentsFromInt(revPoints.get(rollingDate.getMonth().name())));
        }
    }

    private void updateRevPoint(HashMap<String,Integer> revPoint, String month, int rev) {
        if(revPoint.containsKey(month)) {
            revPoint.put(month, revPoint.get(month) + rev);
        } else {
            revPoint.put(month,rev);
        }
    }

    public ArrayList<TransactionState> getExcludedTransactionStates() {
        return excludedTransactionStates;
    }

    @Cacheable("customerCountry")
    public String getCustomerCountry(long transactionId) {
        String country = "unknown";
        Transaction transaction = transactionDAO.findOne(transactionId);
        if(transaction != null && transaction.getCustomerCountry() != null && transaction.getCustomerCountry().trim().length() > 0) {
            country = transaction.getCustomerCountry();
        }
        return country;
    }

    @CacheEvict(value = { "customerCountry"}, allEntries = true)
    public void evictCustomerCountryCaches() {
    }

    @Scheduled(fixedRate = 604800000) // 7 days
    public void runEvict() {
        evictCustomerCountryCaches();
    }
}
