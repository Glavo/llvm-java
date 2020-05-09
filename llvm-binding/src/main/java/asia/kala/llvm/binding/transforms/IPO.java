package asia.kala.llvm.binding.transforms;

import asia.kala.llvm.binding.LLVMLoader;
import asia.kala.llvm.binding.annotations.CInfo;
import asia.kala.llvm.binding.annotations.Pointer;
import asia.kala.llvm.binding.annotations.Unsigned;

import static asia.kala.llvm.binding.Types.*;

@CInfo(
        fileName = "Transforms/IPO.c",
        include = {
                "<llvm-c/Transforms/IPO.h>",
                "../llvm-java.h"
        }
)
public final class IPO extends LLVMLoader {
    private IPO() {
    }

    public static native void LLVMAddArgumentPromotionPass(@LLVMPassManagerRef long PM);

    public static native void LLVMAddConstantMergePass(@LLVMPassManagerRef long PM);

    public static native void LLVMAddMergeFunctionsPass(@LLVMPassManagerRef long PM);

    public static native void LLVMAddCalledValuePropagationPass(@LLVMPassManagerRef long PM);

    public static native void LLVMAddDeadArgEliminationPass(@LLVMPassManagerRef long PM);

    public static native void LLVMAddFunctionAttrsPass(@LLVMPassManagerRef long PM);

    public static native void LLVMAddFunctionInliningPass(@LLVMPassManagerRef long PM);

    public static native void LLVMAddAlwaysInlinerPass(@LLVMPassManagerRef long PM);

    public static native void LLVMAddGlobalDCEPass(@LLVMPassManagerRef long PM);

    public static native void LLVMAddGlobalOptimizerPass(@LLVMPassManagerRef long PM);

    public static native void LLVMAddIPConstantPropagationPass(@LLVMPassManagerRef long PM);

    public static native void LLVMAddPruneEHPass(@LLVMPassManagerRef long PM);

    public static native void LLVMAddIPSCCPPass(@LLVMPassManagerRef long PM);

    public static native void LLVMAddInternalizePass(@LLVMPassManagerRef long PM, @Unsigned int AllButMain);

    /**
     * Create and add the internalize pass to the given pass manager with the
     * provided preservation callback.
     * <p>
     * The context parameter is forwarded to the callback on each invocation.
     * As such, it is the responsibility of the caller to extend its lifetime
     * until execution of this pass has finished.
     */
    public static native void LLVMAddInternalizePassWithMustPreservePredicate(
            @LLVMPassManagerRef long PM,
            @Pointer("void *") long Context,
            @Pointer("LLVMBool (*)(LLVMValueRef, void *)") long MustPreserve
    );

    public static native void LLVMAddStripDeadPrototypesPass(@LLVMPassManagerRef long PM);

    public static native void LLVMAddStripSymbolsPass(@LLVMPassManagerRef long PM);
}
