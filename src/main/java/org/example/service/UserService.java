package org.example.service;
import org.example.entity.User;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final List<User> users = new ArrayList<>();
    private int nextId = 3;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        users.add(new User(1, "Andrew", "Bobne", 15));
        users.add(new User(2, "Anna", "Haybe", 20));

    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void addUser(User user) {
        user.setId(nextId++);
        userRepository.save(user);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }


}

