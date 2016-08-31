package com.betha.cm.common.sql;

import com.betha.cm.common.annotation.Coluna;
import com.betha.cm.common.reflect.PropriedadeObjeto;
import com.betha.cm.common.util.Utils;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.StringJoiner;

public class ElaboraSql {

    private PropriedadeObjeto propriedadeObjeto;
    private final String SQL_INSERT_FORMAT = "INSERT INTO %s (%s, %s) VALUES(%s, %s)";
    private final String SQL_NEXT_SEQUENCE_FORMAT = "(SELECT NEXTVAL('%s'))";
    private final String SQL_CORRENT_SEQUENCE_FORMAT = "(SELECT CURVAL('%s'))";
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
                getSqlNextSequence(),
                getPropriedadeObjeto().getValoresInsertableSemId()
        );
    }

    private String getSqlNextSequence() {
        return String.format(SQL_NEXT_SEQUENCE_FORMAT,
                getPropriedadeObjeto().getSequenceTabela());
    }
    
    public String getSqlCorrentSequence() {
        return String.format(SQL_CORRENT_SEQUENCE_FORMAT,
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
    
   public String getWhere(Map<String, String> dados) throws IllegalArgumentException, IllegalAccessException {
       StringJoiner colunas = new StringJoiner(" AND ");
       dados.keySet().stream()
               .forEach(key -> colunas.add(getPropriedadeObjeto().getNomeColuna(key) + "LIKE '%"+ dados.get(key) +"%'")); 
       return colunas.toString();
   }
   
}
