package br.com.zup.montivaljunior.desafiocasadocodigo.controller;

import br.com.zup.montivaljunior.desafiocasadocodigo.dto.request.NovoAutorRequest;
import br.com.zup.montivaljunior.desafiocasadocodigo.model.Autor;
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
@RequestMapping("/autores")
public class CadastraAutorController {

    private EntityManager manager;

    public CadastraAutorController(EntityManager manager) {
        this.manager = manager;
    }

    //2
    @PostMapping
    @Transactional
    public ResponseEntity criaNovoAutor(@Valid @RequestBody NovoAutorRequest autorRequest) {

        Autor autor = autorRequest.paraAutor();
        manager.persist(autor);
        return ResponseEntity.ok(autor.paraResponse());
    }

}
