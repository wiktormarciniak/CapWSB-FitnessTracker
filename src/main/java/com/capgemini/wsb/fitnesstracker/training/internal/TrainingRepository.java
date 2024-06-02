package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Interfejs repozytorium JPA dla encji Training, zapewniający dostęp do operacji bazodanowych.
 * Rozszerza JpaRepository, co zapewnia implementację standardowych metod CRUD oraz możliwość definiowania własnych zapytań.
 */
public interface TrainingRepository extends JpaRepository<Training, Long> {

    /**
     * Wyszukuje wszystkie treningi przypisane do określonego użytkownika na podstawie jego ID.
     *
     * @param userId Identyfikator użytkownika, dla którego szukamy treningów.
     * @return Lista treningów przypisanych do użytkownika.
     */
    List<Training> findByUserId(Long userId);

    /**
     * Znajduje wszystkie treningi, które zostały zakończone po określonej dacie.
     *
     * @param date Data w formacie String, po której powinny być wyszukane zakończone treningi.
     * @return Lista treningów, które zakończyły się po podanej dacie.
     */
    List<Training> findCompletedAfter(String date);

    /**
     * Wyszukuje treningi według określonego typu aktywności.
     *
     * @param activityType Typ aktywności, dla którego chcemy znaleźć treningi (np. Bieganie, Pływanie).
     * @return Lista treningów odpowiadających podanemu typowi aktywności.
     */
    List<Training> findByActivityType(String activityType);
}
