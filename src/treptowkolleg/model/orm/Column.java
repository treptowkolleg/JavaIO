package treptowkolleg.model.orm;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Column {
    ORMType type() default ORMType.VARCHAR;
    int length() default 255;
    boolean nullable() default false;
}
