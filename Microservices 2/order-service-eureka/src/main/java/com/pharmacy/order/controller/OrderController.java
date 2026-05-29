package com.pharmacy.order.controller;

import com.pharmacy.order.client.MedicineFeignClient;
import com.pharmacy.order.dto.MedicineDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
	private final MedicineFeignClient medicineClient;
	
	public OrderController(MedicineFeignClient medicineClient) {
		this.medicineClient = medicineClient;
	}
	
	@PostMapping("/place")
	public String placeOrder(@RequestParam String medicineId, @RequestParam int quantity) {
		try {
			MedicineDTO medicine = medicineClient.getMedicineById(medicineId);
			
			if (medicine.getStock() < quantity) {
				return "Order Rejected: Not enough stock for " + medicine.getName();
			}
			
			double totalCost = medicine.getPrice() * quantity;
			return "Order Processed! Purchased " + quantity + " units of " + medicine.getName() + ". Total: $" + totalCost;
		} catch (Exception e) {
			return "Order Error: Could not reach Medicine Service or medicine ID is invalid.";
		}
	}
}
