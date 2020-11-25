package br.com.zup.montivaljunior.desafiocasadocodigo.controller;

import br.com.zup.montivaljunior.desafiocasadocodigo.dto.request.NovaCategoriaRequest;
import br.com.zup.montivaljunior.desafiocasadocodigo.model.Categoria;
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
@RequestMapping("/categorias")
public class CategoriaController {

    private EntityManager manager;

    public CategoriaController(EntityManager manager) {
        this.manager = manager;
    }

    //2
    @PostMapping
    @Transactional
    public ResponseEntity criaNovaCategoria(@Valid @RequestBody NovaCategoriaRequest categoriaRequest) {
        Categoria categoria = categoriaRequest.paraCategoria();
        manager.persist(categoria);
        return ResponseEntity.ok(categoria.paraResponse());
    }
}
