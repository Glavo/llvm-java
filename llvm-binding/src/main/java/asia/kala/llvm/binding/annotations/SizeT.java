package asia.kala.llvm.binding.annotations;

import java.lang.annotation.*;

@Unsigned("size_t")
@Documented
@java.lang.annotation.Target(ElementType.TYPE_USE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SizeT {
}
