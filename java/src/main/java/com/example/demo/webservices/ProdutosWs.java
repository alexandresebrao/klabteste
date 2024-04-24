package com.example.demo.webservices;
import com.example.demo.interfaces.Produtos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    
    
}
