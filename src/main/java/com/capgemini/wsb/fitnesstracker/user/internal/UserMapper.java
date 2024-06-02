package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * Komponent odpowiedzialny za mapowanie pomiędzy obiektami domenowymi a obiektami transferu danych (DTO).
 * Umożliwia łatwe przekształcanie pomiędzy encjami {@link User} a ich reprezentacją DTO {@link UserDto}.
 * @author Wiktor Marciniak
 * @version 1.0
 */
@Component
class UserMapper {

    /**
     * Konwertuje obiekt encji User na obiekt DTO UserDto.
     *
     * @param user Obiekt encji User do konwersji.
     * @return Obiekt DTO UserDto zawierający dane z obiektu User.
     */
    UserDto toDto(User user) {
        return new UserDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getEmail());
    }

    /**
     * Konwertuje obiekt DTO UserDto na obiekt encji User.
     *
     * @param userDto Obiekt DTO UserDto do konwersji.
     * @return Obiekt encji User z danymi z obiektu DTO.
     */
    User toEntity(UserDto userDto) {
        User user = new User(
                userDto.firstName(),
                userDto.lastName(),
                userDto.birthdate(),
                userDto.email());
        setId(user, userDto.id());
        return user;
    }

    private void setId(User user, Long id) {
        if (id != null) {
            try {
                Field idField = User.class.getDeclaredField("id");
                idField.setAccessible(true);
                idField.set(user, id);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
