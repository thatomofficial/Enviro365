package com.enviro.assessment.grad001.thato_mokgotsi.controller;

import com.enviro.assessment.grad001.thato_mokgotsi.dto.DisposalGuidelinesRequest;
import com.enviro.assessment.grad001.thato_mokgotsi.dto.DisposalGuidelinesResponse;
import com.enviro.assessment.grad001.thato_mokgotsi.service.DisposalGuidelinesService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing disposal guidelines.
 */
@RestController
@RequestMapping("/api/v1/disposal-guidelines")
public class DisposalGuidelinesController {

    private final DisposalGuidelinesService disposalGuidelinesService;

    public DisposalGuidelinesController(DisposalGuidelinesService disposalGuidelinesService) {
        this.disposalGuidelinesService = disposalGuidelinesService;
    }

    /**
     * GET /api/v1/disposal-guidelines/page : Get disposal guidelines page.
     *
     * @param page the page number
     * @param size the page size
     * @return the ResponseEntity with status 200 (OK) and the page of disposal guidelines
     */
    @GetMapping("/page")
    public ResponseEntity<Page<DisposalGuidelinesResponse>> getDisposalGuidelinesPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(disposalGuidelinesService.getDisposalGuidelinesPage(page, size));
    }

    /**
     * GET /api/v1/disposal-guidelines/sorted : Get disposal guidelines sorted.
     *
     * @param sortBy the sort by field
     * @param sortDir the sort direction
     * @return the ResponseEntity with status 200 (OK) and the sorted list of disposal guidelines
     */
    @GetMapping("/sorted")
    public ResponseEntity<List<DisposalGuidelinesResponse>> getDisposalGuidelinesSorted(
            @RequestParam(defaultValue = "guideline") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        return ResponseEntity.ok(disposalGuidelinesService.getDisposalGuidelinesSorted(sortBy, sortDir));
    }

    /**
     * GET /api/v1/disposal-guidelines/filter : Get disposal guidelines filtered.
     *
     * @param guideline the guideline filter
     * @return the ResponseEntity with status 200 (OK) and the filtered list of disposal guidelines
     */
    @GetMapping("/filter")
    public ResponseEntity<List<DisposalGuidelinesResponse>> getDisposalGuidelinesFiltered(
            @RequestParam(required = false) String guideline) {
        return ResponseEntity.ok(disposalGuidelinesService.getDisposalGuidelinesFiltered(guideline));
    }

    @GetMapping("/search")
    public ResponseEntity<List<DisposalGuidelinesResponse>> searchDisposalGuidelines(
            @RequestParam(required = false) String search) {
        return ResponseEntity.ok(disposalGuidelinesService.searchDisposalGuidelines(search));
    }

    /**
     * GET /api/v1/disposal-guidelines : Get all disposal guidelines.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of disposal guidelines
     */
    @GetMapping
    public ResponseEntity<List<DisposalGuidelinesResponse>> getAllDisposalGuidelines() {
        return ResponseEntity.ok(disposalGuidelinesService.getAllDisposalGuidelines());
    }

    /**
     * GET /api/v1/disposal-guidelines/{id} : Get disposal guideline by id.
     *
     * @param id the id of the disposal guideline
     * @return the ResponseEntity with status 200 (OK) and the disposal guideline
     */
    @GetMapping("/{id}")
    public ResponseEntity<DisposalGuidelinesResponse> getDisposalGuidelineById(@PathVariable Long id) {
        return ResponseEntity.ok(disposalGuidelinesService.getDisposalGuidelineById(id));
    }

    /**
     * POST /api/v1/disposal-guidelines : Create a new disposal guideline.
     *
     * @param request the disposal guideline request
     * @return the ResponseEntity with status 200 (OK) and the created disposal guideline
     */
    @PostMapping
    public ResponseEntity<DisposalGuidelinesResponse> createDisposalGuideline(@RequestBody DisposalGuidelinesRequest request) {
        return ResponseEntity.ok(disposalGuidelinesService.createDisposalGuideline(request));
    }

    /**
     * PUT /api/v1/disposal-guidelines/{id} : Update disposal guideline by id.
     *
     * @param id the id of the disposal guideline
     * @param request the disposal guideline request
     * @return the ResponseEntity with status 200 (OK) and the updated disposal guideline
     */
    @PutMapping("/{id}")
    public ResponseEntity<DisposalGuidelinesResponse> updateDisposalGuideline(@PathVariable Long id, @Valid @RequestBody DisposalGuidelinesRequest request) {
        return ResponseEntity.ok(disposalGuidelinesService.updateDisposalGuideline(id, request));
    }

    /**
     * DELETE /api/v1/disposal-guidelines/{id} : Delete disposal guideline by id.
     *
     * @param id the id of the disposal guideline
     * @return the ResponseEntity with status 204 (NO_CONTENT)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDisposalGuideline(@PathVariable Long id) {
        disposalGuidelinesService.deleteDisposalGuideline(id);
        return ResponseEntity.noContent().build();
    }
}