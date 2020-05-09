package asia.kala.llvm.binding;

import asia.kala.llvm.binding.annotations.CInfo;
import asia.kala.llvm.binding.annotations.Pointer;

import java.lang.annotation.*;

@CInfo(
        fileName = "ErrorHandling.c",
        include = {
                "<llvm-c/ErrorHandling.h>",
                "llvm-java.h"
        }
)
public final class ErrorHandling extends LLVMLoader {
    private ErrorHandling() {
    }

    @Pointer("LLVMFatalErrorHandler")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMFatalErrorHandler {
    }

    /**
     * Install a fatal error handler. By default, if LLVM detects a fatal error, it
     * will call exit(1). This may not be appropriate in many contexts. For example,
     * doing exit(1) will bypass many crash reporting/tracing system tools. This
     * function allows you to install a callback that will be invoked prior to the
     * call to exit(1).
     */
    public static native void LLVMInstallFatalErrorHandler(@LLVMFatalErrorHandler long Handler);

    /**
     * Reset the fatal error handler. This resets LLVM's fatal error handling
     * behavior to the default.
     */
    public static native void LLVMResetFatalErrorHandler();

    /**
     * Enable LLVM's built-in stack trace code. This intercepts the OS's crash
     * signals and prints which component of LLVM you were in at the time if the
     * crash.
     */
    public static native void LLVMEnablePrettyStackTrace();
}
