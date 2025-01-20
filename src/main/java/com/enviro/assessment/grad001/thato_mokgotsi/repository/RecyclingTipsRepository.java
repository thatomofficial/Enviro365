package com.enviro.assessment.grad001.thato_mokgotsi.repository;


import com.enviro.assessment.grad001.thato_mokgotsi.model.RecyclingTips;
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
 * Repository class for managing recycling tips
 */
@Repository
public interface RecyclingTipsRepository extends JpaRepository<RecyclingTips, Long>, JpaSpecificationExecutor<RecyclingTips> {

    @Query(value = "SELECT * FROM recycling_tips", nativeQuery = true)

    @NonNull
    List<RecyclingTips> findAll();

    @NonNull
    Page<RecyclingTips> findAll(@NonNull Pageable pageable);

    @NonNull
    List<RecyclingTips> findAll(@NonNull Sort sort);
}