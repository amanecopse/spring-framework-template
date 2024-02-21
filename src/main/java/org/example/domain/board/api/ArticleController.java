package org.example.domain.board.api;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.domain.board.application.ArticleService;
import org.example.global.common.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/board/articles")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;
    @GetMapping
    public ResponseEntity<ApiResponse<List<String>>> getAll() {
        List<String> result = articleService.getAll();
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> getById(@PathVariable("id") Long id) {
        String result = articleService.getById(id);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @PostMapping
    public ResponseEntity<Void> save(
            //TODO: DTO 구현 시 넣을 것
    ) {
        articleService.save();
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Long id) {
        articleService.update(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        articleService.delete(id);
        return ResponseEntity.ok().build();
    }
}