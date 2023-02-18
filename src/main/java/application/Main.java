package application;

import jakarta.persistence.EntityManager;
import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class Main {

    public static void main(String[] args) {
        System.out.println("Initializing");
        SpringApplication.run(Main.class, args);
        System.out.println("Initialization finished");
    }

}

