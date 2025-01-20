package com.enviro.assessment.grad001.thato_mokgotsi.service;

import com.enviro.assessment.grad001.thato_mokgotsi.dto.RecyclingTipsRequest;
import com.enviro.assessment.grad001.thato_mokgotsi.dto.RecyclingTipsResponse;
import com.enviro.assessment.grad001.thato_mokgotsi.exception.ResourceNotFoundException;
import com.enviro.assessment.grad001.thato_mokgotsi.model.RecyclingTips;
import com.enviro.assessment.grad001.thato_mokgotsi.repository.RecyclingTipsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for managing recycling tips
 */
@Service
public class RecyclingTipsService {
    private final RecyclingTipsRepository repository;

    public RecyclingTipsService(RecyclingTipsRepository repository) {
        this.repository = repository;
    }

    /**
     * Create a base specification
     *
     * @return the base specification
     */
    private Specification<RecyclingTips> createSpecification() {
        return Specification.where(null);
    }

    /**
     * Get recycling tips page
     *
     * @param page the page number
     * @param size the page size
     * @return the page of recycling tips
     */
    public Page<RecyclingTipsResponse> getRecyclingTipsPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable).map(this::mapToResponse);
    }

    /**
     * Get recycling tips sorted
     *
     * @param sortBy the sort by field
     * @param sortDir the sort direction
     * @return the sorted list of recycling tips
     */
    public List<RecyclingTipsResponse> getRecyclingTipsSorted(String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        return repository.findAll(sort).stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    /**
     * Get recycling tips filtered by tip
     *
     * @param tip the tip filter
     * @return the filtered list of recycling tips
     */
    public List<RecyclingTipsResponse> getRecyclingTipsFiltered(String tip) {
        Specification<RecyclingTips> spec = createSpecification();

        if (tip != null && !tip.isEmpty()) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("tip"), "%" + tip + "%"));
        }

        return repository.findAll(spec).stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    /**
     * Get all recycling tips
     *
     * @return the list of recycling tips
     */
    public List<RecyclingTipsResponse> getAllRecyclingTips() {
        return repository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    /**
     * Get recycling tip by id
     *
     * @param id the id of the recycling tip
     * @return the recycling tip
     */
    public RecyclingTipsResponse getRecyclingTipById(Long id) {
        RecyclingTips tip = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("RecyclingTip not found"));
        return mapToResponse(tip);
    }

    /**
     * Create recycling tip
     *
     * @param request the recycling tip request
     * @return the created recycling tip
     */
    public RecyclingTipsResponse createRecyclingTip(RecyclingTipsRequest request) {
        RecyclingTips tip = new RecyclingTips();
        tip.setTip(request.getTip());
        tip = repository.save(tip);
        return mapToResponse(tip);
    }

    /**
     * Update recycling tip
     *
     * @param id the id of the recycling tip
     * @param request the recycling tip request
     * @return the updated recycling tip
     */
    public RecyclingTipsResponse updateRecyclingTip(Long id, RecyclingTipsRequest request) {
        RecyclingTips tip = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("RecyclingTip not found"));
        tip.setTip(request.getTip());
        tip = repository.save(tip);
        return mapToResponse(tip);
    }

    /**
     * Delete recycling tip
     *
     * @param id the id of the recycling tip
     */
    public void deleteRecyclingTip(Long id) {
        RecyclingTips tip = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("RecyclingTip not found"));
        repository.delete(tip);
    }

    public List<RecyclingTipsResponse> searchRecyclingTips(String query) {
        Specification<RecyclingTips> spec = Specification.where(null);

        if (query != null && !query.isEmpty()) {
            spec = spec.and((root, query1, criteriaBuilder) ->
                    criteriaBuilder.like(root.get("tip"), "%" + query + "%"));
        }

        return repository.findAll(spec).stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    /**
     * Map recycling tip to response
     *
     * @param tip the recycling tip
     * @return the recycling tip response
     */
    private RecyclingTipsResponse mapToResponse(RecyclingTips tip) {
        RecyclingTipsResponse response = new RecyclingTipsResponse();
        response.setId(tip.getId());
        response.setTip(tip.getTip());
        return response;
    }
}