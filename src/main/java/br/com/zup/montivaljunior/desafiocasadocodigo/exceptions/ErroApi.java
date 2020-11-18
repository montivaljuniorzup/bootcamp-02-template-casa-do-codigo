package br.com.zup.montivaljunior.desafiocasadocodigo.exceptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ErroApi {

    private List<String> erros = new ArrayList<>();

    public ErroApi(List<String> erros) {
        this.erros = erros;
    }

    public ErroApi(String erro) {
        this.erros = Arrays.asList(erro);
    }

    public List<String> getErros() {
        return erros;
    }
}
