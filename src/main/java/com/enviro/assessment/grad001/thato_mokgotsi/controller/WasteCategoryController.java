package com.enviro.assessment.grad001.thato_mokgotsi.controller;

import com.enviro.assessment.grad001.thato_mokgotsi.dto.WasteCategoryRequest;
import com.enviro.assessment.grad001.thato_mokgotsi.model.WasteCategory;
import com.enviro.assessment.grad001.thato_mokgotsi.service.WasteCategoryService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/waste-categories")
public class WasteCategoryController {

    private final WasteCategoryService wasteCategoryService;

    public WasteCategoryController(WasteCategoryService wasteCategoryService) {
        this.wasteCategoryService = wasteCategoryService;
    }

    @GetMapping("/page")
    public ResponseEntity<Page<WasteCategory>> getWasteCategoriesPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(wasteCategoryService.getWasteCategoriesPage(page, size));
    }

    @GetMapping("/sorted")
    public ResponseEntity<List<WasteCategory>> getWasteCategoriesSorted(
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        return ResponseEntity.ok(wasteCategoryService.getWasteCategoriesSorted(sortBy, sortDir));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<WasteCategory>> getWasteCategoriesFiltered(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description) {
        return ResponseEntity.ok(wasteCategoryService.getWasteCategoriesFiltered(name, description));
    }

    @GetMapping("/search")
    public ResponseEntity<List<WasteCategory>> searchWasteCategories(
            @RequestParam(required = false) String query) {
        return ResponseEntity.ok(wasteCategoryService.searchWasteCategories(query));
    }

    @GetMapping
    public ResponseEntity<List<WasteCategory>> getAllWasteCategories() {
        return ResponseEntity.ok(wasteCategoryService.getAllWasteCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WasteCategory> getWasteCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(wasteCategoryService.getWasteCategoryById(id));
    }

    @PostMapping
    public ResponseEntity<WasteCategory> createWasteCategory(@Valid @RequestBody WasteCategoryRequest request) {
        return ResponseEntity.ok(wasteCategoryService.createWasteCategory(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WasteCategory> updateWasteCategory(@PathVariable Long id, @Valid @RequestBody WasteCategoryRequest request) {
        return ResponseEntity.ok(wasteCategoryService.updateWasteCategory(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWasteCategory(@PathVariable Long id) {
        wasteCategoryService.deleteWasteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
