package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

/**
 * Repozytorium JPA dla obiektów typu {@link User}, oferujące standardowe operacje CRUD
 * oraz metody do wyszukiwania użytkowników na podstawie emaila i daty urodzenia.
 * @author Wiktor Marciniak
 * @version 1.0
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Znajduje wszystkich użytkowników, których adres e-mail zawiera podany ciąg znaków,
     * niezależnie od wielkości liter.
     *
     * @param email Fragment adresu e-mail do wyszukiwania, ignorujący wielkość liter.
     * @return Lista użytkowników, których e-mail zawiera podany fragment.
     */
    List<User> findByEmailContainingIgnoreCase(String email);

    /**
     * Znajduje wszystkich użytkowników, którzy są starsi niż podana data urodzenia.
     *
     * @param cutoffDate Data, przed którą użytkownicy musieli się urodzić, aby zostać zwróconymi.
     * @return Lista użytkowników urodzonych przed określoną datą.
     */
    List<User> findByBirthdateBefore(LocalDate cutoffDate);
}
