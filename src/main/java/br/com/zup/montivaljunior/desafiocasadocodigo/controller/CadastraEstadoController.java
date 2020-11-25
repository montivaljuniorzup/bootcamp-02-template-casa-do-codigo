package br.com.zup.montivaljunior.desafiocasadocodigo.controller;

import br.com.zup.montivaljunior.desafiocasadocodigo.dto.request.NovoEstadoRequest;
import br.com.zup.montivaljunior.desafiocasadocodigo.model.Estado;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

//2
@RestController
@RequestMapping("/estados")
public class CadastraEstadoController {

    private EntityManager manager;

    public CadastraEstadoController(EntityManager manager) {
        this.manager = manager;
    }

    //2
    @PostMapping
    @Transactional
    public ResponseEntity criaNovoEstado(@RequestBody @Valid NovoEstadoRequest estadoRequest) {
        Estado novoEstado = estadoRequest.toModel(manager);
        manager.persist(novoEstado);
        return new ResponseEntity(HttpStatus.OK);
    }
}
