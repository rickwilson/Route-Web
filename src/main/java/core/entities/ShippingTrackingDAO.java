package core.entities;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@Transactional
public interface ShippingTrackingDAO extends CrudRepository<ShippingTracking, Long> {
    ShippingTracking findByAftershipTrackingId(String aftershipTrackingId);
    ShippingTracking findByTransactionId(long transactionId);
    ShippingTracking findByTransactionIdAndAftershipTrackingIdIsNotNull(long transactionId);
    List<ShippingTracking> findByAccountIdAndCreatedAfter(long accountId, Timestamp start);
    List<ShippingTracking> findByOrderIDIsNull();
}
