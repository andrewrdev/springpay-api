package andrewrdev.SpringPay.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import andrewrdev.SpringPay.dtos.UserDTO;
import andrewrdev.SpringPay.models.User;
import andrewrdev.SpringPay.responses.CustomResponse;
import andrewrdev.SpringPay.services.UserService;
import andrewrdev.SpringPay.validations.UserValidation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired 
    private UserValidation userValidation;

    // --------------------------------------------------------------------------------------------

    @GetMapping
    public ResponseEntity<?> getAllUsers() {

        List<User> users = userService.getAllUsers(); 
        
        if(!users.isEmpty()) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new CustomResponse("no users found", "NOT_FOUND", 404), HttpStatus.NOT_FOUND);
        }

    }

    // --------------------------------------------------------------------------------------------

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {

        Optional<User> user = userService.getUserById(id);

        if(user.isPresent()) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new CustomResponse("user not found", "NOT_FOUND", 404), HttpStatus.NOT_FOUND);
        }

    }

    // --------------------------------------------------------------------------------------------

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO userDTO) {      
  
        if (userValidation.hasEmailInDatabase(userDTO.getEmail())) {  
            CustomResponse response = new CustomResponse("email already registered", "BAD_REQUEST", 400);        
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (userValidation.hasDocumentInDatabase(userDTO.getDocument())) {  
            CustomResponse response = new CustomResponse("document already registered", "BAD_REQUEST", 400);        
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        User createdUser = userService.createUser(userDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);

    }

    // --------------------------------------------------------------------------------------------
    
}
