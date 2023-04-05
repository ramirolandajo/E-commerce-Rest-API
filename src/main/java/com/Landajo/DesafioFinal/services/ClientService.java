package com.Landajo.DesafioFinal.services;

import com.Landajo.DesafioFinal.exceptions.ClientExceptions.ClientAlreadyExistsException;
import com.Landajo.DesafioFinal.exceptions.ClientExceptions.ClientNotFoundException;
import com.Landajo.DesafioFinal.exceptions.IdNotValidException;
import com.Landajo.DesafioFinal.models.ClientModel;
import com.Landajo.DesafioFinal.repositories.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public ClientModel createClient(ClientModel newClient) throws ClientAlreadyExistsException {
        Optional<ClientModel> clientOp = this.clientRepository.findByDocnumber(newClient.getDocnumber());

        if (clientOp.isPresent()){
            log.info("El cliente que esta intentando crear ya existe");
            throw new ClientAlreadyExistsException("El cliente que esta intentado crear ya existe");
        } else {
            return this.clientRepository.save(newClient);
        }
    }

    public ClientModel updateClient(ClientModel client, Long id) throws ClientNotFoundException, IdNotValidException {
        log.info("Id ingresado: " + id);
        if (id <= 0){
            log.info("El id ingresado no es valido.");
            throw new IdNotValidException("El id ingresado no es valido");
        }

        Optional<ClientModel> clientOp = this.clientRepository.findById(id);

        if (clientOp.isEmpty()){
            log.info("El cliente que intenta actualizar no se encuentra en la base de datos");
            throw new ClientNotFoundException("El cliente que intenta actualizar no se encuentra en la base de datos");
        } else {
            ClientModel clientDb = clientOp.get();
            clientDb.setName(client.getName());
            clientDb.setLastname(client.getLastname());
            clientDb.setDocnumber(client.getDocnumber());

            log.info("El cliente actualizado: " + clientDb);
            return this.clientRepository.save(clientDb);
        }
    }

    public ClientModel findClientById(Long id) throws IdNotValidException, ClientNotFoundException {
        if (id <= 0){
            log.info("El id ingresado no es valido");
            throw new IdNotValidException("El id ingresado no es valido");
        }
        Optional<ClientModel> clientOp = this.clientRepository.findById(id);

        if (clientOp.isEmpty()) {
            log.info("El cliente que esta intentando acceder no esta registrado en la base de datos");
            throw new ClientNotFoundException("El cliente que esta intentando acceder no se encuentra en la base de datos");
        } else {
            return clientOp.get();
        }
    }

    public List<ClientModel> findAllClients(){
        return this.clientRepository.findAll();
    }
}
