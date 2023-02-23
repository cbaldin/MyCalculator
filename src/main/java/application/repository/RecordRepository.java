package application.repository;

import application.entities.Record;
import application.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {

    public Optional<Record> findFirstByUser(User user, Sort sort);

    public List<Record> findByUser(User user, Pageable pageable);
}
