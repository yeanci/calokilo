package com.mashle.calokilo.userservice.domain;

import com.mashle.calokilo.userservice.domain.User;
import com.mashle.calokilo.userservice.domain.shared.NotValidUserException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserTest {

    @Test
    void createUser_WhenValidData_ThenCreate() {
        assertDoesNotThrow(() -> {
            User.builder()
                    .id(1L)
                    .firstName("John")
                    .email("john@example.com")
                    .password("s@feP@ssword")
                    .birthDate(LocalDate.of(1995, 1, 30))
                    .height(180)
                    .build();
        });
    }

    @Test
    void createUser_WhenFirstNameNull_ThenThrowsException() {
        assertThrows(NotValidUserException.class, () -> {
            User.builder()
                    .id(1L)
                    .firstName(null)
                    .email("john@example.com")
                    .password("s@feP@ssword")
                    .birthDate(LocalDate.of(1995, 1, 30))
                    .height(180)
                    .build();
        });
    }

    @Test
    void createUser_WhenFirstNameEmpty_ThenThrowsException() {
        assertThrows(NotValidUserException.class, () -> {
            User.builder()
                    .id(1L)
                    .firstName("") // <- Empty first name
                    .email("john@example.com")
                    .password("s@feP@ssword")
                    .birthDate(LocalDate.of(1995, 1, 30))
                    .height(180)
                    .build();
        });
    }

    @Test
    void createUser_WhenFirstNameTooLong_ThenThrowsException() {
        assertThrows(NotValidUserException.class, () -> {
            User.builder()
                    .id(1L)
                    .firstName("John James Jack Davis Albert Harry")  // <- Too long first name
                    .email("john@example.com")
                    .password("s@feP@ssword")
                    .birthDate(LocalDate.of(1995, 1, 30))
                    .height(180)
                    .build();
        });
    }

    @Test
    void createUser_WhenEmailEmpty_ThenThrowsException() {
        assertThrows(NotValidUserException.class, () -> {
            User.builder()
                    .id(1L)
                    .firstName("John")
                    .email("") // <- Empty email
                    .password("s@feP@ssword")
                    .birthDate(LocalDate.of(1995, 1, 30))
                    .height(180)
                    .build();
        });
    }

    @Test
    void createUser_WhenEmailNull_ThenThrowsException() {
        assertThrows(NotValidUserException.class, () -> {
            User.builder()
                    .id(1L)
                    .firstName("John")
                    .email(null) // <- Null email
                    .password("s@feP@ssword")
                    .birthDate(LocalDate.of(1995, 1, 30))
                    .height(180)
                    .build();
        });
    }

    @Test
    void createUser_WhenEmailNotValid_ThenThrowsException() {
        assertThrows(NotValidUserException.class, () -> {
            User.builder()
                    .id(1L)
                    .firstName("John")
                    .email("john@example") // <- Not valid email
                    .password("s@feP@ssword")
                    .birthDate(LocalDate.of(1995, 1, 30))
                    .height(180)
                    .build();
        });
    }

    @Test
    void createUser_WhenPasswordEmpty_ThenThrowsException() {
        assertThrows(NotValidUserException.class, () -> {
            User.builder()
                    .id(1L)
                    .firstName("John")
                    .email("john@example.com")
                    .password("") // <- Empty password
                    .birthDate(LocalDate.of(1990, 1, 1))
                    .height(180)
                    .build();
        });
    }

    @Test
    void createUser_WhenPasswordNull_ThenThrowsException() {
        assertThrows(NotValidUserException.class, () -> {
            User.builder()
                    .id(1L)
                    .firstName("John")
                    .email("john@example.com")
                    .password(null) // <- Null password
                    .birthDate(LocalDate.of(1990, 1, 1))
                    .height(180)
                    .build();
        });
    }

    @Test
    void createUser_WhenPasswordTooShort_ThenThrowsException() {
        assertThrows(NotValidUserException.class, () -> {
            User.builder()
                    .id(1L)
                    .firstName("John")
                    .email("john@example.com")
                    .password("shortPwd") // <- Too short password
                    .birthDate(LocalDate.of(1990, 1, 1))
                    .height(180)
                    .build();
        });
    }

    @Test
    void createUser_WhenBirthDateNull_ThenThrowsException() {
        assertThrows(NotValidUserException.class, () -> {
            User.builder()
                    .id(1L)
                    .firstName("John")
                    .email("john@example.com")
                    .password("shortPwd")
                    .birthDate(null) // <- Null birth date
                    .height(180)
                    .build();
        });
    }

    @Test
    void createUser_WhenBirthDateNotInThePast_ThenThrowsException() {
        assertThrows(NotValidUserException.class, () -> {
            User.builder()
                    .id(1L)
                    .firstName("John")
                    .email("john@example.com")
                    .password("shortPwd")
                    .birthDate(LocalDate.of(2060, 1, 1)) // <- Birth date not in the past
                    .height(180)
                    .build();
        });
    }

    @Test
    void createUser_WhenHeightNegative_ThenThrowsException() {
        assertThrows(NotValidUserException.class, () -> {
            User.builder()
                    .id(1L)
                    .firstName("John")
                    .email("john@example.com")
                    .password("shortPwd")
                    .birthDate(LocalDate.of(1990, 1, 1))
                    .height(-100) // <- Negative height
                    .build();
        });
    }

    @Test
    void createUser_WhenHeightTooHigh_ThenThrowsException() {
        assertThrows(NotValidUserException.class, () -> {
            User.builder()
                    .id(1L)
                    .firstName("John")
                    .email("john@example.com")
                    .password("shortPwd")
                    .birthDate(LocalDate.of(1990, 1, 1))
                    .height(305) // <- Too high height
                    .build();
        });
    }
}
