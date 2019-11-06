package ImageHoster.service;

import ImageHoster.model.User;
import ImageHoster.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(User newUser) {
        userRepository.registerUser(newUser);
    }

    public User login(User user) {
        return userRepository.checkUser(user.getUsername(), user.getPassword());
    }
}
