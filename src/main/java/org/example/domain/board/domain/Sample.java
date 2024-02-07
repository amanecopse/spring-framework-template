package org.example.domain.board.domain;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.domain.model.BaseEntity;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sample extends BaseEntity {
    private String content;
}


//package org.example.domain.board.domain;
//
//import jakarta.persistence.Entity;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.experimental.SuperBuilder;
//import org.example.domain.model.BaseEntity;
//
//@Getter
//@Entity
//@SuperBuilder
//@NoArgsConstructor
//@AllArgsConstructor
//public class Sample extends BaseEntity {
//    private String content;
//}
