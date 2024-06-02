package com.capgemini.wsb.fitnesstracker.user.internal;

import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) reprezentujący dane użytkownika.
 * Rekord UserDto jest używany do przenoszenia danych użytkownika w aplikacji,
 * oferując wygodny sposób na kapsułkowanie i przekazywanie informacji o użytkowniku
 * pomiędzy różnymi warstwami aplikacji.
 *
 * @param id Unikalny identyfikator użytkownika.
 * @param firstName Imię użytkownika.
 * @param lastName Nazwisko użytkownika.
 * @param birthdate Data urodzenia użytkownika.
 * @param email Adres email użytkownika.
 *
 * @author Wiktor Marciniak
 * @version 1.0
 */
public record UserDto(Long id, String firstName, String lastName, LocalDate birthdate, String email) {}
