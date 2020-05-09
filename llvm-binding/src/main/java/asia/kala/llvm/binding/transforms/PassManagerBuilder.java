package asia.kala.llvm.binding.transforms;

import asia.kala.llvm.binding.LLVMLoader;
import asia.kala.llvm.binding.annotations.CInfo;
import asia.kala.llvm.binding.annotations.Pointer;
import asia.kala.llvm.binding.annotations.Unsigned;

import java.lang.annotation.*;

import static asia.kala.llvm.binding.Types.LLVMPassManagerRef;

@CInfo(
        fileName = "Transforms/PassManagerBuilder.c",
        include = {
                "<llvm-c/Transforms/PassManagerBuilder.h>",
                "../llvm-java.h"
        }
)
public final class PassManagerBuilder extends LLVMLoader {
    private PassManagerBuilder() {
    }

    @Pointer("LLVMPassManagerBuilderRef")
    @Documented
    @Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMPassManagerBuilderRef {
    }

    public static native @LLVMPassManagerBuilderRef long LLVMPassManagerBuilderCreate();

    public static native void LLVMPassManagerBuilderDispose(@LLVMPassManagerBuilderRef long PMB);

    public static native void LLVMPassManagerBuilderSetOptLevel(
            @LLVMPassManagerBuilderRef long PMB, @Unsigned int OptLevel
    );

    public static native void LLVMPassManagerBuilderSetSizeLevel(
            @LLVMPassManagerBuilderRef long PMB, @Unsigned int SizeLevel
    );

    public static native void LLVMPassManagerBuilderSetDisableUnitAtATime(
            @LLVMPassManagerBuilderRef long PMB, boolean Value
    );

    public static native void LLVMPassManagerBuilderSetDisableUnrollLoops(
            @LLVMPassManagerBuilderRef long PMB, boolean Value
    );

    public static native void LLVMPassManagerBuilderSetDisableSimplifyLibCalls(
            @LLVMPassManagerBuilderRef long PMB, boolean Value
    );

    public static native void LLVMPassManagerBuilderUseInlinerWithThreshold(
            @LLVMPassManagerBuilderRef long PMB, @Unsigned int Threshold
    );

    public static native void LLVMPassManagerBuilderPopulateFunctionPassManager(
            @LLVMPassManagerBuilderRef long PMB, @LLVMPassManagerRef long PM
    );

    public static native void LLVMPassManagerBuilderPopulateModulePassManager(
            @LLVMPassManagerBuilderRef long PMB, @LLVMPassManagerRef long PM
    );

    public static native void LLVMPassManagerBuilderPopulateLTOPassManager(
            @LLVMPassManagerBuilderRef long PMB, @LLVMPassManagerRef long PM,
            boolean Internalize, boolean RunInliner
    );
}
