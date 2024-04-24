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
import java.sql.SQLException;
import java.util.HashMap;
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

            // Converter o valor Double para BigDecimal
            Double totalVendaDouble = ((Number) sale.get("total_venda")).doubleValue();
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
}
