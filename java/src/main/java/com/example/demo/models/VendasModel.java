package com.example.demo.models;

import com.example.demo.interfaces.Vendas;
import com.example.demo.services.NativeScriptService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@Service
public class VendasModel implements Vendas {

    @Autowired
    private NativeScriptService nativeScriptService;

    @Override
    public ResponseEntity<?> registerSale(Map<String, Object> sale) {
        try {
            String sql = "INSERT INTO vendas (produto_id, comprador, quantidades, total_venda) VALUES (?, ?, ?, ?)";
            Connection connection = nativeScriptService.getConectionDb();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, (int) sale.get("produtoId"));
            preparedStatement.setString(2, (String) sale.get("comprador"));
            preparedStatement.setInt(3, (int) sale.get("quantidades"));

            // Converter o valor para Double
            Number totalVendaNumber = (Number) sale.get("total_venda");
            double totalVendaDouble = totalVendaNumber.doubleValue();
            BigDecimal totalVenda = BigDecimal.valueOf(totalVendaDouble);
            preparedStatement.setBigDecimal(4, totalVenda);

            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Venda cadastrada com sucesso.");
            return ResponseEntity.ok().body(response);

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar venda: " + e.getMessage());
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Erro ao cadastrar venda no banco de dados.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    
    public Object getAllSales() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            List<Map<String, Object>> listMap = new ArrayList<>();

            //Construção da string SQL
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM vendas;");

            //Abertura da conexão com o banco e abertura da PreparedStatement para comunicação
            connection = nativeScriptService.getConectionDb();
            preparedStatement = nativeScriptService.getPreparedStatementDb(sql.toString(), connection);

            //Conversão e retorno das informações
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Map<String,Object> map = new HashMap<>();
                map.put("id", rs.getObject("id"));
                map.put("comprador", rs.getObject("comprador"));
                map.put("produto_id", rs.getObject("produto_id"));
                map.put("quantidades", rs.getObject("quantidades"));
                map.put("total_venda", rs.getObject("total_venda"));
                listMap.add(map);
            }
            return listMap;
        } catch (SQLException e) {
            System.out.println("Erro ao consultar o relatorio de vendas: " + e.getMessage());
            e.printStackTrace();
            throw new SQLException("Erro ao consultar o relatorio de vendas no banco de dados.", e.getMessage());
        } finally {
            //Fechamento das conexões
            connection.close();
            preparedStatement.close();
        }
    }
    
    
    
}
