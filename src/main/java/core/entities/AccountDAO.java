package core.entities;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Transactional
public interface AccountDAO extends CrudRepository<Account, Long> {
    Account findByName(String name);
    Long countByCreatedBetween(Timestamp start, Timestamp end);
    Long countByCreatedAfter(Timestamp start);
}
