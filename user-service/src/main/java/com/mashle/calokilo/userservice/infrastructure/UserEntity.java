package com.mashle.calokilo.userservice.infrastructure;

import com.mashle.calokilo.userservice.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "height")
    private int height;


    public UserEntity(User user) {
        this.firstName = user.firstName();
        this.email = user.email();
        this.password = user.password();
        this.birthDate = user.birthDate();
        this.height = user.height();
    }


    public User toCreatedUser() {
        return User.builder()
                .id(this.id)
                .firstName(this.firstName)
                .email(this.email)
                .password(password)
                .birthDate(this.birthDate)
                .height(this.height)
                .build();
    }
}
