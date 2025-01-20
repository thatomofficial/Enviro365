package com.enviro.assessment.grad001.thato_mokgotsi.controller;

import com.enviro.assessment.grad001.thato_mokgotsi.dto.RecyclingTipsRequest;
import com.enviro.assessment.grad001.thato_mokgotsi.dto.RecyclingTipsResponse;
import com.enviro.assessment.grad001.thato_mokgotsi.service.RecyclingTipsService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing recycling tips.
 */
@RestController
@RequestMapping("/api/v1/recycling-tips")
public class RecyclingTipsController {

    private final RecyclingTipsService recyclingTipsService;

    public RecyclingTipsController(RecyclingTipsService recyclingTipsService) {
        this.recyclingTipsService = recyclingTipsService;
    }

    /**
     * GET /api/v1/recycling-tips/page : Get recycling tips page.
     *
     * @param page the page number
     * @param size the page size
     * @return the ResponseEntity with status 200 (OK) and the page of recycling tips
     */
    @GetMapping("/page")
    public ResponseEntity<Page<RecyclingTipsResponse>> getRecyclingTipsPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(recyclingTipsService.getRecyclingTipsPage(page, size));
    }

    /**
     * GET /api/v1/recycling-tips/sorted : Get recycling tips sorted.
     *
     * @param sortBy the sort by field
     * @param sortDir the sort direction
     * @return the ResponseEntity with status 200 (OK) and the sorted list of recycling tips
     */
    @GetMapping("/sorted")
    public ResponseEntity<List<RecyclingTipsResponse>> getRecyclingTipsSorted(
            @RequestParam(defaultValue = "tip") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        return ResponseEntity.ok(recyclingTipsService.getRecyclingTipsSorted(sortBy, sortDir));
    }

    /**
     * GET /api/v1/recycling-tips/filter : Get recycling tips filtered.
     *
     * @param tip the tip filter
     * @return the ResponseEntity with status 200 (OK) and the filtered list of recycling tips
     */
    @GetMapping("/filter")
    public ResponseEntity<List<RecyclingTipsResponse>> getRecyclingTipsFiltered(
            @RequestParam(required = false) String tip) {
        return ResponseEntity.ok(recyclingTipsService.getRecyclingTipsFiltered(tip));
    }

    @GetMapping("/search")
    public ResponseEntity<List<RecyclingTipsResponse>> searchRecyclingTips(
            @RequestParam(required = false) String search) {
        return ResponseEntity.ok(recyclingTipsService.searchRecyclingTips(search));
    }

    /**
     * GET /api/v1/recycling-tips : Get all recycling tips.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of recycling tips
     */
    @GetMapping
    public ResponseEntity<List<RecyclingTipsResponse>> getAllRecyclingTips() {
        return ResponseEntity.ok(recyclingTipsService.getAllRecyclingTips());
    }

    /**
     * GET /api/v1/recycling-tips/{id} : Get recycling tip by id.
     *
     * @param id the id of the recycling tip
     * @return the ResponseEntity with status 200 (OK) and the recycling tip
     */
    @GetMapping("/{id}")
    public ResponseEntity<RecyclingTipsResponse> getRecyclingTipById(@PathVariable Long id) {
        return ResponseEntity.ok(recyclingTipsService.getRecyclingTipById(id));
    }

    /**
     * POST /api/v1/recycling-tips : Create a new recycling tip.
     *
     * @param request the recycling tip request
     * @return the ResponseEntity with status 200 (OK) and the created recycling tip
     */
    @PostMapping
    public ResponseEntity<RecyclingTipsResponse> createRecyclingTip(@RequestBody RecyclingTipsRequest request) {
        return ResponseEntity.ok(recyclingTipsService.createRecyclingTip(request));
    }

    /**
     * PUT /api/v1/recycling-tips/{id} : Update recycling tip.
     *
     * @param id the id of the recycling tip
     * @param request the recycling tip request
     * @return the ResponseEntity with status 200 (OK) and the updated recycling tip
     */
    @PutMapping("/{id}")
    public ResponseEntity<RecyclingTipsResponse> updateRecyclingTip(@PathVariable Long id, @RequestBody RecyclingTipsRequest request) {
        return ResponseEntity.ok(recyclingTipsService.updateRecyclingTip(id, request));
    }

    /**
     * DELETE /api/v1/recycling-tips/{id} : Delete recycling tip.
     *
     * @param id the id of the recycling tip
     * @return the ResponseEntity with status 204 (NO_CONTENT)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecyclingTip(@PathVariable Long id) {
        recyclingTipsService.deleteRecyclingTip(id);
        return ResponseEntity.noContent().build();
    }
}