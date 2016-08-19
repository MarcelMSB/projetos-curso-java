package com.betha.cm.common.util;

import com.google.gson.Gson;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public final class Utils {

    private Utils() {
        throw new UnsupportedOperationException("Classe não pode ser instanciada por ser um utilitário.");
    }

    public static LocalDateTime parseDate(String value, String pattern) {
        return isEmpty(value) ? null : LocalDateTime.parse(value, DateTimeFormatter.ofPattern(pattern));
    }

    public static BigDecimal parseDecimal(String value) {
        return isEmpty(value) ? null : new BigDecimal(value);
    }
    
    public static boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    public static boolean isNotEmpty(String value) {
        return !isEmpty(value);
    }

    public static Long parseLong(String value) {
        return isEmpty(value) ? null : Long.parseLong(value);
    }

    public static String convertListToJson(List<?> itens) {
        List<String> itensString = new ArrayList<>();
        itens.stream().forEach((registro) -> {
            itensString.add(getJson(registro));
        });
        return itensString.toString();
    }
    
    public static Map<String, String> getParameterMap(HttpServletRequest req) {
        Map<String, String> dados = new HashMap<>();
        for (String key : req.getParameterMap().keySet()) {
            dados.put(key, req.getParameter(key));
        }
        return dados;
    }

    public static boolean isNull(Object... values) {
        for (Object value : values) {
            if (value == null) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNotNull(Object... values) {
        return !isNull(values);
    }
    
    public static String nullString(Object value) {
        return isNull(value) ? "" : value.toString();
    }
    
    public static String getJson(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

}
