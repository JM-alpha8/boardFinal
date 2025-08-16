package com.example.board2.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name="users", uniqueConstraints=@UniqueConstraint(columnNames="email"))
@Getter @NoArgsConstructor @AllArgsConstructor @Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String email;
    @Column(nullable = false, length = 60)
    private String password; // BCrypt 해시
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false, length = 20)
    private String role; // "USER"

    private User(String email, String name) {
        this.email = email;
        this.name = name;
        this.password = ""; // OAuth 가입자는 비번 공란 가능
        this.role = "USER";
    }

    public static User create(String email, String name) {
        return new User(email, name);
    }

}
