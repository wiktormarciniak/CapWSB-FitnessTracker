package com.capgemini.wsb.fitnesstracker.user.api;

import java.util.List;
import java.util.Optional;

/**
 * Interfejs definiujący usługi dostępne dla obiektów typu User w systemie.
 * Zapewnia standardowe operacje CRUD oraz dodatkowe metody wyszukiwania użytkowników
 * na podstawie konkretnych kryteriów.
 *
 * @author Wiktor Marciniak
 * @version 1.0
 */
public interface UserService {

    /**
     * Zwraca listę wszystkich zarejestrowanych użytkowników w systemie.
     *
     * @return lista wszystkich użytkowników
     */
    List<User> findAllUsers();

    /**
     * Zwraca użytkownika o określonym identyfikatorze.
     *
     * @param id unikalny identyfikator użytkownika
     * @return Optional zawierający użytkownika, jeśli istnieje; w przeciwnym razie Optional.empty()
     */
    Optional<User> getUser(Long id);

    /**
     * Tworzy nowego użytkownika w systemie z danymi podanymi w obiekcie User.
     *
     * @param user obiekt użytkownika do utworzenia
     * @return stworzony obiekt użytkownika
     */
    User createUser(User user);

    /**
     * Usuwa użytkownika o określonym identyfikatorze z systemu.
     *
     * @param id identyfikator użytkownika do usunięcia
     */
    void deleteUser(Long id);

    /**
     * Aktualizuje dane istniejącego użytkownika na podstawie danych zawartych w obiekcie User.
     *
     * @param id identyfikator użytkownika do zaktualizowania
     * @param user nowe dane użytkownika
     * @return zaktualizowany obiekt użytkownika
     */
    User updateUser(Long id, User user);

    /**
     * Znajduje użytkowników, których adresy e-mail zawierają podany ciąg znaków, niezależnie od wielkości liter.
     *
     * @param email fragment adresu e-mail do wyszukiwania
     * @return lista użytkowników, których e-maile zawierają podany fragment
     */
    List<User> findByEmailContainingIgnoreCase(String email);

    /**
     * Znajduje użytkowników, którzy są starsi niż określona liczba lat.
     *
     * @param age liczba lat, które użytkownicy muszą przekroczyć
     * @return lista użytkowników starszych niż podana liczba lat
     */
    List<User> findUsersOlderThan(int age);
}
