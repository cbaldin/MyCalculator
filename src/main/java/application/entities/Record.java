package application.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Entity
@Table(name = "RECORD")
public class Record {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OPERATION_ID")
    private Operation operation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "AMOUNT")
    private Double amount;

    @Column(name = "USER_BALANCE")
    private Double userBalance;

    @Column(name = "OPERATION_RESPONSE")
    private Double operationResponse;

    @Column(name = "DATE")
    private LocalDateTime date;

    @Column(name = "DELETED_DATE")
    private LocalDate deleted;

    public Record() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getUserBalance() {
        return userBalance;
    }

    public void setUserBalance(Double userBalance) {
        this.userBalance = userBalance;
    }

    public Double getOperationResponse() {
        return operationResponse;
    }

    public void setOperationResponse(Double operationResponse) {
        this.operationResponse = operationResponse;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Optional<LocalDate> getDeleted() {
        return Optional.ofNullable(deleted);
    }

    public void setDeleted(LocalDate deleted) {
        this.deleted = deleted;
    }
}
