package asia.kala.llvm.binding;

import asia.kala.llvm.binding.annotations.CInfo;
import asia.kala.llvm.binding.annotations.Pointer;

import java.lang.annotation.*;

@CInfo(
        fileName = "Error.c",
        include = {
                "<llvm-c/Error.h>",
                "llvm-java.h"
        }
)
public final class ErrorH extends LLVMLoader {
    private ErrorH() {
    }

    public static final int LLVMErrorSuccess = 0;


    @Pointer("LLVMErrorRef")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMErrorRef {
    }

    @Pointer("LLVMErrorTypeId")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMErrorTypeId {
    }

    /**
     * Returns the type id for the given error instance, which must be a failure
     * value (i.e. non-null).
     */
    public static native @LLVMErrorTypeId long LLVMGetErrorTypeId(@LLVMErrorRef long Err);

    /**
     * Dispose of the given error without handling it. This operation consumes the
     * error, and the given LLVMErrorRef value is not usable once this call returns.
     * Note: This method *only* needs to be called if the error is not being passed
     * to some other consuming operation, e.g. LLVMGetErrorMessage.
     */
    public static native void LLVMConsumeError(@LLVMErrorRef long Err);

    /**
     * Returns the given string's error message. This operation consumes the error,
     * and the given LLVMErrorRef value is not usable once this call returns.
     * The caller is responsible for disposing of the string by calling
     * LLVMDisposeErrorMessage.
     */
    public static native @Pointer("char *") long LLVMGetErrorMessage(@LLVMErrorRef long Err);

    /**
     * Dispose of the given error message.
     */
    public static native void LLVMDisposeErrorMessage(@Pointer("char *") long ErrMsg);

    /**
     * Returns the type id for llvm StringError.
     */
    public static native @LLVMErrorTypeId long LLVMGetStringErrorTypeId();
}
