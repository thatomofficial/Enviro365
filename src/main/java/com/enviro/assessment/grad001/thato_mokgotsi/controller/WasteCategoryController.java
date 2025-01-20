package com.enviro.assessment.grad001.thato_mokgotsi.controller;

import com.enviro.assessment.grad001.thato_mokgotsi.dto.WasteCategoryRequest;
import com.enviro.assessment.grad001.thato_mokgotsi.dto.WasteCategoryResponse;
import com.enviro.assessment.grad001.thato_mokgotsi.service.WasteCategoryService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing waste categories.
 */
@RestController
@RequestMapping("/api/v1/waste-categories")
public class WasteCategoryController {

    private final WasteCategoryService wasteCategoryService;

    public WasteCategoryController(WasteCategoryService wasteCategoryService) {
        this.wasteCategoryService = wasteCategoryService;
    }

    /**
     * GET /api/v1/waste-categories/page : Get waste categories page.
     *
     * @param page the page number
     * @param size the page size
     * @return the ResponseEntity with status 200 (OK) and the page of waste categories
     */
    @GetMapping("/page")
    public ResponseEntity<Page<WasteCategoryResponse>> getWasteCategoriesPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(wasteCategoryService.getWasteCategoriesPage(page, size));
    }

    /**
     * GET /api/v1/waste-categories/sorted : Get waste categories sorted.
     *
     * @param sortBy the sort by field
     * @param sortDir the sort direction
     * @return the ResponseEntity with status 200 (OK) and the sorted list of waste categories
     */
    @GetMapping("/sorted")
    public ResponseEntity<List<WasteCategoryResponse>> getWasteCategoriesSorted(
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        return ResponseEntity.ok(wasteCategoryService.getWasteCategoriesSorted(sortBy, sortDir));
    }

    /**
     * GET /api/v1/waste-categories/filter : Get waste categories filtered.
     *
     * @param name the name filter
     * @param description the description filter
     * @return the ResponseEntity with status 200 (OK) and the filtered list of waste categories
     */
    @GetMapping("/filter")
    public ResponseEntity<List<WasteCategoryResponse>> getWasteCategoriesFiltered(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description) {
        return ResponseEntity.ok(wasteCategoryService.getWasteCategoriesFiltered(name, description));
    }

    /**
     * GET /api/v1/waste-categories/search : Search waste categories.
     *
     * @param query the search query
     * @return the ResponseEntity with status 200 (OK) and the list of waste categories matching the query
     */
    @GetMapping("/search")
    public ResponseEntity<List<WasteCategoryResponse>> searchWasteCategories(
            @RequestParam(required = false) String query) {
        return ResponseEntity.ok(wasteCategoryService.searchWasteCategories(query));
    }

    /**
     * GET /api/v1/waste-categories : Get all waste categories.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of waste categories
     */
    @GetMapping
    public ResponseEntity<List<WasteCategoryResponse>> getAllWasteCategories() {
        return ResponseEntity.ok(wasteCategoryService.getAllWasteCategories());
    }

    /**
     * GET /api/v1/waste-categories/{id} : Get waste category by ID.
     *
     * @param id the ID of the waste category
     * @return the ResponseEntity with status 200 (OK) and the waste category
     */
    @GetMapping("/{id}")
    public ResponseEntity<WasteCategoryResponse> getWasteCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(wasteCategoryService.getWasteCategoryById(id));
    }

    /**
     * POST /api/v1/waste-categories : Create a new waste category.
     *
     * @param request the request to create a new waste category
     * @return the ResponseEntity with status 200 (OK) and the created waste category
     */
    @PostMapping
    public ResponseEntity<WasteCategoryResponse> createWasteCategory(@Valid @RequestBody WasteCategoryRequest request) {
        return ResponseEntity.ok(wasteCategoryService.createWasteCategory(request));
    }

    /**
     * PUT /api/v1/waste-categories/{id} : Update waste category by ID.
     *
     * @param id the ID of the waste category
     * @param request the request to update the waste category
     * @return the ResponseEntity with status 200 (OK) and the updated waste category
     */
    @PutMapping("/{id}")
    public ResponseEntity<WasteCategoryResponse> updateWasteCategory(@PathVariable Long id, @Valid @RequestBody WasteCategoryRequest request) {
        return ResponseEntity.ok(wasteCategoryService.updateWasteCategory(id, request));
    }

    /**
     * DELETE /api/v1/waste-categories/{id} : Delete waste category by ID.
     *
     * @param id the ID of the waste category
     * @return the ResponseEntity with status 204 (NO CONTENT)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWasteCategory(@PathVariable Long id) {
        wasteCategoryService.deleteWasteCategory(id);
        return ResponseEntity.noContent().build();
    }
}