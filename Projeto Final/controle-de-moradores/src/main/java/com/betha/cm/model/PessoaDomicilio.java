package com.betha.cm.model;

import com.betha.cm.common.annotation.Coluna;
import com.betha.cm.common.annotation.Id;
import com.betha.cm.common.annotation.Tabela;
import com.betha.cm.common.model.AbstractEntityId;

@Tabela(nome = "PESSOAS_DOMICILIOS", sequence = "SEQ_PESSOAS_DOMICILIOS")
public class PessoaDomicilio implements AbstractEntityId<Long>{

// SELECT PESSOAS_DOMICILIOS.ID AS ID,
//        PESSOAS_DOMICILIOS.I_PESSOAS AS I_PESSOAS,
//        PESSOAS.NOME AS NOME_PESSOA,
//        PESSOAS_DOMICILIOS.I_DOMICILIOS AS I_DOMICILIOS,
//        DOMICILIOS.DESCRICAO AS DESCRICAO_DOMICILIOS,
//        PESSOAS_DOMICILIOS.RESPONSAVEL AS RESPONSAVEL 
//   FROM PESSOAS_DOMICILIOS,  
//        DOMICILIOS,
//	    PESSOAS
//  WHERE DOMICILIOS.ID = PESSOAS_DOMICILIOS.I_DOMICILIOS AND
//	PESSOAS.ID = PESSOAS_DOMICILIOS.I_PESSOAS;
    
    @Id
    @Coluna(nome = "ID")
    private Long id;
    @Coluna(nome = "I_PESSOAS")
    private Long idPessoa;
    @Coluna(nome = "NOME_PESSOA", insertable = false)
    private String nomePessoa;
    @Coluna(nome = "I_DOMICILIOS")
    private Long idDomicilio;
    @Coluna(nome = "DESCRICAO_DOMICILIOS", insertable = false)
    private String descricaoDomicilio;
    @Coluna(nome = "RESPONSAVEL")
    private String responsavel;
    
    public PessoaDomicilio() {
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

    public Long getIdDomicilio() {
        return idDomicilio;
    }

    public void setIdDomicilio(Long idDomicilio) {
        this.idDomicilio = idDomicilio;
    }

    public String getDescricaoDomicilio() {
        return descricaoDomicilio;
    }

    public void setDescricaoDomicilio(String descricaoDomicilio) {
        this.descricaoDomicilio = descricaoDomicilio;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }
    
 }
