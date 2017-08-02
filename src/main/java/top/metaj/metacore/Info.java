package top.metaj.metacore;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by abc on 2017/8/2.
 */
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.METHOD,ElementType.FIELD})
public @interface Info {
    String[] value();
}
