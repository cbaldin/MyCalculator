package application.repository;

import application.dto.RecordDTO;
import application.entities.Record;
import application.entities.Token;
import application.exceptions.InvalidTokenException;
import application.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class RecordPaginationRepository {

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private RecordRepository recordRepository;

    public List<RecordDTO> getRecordDTOSWighPagination(String token, Integer pag, Integer paginationSize, String orderProperty, String orderBy) {
        token = normalizeAuthorizationToken(token);
        final Token tokenEntity = tokenRepository.findOneByToken(token).orElseThrow(UserNotFoundException::new);
        List<Record> userRecords = null;
        if (Optional.ofNullable(pag).isPresent() && Optional.ofNullable(paginationSize).isPresent()) { //TODO move to another class/repo
            PageRequest pageRequest = PageRequest.of(pag, paginationSize);
            if (orderBy.equals("asc")) {
                pageRequest = pageRequest.withSort(Sort.by(orderProperty).ascending());
            } else {
                pageRequest = pageRequest.withSort(Sort.by(orderProperty).descending());
            }

            userRecords = recordRepository.findByUser(tokenEntity.getUser(), pageRequest);
        } else {
            userRecords = recordRepository.findByUser(tokenEntity.getUser(), null);
        }

        List<RecordDTO> recordDTOList = userRecords.stream().filter(record -> record.getDeleted().isEmpty()).map(record ->
                new RecordDTO(record.getId(), record.getOperation().getType().name(), record.getAmount(), record.getUserBalance(), record.getOperationResponse(), record.getDate())
        ).collect(Collectors.toList());
        return recordDTOList;
    }

    private String normalizeAuthorizationToken(String token) { //TODO create filter to check and normalize token
        if (token.length() < 8 ) {
            throw new InvalidTokenException();
        }
        token = token.substring(7);
        return token;
    }

}
