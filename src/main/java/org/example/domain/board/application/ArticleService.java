package org.example.domain.board.application;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    public List<String> getAll() {
        return List.of("1", "2", "3", "4");
    }

    public String getById(long id) {
        return String.valueOf(id);
    }

    public void save() {
        return;
    }

    public void update(long id) {
        return;
    }

    public void delete(long id) {
        return;
    }
}
