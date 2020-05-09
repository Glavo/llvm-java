package asia.kala.llvm.binding;

import asia.kala.llvm.binding.annotations.CEnum;
import asia.kala.llvm.binding.annotations.CInfo;
import asia.kala.llvm.binding.annotations.Pointer;
import asia.kala.llvm.binding.annotations.Signed;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static asia.kala.llvm.binding.Types.*;

@CInfo(
        fileName = "Linker.c",
        include = {
                "<llvm-c/Linker.h>",
                "llvm-java.h"
        }
)
public final class Linker extends LLVMLoader {
    private Linker() {

    }

    @CEnum("LLVMLinkerMode")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMLinkerMode {
        int LLVMLinkerDestroySource = 0; /* This is the default behavior. */
        int LLVMLinkerPreserveSource_Removed = 1; /* This option has been deprecated and should not be used. */
    }

    /**
     * Links the source module into the destination module. The source module is
     * destroyed.
     * The return value is true if an error occurred, false otherwise.
     * Use the diagnostic handler to get any diagnostic message.
     */
    public static native boolean LLVMLinkModules2(@LLVMModuleRef long Dest, @LLVMModuleRef long Src);

}
