package com.dansoft.empresaCoelhoDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {

	private static final String URL = "jdbc:mysql://u1jokrgyhvjrhfu9:UP8ghPMdP7xvVUULXGEO@b5xazrlpk4eidd5uoo9x-mysql.services.clever-cloud.com:3306/b5xazrlpk4eidd5uoo9x";
	private static final String USER = "u1jokrgyhvjrhfu9";
	private static final String PASSWD = "UP8ghPMdP7xvVUULXGEO";

	private static Connection con = null;

    private DAO() {
    }

    public static Connection getConnection() {
        if (con == null) {
            try {
                con = DriverManager.getConnection(URL, USER, PASSWD);
                System.out.println("Conexão com o banco de dados realizada com sucesso.\n");
            } catch (SQLException e) {
                System.out.println("Erro de conexão: " + e.getMessage());
            }
        }
        return con;
    }

    public static void closeConnection() {
        if (con != null) {
            try {
                con.close();
                System.out.println("Conxão com o banco de dados finalizada.\n");
            } catch (SQLException e) {
                System.out.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }
}
