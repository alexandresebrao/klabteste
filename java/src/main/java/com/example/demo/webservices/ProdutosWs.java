package com.example.demo.webservices;
import com.example.demo.interfaces.Produtos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/produtos")
public class ProdutosWs {

    @Autowired
    private Produtos produtos;

    @GetMapping()
    public ResponseEntity<Object> getAllProducts() {
        try {
            return ResponseEntity.ok(produtos.getAllProducts());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Deu erro GET");
        }
    }

    @PostMapping()
    public ResponseEntity<String> createProduct(@RequestBody Map<String, Object> product) {
        try {
            produtos.insertProduct(product);
            return ResponseEntity.ok("Produto criado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Deu erro POST");
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductDetails(@PathVariable("id") int id) {
        try {
            Object productDetails = produtos.getProductDetails(id);
            if (productDetails != null) {
                return ResponseEntity.ok(productDetails);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao obter os detalhes do produto.");
        }
    }
    
    @PutMapping("/{id}/quantidade")
    public ResponseEntity<?> updateAvailableQuantity(@PathVariable int id, @RequestParam int quantidade) {
        try {
            produtos.updateAvailableQuantity(id, quantidade);
            return ResponseEntity.ok().body("Quantidade disponível atualizada com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Erro ao atualizar a quantidade disponível: " + e.getMessage());
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
            Double precoValue = Double.valueOf(productDetails.get("preco").toString());
            BigDecimal preco = BigDecimal.valueOf(precoValue);
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
    
    
}
