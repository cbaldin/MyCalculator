package application.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SYS_USER")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password; //criptografar

    @Column(name = "STATUS")
    private String status; //active inactive

    @OneToMany(targetEntity=Record.class, mappedBy="user")
    private List<Record> persons = new ArrayList<>();

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Record> getPersons() {
        return persons;
    }

    public void setPersons(List<Record> persons) {
        this.persons = persons;
    }
}
