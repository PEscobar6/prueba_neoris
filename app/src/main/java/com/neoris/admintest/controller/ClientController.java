package com.neoris.admintest.controller;

import com.neoris.admintest.model.Client;
import com.neoris.admintest.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping("/{userId}")
    public ResponseEntity<Client> saveClient(@PathVariable("userId") Long userId, @RequestBody Client client ){
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.saveClient(userId, client));
    }

    @GetMapping
    public ResponseEntity<Page<Client>> getAllClients(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam(required = false, defaultValue = "false") Boolean enablePagination ){
        return ResponseEntity.ok(clientService.getAllClients(page, size, enablePagination));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Client> findClientById(@PathVariable("id") Long id) {
        Optional<Client> optionalClient = clientService.findById(id);

        if (optionalClient.isPresent()) {
            return ResponseEntity.ok(optionalClient.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping
    public ResponseEntity<Client> updateClient(Client client ){
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.editClient(client));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteClient( @PathVariable("id") Long id ){
        clientService.deleteStudent(id);
        return ResponseEntity.ok(clientService.existById(id));
    }
}
