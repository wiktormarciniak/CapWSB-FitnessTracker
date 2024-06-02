package com.capgemini.wsb.fitnesstracker.user.api;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;


/**
 * Klasa encji reprezentująca użytkownika w bazie danych.
 * Zawiera podstawowe informacje o użytkowniku, takie jak imię, nazwisko, data urodzenia i adres e-mail.
 */
@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class User {

    /**
     * Unikalny identyfikator użytkownika, generowany automatycznie.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Long id;

    /**
     * Imię użytkownika. Pole nie może być puste.
     */
    @Column(name = "first_name", nullable = false)
    private String firstName;

    /**
     * Nazwisko użytkownika. Pole nie może być puste.
     */
    @Column(name = "last_name", nullable = false)
    private String lastName;

    /**
     * Data urodzenia użytkownika. Pole nie może być puste.
     */
    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;

    /**
     * Adres e-mail użytkownika, który musi być unikalny w systemie. Pole nie może być puste.
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * Konstruktor tworzący obiekt użytkownika z pełnym zestawem informacji.
     *
     * @param firstName Imię użytkownika.
     * @param lastName Nazwisko użytkownika.
     * @param birthdate Data urodzenia użytkownika.
     * @param email Adres e-mail użytkownika.
     */
    public User(
            final String firstName,
            final String lastName,
            final LocalDate birthdate,
            final String email) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.email = email;
    }

}

