package org.example.fancy_project.classes.builder;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "[user]")
public class User {
    // Getters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    private String email;
    private String username;
    private String password;

    // Private constructor to prevent direct instantiation
    private User() {
    }

    // Builder method to initiate building a User object
    public static UserBuilder builder() {
        return new UserBuilder();
    }

    // Builder class
    public static class UserBuilder {
        private String email;
        private String username;
        private String password;

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        // Build method to create a User object
        public User build() {
            User user = new User();
            user.email = this.email;
            user.username = this.username;
            user.password = this.password;
            return user;
        }
    }
}
