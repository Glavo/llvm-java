package asia.kala.llvm.binding.transforms;

import asia.kala.llvm.binding.LLVMLoader;
import asia.kala.llvm.binding.annotations.CInfo;

import static asia.kala.llvm.binding.Types.*;

@CInfo(
        fileName = "Transforms/AggressiveInstCombine.c",
        include = {
                "<llvm-c/Transforms/AggressiveInstCombine.h>",
                "../llvm-java.h"
        }
)
public final class AggressiveInstCombine extends LLVMLoader {
    private AggressiveInstCombine() {
    }

    public static native void LLVMAddAggressiveInstCombinerPass(@LLVMPassManagerRef long PM);
}
