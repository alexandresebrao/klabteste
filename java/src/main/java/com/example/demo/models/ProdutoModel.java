package com.example.demo.models;

import com.example.demo.interfaces.Produtos;
import com.example.demo.services.NativeScriptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Construa suas regras de negócio da forma que for necessária.
 * Se basear nos exemplos abaixo, complementando-os, ou até mesmo melhorando-os.
 * As operações no devem ser feitas por meio de strings SQL.
 */
@Service
public class ProdutoModel implements Produtos {

    @Autowired
    private NativeScriptService nativeScriptService;

    public Object getAllProducts() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            List<Map<String, Object>> listMap = new ArrayList<>();

            //Construção da string SQL
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM produtos;");

            //Abertura da conexão com o banco e abertura da PreparedStatement para comunicação
            connection = nativeScriptService.getConectionDb();
            preparedStatement = nativeScriptService.getPreparedStatementDb(sql.toString(), connection);

            //Conversão e retorno das informações
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Map<String,Object> map = new HashMap<>();
                map.put("id", rs.getObject("id"));
                map.put("nome", rs.getObject("nome"));
                map.put("preco", rs.getObject("preco"));
                map.put("defeitos", rs.getObject("defeitos"));
                map.put("quantidades", rs.getObject("quantidades"));
                listMap.add(map);
            }
            return listMap;
        } catch (SQLException e) {
            System.out.println("Erro ao consultar produtos: " + e.getMessage());
            e.printStackTrace();
            throw new SQLException("Erro ao consultar produtos no banco de dados.", e.getMessage());
        } finally {
            //Fechamento das conexões
            connection.close();
            preparedStatement.close();
        }
    }

    public void insertProduct(Map<String, Object> product) throws SQLException {
        try {
            // Monta a consulta SQL de inserção
            String sql = "INSERT INTO produtos (nome, quantidades, defeitos, preco) VALUES (?, ?, ?, ?)";
            
            // Obtém uma conexão com o banco de dados
            Connection connection = nativeScriptService.getConectionDb();
            
            // Cria um PreparedStatement com a consulta SQL
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            // Define os valores dos parâmetros da consulta
            preparedStatement.setString(1, (String) product.get("nome"));
            preparedStatement.setInt(2, (int) product.get("quantidades"));
            preparedStatement.setInt(3, (int) product.get("defeitos"));

            // Converte o Double para BigDecimal
            Double precoDouble = (Double) product.get("preco");
            BigDecimal preco = BigDecimal.valueOf(precoDouble);
            preparedStatement.setBigDecimal(4, preco);
            
            // Executa a consulta
            preparedStatement.executeUpdate();
            
            // Fecha a conexão
            preparedStatement.close();
            connection.close();
            
            System.out.println("Produto inserido com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao inserir produto: " + e.getMessage());
            e.printStackTrace();
            throw new SQLException("Erro ao inserir produto no banco de dados.", e.getMessage());
        }
    }

}
