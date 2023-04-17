package ETU2035.framework.server;
import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface GetUrl {
    String url() default "";
}
