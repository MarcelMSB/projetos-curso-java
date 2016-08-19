package com.betha.cm.common.dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connection {

    private static Connection INSTANCE;

    private java.sql.Connection conn;
    private final String url;
    private final String usuario;
    private final String senha;

    private Connection() {
        url = "jdbc:postgresql://localhost/CM";
        usuario = "postgres";
        senha = "admin";
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Connection get() {
        if (INSTANCE == null) {
            INSTANCE = new Connection();
        }
        return INSTANCE;
    }

    public Statement getStm() throws SQLException {
        getConn();
        return conn.createStatement();
    }

    public PreparedStatement getParamStm(String sql) throws SQLException {
        getConn();
        return conn.prepareStatement(sql);
    }

    private void getConn() throws SQLException {
        if (conn == null) {
            conn = DriverManager.getConnection(url, usuario, senha);
        }
        if (conn.isClosed()) {
            conn = DriverManager.getConnection(url, usuario, senha);
        }
    }

}
