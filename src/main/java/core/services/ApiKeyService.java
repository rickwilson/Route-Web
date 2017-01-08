package core.services;

import core.entities.ApiKey;
import core.entities.ApiKeyDAO;
import core.entities.enums.MarkupType;
import core.security.GenerateAPIKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.sql.Timestamp;

@Service
public class ApiKeyService {
    private final ApiKeyDAO apiKeyDAO;

    @Autowired
    public ApiKeyService(ApiKeyDAO apiKeyDAO) {
        Assert.notNull(apiKeyDAO, "ApiKeyDAO must not be null!");
        this.apiKeyDAO = apiKeyDAO;
    }

    public ApiKey createInitialApiKey(long accountId, String accountName, long userId, String firstLast, boolean isSystemAdmin) {
        ApiKey apiKeyEntity = new ApiKey();
        apiKeyEntity.setAccountId(accountId);
        String apiKey1 = GenerateAPIKey.generateSafeKey(apiKeyDAO,accountName,"LIVE_wk_");
        String apiKey2 = GenerateAPIKey.generateSafeKey(apiKeyDAO,accountName,"LIVE_ak_");
        String apiKey3 = GenerateAPIKey.generateSafeKey(apiKeyDAO,accountName,"LIVE_s_");
        String apiKey4 = GenerateAPIKey.generateSafeKey(apiKeyDAO,accountName,"test_wk_");
        String apiKey5 = GenerateAPIKey.generateSafeKey(apiKeyDAO,accountName,"test_ak_");
        String apiKey6 = GenerateAPIKey.generateSafeKey(apiKeyDAO,accountName,"test_s_");
        apiKeyEntity.setName("Initial Keys");
        apiKeyEntity.setWidgetKey("LIVE_wk_"+apiKey1);
        apiKeyEntity.setApiKey("LIVE_ak_"+apiKey2);
        apiKeyEntity.setSecret("LIVE_s_"+apiKey3);
        apiKeyEntity.setTestWidgetKey("test_wk_"+apiKey4);
        apiKeyEntity.setTestApiKey("test_ak_"+apiKey5);
        apiKeyEntity.setTestSecret("test_s_"+apiKey6);
        apiKeyEntity.setMarkupType(MarkupType.MACHINE_LEARNING);
        apiKeyEntity.setMarkupFixed(new Float(2.00));
        apiKeyEntity.setMarkupPercent(30);
        apiKeyEntity.setShippingNotifications(false);
        apiKeyEntity.setSystemAdmin(isSystemAdmin);
        apiKeyEntity.setActive(true);
        apiKeyEntity.setCreated(new Timestamp(System.currentTimeMillis()));
        apiKeyEntity.setAddedByUserId(userId);
        apiKeyEntity.setAddedByName(firstLast);
        apiKeyEntity.setDescription("Keys added automatically during registration process.");
        apiKeyDAO.save(apiKeyEntity);
        return apiKeyEntity;
    }
}
