package com.betha.cm.common.model;

import com.betha.cm.common.annotation.Coluna;
import com.betha.cm.common.util.Utils;
import java.lang.reflect.Field;
import java.util.Map;

public interface Parseable {

    default void parse(Map<String, String> dados) throws IllegalArgumentException, IllegalAccessException {
        for (Field field : this.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Coluna.class)) {
                field.setAccessible(true);
                Coluna coluna = field.getAnnotation(Coluna.class);
                if (coluna.insertable()) {
                    switch (field.getType().getName()) {
                        case "java.lang.String":
                            field.set(this, dados.get(field.getName()));
                            break;
                        case "java.lang.Long":
                            field.set(this, Utils.parseLong(dados.get(field.getName())));
                            break;
                        case "java.lang.Float":
                            field.set(this, Float.parseFloat(dados.get(field.getName())));
                            break;
                    }
                }
            }
        }
    }
}
