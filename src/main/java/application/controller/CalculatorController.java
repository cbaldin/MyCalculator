package application.controller;

import application.dto.RecordDTO;
import application.entities.Record;
import application.entities.Token;
import application.entities.User;
import application.exceptions.InvalidTokenException;
import application.exceptions.PasswordNotMatch;
import application.exceptions.UserNotFoundException;
import application.repository.RecordPaginationRepository;
import application.repository.RecordRepository;
import application.repository.TokenRepository;
import application.repository.UserRepository;
import application.services.CalculatorService;
import application.services.auth.AuthService;
import application.services.operations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins = {"*"})
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RandomStringGeneratorService randomStringGeneratorService;

    @Autowired
    private AuthService authService;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private RecordPaginationRepository recordPaginationRepository;

    @GetMapping("/sum")
    public ResponseEntity sum(@RequestParam(value = "x") String x,
                      @RequestParam(value = "y") String y,
                      @RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(calculatorService.sum(x, y, token).toString(), HttpStatus.OK);
    }

    @GetMapping("/sub")
    public ResponseEntity sub(@RequestParam(value = "x", defaultValue = "0") String x,
                      @RequestParam(value = "y", defaultValue = "0") String y,
                      @RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(calculatorService.sub(x, y, token).toString(), HttpStatus.OK);
    }

    @GetMapping("/mult")
    public ResponseEntity mult(@RequestParam(value = "x", defaultValue = "0") String x,
                       @RequestParam(value = "y", defaultValue = "0") String y,
                       @RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(calculatorService.mult(x, y, token).toString(), HttpStatus.OK);
    }

    @GetMapping("/div")
    public ResponseEntity div(@RequestParam(value = "x", defaultValue = "0") String x,
                      @RequestParam(value = "y", defaultValue = "0") String y,
                      @RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(calculatorService.div(x, y, token).toString(), HttpStatus.OK);
    }

    @GetMapping("/square-root")
    public ResponseEntity squareRoot(@RequestParam(value = "x") String x, @RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(calculatorService.squareRoot(x, token).toString(), HttpStatus.OK);
    }

    @GetMapping("/random-string")
    public ResponseEntity randomString(@RequestHeader("Authorization") String token) throws IOException, InterruptedException {
        return new ResponseEntity<>(randomStringGeneratorService.getRandomString(token), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserLogin userLogin)  { //TODO improve return
        return new ResponseEntity<>(authService.registryToken(userLogin), HttpStatus.OK);
    }

    @GetMapping("/login/verify")
    public ResponseEntity verify(@RequestHeader("Authorization") String token)  {
        token = normalizeAuthorizationToken(token);
        final Token tokenEntity = tokenRepository.findOneByToken(token).orElseThrow(InvalidTokenException::new);
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }

    @GetMapping("/records")
    public ResponseEntity getRecords(@RequestHeader("Authorization") String token,
                           @RequestParam(value = "pag", required = false) Integer pag,
                           @RequestParam(value = "paginationSize", required = false) Integer paginationSize,
                           @RequestParam(value = "orderByProperty", required = false, defaultValue = "id") String orderProperty,
                           @RequestParam(value = "orderBy", required = false, defaultValue = "ASC") Sort.Direction orderBy) {
        final Page<RecordDTO> recordDTOList = recordPaginationRepository.getRecordDTOSWighPagination(token, pag, paginationSize, orderProperty, orderBy);
        return new ResponseEntity<>(recordDTOList, HttpStatus.OK);
    }

    @DeleteMapping("/records")
    @Transactional
    public ResponseEntity deleteRecord(@RequestHeader("Authorization") String token,
                    @RequestParam(value = "id", required = false) Long id) {
        token = normalizeAuthorizationToken(token);
        final Token tokenEntity = tokenRepository.findOneByToken(token).orElseThrow(UserNotFoundException::new);
        final Optional<Record> record = tokenEntity.getUser().getRecords().stream().filter(deletedRecord -> deletedRecord.getId().equals(id)).findFirst();
        record.get().setDeleted(LocalDate.now());

        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    private String normalizeAuthorizationToken(String token) { //TODO create filter to check and normalize token
        if (token.length() < 8 ) {
            throw new InvalidTokenException();
        }
        token = token.substring(7);
        return token;
    }
}
