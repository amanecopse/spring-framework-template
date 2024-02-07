package org.example.sample;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.board.domain.QSample;
import org.example.domain.board.domain.Sample;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/root-context.xml")
@Slf4j
public class QueryDslTest {
    @Autowired
    JPAQueryFactory jpaQueryFactory;

    @Test
    public void test() {
        QSample sample = QSample.sample;
        List<Sample> samples = jpaQueryFactory.selectFrom(sample).fetch();
        log.debug("asd" + samples.toString());
    }
}
