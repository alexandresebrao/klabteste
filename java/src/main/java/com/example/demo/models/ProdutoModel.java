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
    
    @Override
    public Object getProductDetails(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            String sql = "SELECT * FROM produtos WHERE id = ?";
            connection = nativeScriptService.getConectionDb();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                Map<String, Object> productDetails = new HashMap<>();
                productDetails.put("id", rs.getObject("id"));
                productDetails.put("nome", rs.getObject("nome"));
                productDetails.put("preco", rs.getObject("preco"));
                productDetails.put("defeitos", rs.getObject("defeitos"));
                productDetails.put("quantidades", rs.getObject("quantidades"));
                return productDetails;
            } else {
                return null; 
            }
        } catch (SQLException e) {
            System.out.println("Erro ao obter detalhes do produto: " + e.getMessage());
            e.printStackTrace();
            throw new SQLException("Erro ao obter detalhes do produto no banco de dados.", e.getMessage());
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
       
    @Override
    public void updateAvailableQuantity(int productId, int soldQuantity) throws SQLException {
        try {
            String sql = "UPDATE produtos SET quantidades = quantidades - ? WHERE id = ?";
            Connection connection = nativeScriptService.getConectionDb();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, soldQuantity);
            preparedStatement.setInt(2, productId);

            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

            System.out.println("Quantidade disponível para venda atualizada com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar quantidade disponível para venda: " + e.getMessage());
            throw new SQLException("Erro ao atualizar quantidade disponível para venda no banco de dados.", e.getMessage());
        }
    }
    
    @Override
    public void updateProductDetails(int productId, BigDecimal newPrice, int newDefects) {
        try {
            // Verifica se o novo preço é menor que o preço atual
            BigDecimal currentPrice = getProductPrice(productId);
            if (newPrice.compareTo(currentPrice) < 0) {
                throw new IllegalArgumentException("O novo preço deve ser maior ou igual ao preço atual.");
            }
            
            // Atualiza a quantidade com defeito e o preço
            String sql = "UPDATE produtos SET preco = ?, defeitos = ? WHERE id = ?";
            Connection connection = nativeScriptService.getConectionDb();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setBigDecimal(1, newPrice);
            preparedStatement.setInt(2, newDefects);
            preparedStatement.setInt(3, productId);

            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

            System.out.println("Detalhes do produto atualizados com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar detalhes do produto: " + e.getMessage());
            throw new RuntimeException("Erro ao atualizar detalhes do produto no banco de dados.", e);
        }
    }

    
    @Override
    public BigDecimal getProductPrice(int productId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            String sql = "SELECT preco FROM produtos WHERE id = ?";
            connection = nativeScriptService.getConectionDb();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, productId);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getBigDecimal("preco");
            } else {
                throw new IllegalArgumentException("Produto não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao obter preço do produto: " + e.getMessage());
            throw new RuntimeException("Erro ao obter preço do produto no banco de dados.", e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }

    

}
