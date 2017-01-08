package core.entities;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ShippingTrackingDetailDAO extends CrudRepository<ShippingTrackingDetail, Long> {
    List<ShippingTrackingDetail> findByShippingTrackingId(long shippingTrackingId);
}
