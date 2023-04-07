package com.example.week7.model;

import lombok.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
//import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;


import java.time.LocalDateTime;

@Data
@Builder
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name = "firstName")
    private String firstName;
    @Column (name = "lastName")
    private String lastName;
    @Column (name = "username")
    private String username;
    @Column (name = "email")
    private String email;
    @Column (name = "password")
    private String password;
    @CreationTimestamp
    private LocalDateTime createdOn;
//    @UpdateTimestamp
//    private LocalDateTime updatedOn;

//    public User(User user) {
//        this.id = user.getId();
//        this.firstName = user.getFirstName();
//        this.lastName = user.getLastName();
//        this.username = user.getUsername();
//        this.email = user.getEmail();
//        this.password = user.getPassword();
//        this.createdOn = user.getCreatedOn();
////        this.updatedOn = user.getUpdatedOn()
//
//    }
}
