package br.com.zup.montivaljunior.desafiocasadocodigo.controller;

import br.com.zup.montivaljunior.desafiocasadocodigo.dto.request.NovoPaisRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

//1
@RestController
@RequestMapping("/pais")
public class CadastraPaisController {

    private EntityManager manager;

    public CadastraPaisController(EntityManager manager) {
        this.manager = manager;
    }

    //1
    @PostMapping
    @Transactional
    public ResponseEntity criaNovoPais(@RequestBody @Valid NovoPaisRequest request) {
        manager.persist(request.toModel());
        return new ResponseEntity(HttpStatus.OK);
    }

}
