package com.example.portfolio.controller;

import com.example.portfolio.entity.Icon;
import com.example.portfolio.service.IconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/icon")
public class IconController {

    @Autowired
    private IconService iconService;

    // Get all icons
    @GetMapping
    public List<Icon> getAllIcons() {
        return iconService.getAllIcons();
    }

    // Get an icon by ID
    @GetMapping("/{id}")
    public ResponseEntity<Icon> getIconById(@PathVariable UUID id) {
        Icon icon = iconService.getIconById(id);
        if (icon != null) {
            return ResponseEntity.ok(icon);
        }
        return ResponseEntity.notFound().build();
    }

    // Add a new icon
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> addIcon(
            @RequestParam("icon") String icon,
            @RequestParam(value = "img", required = false) MultipartFile img) {
        try {
            iconService.addIcon(icon, img);
            return ResponseEntity.ok("Icon added successfully");
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Error saving icon: " + e.getMessage());
        }
    }

    // Update an icon
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateIcon(
            @PathVariable UUID id,
            @RequestParam("icon") String icon,
            @RequestParam(value = "img", required = false) MultipartFile img) {
        try {
            Icon updatedIcon = iconService.updateIcon(id, icon, img);
            if (updatedIcon != null) {
                return ResponseEntity.ok("Icon updated successfully");
            }
            return ResponseEntity.notFound().build();
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Error updating icon: " + e.getMessage());
        }
    }

    // Delete an icon
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteIcon(@PathVariable UUID id) {
        boolean isDeleted = iconService.deleteIcon(id);
        if (isDeleted) {
            return ResponseEntity.ok("Icon deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }

    // Get icon image
    @GetMapping("/{id}/img")
    public ResponseEntity<byte[]> getIconImage(@PathVariable UUID id) {
        Icon icon = iconService.getIconById(id);
        if (icon != null && icon.getImg() != null) {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .body(icon.getImg());
        }
        return ResponseEntity.notFound().build();
    }
}
