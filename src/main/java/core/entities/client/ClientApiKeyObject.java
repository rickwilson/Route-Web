package core.entities.client;

import java.sql.Timestamp;

public class ClientApiKeyObject {
    private String key;
    private String secret;
    private String revPercent;
    private String shipNotifications;
    private String status;
    private Timestamp created;
    private Timestamp deactivated;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getRevPercent() {
        return revPercent;
    }

    public void setRevPercent(String revPercent) {
        this.revPercent = revPercent;
    }

    public String getShipNotifications() {
        return shipNotifications;
    }

    public void setShipNotifications(String shipNotifications) {
        this.shipNotifications = shipNotifications;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getDeactivated() {
        return deactivated;
    }

    public void setDeactivated(Timestamp deactivated) {
        this.deactivated = deactivated;
    }
}
