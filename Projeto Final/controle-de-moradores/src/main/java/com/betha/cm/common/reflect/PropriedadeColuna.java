package com.betha.cm.common.reflect;

public class PropriedadeColuna {

    private boolean id;
    private boolean insertable;
    private String nome;
    private String tipo;
    private String coluna;
    private String valor;

    public PropriedadeColuna(final boolean id, final boolean insertable, final String nome,
            final String tipo, final String coluna, final String valor) {
        this.id = id;
        this.insertable = insertable;
        this.nome = nome;
        this.tipo = tipo;
        this.coluna = coluna;
        this.valor = valor;
    }

    public boolean isId() {
        return id;
    }

    public void setId(boolean id) {
        this.id = id;
    }

    public boolean isInsertable() {
        return insertable;
    }

    public void setInsertable(boolean insertable) {
        this.insertable = insertable;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getColuna() {
        return coluna;
    }

    public void setColuna(String coluna) {
        this.coluna = coluna;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
