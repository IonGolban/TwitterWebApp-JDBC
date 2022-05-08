package fiiPractic.TwitterWebApp.controller;

import fiiPractic.TwitterWebApp.model.dto.UserRegisterDto;
import fiiPractic.TwitterWebApp.service.UserService;
import fiiPractic.TwitterWebApp.model.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users",  produces = "application/json")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping(value = "/users/id={id}", produces = "application/json")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
        return new ResponseEntity<>(userService.getUserById(id),HttpStatus.OK);
    }

    @GetMapping(value = "/users/userName={userName}", produces = "application/json")
    public ResponseEntity<UserDto> getUserByUserName(@PathVariable String userName) {
        return new ResponseEntity<>(userService.getUserByUserName(userName),HttpStatus.OK);
    }

    @GetMapping(value = "/users/lastName={lastName}", produces = "application/json")
    public ResponseEntity<List<UserDto>> getUserByLastName(@PathVariable String lastName) {
        return new ResponseEntity<>(userService.getUserByLastName(lastName),HttpStatus.OK);
    }

    @GetMapping(value = "/users/firstName={firstName}", produces = "application/json")
    public ResponseEntity<List<UserDto>> getUserByFirstName(@PathVariable String firstName) {
        return new ResponseEntity<>(userService.getUserByFirstName(firstName),HttpStatus.OK);
    }

    @PostMapping(value = "/users", produces = "application/json")
    public void addUser(@RequestBody UserRegisterDto user ) {
         userService.registerUser(user);
    }

    @DeleteMapping(value = "/users/id={userId}/delete",produces = "application/json")
    public void deleteUser(@PathVariable Integer userId){
        userService.deleteUser(userId);
    }

}
