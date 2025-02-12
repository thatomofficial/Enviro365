package com.enviro.assessment.grad001.thato_mokgotsi.repository;

import com.enviro.assessment.grad001.thato_mokgotsi.model.WasteCategory;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for waste categories
 */
@Repository
public interface WasteCategoryRepository extends JpaRepository<WasteCategory, Long>, JpaSpecificationExecutor<WasteCategory> {

    @Query(value = "SELECT * FROM waste_category", nativeQuery = true)

    @NonNull
    List<WasteCategory> findAll();

    @NonNull
    Page<WasteCategory> findAll(@NonNull Pageable pageable);

    @NonNull
    List<WasteCategory> findAll(@NonNull Sort sort);

}
