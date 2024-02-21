package org.example.domain.board.dao;

import org.example.domain.board.domain.Sample;
import org.example.domain.board.dto.SampleSearchRequest;
import org.example.global.common.request.SearchFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SampleDaoCustom {
    Page<Sample> search(SampleSearchRequest request, SearchFilter filter, Pageable pageable);
}
