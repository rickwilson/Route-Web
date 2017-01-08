package core.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
public class ResetPassword {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(nullable = false)
    private String resetKey;

    @NotNull
    @Column(nullable = false)
    private long resetToken;

    @NotNull
    @Column(nullable = false)
    private String email;

    @NotNull
    @Column(nullable = false)
    private Timestamp created;

    @NotNull
    @Column(nullable = false)
    private Timestamp expires;
    
    private boolean used;

    public ResetPassword() {
    }

    public ResetPassword(String resetKey, long resetToken, String email, Timestamp created, Timestamp expires, boolean used) {
        this.resetKey = resetKey;
        this.resetToken = resetToken;
        this.email = email;
        this.created = created;
        this.expires = expires;
        this.used = used;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getResetKey() {
        return resetKey;
    }

    public void setResetKey(String resetKey) {
        this.resetKey = resetKey;
    }

    public long getResetToken() {
        return resetToken;
    }

    public void setResetToken(long resetToken) {
        this.resetToken = resetToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getExpires() {
        return expires;
    }

    public void setExpires(Timestamp expires) {
        this.expires = expires;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    @Override
    public String toString() {
        return "ResetPassword{" +
                "id=" + id +
                ", resetKey='" + resetKey + '\'' +
                ", resetToken=" + resetToken +
                ", email='" + email + '\'' +
                ", created=" + created +
                ", expires=" + expires +
                ", used=" + used +
                '}';
    }
}
