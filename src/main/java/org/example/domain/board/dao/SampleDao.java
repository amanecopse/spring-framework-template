package org.example.domain.board.dao;

import java.util.Optional;
import org.example.domain.board.domain.Sample;
import org.example.domain.board.dto.SampleSearchRequest;
import org.example.global.common.request.SearchFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface SampleDao extends JpaRepository<Sample, Long>, SampleDaoCustom {
    @Override
    @NonNull
    Page<Sample> findAll(@NonNull Pageable pageable);

    @Override
    Page<Sample> search(SampleSearchRequest dto, SearchFilter filter, @NonNull Pageable pageable);

    @Override
    @NonNull
    Optional<Sample> findById(@NonNull Long id);
}
