package com.teco.market.support.annotation;

import java.util.Objects;

public class AnnotationUtils {
    public static Permission getPriority(Permission clazzAnnotation, Permission methodAnnotation) {
        if (Objects.isNull(clazzAnnotation) && Objects.isNull(methodAnnotation)) {
            throw new AssertionError("인가와 관련된 어노테이션을 추가해주세요.");
        }

        if (Objects.nonNull(methodAnnotation)) {
            return methodAnnotation;
        }

        return clazzAnnotation;
    }
}
