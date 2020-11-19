package br.com.zup.montivaljunior.desafiocasadocodigo.controller;

import br.com.zup.montivaljunior.desafiocasadocodigo.dto.request.NovoLivroRequest;
import br.com.zup.montivaljunior.desafiocasadocodigo.dto.response.LivroDadosMinimosResponse;
import br.com.zup.montivaljunior.desafiocasadocodigo.model.Livro;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @PersistenceContext
    private EntityManager manager;

    public LivroController(EntityManager manager) {
        this.manager = manager;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> criaNovoLivro(@Valid @RequestBody NovoLivroRequest livroRequest){
        Livro livro = livroRequest.paraLivro(manager);
        manager.persist(livro);
        return ResponseEntity.ok(livro.paraLivroDadosCompletosResponse());
    }

    @GetMapping
    @Transactional
    public ResponseEntity<?> buscaLivros(){
        List<Livro> livros = manager.createQuery("Select u from Livro u").getResultList();
        List<LivroDadosMinimosResponse> livrosResponse = livros.stream().map(l -> l.paraLivroDadosMinimosResponse()).collect(Collectors.toList());
        return ResponseEntity.ok(livrosResponse);
    }
}
