package com.betha.cm.common.sql;

import com.betha.cm.common.reflect.PropriedadeObjeto;

public class ElaboraSql {

    private PropriedadeObjeto propriedadeObjeto;
    private final String SQL_INSERT_FORMAT = "INSERT INTO %s (%s, %s) VALUES(%s, %s)";
    private final String SQL_SEQUENCE_FORMAT = "(SELECT NEXTVAL('%s'))";
    private final String SQL_UPDADE_FORMAT = "UPDATE %s SET %s WHERE %s";
    private final String SQL_DELETE_FORMAT = "DELETE FROM %s WHERE %s";
    private final String SQL_FIND_FORMAT = "SELECT * FROM PUBLIC.\"%s\" WHERE %s";
    private final String SQL_FIND_ALL_FORMAT = "SELECT * FROM PUBLIC.\"%s\" ORDER BY %s";

    public ElaboraSql(PropriedadeObjeto propriedadeObjeto) {
        this.propriedadeObjeto = propriedadeObjeto;
    }

    public PropriedadeObjeto getPropriedadeObjeto() {
        return propriedadeObjeto;
    }

    public String getSqlInsert() {
        return String.format(SQL_INSERT_FORMAT,
                getPropriedadeObjeto().getNomeTabela(),
                getPropriedadeObjeto().getColunaId(),
                getPropriedadeObjeto().getColunasInsertableSemId(),
                getSqlSequence(),
                getPropriedadeObjeto().getValoresInsertableSemId()
        );
    }

    private String getSqlSequence() {
        return String.format(SQL_SEQUENCE_FORMAT,
                getPropriedadeObjeto().getSequenceTabela());
    }

    public String getSqlUpdade() {
        return String.format(SQL_UPDADE_FORMAT,
                getPropriedadeObjeto().getNomeTabela(),
                getPropriedadeObjeto().getColunasAndValoresInsertableSemId(),
                getPropriedadeObjeto().getColunaAndValorId()
        );
    }

    public String getSqlDelete() {
        return String.format(SQL_DELETE_FORMAT,
                getPropriedadeObjeto().getNomeTabela(),
                getPropriedadeObjeto().getColunaAndValorId()
        );
    }
    
    public String getSqlFind() {
        return String.format(SQL_FIND_FORMAT,
                getPropriedadeObjeto().getNomeView(),
                getPropriedadeObjeto().getColunaAndValorId()
        );
    }
    
    public String getSqlFindAll(final String where) {
        return String.format(SQL_FIND_ALL_FORMAT,
                getPropriedadeObjeto().getNomeView(),
                getPropriedadeObjeto().getColunaId()
        );
    }
}
