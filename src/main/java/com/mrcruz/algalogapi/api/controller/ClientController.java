package com.mrcruz.algalogapi.api.controller;

import com.mrcruz.algalogapi.domain.model.Client;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {

    @PersistenceContext
    private EntityManager manager;

    @GetMapping
    public List<Client> listAll(){
        return manager.createQuery("from Client", Client.class).getResultList();
    }
}
