package com.enviro.assessment.grad001.thato_mokgotsi.service;

import com.enviro.assessment.grad001.thato_mokgotsi.dto.WasteCategoryRequest;
import com.enviro.assessment.grad001.thato_mokgotsi.dto.WasteCategoryResponse;
import com.enviro.assessment.grad001.thato_mokgotsi.exception.ResourceNotFoundException;
import com.enviro.assessment.grad001.thato_mokgotsi.model.WasteCategory;
import com.enviro.assessment.grad001.thato_mokgotsi.repository.WasteCategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for managing waste categories
 */
@Service
public class WasteCategoryService {
    private final WasteCategoryRepository repository;

    public WasteCategoryService(WasteCategoryRepository repository) {
        this.repository = repository;
    }

    /**
     * Get waste categories page
     *
     * @param page the page number
     * @param size the page size
     * @return the page of waste categories
     */
    public Page<WasteCategoryResponse> getWasteCategoriesPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable).map(this::mapToResponse);
    }

    /**
     * Get waste categories sorted
     *
     * @param sortBy the sort by field
     * @param sortDir the sort direction
     * @return the sorted list of waste categories
     */
    public List<WasteCategoryResponse> getWasteCategoriesSorted(String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        return repository.findAll(sort).stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    /**
     * Get waste categories filtered by name and description
     *
     * @param name the name to filter by
     * @param description the description to filter by
     * @return the list of waste categories
     */
    public List<WasteCategoryResponse> getWasteCategoriesFiltered(String name, String description) {
        Specification<WasteCategory> spec = Specification.where(null);

        if (name != null && !name.isEmpty()) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + name + "%"));
        }

        if (description != null && !description.isEmpty()) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("description"), "%" + description + "%"));
        }

        return repository.findAll(spec).stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    /**
     * Get all waste categories
     *
     * @return the list of waste categories
     */
    public List<WasteCategoryResponse> getAllWasteCategories() {
        return repository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    /**
     * Create a new waste category
     *
     * @param request the request to create a waste category
     * @return the created waste category
     */
    public WasteCategoryResponse createWasteCategory(WasteCategoryRequest request) {
        WasteCategory wasteCategory = new WasteCategory();
        wasteCategory.setName(request.getName());
        wasteCategory.setDescription(request.getDescription());
        wasteCategory = repository.save(wasteCategory);
        return mapToResponse(wasteCategory);
    }

    /**
     * Get waste category by ID
     *
     * @param id the waste category ID
     * @return the waste category
     */
    public WasteCategoryResponse getWasteCategoryById(Long id) {
        WasteCategory wasteCategory = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("WasteCategory not found"));
        return mapToResponse(wasteCategory);
    }

    /**
     * Update waste category
     *
     * @param id the waste category ID
     * @param request the request to update a waste category
     * @return the updated waste category
     */
    public WasteCategoryResponse updateWasteCategory(Long id, WasteCategoryRequest request) {
        WasteCategory wasteCategory = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("WasteCategory not found"));
        wasteCategory.setName(request.getName());
        wasteCategory.setDescription(request.getDescription());
        wasteCategory = repository.save(wasteCategory);
        return mapToResponse(wasteCategory);
    }

    /**
     * Delete waste category
     *
     * @param id the waste category ID
     */
    public void deleteWasteCategory(Long id) {
        WasteCategory wasteCategory = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("WasteCategory not found"));
        repository.delete(wasteCategory);
    }


    /**
     * Search waste categories
     *
     * @param query the search query
     * @return the list of waste categories
     */
    public List<WasteCategoryResponse> searchWasteCategories(String query) {
        Specification<WasteCategory> spec = Specification.where(null);

        if (query != null && !query.isEmpty()) {
            spec = spec.and((root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.or(
                            criteriaBuilder.like(root.get("name"), "%" + query + "%"),
                            criteriaBuilder.like(root.get("description"), "%" + query + "%")
                    )
            );
        }

        return repository.findAll(spec).stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    /**
     * Map waste category to response
     *
     * @param wasteCategory the waste category
     * @return the waste category response
     */
    private WasteCategoryResponse mapToResponse(WasteCategory wasteCategory) {
        WasteCategoryResponse response = new WasteCategoryResponse();
        response.setId(wasteCategory.getId());
        response.setName(wasteCategory.getName());
        response.setDescription(wasteCategory.getDescription());
        return response;
    }
}