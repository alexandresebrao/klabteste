package com.example.demo.webservices;

import com.example.demo.interfaces.Produtos;
import com.example.demo.interfaces.Vendas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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
            
            // A inserção da venda foi bem-sucedida, agora atualize a quantidade disponível para venda
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
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProductDetails(@PathVariable int id, @RequestBody Map<String, Object> productDetails) {
        try {
            // Verifica se o produto existe
            Object existingProduct = produtos.getProductDetails(id);
            if (existingProduct == null) {
                return ResponseEntity.notFound().build();
            }

            // Verifica se os novos valores foram fornecidos
            if (!productDetails.containsKey("preco") || !productDetails.containsKey("defeitos")) {
                return ResponseEntity.badRequest().body("Os novos valores de preço e quantidade com defeito são necessários.");
            }

            // Extrai os novos valores
            BigDecimal preco = new BigDecimal((double) productDetails.get("preco"));
            int defeitos = (int) productDetails.get("defeitos");

            // Atualiza o preço e a quantidade com defeito do produto no banco de dados
            produtos.updateProductDetails(id, preco, defeitos);

            // Retorna uma resposta de sucesso
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Detalhes do produto atualizados com sucesso.");
            return ResponseEntity.ok().body(response);
        } catch (SQLException e) {
            // Em caso de erro, retorna uma resposta de erro interno do servidor
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Erro ao atualizar os detalhes do produto: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping()
    public ResponseEntity<Object> getAllSales() {
        try {
            return ResponseEntity.ok(vendas.getAllSales());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Deu erro GET");
        }
    }

}
