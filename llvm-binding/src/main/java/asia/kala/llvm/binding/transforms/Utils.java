package asia.kala.llvm.binding.transforms;

import asia.kala.llvm.binding.LLVMLoader;
import asia.kala.llvm.binding.annotations.CInfo;

import static asia.kala.llvm.binding.Types.LLVMPassManagerRef;

@CInfo(
        fileName = "Transforms/Utils.c",
        include = {
                "<llvm-c/Transforms/Utils.h>",
                "../llvm-java.h"
        }
)
public final class Utils extends LLVMLoader {
    private Utils() {
    }

    public static native void LLVMAddLowerSwitchPass(@LLVMPassManagerRef long PM);

    public static native void LLVMAddPromoteMemoryToRegisterPass(@LLVMPassManagerRef long PM);

    public static native void LLVMAddAddDiscriminatorsPass(@LLVMPassManagerRef long PM);
}
