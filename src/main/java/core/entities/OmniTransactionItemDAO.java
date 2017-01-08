package core.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface OmniTransactionItemDAO extends JpaRepository<OmniTransactionItem, Long> {
}
