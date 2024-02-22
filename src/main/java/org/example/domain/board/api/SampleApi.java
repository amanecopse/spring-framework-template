package org.example.domain.board.api;

import lombok.RequiredArgsConstructor;
import org.example.domain.board.application.SampleService;
import org.example.domain.board.dto.SampleCreateRequest;
import org.example.domain.board.dto.SampleResponse;
import org.example.domain.board.dto.SampleSearchRequest;
import org.example.domain.board.dto.SampleUpdateRequest;
import org.example.global.common.request.SearchFilter;
import org.example.global.common.response.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/board/samples")
@RequiredArgsConstructor
public class SampleApi {
    private final SampleService sampleService;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<SampleResponse>>> getAll(@PageableDefault Pageable pageable) {
        Page<SampleResponse> result = sampleService.getAll(pageable);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<Page<SampleResponse>>> search(SampleSearchRequest request, SearchFilter filter, @PageableDefault Pageable pageable) {
        Page<SampleResponse> result = sampleService.search(request, filter, pageable);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SampleResponse>> getById(@PathVariable("id") long id) {
        SampleResponse result = sampleService.getById(id);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Validated @RequestBody SampleCreateRequest request) {
        sampleService.save(request);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(
            @PathVariable("id")
            long id,

            @Validated
            @RequestBody
            SampleUpdateRequest request
    ) {
        sampleService.update(id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        sampleService.delete(id);
        return ResponseEntity.ok().build();
    }
}