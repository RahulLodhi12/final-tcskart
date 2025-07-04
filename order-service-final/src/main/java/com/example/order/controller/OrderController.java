package com.example.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.order.bean.OrderBean;
import com.example.order.entity.Orders;
import com.example.order.security.JwtUtil;
import com.example.order.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	OrderService services;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@Operation(summary = "Place Order")
	@PostMapping("/placeorder")
	public Orders PlaceOrder(@RequestHeader("Authorization") String authHeader,@RequestBody OrderBean orderdto)
	{
		  String token = authHeader.substring(7);
		  String email = jwtUtil.extractEmail(token);  
	      return  services.PlaceOrder(orderdto,email);
			
	}
	
	@Operation(summary = "Get All Orders By Email")
	@GetMapping("/users")
	 public List<Orders> getOrdersByEmail(@RequestHeader("Authorization") String authHeader) {
		String token = authHeader.substring(7);
		String email = jwtUtil.extractEmail(token);  
		return services.getOrdersByUserEmail(email);
	 }
	
	@Operation(summary = "Get All Orders")
	@GetMapping("/allorders")
	 public List<Orders> getAllOrders() {
	       return services.getallorders();
	       
	 }
	
	@Operation(summary = "Get My Cart")
	@GetMapping("/myorder")
    public ResponseEntity<?> getMyCart(@RequestHeader("Authorization") String authHeader) {

        String token = authHeader.substring(7); // Remove "Bearer "
        String email = jwtUtil.extractEmail(token);

        // Now use email as needed
        return ResponseEntity.ok("Fetching cart for: " + email);
    }
	
	@Operation(summary = "Change Status")
	@PatchMapping("/admin/updateStatus/{id}/{status}")
	public String changeStatus(@PathVariable Long id,@PathVariable String status)
	{
		return services.updateStatus(id, status);
	}
	
//	@GetMapping("/carttomoveorder/{email}/{address}")
//	public Orders carttoMoveOrder(@PathVariable String email,@PathVariable  String address)
//	{
//		return services.cartMoveToOrder(email, address);
//	}
	 

}

