package service;

import model.User;
import repository.UserRepository;

public class UserService {
    private final UserRepository userRepository = new UserRepository();

    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "secret_password";

    public boolean registerUser(User user) {
        return userRepository.addUser(user);
    }

    public boolean loginUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        return user != null && user.getPassword().equals(password);
    }

    public Long userId(String username) {
        User user = userRepository.findByUsername(username);
        return user.getUserId();
    }

    public boolean isAdmin(String username, String password) {
        return ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password);
    }
}

