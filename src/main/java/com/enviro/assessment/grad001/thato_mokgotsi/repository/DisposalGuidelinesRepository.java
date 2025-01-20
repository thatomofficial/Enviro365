package com.enviro.assessment.grad001.thato_mokgotsi.repository;

import com.enviro.assessment.grad001.thato_mokgotsi.model.DisposalGuidelines;
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
 * Repository interface for disposal guidelines
 */
@Repository
public interface DisposalGuidelinesRepository extends JpaRepository<DisposalGuidelines, Long>, JpaSpecificationExecutor<DisposalGuidelines> {

    @Query(value = "SELECT * FROM disposal_guidelines", nativeQuery = true)

    @NonNull
    List<DisposalGuidelines> findAll();

    @NonNull
    Page<DisposalGuidelines> findAll(@NonNull Pageable pageable);

    @NonNull
    List<DisposalGuidelines> findAll(@NonNull Sort sort);

}