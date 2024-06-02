package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Kontroler REST dla operacji na użytkownikach.
 * Zapewnia funkcjonalności API do zarządzania użytkownikami, w tym pobieranie, dodawanie, usuwanie oraz aktualizacja danych użytkowników.
 *
 * @author Wiktor Marciniak
 * @version 1.0
 */
@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final UserServiceImpl userService;
    private final UserMapper userMapper;

    /**
     * Konstruktor dla UserController, wstrzykujący zależności do serwisu użytkowników i mappera użytkowników.
     *
     * @param userService Serwis obsługujący logikę biznesową użytkowników.
     * @param userMapper Mapper do konwersji między obiektami User a UserDto.
     */
    public UserController(UserServiceImpl userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    /**
     * Pobiera listę wszystkich użytkowników.
     *
     * @return Lista DTO użytkowników.
     */
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Tworzy nowego użytkownika na podstawie danych przesłanych w formacie DTO.
     *
     * @param userDto DTO z danymi nowego użytkownika.
     * @return Stworzony obiekt użytkownika.
     */
    @PostMapping
    public User addUser(@RequestBody UserDto userDto) {
        User newUser = userMapper.toEntity(userDto);
        return userService.createUser(newUser);
    }

    /**
     * Usuwa użytkownika na podstawie podanego identyfikatora.
     *
     * @param id Identyfikator użytkownika do usunięcia.
     * @return ResponseEntity reprezentujący wynik operacji (no content).
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Aktualizuje dane użytkownika na podstawie przesłanych informacji w formacie DTO.
     *
     * @param id Identyfikator użytkownika do zaktualizowania.
     * @param userDto DTO z nowymi danymi użytkownika.
     * @return ResponseEntity z DTO zaktualizowanego użytkownika.
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        User updatedUser = userMapper.toEntity(userDto);
        updatedUser = userService.updateUser(id, updatedUser);
        return ResponseEntity.ok(userMapper.toDto(updatedUser));
    }

    /**
     * Wyszukuje użytkowników na podstawie podanego fragmentu email.
     *
     * @param email Fragment email do wyszukiwania.
     * @return ResponseEntity z listą DTO użytkowników, którzy spełniają kryterium wyszukiwania.
     */
    @GetMapping("/searchByEmail")
    public ResponseEntity<List<UserDto>> searchByEmail(@RequestParam String email) {
        List<User> users = userService.findByEmailContainingIgnoreCase(email);
        return ResponseEntity.ok(users.stream().map(userMapper::toDto).collect(Collectors.toList()));
    }

    /**
     * Wyszukuje użytkowników starszych niż podany wiek.
     *
     * @param age Wiek, powyżej którego użytkownicy mają być wyszukiwani.
     * @return ResponseEntity z listą DTO użytkowników, którzy spełniają kryterium wieku.
     */
    @GetMapping("/searchByAge")
    public ResponseEntity<List<UserDto>> searchByAge(@RequestParam int age) {
        List<User> users = userService.findUsersOlderThan(age);
        return ResponseEntity.ok(users.stream().map(userMapper::toDto).collect(Collectors.toList()));
    }

    /**
     * Pobiera użytkownika na podstawie identyfikatora.
     *
     * @param id Identyfikator użytkownika do pobrania.
     * @return ResponseEntity z DTO użytkownika.
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        User user = userService.getUser(id).orElseThrow(() -> new UserNotFoundException(id));
        return ResponseEntity.ok(userMapper.toDto(user));
    }
}
