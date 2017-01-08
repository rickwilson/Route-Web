package core.entities.subscription;

import core.entities.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface BillingDAO extends CrudRepository<Billing, Long> {
    List<Billing> findByAccountIdAndActiveAndStripeCustomerIdNotNull(long accountId, boolean active);
    List<Billing> findByAccountIdAndStripeCustomerIdNotNullOrderByActiveAscCreatedAscNameAsc(long accountId);
}
