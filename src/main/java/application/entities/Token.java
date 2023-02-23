package application.entities;

import jakarta.persistence.*;
import org.hibernate.Hibernate;

import java.time.LocalDate;

@Entity
@Table(name = "TOKEN")
public class Token {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "TOKEN")
    private String token;

    @Column(name = "DATE")
    private LocalDate date;

    public Token() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public User getUser() {
        return (User) Hibernate.unproxy( user );
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
