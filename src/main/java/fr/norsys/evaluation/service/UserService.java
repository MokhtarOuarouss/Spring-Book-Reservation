package fr.norsys.evaluation.service;

import fr.norsys.evaluation.entity.User;
import fr.norsys.evaluation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository ;
    public void saveUser(User user) {
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public void DeleteUser(Long id) {
        Optional<User> deletedUser = userRepository.findById(id);
        userRepository.delete(deletedUser.get());
    }

    public List<User> getUsersByName(String name){
       return  userRepository.findUserByName(name);
    }
}
