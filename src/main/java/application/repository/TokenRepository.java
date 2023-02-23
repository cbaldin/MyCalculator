package application.repository;

import application.entities.Operation;
import application.entities.OperationType;
import application.entities.Token;
import application.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    public Optional<Token> findOneByToken(String token);

}
