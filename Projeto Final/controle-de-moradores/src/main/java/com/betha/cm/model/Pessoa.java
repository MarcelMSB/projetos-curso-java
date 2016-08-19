package com.betha.cm.model;

import com.betha.cm.common.annotation.Coluna;
import com.betha.cm.common.annotation.Id;
import com.betha.cm.common.annotation.Tabela;
import com.betha.cm.common.model.AbstractEntityId;

@Tabela(nome = "PESSOAS", sequence = "SEQ_PESSOAS")
public class Pessoa implements AbstractEntityId<Long>{

// SELECT PESSOAS.ID AS ID,
//        PESSOAS.NOME AS NOME,
//        PESSOAS.SEXO AS SEXO,
//        PESSOAS.CPF AS CPF,
//        PESSOAS.RG AS RG,
//        PESSOAS.DATA_NASCIMENTO AS DATA_NASCIMENTO,
//        PESSOAS.TELEFONE_FIXO AS TELEFONE_FIXO,
//        PESSOAS.TELEFONE_CELULAR AS TELEFONE_CELULAR,
//        PESSOAS.EMAIL AS EMAIL,
//		(SELECT PESSOAS_SITUACOES.SITUACAO
//		   FROM PESSOAS_SITUACOES 
//		  WHERE PESSOAS_SITUACOES.I_PESSOAS = PESSOAS.ID 
//	   ORDER BY PESSOAS_SITUACOES.ID DESC 
//	      LIMIT 1) AS SITUACAO_ATUAL
//   FROM PESSOAS;
    
    @Id
    @Coluna(nome = "ID")
    private Long id;
    @Coluna(nome = "NOME")
    private String nome;
    @Coluna(nome = "SEXO")
    private String sexo;
    @Coluna(nome = "CPF")
    private String cpf;
    @Coluna(nome = "RG")
    private String rg;
    @Coluna(nome = "DATA_NASCIMENTO")
    private String dataNascimento;
    @Coluna(nome = "TELEFONE_FIXO")
    private String telefoneFixo;
    @Coluna(nome = "TELEFONE_CELULAR")
    private String telefoneCelular;
    @Coluna(nome = "EMAIL")
    private String email;
    @Coluna(nome = "SITUACAO_ATUAL", insertable = false)
    private String situacaoAtual;

    public Pessoa() {
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefoneFixo() {
        return telefoneFixo;
    }

    public void setTelefoneFixo(String telefoneFixo) {
        this.telefoneFixo = telefoneFixo;
    }

    public String getTelefoneCelular() {
        return telefoneCelular;
    }

    public void setTelefoneCelular(String telefoneCelular) {
        this.telefoneCelular = telefoneCelular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSituacaoAtual() {
        return situacaoAtual;
    }

    public void setSituacaoAtual(String situacaoAtual) {
        this.situacaoAtual = situacaoAtual;
    }
}
