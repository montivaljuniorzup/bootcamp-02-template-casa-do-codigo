package br.com.zup.montivaljunior.desafiocasadocodigo.controller;

import br.com.zup.montivaljunior.desafiocasadocodigo.dto.request.NovoLivroRequest;
import br.com.zup.montivaljunior.desafiocasadocodigo.dto.response.LivroDadosCompletosResponse;
import br.com.zup.montivaljunior.desafiocasadocodigo.model.Livro;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

//3
@RestController
@RequestMapping("/livros")
public class CadastraLivroController {

    private EntityManager manager;

    public CadastraLivroController(EntityManager manager) {
        this.manager = manager;
    }

    //3
    @PostMapping
    @Transactional
    public ResponseEntity criaNovoLivro(@Valid @RequestBody NovoLivroRequest livroRequest) {
        Livro livro = livroRequest.paraLivro(manager);
        manager.persist(livro);
        return ResponseEntity.ok(new LivroDadosCompletosResponse(livro));
    }
}
