package br.com.zup.montivaljunior.desafiocasadocodigo.dto.request;

import br.com.zup.montivaljunior.desafiocasadocodigo.model.Livro;
import br.com.zup.montivaljunior.desafiocasadocodigo.validation.annotation.ExistId;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Objects;

public class ItemRequest {

    @NotNull
    @ExistId(atributo = "id", classe = Livro.class, message = "Não foi encontrado Livro com o id informado")
    private Long idLivro;

    @NotNull
    @Positive
    @Min(1)
    private int quantidade;

    /**
     * @Deprecated
     */
    @Deprecated
    public ItemRequest() {
    }

    public ItemRequest(@NotNull Long idLivro, @NotNull @Positive @Min(1) int quantidade) {
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
        ItemRequest item = (ItemRequest) o;
        return idLivro.equals(item.idLivro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idLivro);
    }
}
