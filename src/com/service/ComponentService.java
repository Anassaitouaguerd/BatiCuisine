package com.service;

import com.model.Component;
import com.repository.ComponentRepository;

import java.util.List;

public class ComponentService {
    public void createComponent() {
    }

    public void updateComponent(Double vat , Long projectId) {
        new ComponentRepository().updateComponent(vat, projectId);
    }

    public void deleteComponent() {
    }

    public List<Object> getComponent(Long lastProjectId) {
        return new ComponentRepository().getComponent(lastProjectId);
    }

    public void getAllComponents() {
    }
}
