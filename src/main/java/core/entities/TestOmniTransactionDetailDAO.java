package core.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TestOmniTransactionDetailDAO extends JpaRepository<TestOmniTransactionDetail, Long> {
}
