package application.repository;

import application.entities.Operation;
import application.entities.OperationType;
import application.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {

    public Optional<Operation> findOneByType(OperationType type);

}
