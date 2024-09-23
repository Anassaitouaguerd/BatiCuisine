package com.interfaces;

import com.model.Project;

public interface ProjectInterface {

        public long createProject(String name , Double surface , Long clientId);

        public void updateProject(Double totalCost, Long projectId);

        public void deleteProject();

        public Project getProject(Long lastProjectId);

        public void getAllProjects();
}
