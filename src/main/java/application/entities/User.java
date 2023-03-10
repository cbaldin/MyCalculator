package application.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SYS_USER")
public class User {

    public static final Double INITIAL_BALANCE = 50.0;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password; //criptografar

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private UserStatus status;

    @OneToMany(targetEntity=Record.class, mappedBy="user")
    private List<Record> records = new ArrayList<>();

    @OneToMany(targetEntity=Token.class, mappedBy="user")
    private List<Token> tokens = new ArrayList<>();


    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public void addTokens(Token token) {
        getTokens().add(token);
    }

    public void addTokens(String stringToken) {  //TODO move to factory
        Token token = new Token();
        token.setToken(stringToken);
        token.setDate(LocalDate.now());
        token.setUser(this);
        getTokens().add(token);
    }
}
