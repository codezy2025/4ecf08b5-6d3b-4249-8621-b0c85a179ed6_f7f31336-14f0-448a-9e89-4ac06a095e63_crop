package com.java.coreTemplate.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.java.coreTemplate.model.dto.MachineLearningModule;
import com.java.coreTemplate.service.MachineLearningModuleService;

import java.util.List;

@Tag(name = "Machine Learning Module API", description = "Operations for managing machine learning modules")
@RestController
@RequestMapping("/api/v1/machine-learning-module")
public class MachineLearningModuleController {

    private final MachineLearningModuleService service;

    public MachineLearningModuleController(MachineLearningModuleService service) {
        this.service = service;
    }

    @Operation(
        summary = "Create a new machine learning module",
        description = "Saves a new machine learning module entity to the database.",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "MachineLearningModule object to be created",
            required = true,
            content = @Content(schema = @Schema(implementation = MachineLearningModule.class))
        ),
        responses = {
            @ApiResponse(responseCode = "201", description = "Machine learning module created successfully",
                content = @Content(schema = @Schema(implementation = MachineLearningModule.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
        }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MachineLearningModule> create(@RequestBody MachineLearningModule entity) {
        MachineLearningModule saved = service.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @Operation(
        summary = "Get a machine learning module by ID",
        description = "Retrieves a machine learning module by its unique ID.",
        parameters = {
            @Parameter(name = "id", description = "Unique identifier of the machine learning module", required = true, example = "1")
        },
        responses = {
            @ApiResponse(responseCode = "200", description = "Machine learning module found",
                content = @Content(schema = @Schema(implementation = MachineLearningModule.class))),
            @ApiResponse(responseCode = "404", description = "Machine learning module not found")
        }
    )
    @GetMapping("/{id}")
    public ResponseEntity<MachineLearningModule> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
        summary = "Get all machine learning modules with pagination",
        description = "Retrieves a paginated list of all machine learning modules. Supports sorting and pagination.",
        parameters = {
            @Parameter(name = "page", description = "Page number (0-based)", example = "0"),
            @Parameter(name = "size", description = "Number of items per page", example = "10"),
            @Parameter(name = "sort", description = "Sorting field (e.g., name, createdAt)", example = "name,asc")
        },
        responses = {
            @ApiResponse(responseCode = "200", description = "List of machine learning modules retrieved successfully",
                content = @Content(schema = @Schema(implementation = Page.class)))
        }
    )
    @GetMapping
    public ResponseEntity<Page<MachineLearningModule>> getAll(
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC)
            Pageable pageable) {
        Page<MachineLearningModule> modules = service.findAll(pageable);
        return ResponseEntity.ok(modules);
    }

    @Operation(
        summary = "Update an existing machine learning module",
        description = "Updates the details of an existing machine learning module by ID.",
        parameters = {
            @Parameter(name = "id", description = "Unique identifier of the machine learning module to update", required = true, example = "1")
        },
        responses = {
            @ApiResponse(responseCode = "200", description = "Machine learning module updated successfully",
                content = @Content(schema = @Schema(implementation = MachineLearningModule.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input or module not found"),
            @ApiResponse(responseCode = "404", description = "Machine learning module not found")
        }
    )
    @PutMapping("/{id}")
    public ResponseEntity<MachineLearningModule> update(
            @PathVariable Long id,
            @RequestBody MachineLearningModule entity) {

        return service.findById(id)
                .map(existing -> {
                    entity.setId(id); // Ensure ID is preserved
                    MachineLearningModule updated = service.save(entity);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
        summary = "Delete a machine learning module by ID",
        description = "Deletes a machine learning module from the database.",
        parameters = {
            @Parameter(name = "id", description = "Unique identifier of the machine learning module to delete", required = true, example = "1")
        },
        responses = {
            @ApiResponse(responseCode = "204", description = "Machine learning module deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Machine learning module not found")
        }
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.findById(id).isPresent()) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
        summary = "Search machine learning modules by name (partial match)",
        description = "Finds all modules whose name contains the given search term (case-insensitive).",
        parameters = {
            @Parameter(name = "name", description = "Partial or full name to search for", example = "model")
        },
        responses = {
            @ApiResponse(responseCode = "200", description = "List of matching machine learning modules",
                content = @Content(schema = @Schema(implementation = List.class)))
        }
    )
    @GetMapping("/search")
    public ResponseEntity<List<MachineLearningModule>> searchByName(@RequestParam String name) {
        List<MachineLearningModule> results = service.findByNameContainingIgnoreCase(name);
        return ResponseEntity.ok(results);
    }
}