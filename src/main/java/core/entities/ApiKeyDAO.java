package core.entities;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ApiKeyDAO extends CrudRepository<ApiKey, Long> {

    ApiKey findByApiKey(String apiKey);
    ApiKey findByApiKeyAndActive(String apiKey, boolean active);
    List<ApiKey> findByAccountIdAndActive(long accountId, boolean active);
    List<ApiKey> findByAccountId(long accountId);
    ApiKey findByWidgetKeyOrApiKeyOrSecretOrTestWidgetKeyOrTestApiKeyOrTestSecret(String widgetKey, String apiKey, String secret, String testWidgetKey, String testApiKey, String testSecret);
}
