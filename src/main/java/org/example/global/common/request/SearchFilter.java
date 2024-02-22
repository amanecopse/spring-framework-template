package org.example.global.common.request;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class SearchFilter {
    String keyword;
    LocalDateTime createdAfter;
    LocalDateTime createdBefore;
    LocalDateTime updatedAfter;
    LocalDateTime updatedBefore;
}
