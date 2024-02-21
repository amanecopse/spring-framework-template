package org.example.domain.board.dto;

import jakarta.validation.constraints.Null;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class SampleUpdateRequest {
    @Null
    private Long id;

    @Null
    private LocalDateTime createdAt;

    @Null
    private LocalDateTime updatedAt;

    private String title;

    private String content;
}