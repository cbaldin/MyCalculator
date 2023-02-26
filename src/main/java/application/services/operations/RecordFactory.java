package application.services.operations;

import application.entities.Operation;
import application.entities.Record;
import application.entities.User;
import application.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RecordFactory {

    @Autowired
    private RecordRepository recordRepository;

    public Record createRecord(User user, double userBalance, Operation operation, String operationResponse) {
        var record = new Record();
        record.setOperation(operation);
        record.setAmount(operation.getCost());
        record.setDate(LocalDateTime.now());
        record.setUser(user);
        record.setOperationResponse(operationResponse);
        record.setUserBalance(userBalance - operation.getCost());
        Record savedRecord = recordRepository.save(record);
        return savedRecord;
    }

}
