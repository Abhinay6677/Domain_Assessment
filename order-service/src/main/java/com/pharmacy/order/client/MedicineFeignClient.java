package com.pharmacy.order.client;

import com.pharmacy.order.dto.MedicineDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "medicine-client", url = "${medicine.service.url}")
public interface MedicineFeignClient {
	@GetMapping("/medicines/{id}")
	MedicineDTO getMedicineById(@PathVariable("id") String id);

}
