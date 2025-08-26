package com.java.coreTemplate.service;

import com.java.coreTemplate.exception.ResourceNotFoundException;
import com.java.coreTemplate.model.dto.MachineLearningModule;
import com.java.coreTemplate.repository.MachineLearningModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing MachineLearningModule entities.
 * Provides business logic for CRUD operations and queries.
 */
@Service
@Transactional(readOnly = true)
public class MachineLearningModuleService {

    private final MachineLearningModuleRepository repository;

    @Autowired
    public MachineLearningModuleService(MachineLearningModuleRepository repository) {
        this.repository = repository;
    }

    /**
     * Saves a new or updates an existing MachineLearningModule.
     *
     * @param entity the MachineLearningModule to save
     * @return the saved entity
     * @throws IllegalArgumentException if entity is null
     */
    @Transactional
    public MachineLearningModule save(MachineLearningModule entity) {
        if (entity == null) {
            throw new IllegalArgumentException("MachineLearningModule entity cannot be null");
        }
        return repository.save(entity);
    }

    /**
     * Finds a MachineLearningModule by its ID.
     *
     * @param id the ID of the module
     * @return Optional containing the module if found, empty otherwise
     */
    public Optional<MachineLearningModule> findById(Long id) {
        return repository.findById(id);
    }

    /**
     * Finds all active MachineLearningModules (where isActive = true).
     *
     * @return list of active modules
     */
    public List<MachineLearningModule> findAllActive() {
        return repository.findByIsActiveTrue();
    }

    /**
     * Finds all MachineLearningModules regardless of activation status.
     *
     * @return list of all modules
     */
    public List<MachineLearningModule> findAll() {
        return repository.findAll();
    }

    /**
     * Finds a MachineLearningModule by its unique name.
     *
     * @param name the name of the module
     * @return Optional containing the module if found, empty otherwise
     */
    public Optional<MachineLearningModule> findByName(String name) {
        return repository.findByName(name);
    }

    /**
     * Updates an existing MachineLearningModule.
     *
     * @param id the ID of the module to update
     * @param updatedEntity the updated entity data
     * @return the updated entity
     * @throws ResourceNotFoundException if module with given ID not found
     */
    @Transactional
    public MachineLearningModule update(Long id, MachineLearningModule updatedEntity) {
        return findById(id)
                .map(existing -> {
                    existing.setName(updatedEntity.getName());
                    existing.setDescription(updatedEntity.getDescription());
                    existing.setIsActive(updatedEntity.getIsActive());
                    existing.setVersion(updatedEntity.getVersion());
                    // Update other fields as needed
                    return save(existing);
                })
                .orElseThrow(() -> new ResourceNotFoundException(
                        "MachineLearningModule with id " + id + " not found"));
    }

    /**
     * Deletes a MachineLearningModule by ID.
     *
     * @param id the ID of the module to delete
     * @throws ResourceNotFoundException if module with given ID not found
     */
    @Transactional
    public void deleteById(Long id) {
        if (!findById(id).isPresent()) {
            throw new ResourceNotFoundException(
                    "MachineLearningModule with id " + id + " not found");
        }
        repository.deleteById(id);
    }

    /**
     * Checks if a MachineLearningModule with the given name exists.
     *
     * @param name the name to check
     * @return true if exists, false otherwise
     */
    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }

    /**
     * Checks if a MachineLearningModule with the given ID exists.
     *
     * @param id the ID to check
     * @return true if exists, false otherwise
     */
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }
}