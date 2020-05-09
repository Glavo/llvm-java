package asia.kala.llvm.binding.annotations;

import java.lang.annotation.*;

@Documented
@java.lang.annotation.Target({ElementType.TYPE_USE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Pointer {
    String value();
}
