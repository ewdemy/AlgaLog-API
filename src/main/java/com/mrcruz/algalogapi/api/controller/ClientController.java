package com.mrcruz.algalogapi.api.controller;

import com.mrcruz.algalogapi.domain.model.Client;
import com.mrcruz.algalogapi.domain.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {

    private ClientRepository clientRepository;

    @GetMapping
    public List<Client> listAll(){
        return clientRepository.findAll();
    }

    @GetMapping("/{id}")
    public Client findClient(@PathVariable Long id){
        return clientRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Client not found"));
    }

    @PostMapping
    public Client createClient(@Valid @RequestBody Client client){
        return clientRepository.save(client);
    }

    @PutMapping("/{id}")
    public Client updateClient(@PathVariable Long id, @Valid @RequestBody Client client){
        if(!clientRepository.existsById(id)){
            throw new EntityNotFoundException("Client not found");
        }
        client.setId(id);
        return clientRepository.save(client);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable Long id){
        if(!clientRepository.existsById(id)){
            throw new EntityNotFoundException("Client not found");
        }
        clientRepository.deleteById(id);
    }

}
