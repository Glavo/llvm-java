package asia.kala.llvm.binding.annotations;

import java.lang.annotation.*;

@Documented
@java.lang.annotation.Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CInfo {
    String fileName();

    String[] include();
}
