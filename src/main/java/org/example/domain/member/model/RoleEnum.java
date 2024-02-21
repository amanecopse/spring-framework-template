package org.example.domain.member.model;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Getter;

@Getter
public enum RoleEnum {
    ROLE_USER(0),
    ROLE_ADMIN(1);

    private final int value;
    private static final Map<Integer, RoleEnum> valueToName =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(RoleEnum::getValue, Function.identity())));

    RoleEnum(int value) {
        this.value = value;
    }

    public static RoleEnum getByValue(int value){
        return valueToName.get(value);
    }
}
