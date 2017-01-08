package core.entities;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface TransactionDetailDAO extends CrudRepository<TransactionDetail, Long> {
    List<TransactionDetail> findByTransactionId(long transactionId);
}
