package br.com.zup.montivaljunior.desafiocasadocodigo.controller;

import br.com.zup.montivaljunior.desafiocasadocodigo.dto.response.LivroDadosCompletosResponse;
import br.com.zup.montivaljunior.desafiocasadocodigo.dto.response.LivroDadosMinimosResponse;
import br.com.zup.montivaljunior.desafiocasadocodigo.model.Livro;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

//5
@RestController
@RequestMapping("/livros/produtos")
public class BuscaLivroController {

    private EntityManager manager;

    public BuscaLivroController(EntityManager manager) {
        this.manager = manager;
    }

    //3
    @GetMapping
    @Transactional
    public ResponseEntity buscaLivros() {
        List<Livro> livros = manager.createQuery("Select u from Livro u").getResultList();
        List<LivroDadosMinimosResponse> livrosResponse = livros.stream().map(l -> new LivroDadosMinimosResponse(l)).collect(Collectors.toList());
        return ResponseEntity.ok(livrosResponse);
    }

    //2
    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<?> buscaLivroPeloId(@PathVariable("id") Long id) {
        Livro livro = manager.find(Livro.class, id);
        Assert.notNull(livro, "Não foi encontrado Livro com esse Id: " + id);
        return ResponseEntity.ok(new LivroDadosCompletosResponse(livro));
    }
}
