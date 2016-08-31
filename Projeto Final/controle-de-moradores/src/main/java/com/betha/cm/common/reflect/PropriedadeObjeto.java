package com.betha.cm.common.reflect;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringJoiner;

public class PropriedadeObjeto {

    private String nomeTabela;
    private String sequenceTabela;
    private Set<PropriedadeColuna> propriedades = new LinkedHashSet<>();

    public PropriedadeObjeto() {
    }

    public String getNomeTabela() {
        return nomeTabela;
    }

    public void setNomeTabela(String nomeTabela) {
        this.nomeTabela = nomeTabela;
    }

    public String getSequenceTabela() {
        return sequenceTabela;
    }

    public void setSequenceTabela(String sequenceTabela) {
        this.sequenceTabela = sequenceTabela;
    }

    public boolean addPropriedadeColuna(final PropriedadeColuna propriedadeColuna) {
        if (propriedadeColuna == null) {
            return false;
        }
        return propriedades.add(propriedadeColuna);
    }

    public Set<PropriedadeColuna> getPropriedades() {
        return propriedades;
    }

    public String getColunasInsertableComId() {
        StringJoiner colunas = new StringJoiner(", ");
        getPropriedades().stream()
                .filter(propriedade -> propriedade.isInsertable())
                .forEach(prop -> colunas.add(prop.getColuna()));
        return colunas.toString();
    }

    public String getColunasInsertableSemId() {
        StringJoiner colunas = new StringJoiner(", ");
        getPropriedades().stream()
                .filter(propriedade -> propriedade.isInsertable() && !propriedade.isId())
                .forEach(prop -> colunas.add(prop.getColuna()));
        return colunas.toString();
    }

    public String getColunas() {
        StringJoiner colunas = new StringJoiner(", ");
        getPropriedades().stream()
                .forEach(prop -> colunas.add(prop.getColuna()));
        return colunas.toString();
    }

    public String getValoresInsertableComId() {
        StringJoiner colunas = new StringJoiner(", ");
        getPropriedades().stream()
                .filter(propriedade -> propriedade.isInsertable())
                .forEach(prop -> colunas.add(prop.getValor()));
        return colunas.toString();
    }

    public String getValoresInsertableSemId() {
        StringJoiner colunas = new StringJoiner(", ");
        getPropriedades().stream()
                .filter(propriedade -> propriedade.isInsertable() && !propriedade.isId())
                .forEach(prop -> colunas.add(prop.getValor()));
        return colunas.toString();
    }

    public String getValores() {
        StringJoiner colunas = new StringJoiner(", ");
        getPropriedades().stream()
                .forEach(prop -> colunas.add(prop.getValor()));
        return colunas.toString();
    }

    public String getColunaId() {
        return getPropriedades().stream()
                .filter(propriedade -> propriedade.isId()).findFirst().get().getColuna();
    }

    public String getValorId() {
        return getPropriedades().stream()
                .filter(propriedade -> propriedade.isId()).findFirst().get().getValor();
    }

    public String getColunasAndValoresInsertableSemId() {
        StringJoiner colunas = new StringJoiner(", ");
        getPropriedades().stream()
                .filter(propriedade -> propriedade.isInsertable() && !propriedade.isId())
                .forEach(prop -> colunas.add(String.format("%s = %s",
                                        prop.getColuna(), prop.getValor())));
        return colunas.toString();
    }

    public String getColunaAndValorId() {
        final PropriedadeColuna id = getPropriedades().stream()
                .filter(propriedade -> propriedade.isId()).findFirst().get();
        return id.getColuna() + " = " + id.getValor();
    }

    public String getNomeView() {
        return "VW_" + getNomeTabela();
    }
    
     public String getNomeColuna(final String key) {
        return getPropriedades().stream()
               .filter(propriedade -> propriedade.getNome().equals(key))
                .findFirst().get().getColuna();
    }
}
