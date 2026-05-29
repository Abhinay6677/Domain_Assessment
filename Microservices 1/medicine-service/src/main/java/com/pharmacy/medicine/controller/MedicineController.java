package com.pharmacy.medicine.controller;

import com.pharmacy.medicine.entity.Medicine;
import com.pharmacy.medicine.repository.MedicineRepository;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/medicines")

public class MedicineController {
	private final MedicineRepository repository;
	
	public MedicineController(MedicineRepository repository) {
		this.repository = repository;
	}
	
	@PostConstruct
	public void initData() {
		repository.save(new Medicine("M1", "Paracetamol", 150, 10.0));
		repository.save(new Medicine("M2", "Glimin", 75, 15.5));
	}
	
	@GetMapping 
	public List<Medicine> getAll(){
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public Medicine getById(@PathVariable String id) {
		return repository.findById(id).orElseThrow(() -> new RuntimeException("Medicine Not found"));
	}
}
