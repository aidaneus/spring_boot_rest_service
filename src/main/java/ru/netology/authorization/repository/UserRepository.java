package ru.netology.authorization.repository;

import org.springframework.stereotype.Repository;
import ru.netology.authorization.model.Authorities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {

    private final Map<String, String> users = new HashMap<>();
    private final Map<String, List<Authorities>> authorities = new HashMap<>();

    public UserRepository() {
        // тестовые пользователи
        users.put("alice", "qwerty");
        users.put("bob", "12345");
        users.put("carol", "password");

        // права alice — все три
        authorities.put("alice", List.of(Authorities.READ, Authorities.WRITE, Authorities.DELETE));
        // права bob — только чтение и запись
        authorities.put("bob", List.of(Authorities.READ, Authorities.WRITE));
        // carol — без прав (пустая коллекция), чтобы проверить UnauthorizedUser
        authorities.put("carol", new ArrayList<>());
    }

    public List<Authorities> getUserAuthorities(String user, String password) {
        if (!users.containsKey(user)) {
            return new ArrayList<>();
        }
        if (!users.get(user).equals(password)) {
            return new ArrayList<>();
        }
        return authorities.getOrDefault(user, new ArrayList<>());
    }
}