package core.entities;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface KonnektiveOrderDetailsItemDAO extends CrudRepository<KonnektiveOrderDetailsItem, Long> {
    List<KonnektiveOrderDetailsItem> findByKonnektiveOrderDetailsId(long konnektiveOrderDetailsId);
}
