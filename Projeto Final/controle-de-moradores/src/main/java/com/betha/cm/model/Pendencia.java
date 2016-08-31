package com.betha.cm.model;

import com.betha.cm.common.annotation.Coluna;
import com.betha.cm.common.annotation.Id;
import com.betha.cm.common.annotation.Tabela;
import com.betha.cm.common.model.AbstractEntityId;

@Tabela(nome = "PENDENCIAS", sequence = "SEQ_PENDENCIAS")
public class Pendencia implements AbstractEntityId<Long> {

// SELECT PENDENCIAS.ID AS ID,
//        PENDENCIAS.I_PESSOAS AS I_PESSOAS,
//	  PESSOAS.NOME AS NOME_PESSOA,
//        PENDENCIAS.DATA_CADASTRO AS DATA_CADASTRO,
//        PENDENCIAS.DATA_PREVISTA_RESOLUCAO AS DATA_PREVISTA_RESOLUCAO,
//        PENDENCIAS.TIPO_PENDENCIA AS TIPO_PENDENCIA,
//        CASE PENDENCIAS.TIPO_PENDENCIA
//            WHEN 'S' THEN 'Solicitação'
//            WHEN 'P' THEN 'Pendencia' AS DESCRICAO_TIPO_PENDENCIA,
//        PENDENCIAS.DESCRICAO AS DESCRICAO,
//	  (SELECT PENDENCIAS_SITUACOES.SITUACAO
//           FROM PENDENCIAS_SITUACOES 
//	    WHERE PENDENCIAS_SITUACOES.I_PENDENCIAS = PENDENCIAS.ID 
//       ORDER BY PENDENCIAS_SITUACOES.ID DESC 
//          LIMIT 1) AS SITUACAO_ATUAL,
//        CASE (SELECT PENDENCIAS_SITUACOES.SITUACAO
//                FROM PENDENCIAS_SITUACOES 
//               WHERE PENDENCIAS_SITUACOES.I_PENDENCIAS = PENDENCIAS.ID 
//            ORDER BY PENDENCIAS_SITUACOES.ID DESC 
//               LIMIT 1)
//            WHEN 'A' THEN 'Pendente'
//            WHEN 'R' THEN 'Resolvido'
//        END AS DESCRICAO_SITUACAO_ATUAL
//   FROM PENDENCIAS,
//   	  PESSOAS
//  WHERE PESSOAS.ID = PENDENCIAS.I_PESSOAS;
    @Id
    @Coluna(nome = "ID")
    private Long id;
    @Coluna(nome = "I_PESSOAS")
    private Long idPessoa;
    @Coluna(nome = "NOME_PESSOA")
    private String nomePessoa;
    @Coluna(nome = "DATA_CADASTRO")
    private String dataCadastro;
    @Coluna(nome = "DATA_PREVISTA_RESOLUCAO")
    private String dataPrevistaResolucao;
    @Coluna(nome = "TIPO_PENDENCIA")
    private String tipoPendencia;
    @Coluna(nome = "DESCRICAO_TIPO_PENDENCIA", insertable = false)
    private String descricaoTipoPendencia;
    @Coluna(nome = "DESCRICAO")
    private String descricao;
    @Coluna(nome = "SITUACAO_ATUAL", insertable = false)
    private String situacaoAtual;
    @Coluna(nome = "DESCRICAO_SITUACAO_ATUAL", insertable = false)
    private String situacaoAtualDescricao;

    public Pendencia() {
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getDataPrevistaResolucao() {
        return dataPrevistaResolucao;
    }

    public void setDataPrevistaResolucao(String dataPrevistaResolucao) {
        this.dataPrevistaResolucao = dataPrevistaResolucao;
    }

    public String getTipoPendencia() {
        return tipoPendencia;
    }

    public void setTipoPendencia(String tipoPendencia) {
        this.tipoPendencia = tipoPendencia;
    }

    public String getDescricaoTipoPendencia() {
        return descricaoTipoPendencia;
    }

    public void setDescricaoTipoPendencia(String descricaoTipoPendencia) {
        this.descricaoTipoPendencia = descricaoTipoPendencia;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSituacaoAtual() {
        return situacaoAtual;
    }

    public void setSituacaoAtual(String situacaoAtual) {
        this.situacaoAtual = situacaoAtual;
    }

    public String getSituacaoAtualDescricao() {
        return situacaoAtualDescricao;
    }

    public void setSituacaoAtualDescricao(String situacaoAtualDescricao) {
        this.situacaoAtualDescricao = situacaoAtualDescricao;
    }

}
