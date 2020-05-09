package asia.kala.llvm.binding.transforms;

import asia.kala.llvm.binding.LLVMLoader;
import asia.kala.llvm.binding.annotations.CInfo;

import static asia.kala.llvm.binding.Types.LLVMPassManagerRef;

@CInfo(
        fileName = "Transforms/Vectorize.c",
        include = {
                "<llvm-c/Transforms/Vectorize.h>",
                "../llvm-java.h"
        }
)
public final class Vectorize extends LLVMLoader {
    private Vectorize() {
    }

    public static native void LLVMAddLoopVectorizePass(@LLVMPassManagerRef long PM);

    public static native void LLVMAddSLPVectorizePass(@LLVMPassManagerRef long PM);
}
