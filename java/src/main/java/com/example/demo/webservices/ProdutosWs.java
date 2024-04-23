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
    
    
    // Novo endpoint para obter os detalhes de um produto com um ID específico
    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductDetails(@PathVariable("id") int id) {
        try {
            // Implemente a lógica para recuperar os detalhes do produto com o ID fornecido
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
    
    
}
