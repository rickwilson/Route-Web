package core.outbound;

import core.services.EnvVariablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import sun.misc.BASE64Encoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.SignatureException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AlexaQuery {

    private final EnvVariablesService envVariablesService;
    private final String ACTION_NAME = "UrlInfo";
    private final String RESPONSE_GROUP_NAME = "Rank,LinksInCount";
    private final String SERVICE_HOST = "awis.amazonaws.com";
    private final String AWS_BASE_URL = "http://" + SERVICE_HOST + "/?";
    private final String HASH_ALGORITHM = "HmacSHA256";

    private final String DATEFORMAT_AWS = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    private String alexaAccessKey;
    private String alexaSecret;
    private String alexaSite;

    @Autowired
    public AlexaQuery(EnvVariablesService envVariablesService) {
        Assert.notNull(envVariablesService, "EnvVariablesService must not be null!");
        this.envVariablesService = envVariablesService;

        this.alexaAccessKey = envVariablesService.getAlexaAccessKey();
        this.alexaSecret = envVariablesService.getAlexaSecret();
        this.alexaSite = envVariablesService.getAlexaSite();
    }

    /**
     * Generates a timestamp for use with AWS request signing
     *
     * @param date current date
     * @return timestamp
     */
    protected String getTimestampFromLocalTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(DATEFORMAT_AWS);
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        return format.format(date);
    }

    /**
     * Computes RFC 2104-compliant HMAC signature.
     *
     * @param data The data to be signed.
     * @return The base64-encoded RFC 2104-compliant HMAC signature.
     * @throws SignatureException
     *          when signature generation fails
     */
    protected String generateSignature(String data)
            throws SignatureException {
        String result;
        try {
            // get a hash key from the raw key bytes
            SecretKeySpec signingKey = new SecretKeySpec(
                    alexaAccessKey.getBytes(), HASH_ALGORITHM);

            // get a hasher instance and initialize with the signing key
            Mac mac = Mac.getInstance(HASH_ALGORITHM);
            mac.init(signingKey);

            // compute the hmac on input data bytes
            byte[] rawHmac = mac.doFinal(data.getBytes());

            // base64-encode the hmac
            // result = Encoding.EncodeBase64(rawHmac);
            result = new BASE64Encoder().encode(rawHmac);

        } catch (Exception e) {
            throw new SignatureException("Failed to generate HMAC : "
                    + e.getMessage());
        }
        return result;
    }

    /**
     * Makes a request to the specified Url and return the results as a String
     *
     * @param requestUrl url to make request to
     * @return the XML document as a String
     * @throws IOException
     */
    public String makeRequest(String requestUrl) throws IOException {
        URL url = new URL(requestUrl);
        URLConnection conn = url.openConnection();
        InputStream in = conn.getInputStream();

        // Read the response
        StringBuffer sb = new StringBuffer();
        int c;
        int lastChar = 0;
        while ((c = in.read()) != -1) {
            if (c == '<' && (lastChar == '>'))
                sb.append('\n');
            sb.append((char) c);
            lastChar = c;
        }
        in.close();
        return sb.toString();
    }


    /**
     * Builds the query string
     */
    protected String buildQuery()
            throws UnsupportedEncodingException {
        String timestamp = getTimestampFromLocalTime(Calendar.getInstance().getTime());

        Map<String, String> queryParams = new TreeMap<String, String>();
        queryParams.put("Action", ACTION_NAME);
        queryParams.put("ResponseGroup", RESPONSE_GROUP_NAME);
        queryParams.put("AWSAccessKeyId", alexaAccessKey);
        queryParams.put("Timestamp", timestamp);
        queryParams.put("Url", alexaSite);
        queryParams.put("SignatureVersion", "2");
        queryParams.put("SignatureMethod", HASH_ALGORITHM);

        String query = "";
        boolean first = true;
        for (String name : queryParams.keySet()) {
            if (first)
                first = false;
            else
                query += "&";

            query += name + "=" + URLEncoder.encode(queryParams.get(name), "UTF-8");
        }

        return query;
    }
}
