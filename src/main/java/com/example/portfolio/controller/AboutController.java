package com.example.portfolio.controller;

import com.example.portfolio.entity.About;
import com.example.portfolio.service.AboutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/about")
public class AboutController {

    @Autowired
    private AboutService aboutService;

    // Get all projects
    @GetMapping
    public List<About> getAllAbout() {
        return aboutService.getAllAbout();
    }

    // Get a project by ID
    @GetMapping("/{id}")
    public ResponseEntity<About> getProjectById(@PathVariable UUID id) {
        About about = aboutService.getAboutById(id);
        if (about != null) {
            return ResponseEntity.ok(about);
        }
        return ResponseEntity.notFound().build();
    }

    // Add a new project
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> addProject(
            @RequestParam("description") String description) { // Fix here, moved '{' to the correct place
        try {
            aboutService.addAbout(description);
            return ResponseEntity.ok("About added successfully");
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Error saving project: " + e.getMessage());
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateProject(
            @PathVariable UUID id,
            @RequestParam("description") String description) { // Fix here, moved '{' to the correct place
        try {
            About updatedAbout = aboutService.updateAbout(id, description);
            if (updatedAbout != null) {
                return ResponseEntity.ok("About updated successfully");
            }
            return ResponseEntity.notFound().build();
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Error updating About: " + e.getMessage());
        }
    }

    // Delete a project
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAbout(@PathVariable UUID id) {
        boolean isDeleted = aboutService.deleteAbout(id);
        if (isDeleted) {
            return ResponseEntity.ok("About deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }
}
