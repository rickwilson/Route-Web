package core.entities.subscription;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface PlaidLinkDAO extends CrudRepository<PlaidLink, Long> {
}

//public class PlaidLinkDAO{}
