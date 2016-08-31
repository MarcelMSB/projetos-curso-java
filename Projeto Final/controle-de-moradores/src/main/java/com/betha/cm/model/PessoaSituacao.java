package com.betha.cm.model;

import com.betha.cm.common.annotation.Coluna;
import com.betha.cm.common.annotation.Id;
import com.betha.cm.common.annotation.Tabela;
import com.betha.cm.common.model.AbstractEntityId;

@Tabela(nome = "PESSOAS_SITUACOES", sequence = "SEQ_PESSOAS_SITUACOES")
public class PessoaSituacao implements AbstractEntityId<Long>{

// SELECT PESSOAS_SITUACOES.ID AS ID,
//        PESSOAS_SITUACOES.I_PESSOAS AS I_PESSOAS,
//        PESSOAS_SITUACOES.DATA_CADASTRO AS DATA_CADASTRO,
//        PESSOAS_SITUACOES.SITUACAO AS SITUACAO
//   FROM PESSOAS_SITUACOES;
    
    @Id
    @Coluna(nome = "ID")
    private Long id;
    @Coluna(nome = "I_PESSOAS")
    private String idPessoa;
    @Coluna(nome = "DATA_CADASTRO")
    private String dataCadastro;
    @Coluna(nome = "SITUACAO")
    private String situacao;
    
    public PessoaSituacao() {
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(String idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

}
