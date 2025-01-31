package com.example.portfolio.service;

import com.example.portfolio.entity.Project;
import com.example.portfolio.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    // Get all projects
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    // Get a project by ID
    public Project getProjectById(UUID id) {
        return projectRepository.findById(id).orElse(null);
    }

    // Add a new project
    public Project addProject() throws IOException {
        return addProject(null, null, null);
    }

    // Add a new project
    public Project addProject(String name, MultipartFile image, String description) throws IOException {
        Project project = new Project();
        project.setName(name);
        project.setDescription(description);

        if (image != null && !image.isEmpty()) {
            project.setImage(image.getBytes());
        }

        return projectRepository.save(project);
    }

    public Project updateProject(UUID id, String name, MultipartFile image, String description) throws IOException {
        Project existingProject = getProjectById(id);
        if (existingProject != null) {
            existingProject.setName(name);
            existingProject.setDescription(description);

            if (image != null && !image.isEmpty()) {
                existingProject.setImage(image.getBytes());
            }

            return projectRepository.save(existingProject);
        }
        return null;
    }


    // Delete a project
    public boolean deleteProject(UUID id) {
        if (projectRepository.existsById(id)) {
            projectRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
