package core.entities;

import core.entities.enums.TransactionState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@Transactional
public interface TransactionDAO extends JpaRepository<Transaction, Long> {

    List<Transaction> findByAccountId(long accountId);
    List<Transaction> findByCrmType(Transaction.CrmType crmType);
    List<Transaction> findByCreatedAfterAndTransactionStateNotIn(Timestamp start, Collection<TransactionState> transactionState);
    List<Transaction> findByAccountIdAndCreatedAfterAndTransactionStateNotIn(long accountId, Timestamp start, Collection<TransactionState> transactionState);
    Long countByCreatedBetweenAndTransactionStateNotIn(Timestamp start, Timestamp end, Collection<TransactionState> transactionState);
    Long countByCreatedAfterAndTransactionStateNotIn(Timestamp start, Collection<TransactionState> transactionState);
    List<Transaction> findFirst25ByOrderByCreatedDesc();
    List<Transaction> findFirst50ByOrderByCreatedDesc();

    Transaction findByApiKeyAndOrderDetailTableAndOrderDetailTableId(String apiKey, Transaction.OrderDetailTable orderDetailTable, long orderDetailTableId);

    Page<Transaction> findByAccountId(long accountId, Pageable pageable);
    Page<Transaction> findByAccountIdAndCreatedBetween(long accountId, Timestamp start, Timestamp end, Pageable pageable);
    Page<Transaction> findByAccountIdAndCreatedBetweenAndOrderIdContainingIgnoreCase(long accountId, Timestamp start, Timestamp end, String orderId, Pageable pageable);
    Page<Transaction> findByAccountIdAndCreatedBetweenAndCustomerFirstContainingIgnoreCase(long accountId, Timestamp start, Timestamp end, String customerFirst, Pageable pageable);
    Page<Transaction> findByAccountIdAndCreatedBetweenAndCustomerLastContainingIgnoreCase(long accountId, Timestamp start, Timestamp end, String customerLast, Pageable pageable);
    Page<Transaction> findByAccountIdAndCreatedBetweenAndCustomerProvenceContainingIgnoreCase(long accountId, Timestamp start, Timestamp end, String customerProvence, Pageable pageable);
    Page<Transaction> findByAccountIdAndCreatedBetweenAndCustomerCountryContainingIgnoreCase(long accountId, Timestamp start, Timestamp end, String customerCountry, Pageable pageable);
    Page<Transaction> findByAccountIdAndCreatedBetweenAndCourierContainingIgnoreCase(long accountId, Timestamp start, Timestamp end, String courier, Pageable pageable);
    Page<Transaction> findByAccountIdAndCreatedBetweenAndTrackingNumberContainingIgnoreCase(long accountId, Timestamp start, Timestamp end, String trackingNumber, Pageable pageable);
    Page<Transaction> findByAccountIdAndCreatedBetweenAndCustomerPhoneContainingIgnoreCase(long accountId, Timestamp start, Timestamp end, String customerPhone, Pageable pageable);
    Page<Transaction> findByAccountIdAndCreatedBetweenAndCustomerEmailContainingIgnoreCase(long accountId, Timestamp start, Timestamp end, String customerEmail, Pageable pageable);

    @Query(value = "SELECT a.`name` AS account_name, DATE(t.created) AS transaction_date, COUNT(t.id) AS transaction_count FROM `transaction` t, account a WHERE DATE(t.created) = DATE(NOW()) - INTERVAL 1 DAY AND (t.transaction_state = 0 OR t.transaction_state = 1 OR t.transaction_state = 2 OR t.transaction_state = 3 OR t.transaction_state = 4 OR t.transaction_state = 5) AND t.account_id = a.id GROUP BY account_name, transaction_date ORDER BY account_name", nativeQuery = true)
    List<Object[]> findTransactionsForYesterdayGroupByAccount();

//    @Query(value = "SELECT a.`name` AS account_name, DATE(t.created) AS transaction_date, COUNT(t.id) AS transaction_count FROM `transaction` t, account a WHERE DATE(t.created) BETWEEN DATE(?1) AND DATE(?2) AND (t.transaction_state = 0 OR t.transaction_state = 1 OR t.transaction_state = 2 OR t.transaction_state = 3 OR t.transaction_state = 4 OR t.transaction_state = 5) AND t.account_id = a.id GROUP BY account_name, transaction_date ORDER BY account_name", nativeQuery = true)
    @Query(value = "SELECT a.`name` AS account_name, COUNT(t.id) AS transaction_count FROM `transaction` t, account a WHERE DATE(t.created) BETWEEN DATE(?1) AND DATE(?2) AND (t.transaction_state = 0 OR t.transaction_state = 1 OR t.transaction_state = 2 OR t.transaction_state = 3 OR t.transaction_state = 4 OR t.transaction_state = 5) AND t.account_id = a.id GROUP BY account_name ORDER BY account_name", nativeQuery = true)
    List<Object[]> findTransactionsBetweenRangeGroupByAccount(Timestamp start, Timestamp end);

    @Query(value = "SELECT COUNT(t.id) AS transaction_count, DATE(t.created) AS transaction_date FROM `transaction` t WHERE DATE(t.created) >= DATE(NOW()) - INTERVAL 60 DAY AND (t.transaction_state = 0 OR t.transaction_state = 1 OR t.transaction_state = 2 OR t.transaction_state = 3 OR t.transaction_state = 4 OR t.transaction_state = 5) GROUP BY transaction_date ORDER BY transaction_date", nativeQuery = true)
    List<Object[]> findTransactionsForLast60Days();

    List<Transaction> findByOrderDetailTableAndShippingTrackingState(Transaction.OrderDetailTable orderDetailTable, ShippingTracking.ShippingTrackingState shippingTrackingState);
    List<Transaction> findByInsPriceTermCurrencyCode(String insPriceTermCurrencyCode);
    List<Transaction> findByAccountIdAndInsTermPrice(long accountId,String insTermPrice);


    Long countByNoteIsNull();
    List<Transaction> findFirst100ByNoteIsNull();
}
