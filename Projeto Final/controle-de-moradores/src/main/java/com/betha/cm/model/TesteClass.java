package com.betha.cm.model;

import com.betha.cm.dao.PessoaDao;
import java.util.List;

public class TesteClass {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, Exception {

        Pessoa pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("MARCEL");
        pessoa.setCpf("CPF");
        pessoa.setRg("RG");
        pessoa.setSexo("M");
        pessoa.setDataNascimento("2015-01-01");
        pessoa.setTelefoneCelular("CELULAR");
        pessoa.setTelefoneFixo("FIXO");
        pessoa.setEmail("EMAIL");
        pessoa.setSituacaoAtual("SITUAÇÃO");

        final String SQL_FIND_ALL_FORMAT = "SELECT * FROM PUBLIC.\"%s\"  %s ORDER BY %s";
        System.out.println(String.format(SQL_FIND_ALL_FORMAT, "123", "122", ""));

    }
}
