package application.services.operations;

import application.entities.Operation;
import application.entities.OperationType;
import application.entities.Record;
import application.entities.User;
import application.exceptions.InsufficientFundsException;
import application.repository.OperationRepository;
import application.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public abstract class Chargeable {

    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private RecordRepository recordRepository;

    public Record charge(User user, OperationType operationType) {
        var userBalance = 0.0;

        if (user.getRecords().isEmpty()) {
            userBalance = User.INITIAL_BALANCE;
        } else {
            userBalance = user.getRecords().stream().map(Record::getUserBalance).min(Double::compare).get();
        }

        var operation = operationRepository.findOneByType(operationType).get();
        if((userBalance - operation.getCost()) < 0.0) {
            throw new InsufficientFundsException();
        }

        var record = new Record();
        record.setOperation(operation);
        record.setAmount(operation.getCost());
        record.setOperationResponse(0.0);
        record.setDate(LocalDateTime.now());
        record.setUser(user);
        record.setUserBalance(userBalance - operation.getCost());
        recordRepository.save(record);
        return record;
    }

}
