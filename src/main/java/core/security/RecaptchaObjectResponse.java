package core.security;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.sql.Timestamp;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RecaptchaObjectResponse {
    private boolean success;
    private Timestamp challenge_ts;
    private String hostname;

    public RecaptchaObjectResponse() {
    }

    public RecaptchaObjectResponse(boolean success, Timestamp challenge_ts, String hostname) {
        this.success = success;
        this.challenge_ts = challenge_ts;
        this.hostname = hostname;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Timestamp getChallenge_ts() {
        return challenge_ts;
    }

    public void setChallenge_ts(Timestamp challenge_ts) {
        this.challenge_ts = challenge_ts;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    @Override
    public String toString() {
        return "RecaptchaObjectResponse{" +
                "success=" + success +
                ", challenge_ts=" + challenge_ts +
                ", hostname='" + hostname + '\'' +
                '}';
    }
}
