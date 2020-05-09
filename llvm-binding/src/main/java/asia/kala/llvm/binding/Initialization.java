package asia.kala.llvm.binding;

import asia.kala.llvm.binding.annotations.CInfo;

import static asia.kala.llvm.binding.Types.*;

@CInfo(
        fileName = "Initialization.c",
        include = {
                "<llvm-c/Initialization.h>",
                "llvm-java.h"
        }
)
public final class Initialization extends LLVMLoader {
    private Initialization() {
    }

    public static native void LLVMInitializeCore(@LLVMPassRegistryRef long R);

    public static native void LLVMInitializeTransformUtils(@LLVMPassRegistryRef long R);

    public static native void LLVMInitializeScalarOpts(@LLVMPassRegistryRef long R);

    public static native void LLVMInitializeObjCARCOpts(@LLVMPassRegistryRef long R);

    public static native void LLVMInitializeVectorization(@LLVMPassRegistryRef long R);

    public static native void LLVMInitializeInstCombine(@LLVMPassRegistryRef long R);

    public static native void LLVMInitializeAggressiveInstCombiner(@LLVMPassRegistryRef long R);

    public static native void LLVMInitializeIPO(@LLVMPassRegistryRef long R);

    public static native void LLVMInitializeInstrumentation(@LLVMPassRegistryRef long R);

    public static native void LLVMInitializeAnalysis(@LLVMPassRegistryRef long R);

    public static native void LLVMInitializeIPA(@LLVMPassRegistryRef long R);

    public static native void LLVMInitializeCodeGen(@LLVMPassRegistryRef long R);

    public static native void LLVMInitializeTarget(@LLVMPassRegistryRef long R);

}
