package core.entities;

import org.apache.commons.digester.annotations.rules.AttributeCallParam;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDAO extends CrudRepository<User, Long> {
    User findByUserName(String username);
    User findByEmail(String email);
    List<User> findByAccountId(long accountId);
}
