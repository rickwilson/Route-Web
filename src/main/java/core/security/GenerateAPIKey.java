package core.security;

import core.entities.ApiKeyDAO;
import core.entities.ApiKey;
import org.springframework.util.DigestUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GenerateAPIKey {

    public static String generateGenericKey() {
        return generateKey(" "+System.currentTimeMillis(),"generateGenericKey"+System.currentTimeMillis());
    }

    public static String generateSafeKey(ApiKeyDAO apiKeyDAO,String accountName, String seed) {
        String key = "";
        boolean foundNewKey = false;
        while(!foundNewKey) {
            key = generateKey(accountName, seed);
            if(!keyExists(key,apiKeyDAO)) {
                foundNewKey = true;
            }
        }
        return key;
    }

    public static String generateKey(String accountName, String seed){
        accountName +=System.currentTimeMillis();
        accountName +=seed;
        return DigestUtils.md5DigestAsHex(accountName.getBytes());
    }

    private static boolean keyExists(String key,ApiKeyDAO apiKeyDAO) {
        boolean doesKeyExist;
        ApiKey apiKey = apiKeyDAO.findByWidgetKeyOrApiKeyOrSecretOrTestWidgetKeyOrTestApiKeyOrTestSecret("LIVE_wk_"+key,"LIVE_ak_"+key,"LIVE_s_"+key,"test_wk_"+key,"test_ak_"+key,"test_s_"+key);
        if(apiKey == null || apiKey.getAccountId() < 1) {
            doesKeyExist = false;
        } else {
            System.out.println("------------- APIKey Exists: "+apiKey+"    AccountID: "+apiKey.getAccountId());
            doesKeyExist = true;
        }
        return doesKeyExist;
    }
}
