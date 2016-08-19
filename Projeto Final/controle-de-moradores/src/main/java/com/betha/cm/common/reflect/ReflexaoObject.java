package com.betha.cm.common.reflect;

import com.betha.cm.common.annotation.Coluna;
import com.betha.cm.common.annotation.Id;
import com.betha.cm.common.annotation.Tabela;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReflexaoObject {

    private ReflexaoObject() {
    }

    public static PropriedadeObjeto carregaPropriedadeColunas(final Object objeto) {
        PropriedadeObjeto propriedadeObjeto = carregaPropriedadeTabela(objeto);

        for (Field field : objeto.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Coluna.class)) {
                field.setAccessible(true);
                Coluna coluna = field.getAnnotation(Coluna.class);
                propriedadeObjeto.addPropriedadeColuna(
                        new PropriedadeColuna(field.isAnnotationPresent(Id.class), coluna.insertable(), field.getName(), field.getType().getName(), coluna.nome(), getValorMetodo(objeto, getMetodoGetParaColuna(field.getName()))));
            }
        }
        return propriedadeObjeto;
    }

    public static PropriedadeObjeto carregaPropriedadeTabela(final Object objeto) {
        PropriedadeObjeto propriedadeObjeto = new PropriedadeObjeto();

        Annotation annotation = objeto.getClass().getAnnotation(Tabela.class);
        Tabela tabela = (Tabela) annotation;
        propriedadeObjeto.setNomeTabela(tabela.nome());
        propriedadeObjeto.setSequenceTabela(tabela.sequence());
        return propriedadeObjeto;
    }

    public static String getValorMetodo(final Object o, final String metodo) {
        Method metodoM = null;
        String retorno = null;
        try {
            metodoM = o.getClass().getMethod(metodo);
        } catch (NoSuchMethodException | SecurityException ex) {
            Logger.getLogger(ReflexaoObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Object objetoMetodo = metodoM.invoke(o);
            if (objetoMetodo == null) {
                retorno = "null";
            } else {
                retorno = metodoM.invoke(o).toString();
            }
            if (objetoMetodo instanceof String || objetoMetodo instanceof LocalDate) {
                retorno = "'" + retorno + "'";
            }
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ReflexaoObject.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(ReflexaoObject.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(ReflexaoObject.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ReflexaoObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public static String getMetodoGetParaColuna(final String campo) {
        return "get" + campo.substring(0, 1).toUpperCase() + campo.substring(1);
    }
}
