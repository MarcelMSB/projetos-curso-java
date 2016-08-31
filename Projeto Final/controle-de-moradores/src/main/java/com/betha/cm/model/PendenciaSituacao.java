package com.betha.cm.model;

import com.betha.cm.common.annotation.Coluna;
import com.betha.cm.common.annotation.Id;
import com.betha.cm.common.annotation.Tabela;
import com.betha.cm.common.model.AbstractEntityId;

@Tabela(nome = "PENDENCIAS_SITUACOES", sequence = "SEQ_PENDENCIAS_SITUACOES")
public class PendenciaSituacao implements AbstractEntityId<Long>{

// SELECT PENDENCIAS_SITUACOES.ID AS ID,
//        PENDENCIAS_SITUACOES.I_PENDENCIAS AS I_PENDENCIAS,
//        PENDENCIAS_SITUACOES.DATA_CADASTRO AS DATA_CADASTRO,
//        PENDENCIAS_SITUACOES.SITUACAO AS SITUACAO
//   FROM PENDENCIAS_SITUACOES;
    
    @Id
    @Coluna(nome = "ID")
    private Long id;
    @Coluna(nome = "I_PENDENCIAS")
    private Long idPendencia;
    @Coluna(nome = "DATA_CADASTRO")
    private String dataCadastro;
    @Coluna(nome = "SITUACAO")
    private String situacao;
    
    public PendenciaSituacao() {
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdPendencia() {
        return idPendencia;
    }

    public void setIdPendencia(Long idPendencia) {
        this.idPendencia = idPendencia;
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
