package fr.norsys.evaluation.controller;

import fr.norsys.evaluation.entity.Book;
import fr.norsys.evaluation.entity.User;
import fr.norsys.evaluation.service.UserService;
import org.springframework.stereotype.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@Controller
@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService ;
    @PostMapping
    public void CreateUser(@RequestBody User user) {

        userService.saveUser(user);
        ResponseEntity.ok("User Created successfully");
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();

    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable Long  id){
        Optional<User> user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable long id, @RequestBody User updatedUser) throws Exception {
        Optional<User> existingUserOptional = userService.getUserById(id);

        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();

            if (updatedUser.getUserName() != null) {
                existingUser.setUserName(updatedUser.getUserName());
            }
            if (updatedUser.getEmail() != null) {
                existingUser.setEmail(updatedUser.getEmail());
            }

            // Save the updated User
            this.CreateUser(existingUser);
            return ResponseEntity.ok(null);

        } else {
            throw new Exception("User id not found");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
        userService.DeleteUser(id);
        return ResponseEntity.ok(null);

    }

    @GetMapping("/search/{name}")
    public List<User> getUsersByName(@PathVariable String name ) {
        return userService.getUsersByName(name);

    }

    @GetMapping("/OwnedBooks/{id}")
    public List<Book> getOwnedBooksByUser(@PathVariable long id) {
        Optional<User> user =  userService.getUserById(id);
        return user.get().getUserOwnedBooks();

    }


}
