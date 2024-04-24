package com.example.demo.webservices;

import com.example.demo.interfaces.Produtos;
import com.example.demo.interfaces.Vendas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/vendas")
public class VendasWs {

    private final Vendas vendas;
    private final Produtos produtos;

    @Autowired
    public VendasWs(Vendas vendas, Produtos produtos) {
        this.vendas = vendas;
        this.produtos = produtos;
    }

    @PostMapping()
    public ResponseEntity<Map<String, Object>> registerSale(@RequestBody Map<String, Object> sale) {
        try {
            vendas.registerSale(sale);
            
            // Chama o método para atualizar a quantidade disponível para venda
            int produtoId = (int) sale.get("produtoId");
            int quantidadeVendida = (int) sale.get("quantidades");
            produtos.updateAvailableQuantity(produtoId, quantidadeVendida);
            
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
