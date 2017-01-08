package core.entities;

import core.entities.enums.SourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TestOmniTransactionDAO extends JpaRepository<TestOmniTransaction, Long> {
    TestOmniTransaction findByTestSourceEntityAndTestSourceEntityIdAndTestApiKey(SourceEntity testSourceEntity, long testOpenApiOrderId, String testApiKey);
}
