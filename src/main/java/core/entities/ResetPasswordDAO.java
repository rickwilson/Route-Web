package core.entities;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Transactional
public interface ResetPasswordDAO extends CrudRepository<ResetPassword, Long> {
    ResetPassword findByResetKeyAndResetTokenAndExpiresAfterAndUsedFalse(String resetKey,long resetToken,Timestamp expires);
}
