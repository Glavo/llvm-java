package asia.kala.llvm.binding.transforms;

import asia.kala.llvm.binding.LLVMLoader;
import asia.kala.llvm.binding.annotations.CInfo;

import static asia.kala.llvm.binding.Types.*;

@CInfo(
        fileName = "Transforms/Coroutines.c",
        include = {
                "<llvm-c/Transforms/Coroutines.h>",
                "../llvm-java.h"
        }
)
public final class Coroutines extends LLVMLoader {
    private Coroutines() {
    }

    public static native void LLVMAddCoroEarlyPass(@LLVMPassManagerRef long PM);

    public static native void LLVMAddCoroSplitPass(@LLVMPassManagerRef long PM);

    public static native void LLVMAddCoroElidePass(@LLVMPassManagerRef long PM);

    public static native void LLVMAddCoroCleanupPass(@LLVMPassManagerRef long PM);

}
