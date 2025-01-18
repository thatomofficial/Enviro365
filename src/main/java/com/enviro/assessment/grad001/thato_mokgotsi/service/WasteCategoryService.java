package com.enviro.assessment.grad001.thato_mokgotsi.service;

import com.enviro.assessment.grad001.thato_mokgotsi.dto.WasteCategoryRequest;
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

@Service
public class WasteCategoryService {
    private final WasteCategoryRepository repository;

    public WasteCategoryService(WasteCategoryRepository repository) {
        this.repository = repository;
    }

    public Page<WasteCategory> getWasteCategoriesPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable);
    }

    public List<WasteCategory> getWasteCategoriesSorted(String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        return repository.findAll(sort);
    }

    public List<WasteCategory> getAllWasteCategories() {
        return repository.findAllWasteCategories();
    }

    public WasteCategory createWasteCategory(WasteCategoryRequest request) {
        WasteCategory wasteCategory = new WasteCategory();
        wasteCategory.setName(request.getName());
        wasteCategory.setDescription(request.getDescription());
        return repository.save(wasteCategory);
    }

    public WasteCategory getWasteCategoryById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("WasteCategory not found"));
    }

    public WasteCategory updateWasteCategory(Long id, WasteCategoryRequest request) {
        WasteCategory wasteCategory = getWasteCategoryById(id);
        wasteCategory.setName(request.getName());
        wasteCategory.setDescription(request.getDescription());
        return repository.save(wasteCategory);
    }

    public void deleteWasteCategory(Long id) {
        WasteCategory wasteCategory = getWasteCategoryById(id);
        repository.delete(wasteCategory);
    }

    public List<WasteCategory> getWasteCategoriesFiltered(String name, String description) {
        Specification<WasteCategory> spec = Specification.where(null);

        if (name != null && !name.isEmpty()) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + name + "%"));
        }

        if (description != null && !description.isEmpty()) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("description"), "%" + description + "%"));
        }

        return repository.findAll(spec);
    }


    public List<WasteCategory> searchWasteCategories(String query) {
        Specification<WasteCategory> spec = Specification.where(null);

        if (query != null && !query.isEmpty()) {
            spec = spec.and((root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.or(
                            criteriaBuilder.like(root.get("name"), "%" + query + "%"),
                            criteriaBuilder.like(root.get("description"), "%" + query + "%")
                    )
            );
        }

        return repository.findAll(spec);
    }


}
