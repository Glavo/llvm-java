package asia.kala.llvm.binding.transforms;

import asia.kala.llvm.binding.LLVMLoader;
import asia.kala.llvm.binding.annotations.CInfo;

import static asia.kala.llvm.binding.Types.LLVMPassManagerRef;

@CInfo(
        fileName = "Transforms/InstCombine.c",
        include = {
                "<llvm-c/Transforms/InstCombine.h>",
                "../llvm-java.h"
        }
)
public final class InstCombine extends LLVMLoader {
    private InstCombine() {
    }

    public static native void LLVMAddInstructionCombiningPass(@LLVMPassManagerRef long PM);
}
