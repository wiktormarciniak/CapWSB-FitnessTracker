package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserService;
import com.capgemini.wsb.fitnesstracker.user.internal.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


/**
 * Implementacja serwisu użytkowników, zapewniająca funkcje zarządzania użytkownikami w aplikacji.
 * Obsługuje podstawowe operacje CRUD oraz specyficzne zapytania dotyczące użytkowników.
 * @author Wiktor Marciniak
 * @version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Znajduje wszystkich użytkowników zarejestrowanych w systemie.
     *
     * @return lista wszystkich użytkowników
     */

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Znajduje użytkownika na podstawie podanego identyfikatora.
     *
     * @param id Identyfikator użytkownika
     * @return Opcjonalny obiekt użytkownika, jeśli istnieje
     */

    @Override
    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Tworzy nowego użytkownika w bazie danych.
     *
     * @param user Obiekt użytkownika do zapisania
     * @return Zapisany obiekt użytkownika
     */

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Usuwa użytkownika na podstawie podanego identyfikatora.
     *
     * @param id Identyfikator użytkownika do usunięcia
     */

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    /**
     * Aktualizuje informacje o użytkowniku na podstawie podanego identyfikatora.
     *
     * @param id Identyfikator użytkownika, którego dane mają być zaktualizowane
     * @param user Obiekt użytkownika z nowymi danymi
     * @return Zaktualizowany obiekt użytkownika
     */

    @Override
    public User updateUser(Long id, User user) {
        return userRepository.save(user);
    }

    /**
     * Wyszukuje użytkowników, których adres email zawiera podany ciąg znaków, niezależnie od wielkości liter.
     *
     * @param email Fragment emaila do wyszukania
     * @return Lista użytkowników spełniających kryterium wyszukiwania
     */

    @Override
    public List<User> findByEmailContainingIgnoreCase(String email) {
        return userRepository.findByEmailContainingIgnoreCase(email);
    }

    /**
     * Wyszukuje użytkowników, którzy są starsi niż podana liczba lat.
     *
     * @param age Wiek, powyżej którego użytkownicy mają być wyszukani
     * @return Lista użytkowników spełniających kryterium wieku
     */

    @Override
    public List<User> findUsersOlderThan(int age) {
        LocalDate cutoffDate = LocalDate.now().minusYears(age);
        return userRepository.findByBirthdateBefore(cutoffDate);
    }
}
