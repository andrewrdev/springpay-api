package andrewrdev.SpringPay.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import andrewrdev.SpringPay.dtos.UserDTO;
import andrewrdev.SpringPay.models.User;
import andrewrdev.SpringPay.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> users = this.userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }  

    @PostMapping
    public ResponseEntity<User> save(@RequestBody UserDTO userDTO) {

        User user = new User(userDTO);

        userService.save(user);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
