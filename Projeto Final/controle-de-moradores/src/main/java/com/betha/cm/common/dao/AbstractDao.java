package com.betha.cm.common.dao;

import com.betha.cm.common.annotation.Coluna;
import com.betha.cm.common.reflect.ReflexaoObject;
import com.betha.cm.common.sql.ElaboraSql;
import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AbstractDao<T> {

    public T inserir(T t) throws Exception {
        final ElaboraSql elaboraSql = new ElaboraSql(ReflexaoObject.carregaPropriedadeColunas(t));
        try {
            PreparedStatement stm = Connection.get().getParamStm(elaboraSql.getSqlInsert());
            stm.execute();
            return t;
        } catch (SQLException ex) {
            throw new Exception("Erro ao inserir o registro", ex);
        }
    }

    public T atualizar(T t) throws Exception {
        final ElaboraSql elaboraSql = new ElaboraSql(ReflexaoObject.carregaPropriedadeColunas(t));
        try {
            PreparedStatement stm = Connection.get().getParamStm(elaboraSql.getSqlUpdade());
            stm.execute();
            return t;
        } catch (SQLException ex) {
            throw new Exception("Erro ao atualizar o registro", ex);
        }
    }

    public void deletar(T t) throws Exception {
        final ElaboraSql elaboraSql = new ElaboraSql(ReflexaoObject.carregaPropriedadeColunas(t));
        try {
            PreparedStatement stm = Connection.get().getParamStm(elaboraSql.getSqlDelete());
            stm.execute();
        } catch (SQLException ex) {
            throw new Exception("Erro ao deletar o registro", ex);
        }
    }

    public List<T> buscarTodos(T t, final String where) throws Exception {
        final ElaboraSql elaboraSql = new ElaboraSql(ReflexaoObject.carregaPropriedadeColunas(t));
        System.out.println("where" + where);
        try {
            List<T> lista = new ArrayList<>();
            PreparedStatement stm = Connection.get().getParamStm(elaboraSql.getSqlFindAll(where));
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                lista.add(parse(rs, t.getClass()));
            }
            return lista;
        } catch (SQLException ex) {
            throw new Exception("Erro ao buscar o registros", ex);
        }
    }

    public T buscar(T t) throws Exception {
        final ElaboraSql elaboraSql = new ElaboraSql(ReflexaoObject.carregaPropriedadeColunas(t));
        try {
            PreparedStatement stm = Connection.get().getParamStm(elaboraSql.getSqlFind());
            ResultSet rs = stm.executeQuery();
            rs.next();
            return parse(rs, t.getClass());
        } catch (SQLException ex) {
            throw new Exception("Erro ao buscar o registro", ex);
        }
    }

    private T parse(ResultSet rs, Class classe) throws SQLException, InstantiationException, IllegalAccessException {
        Object newObject = classe.newInstance();
        for (Field field : newObject.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Coluna.class)) {
                field.setAccessible(true);
                Coluna coluna = field.getAnnotation(Coluna.class);
                switch (field.getType().getName()) {
                    case "java.lang.String":
                        field.set(newObject, rs.getString(coluna.nome()));
                        break;
                    case "java.lang.Long":
                        field.set(newObject, rs.getLong(coluna.nome()));
                        break;
                    case "java.lang.Float":
                        field.set(newObject, rs.getFloat(coluna.nome()));
                        break;
                }
            }
        }
        return (T) newObject;
    }
}
