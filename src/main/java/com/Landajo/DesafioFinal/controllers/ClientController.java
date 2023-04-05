package com.Landajo.DesafioFinal.controllers;

import com.Landajo.DesafioFinal.exceptions.ClientExceptions.ClientAlreadyExistsException;
import com.Landajo.DesafioFinal.exceptions.ClientExceptions.ClientNotFoundException;
import com.Landajo.DesafioFinal.exceptions.IdNotValidException;
import com.Landajo.DesafioFinal.models.ClientModel;
import com.Landajo.DesafioFinal.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/client")
public class ClientController {

    @Autowired
    ClientService clientService;

    @PostMapping(path = "/")
    public ResponseEntity<ClientModel> createClient(@RequestBody ClientModel newClient) throws ClientAlreadyExistsException {
        return new ResponseEntity<>(this.clientService.createClient(newClient), HttpStatus.OK);
    }
    @PutMapping(path = "/{id}")
    public ResponseEntity<ClientModel> updateClient(@RequestBody ClientModel client, @PathVariable Long id) throws ClientNotFoundException, IdNotValidException {
        return new ResponseEntity<>(this.clientService.updateClient(client, id), HttpStatus.OK);
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<ClientModel> findClientById(@PathVariable Long id) throws ClientNotFoundException, IdNotValidException {
        return new ResponseEntity<>(this.clientService.findClientById(id), HttpStatus.OK);
    }
    @GetMapping(path = "/")
    public ResponseEntity<List<ClientModel>> findAllClients(){
        return new ResponseEntity<>(this.clientService.findAllClients(), HttpStatus.OK);
    }
}
