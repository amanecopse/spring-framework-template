package org.example.domain.board.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.domain.board.domain.Sample;

@AllArgsConstructor
@Data
@Builder
public class SampleResponse{
    Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    LocalDateTime updatedAt;

    String title;

    String content;

    public SampleResponse(Sample sample) {
        this.id = sample.getId();
        this.createdAt = sample.getCreatedAt();
        this.updatedAt = sample.getUpdatedAt();
        this.title = sample.getTitle();
        this.content = sample.getContent();
    }
}