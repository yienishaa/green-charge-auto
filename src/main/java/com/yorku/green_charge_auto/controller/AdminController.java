package com.yorku.green_charge_auto.controller;

import com.yorku.green_charge_auto.model.Vehicle;
import com.yorku.green_charge_auto.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000")

@RestController
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    private VehicleRepository vehicleRepository;

    private static final String UPLOAD_DIR = "/Users/yienishaabeyratne/Desktop/";

    @PostMapping(value = "/products", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Vehicle> uploadProduct(
            @RequestParam("brand") String brand,
            @RequestParam("model") String model,
            @RequestParam("image") MultipartFile imageFile) throws IOException {
        System.out.println("Admin page");
        // Save file to local disk
        String filename = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
        Path filepath = Paths.get(UPLOAD_DIR, filename);
        Files.createDirectories(filepath.getParent()); // Ensure dir exists
        Files.copy(imageFile.getInputStream(), filepath, StandardCopyOption.REPLACE_EXISTING);

        // Save product with file path
        Vehicle product = new Vehicle();
        product.setBrand(brand);
        product.setModel(model);
        product.setImage(UPLOAD_DIR + filename);

        Vehicle saved = vehicleRepository.save(product);

        return ResponseEntity.ok(saved);
    }


}
