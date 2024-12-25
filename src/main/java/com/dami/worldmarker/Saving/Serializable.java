package com.dami.worldmarker.Saving;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // Specifies the retention policy
@Target({ElementType.TYPE}) // Specifies where the annotation can be applied
public @interface Serializable {
}
