package br.com.zup.montivaljunior.desafiocasadocodigo.model;

import br.com.zup.montivaljunior.desafiocasadocodigo.validation.ExistId;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Objects;


public class Item {

    @NotNull
    @ExistId(atributo = "id", classe = Livro.class)
    private Long idLivro;

    @NotNull
    @Positive
    @Min(1)
    private int quantidade;

    /**
     * @Deprecated
     */
    @Deprecated
    public Item() {
    }

    public Boolean estaCompleto(){
        return this.getIdLivro() != null;
    }

    public Item(@NotNull Long idLivro, @NotNull @Positive @Min(1) int quantidade) {
        this.idLivro = idLivro;
        this.quantidade = quantidade;
    }

    public Long getIdLivro() {
        return idLivro;
    }

    public int getQuantidade() {
        return quantidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return idLivro.equals(item.idLivro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idLivro);
    }
}
