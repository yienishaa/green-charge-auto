package com.yorku.green_charge_auto.controller;

import com.yorku.green_charge_auto.model.Vehicle;
import com.yorku.green_charge_auto.repository.VehicleRepository;
import com.yorku.green_charge_auto.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class S3Controller {

        @Autowired
        private S3Service s3Service;

        @Autowired
        private VehicleRepository vehicleRepository;

        @PostMapping(value = "/products", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public ResponseEntity<Vehicle> uploadProduct(
                @RequestParam("brand") String brand,
                @RequestParam("model") String model,
                @RequestParam("description") String description,
                @RequestParam("mileage") String mileage,
                @RequestParam("manufactured_year") String manufacturedYear,
                @RequestParam("hasBeenInAccident") String hasBeenInAccident,
                @RequestParam("price") String price,
                @RequestParam("quantity") String quantity,
                @RequestParam("image") MultipartFile imageFile) throws IOException {

            System.out.println("Admin page");

            // Upload to S3
            String imageUrl = s3Service.uploadFile(imageFile);

            // Save product with S3 URL
            Vehicle product = new Vehicle();
            product.setBrand(brand);
            product.setModel(model);
            product.setDescription(description);
            product.setMileage(Double.parseDouble(mileage));
            product.setManufacturedYear(Integer.parseInt(manufacturedYear));
            product.setHasBeenInAccident(Boolean.parseBoolean(hasBeenInAccident));
            product.setPrice(Double.parseDouble(price));
            product.setQuantity(Integer.parseInt(quantity));
            product.setImage(imageUrl); // <-- store the full S3 URL

            Vehicle saved = vehicleRepository.save(product);

            return ResponseEntity.ok(saved);
    }
}
