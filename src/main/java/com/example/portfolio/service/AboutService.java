package com.example.portfolio.service;



import com.example.portfolio.entity.About;
import com.example.portfolio.repository.AboutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class AboutService {

    @Autowired
    private AboutRepository aboutRepository;

    // Get all projects
    public List<About> getAllAbout() {
        return aboutRepository.findAll();
    }

    // Get a project by ID
    public About getAboutById(UUID id) {
        return aboutRepository.findById(id).orElse(null);
    }

    // Add a new project
    public About addAbout() throws IOException {
        return addAbout(null);
    }

    // Add a new project
    public About addAbout(String description) throws IOException {
        About about = new About();

        about.setDescription(description);



        return aboutRepository.save(about);
    }

    public About updateAbout(UUID id, String description) throws IOException {
        About existingAbout = getAboutById(id);
        if (existingAbout != null) {

            existingAbout.setDescription(description);



            return aboutRepository.save(existingAbout);
        }
        return null;
    }


    // Delete a project
    public boolean deleteAbout(UUID id) {
        if (aboutRepository.existsById(id)) {
            aboutRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
