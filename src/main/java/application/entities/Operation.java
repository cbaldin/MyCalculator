package application.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "OPERATION")
public class Operation {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TYPE")
    private OperationType type;

    @Column(name = "COST")
    private Double cost;

    @OneToMany(targetEntity=Record.class, mappedBy="operation")
    private List<Record> records = new ArrayList<>();

    public Operation(){}

    public Operation(OperationType operationType, Double cost) {
        this.type = operationType;
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OperationType getType() {
        return type;
    }

    public void setType(OperationType type) {
        this.type = type;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }
}
