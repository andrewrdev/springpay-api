package andrewrdev.SpringPay.controllers;

import java.sql.Connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import andrewrdev.SpringPay.database.DatabaseConnection;

@RequestMapping("/api/users")
@RestController
public class UserController {

    @Autowired
    private DatabaseConnection databaseConnection;  
    

    @GetMapping
    public String teste() {
        Connection conn = databaseConnection.getConnection();
        // Faça algo com a conexão, se necessário
        return "servidor ok";
    }
}
