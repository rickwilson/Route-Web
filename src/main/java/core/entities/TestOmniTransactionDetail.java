package core.entities;

import core.entities.enums.TransactionState;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class TestOmniTransactionDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(nullable = false)
    private long accountId;

    @NotNull
    @Column(nullable = false)
    private long testOmniTransactionId;

    @NotNull
    @Column(nullable = false)
    private String shortNote;

    @NotNull
    @Column(nullable = false)
    private TransactionState transactionState;

    private LocalDateTime createdByDateTime;

    public TestOmniTransactionDetail() {
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

    public long getTestOmniTransactionId() {
        return testOmniTransactionId;
    }

    public void setTestOmniTransactionId(long testOmniTransactionId) {
        this.testOmniTransactionId = testOmniTransactionId;
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

    public LocalDateTime getCreatedByDateTime() {
        return createdByDateTime;
    }

    public void setCreatedByDateTime(LocalDateTime createdByDateTime) {
        this.createdByDateTime = createdByDateTime;
    }
}
