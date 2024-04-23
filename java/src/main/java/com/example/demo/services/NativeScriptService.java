package com.example.demo.services;

import org.springframework.stereotype.Component;
import jakarta.persistence.PersistenceException;

import java.sql.*;

/*
 * Classe responsável pela conexão com o banco de dados de teste
 * Não será necessária a alteração desta implementação.
 */

@Component
public class NativeScriptService {
    private static final String URL = "jdbc:postgresql://localhost:7000/postgres";//Alterado pois com db:5432 não funcionou, apontar no dia da entrega
    private static final String USUARIO = "postgres";
    private static final String SENHA = "example";

    public ResultSet execute(String sql) throws PersistenceException {
        try {
            return executeQuery(sql);
        } catch (PersistenceException | SQLException e) {
            throw new PersistenceException("Erro ao executar consulta nativa: " + e.getMessage());
        }
    }

    private ResultSet executeQuery(String sql) throws PersistenceException, SQLException {
        Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
        PreparedStatement statement = conexao.prepareStatement(sql);
        try {
            return statement.executeQuery();
        } catch (Exception e) {
            throw new PersistenceException("Erro ao executar a consulta no banco de dados: " + e.getMessage());
        } finally {
            conexao.close();
            statement.close();
        }
    }

    // Preparações isoladas

    public Connection getConectionDb() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }

    public PreparedStatement getPreparedStatementDb(String sql, Connection connection) throws SQLException {
        return connection.prepareStatement(sql);
    }

}
