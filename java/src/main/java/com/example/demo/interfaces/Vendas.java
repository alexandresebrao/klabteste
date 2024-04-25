package com.example.demo.interfaces;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface Vendas {
	
	ResponseEntity<?> registerSale(Map<String, Object> sale);
	
	public Object getAllSales() throws SQLException ;
}
