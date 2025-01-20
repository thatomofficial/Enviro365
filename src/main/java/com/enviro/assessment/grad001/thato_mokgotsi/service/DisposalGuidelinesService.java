package com.enviro.assessment.grad001.thato_mokgotsi.service;

import com.enviro.assessment.grad001.thato_mokgotsi.dto.DisposalGuidelinesRequest;
import com.enviro.assessment.grad001.thato_mokgotsi.dto.DisposalGuidelinesResponse;
import com.enviro.assessment.grad001.thato_mokgotsi.exception.ResourceNotFoundException;
import com.enviro.assessment.grad001.thato_mokgotsi.model.DisposalGuidelines;
import com.enviro.assessment.grad001.thato_mokgotsi.repository.DisposalGuidelinesRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for managing Disposal Guidelines
 */
@Service
public class DisposalGuidelinesService {
    private final DisposalGuidelinesRepository repository;

    public DisposalGuidelinesService(DisposalGuidelinesRepository repository) {
        this.repository = repository;
    }

    /**
     * Create a base specification
     *
     * @return the base specification
     */
    private Specification<DisposalGuidelines> createSpecification() {
        return Specification.where(null);
    }

    /**
     * Get disposal guidelines page
     *
     * @param page the page number
     * @param size the page size
     * @return the page of disposal guidelines
     */
    public Page<DisposalGuidelinesResponse> getDisposalGuidelinesPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable).map(this::mapToResponse);
    }

    /**
     * Get disposal guidelines sorted
     *
     * @param sortBy the sort by field
     * @param sortDir the sort direction
     * @return the sorted list of disposal guidelines
     */
    public List<DisposalGuidelinesResponse> getDisposalGuidelinesSorted(String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        return repository.findAll(sort).stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    /**
     * Get disposal guidelines filtered
     *
     * @param guideline the guideline filter
     * @return the filtered list of disposal guidelines
     */
    public List<DisposalGuidelinesResponse> getDisposalGuidelinesFiltered(String guideline) {
        Specification<DisposalGuidelines> spec = createSpecification();

        if (guideline != null && !guideline.isEmpty()) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("guideline"), "%" + guideline + "%"));
        }

        return repository.findAll(spec).stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    /**
     * Get all disposal guidelines
     *
     * @return the list of disposal guidelines
     */
    public List<DisposalGuidelinesResponse> getAllDisposalGuidelines() {
        return repository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    /**
     * Get disposal guideline by id
     *
     * @param id the id of the disposal guideline
     * @return the disposal guideline
     */
    public DisposalGuidelinesResponse getDisposalGuidelineById(Long id) {
        DisposalGuidelines guideline = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("DisposalGuideline not found"));
        return mapToResponse(guideline);
    }

    /**
     * Create a new disposal guideline
     *
     * @param request the request to create a disposal guideline
     * @return the created disposal guideline
     */
    public DisposalGuidelinesResponse createDisposalGuideline(DisposalGuidelinesRequest request) {
        DisposalGuidelines guideline = new DisposalGuidelines();
        guideline.setGuideline(request.getGuideline());
        guideline = repository.save(guideline);
        return mapToResponse(guideline);
    }

    /**
     * Update disposal guideline
     *
     * @param id the id of the disposal guideline
     * @param request the request to update the disposal guideline
     * @return the updated disposal guideline
     */
    public DisposalGuidelinesResponse updateDisposalGuideline(Long id, DisposalGuidelinesRequest request) {
        DisposalGuidelines guideline = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("DisposalGuideline not found"));
        guideline.setGuideline(request.getGuideline());
        guideline = repository.save(guideline);
        return mapToResponse(guideline);
    }

    /**
     * Delete disposal guideline
     *
     * @param id the id of the disposal guideline
     */
    public void deleteDisposalGuideline(Long id) {
        DisposalGuidelines guideline = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("DisposalGuideline not found"));
        repository.delete(guideline);
    }

    /**
     * Search disposal guidelines by query.
     *
     * @param query the search query
     * @return the list of disposal guidelines matching the query
     */
    public List<DisposalGuidelinesResponse> searchDisposalGuidelines(String query) {
        Specification<DisposalGuidelines> spec = Specification.where(null);

        if (query != null && !query.isEmpty()) {
            spec = spec.and((root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.like(root.get("guideline"), "%" + query + "%"));
        }

        return repository.findAll(spec).stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    /**
     * Map disposal guideline to response
     *
     * @param guideline the disposal guideline
     * @return the disposal guideline response
     */
    private DisposalGuidelinesResponse mapToResponse(DisposalGuidelines guideline) {
        DisposalGuidelinesResponse response = new DisposalGuidelinesResponse();
        response.setId(guideline.getId());
        response.setGuideline(guideline.getGuideline());
        return response;
    }
}