package org.example.global.config;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManagerFactory;
import java.util.Properties;
import javax.sql.DataSource;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.dbcp.BasicDataSource;
import org.example.domain.member.dao.RoleDao;
import org.example.domain.member.domain.Role;
import org.example.domain.member.model.RoleEnum;
import org.example.global.util.Util;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@RequiredArgsConstructor
public class DbRowConfig implements InitializingBean {
    private final RoleDao roleDao;

    @Override
    public void afterPropertiesSet() throws Exception {
        trySave(roleDao, Role.builder()
                .value(RoleEnum.ROLE_USER)
                .build());
        trySave(roleDao, Role.builder()
                .value(RoleEnum.ROLE_ADMIN)
                .build());
    }

    private <T> void trySave(JpaRepository<T, Long> dao, T entity){
        try {
            dao.save(entity);
        } catch (Exception e) {
            return;
        }
    }
}
