package core.entities;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
public interface OmniTrackingDAO extends CrudRepository<OmniTracking, Long> {
    OmniTracking findByAftershipTrackingId(String aftershipTrackingId);
    List<OmniTracking> findBySlugIsNullOrTagIsNull();
    List<OmniTracking> findByActiveTrueAndSlugStartingWithAndLastUpdatedDateTimeBefore(String slugStart, LocalDateTime start);
//    OmniTracking findByTransactionId(long transactionId);

//    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM OmniTracking s WHERE s.omniTransactionId = :transId")
//    boolean existsByOmniTransactionId(@Param("transId") Long omniTransactionId);
}
