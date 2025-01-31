package com.example.portfolio.service;

import com.example.portfolio.entity.Icon;
import com.example.portfolio.repository.IconRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class IconService {

    @Autowired
    private IconRepository iconRepository;

    // Get all icons
    public List<Icon> getAllIcons() {
        return iconRepository.findAll();
    }

    // Get an icon by ID
    public Icon getIconById(UUID id) {
        return iconRepository.findById(id).orElse(null);
    }

    // Add a new icon
    public Icon addIcon(String icon, MultipartFile img) throws IOException {
        Icon newIcon = new Icon();
        newIcon.setIcon(icon);

        if (img != null && !img.isEmpty()) {
            newIcon.setImg(img.getBytes());
        }

        return iconRepository.save(newIcon);
    }

    // Update an icon
    public Icon updateIcon(UUID id, String icon, MultipartFile img) throws IOException {
        Icon existingIcon = getIconById(id);
        if (existingIcon != null) {
            existingIcon.setIcon(icon);

            if (img != null && !img.isEmpty()) {
                existingIcon.setImg(img.getBytes());
            }

            return iconRepository.save(existingIcon);
        }
        return null;
    }

    // Delete an icon
    public boolean deleteIcon(UUID id) {
        if (iconRepository.existsById(id)) {
            iconRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
