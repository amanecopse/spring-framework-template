package org.example.domain.board.domain;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/root-context.xml")
@Slf4j
public class SampleTest {
    @Autowired
    EntityManagerFactory emf;

    @Test
    public void insertTest() {
        Sample sample = Sample.builder().content("aaaaa").build();
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(sample);
        em.flush();
        em.getTransaction().commit();
    }
}
