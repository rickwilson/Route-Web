package core.entities;

import core.entities.enums.TransactionState;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
public class TransactionDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(nullable = false)
    private long accountId;

    @NotNull
    @Column(nullable = false)
    private long transactionId;

    @NotNull
    @Column(nullable = false)
    private String shortNote;

    @NotNull
    @Column(nullable = false)
    private TransactionState transactionState;

    private Timestamp created;

    public TransactionDetail() {
    }

    public TransactionDetail(long accountId, long transactionId, String shortNote, TransactionState transactionState, Timestamp created) {
        this.accountId = accountId;
        this.transactionId = transactionId;
        this.shortNote = shortNote;
        this.transactionState = transactionState;
        this.created = created;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public String getShortNote() {
        return shortNote;
    }

    public void setShortNote(String shortNote) {
        this.shortNote = shortNote;
    }

    public TransactionState getTransactionState() {
        return transactionState;
    }

    public void setTransactionState(TransactionState transactionState) {
        this.transactionState = transactionState;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }
}
