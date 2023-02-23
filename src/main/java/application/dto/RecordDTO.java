package application.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class RecordDTO {

    private Long id;
    private String operation;
    private Double amount;
    private Double userBalance;
    private Double operationResponse;
    private LocalDateTime date;

    public RecordDTO(Long id, String operation, Double amount, Double userBalance, Double operationResponse, LocalDateTime date) {
        this.id = id;
        this.operation = operation;
        this.amount = amount;
        this.userBalance = userBalance;
        this.operationResponse = operationResponse;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public String getOperation() {
        return operation;
    }

    public Double getAmount() {
        return amount;
    }

    public Double getUserBalance() {
        return userBalance;
    }

    public Double getOperationResponse() {
        return operationResponse;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
