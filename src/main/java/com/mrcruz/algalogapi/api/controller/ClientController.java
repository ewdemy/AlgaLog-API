package com.mrcruz.algalogapi.api.controller;

import com.mrcruz.algalogapi.domain.model.Client;
import com.mrcruz.algalogapi.domain.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {

    private ClientService clientService;

    @GetMapping
    public List<Client> listAll(){
        return clientService.findAll();
    }

    @GetMapping("/{id}")
    public Client findClient(@PathVariable Long id){
        return clientService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client createClient(@Valid @RequestBody Client client){
        return clientService.save(client);
    }

    @PutMapping("/{id}")
    public Client updateClient(@PathVariable Long id, @Valid @RequestBody Client client){
        return clientService.update(id, client);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable Long id){
        clientService.delete(id);
    }

}
