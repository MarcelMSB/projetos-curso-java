package com.betha.cm.model;

import com.betha.cm.common.annotation.Coluna;
import com.betha.cm.common.annotation.Id;
import com.betha.cm.common.annotation.Tabela;
import com.betha.cm.common.model.AbstractEntityId;

@Tabela(nome = "DOMICILIOS", sequence = "SEQ_DOMICILIOS")
public class Domicilio implements AbstractEntityId<Long>{

// SELECT DOMICILIOS.ID AS ID,
//        DOMICILIOS.DESCRICAO AS DESCRICAO,
//        DOMICILIOS.CEP AS CEP,
//        DOMICILIOS.NUMERO AS NUMERO,
//        DOMICILIOS.RUA AS RUA,
//        DOMICILIOS.BAIRRO AS BAIRRO,
//        DOMICILIOS.CIDADE AS CIDADE,
//        DOMICILIOS.UF AS UF,
//        DOMICILIOS.TELEFONE_FIXO AS TELEFONE_FIXO
//   FROM DOMICILIOS;
    
    @Id
    @Coluna(nome = "ID")
    private Long id;
    @Coluna(nome = "DESCRICAO")
    private String descricao;
    @Coluna(nome = "CEP")
    private String cep;
    @Coluna(nome = "NUMERO")
    private String numero;
    @Coluna(nome = "RUA")
    private String rua;
    @Coluna(nome = "BAIRRO")
    private String bairro;
    @Coluna(nome = "CIDADE")
    private String cidade;
    @Coluna(nome = "UF")
    private String uf;
    @Coluna(nome = "TELEFONE_FIXO")
    private String telefoneFixo;
    
    public Domicilio() {
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getTelefoneFixo() {
        return telefoneFixo;
    }

    public void setTelefoneFixo(String telefoneFixo) {
        this.telefoneFixo = telefoneFixo;
    }

}
