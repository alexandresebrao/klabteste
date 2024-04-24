package com.example.demo.interfaces;

import java.sql.SQLException;
import java.util.Map;

//Comunicação com a camada de negócio da aplicação
public interface Produtos {

    public void insertProduct(Map<String, Object> product) throws SQLException;

    public Object getAllProducts() throws SQLException ;
    
    public Object getProductDetails(int id) throws SQLException;
    
    public void updateAvailableQuantity(int productId, int soldQuantity) throws SQLException;
}
