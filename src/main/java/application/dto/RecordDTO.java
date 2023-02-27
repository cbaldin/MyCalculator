package application.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RecordDTO {

    private Long id;
    private String operation;
    private Double amount;
    private Double userBalance;
    private String operationResponse;
    private String date;

    public RecordDTO(Long id, String operation, Double amount, Double userBalance, String operationResponse, LocalDateTime date) {
        this.id = id;
        this.operation = operation;
        this.amount = amount;
        this.userBalance = userBalance;
        this.operationResponse = operationResponse;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMgi/dd/yyyy HH:mm:ss");
        this.date = date.format(formatter);
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

    public String getOperationResponse() {
        return operationResponse;
    }

    public String getDate() {
        return date;
    }
}
