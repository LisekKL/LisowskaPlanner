package pl.umcs.lisowska.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.umcs.lisowska.common.User;
import pl.umcs.lisowska.repository.UserRepository;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    private final UserRepository userRepository;


    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public List<User> findAll() {
        Iterable<User> usersIterable = userRepository.findAll();
        return (List<User>) usersIterable;
    }

    public User find(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}

