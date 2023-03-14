package co.develhope.Unit.test.controller;

import co.develhope.Unit.test.entities.User;
import co.develhope.Unit.test.repositories.UserRepository;
import co.develhope.Unit.test.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PostMapping("/create-user")
    public @ResponseBody User createUser(@RequestBody User user){
        return userRepository.save(user);
    }

    @GetMapping("/find-users")
    public @ResponseBody List<User> getUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/fund-user/{id}")
    public @ResponseBody User getId(@PathVariable Long id){
        Optional<User> user= userRepository.findById(id);
        return user.orElse(null);
    }

    @PutMapping("/update-user/{id}")
    public @ResponseBody User update(@PathVariable Long id,@RequestBody User user){
        user.setId(id);
        return userRepository.save(user);
    }

    @PutMapping("/update-user/{id}/active")
    public @ResponseBody User update(@PathVariable Long id, @RequestParam("active") boolean active){
        return userService.setUserActivationStatus(id, active);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        userRepository.deleteById(id);
    }

    @DeleteMapping("/delete-all")
    public void deleteAll(){
        userRepository.deleteAll();
    }

}