package asia.kala.llvm.binding.transforms;

import asia.kala.llvm.binding.LLVMLoader;
import asia.kala.llvm.binding.annotations.CInfo;
import asia.kala.llvm.binding.annotations.Signed;

import static asia.kala.llvm.binding.Types.*;

@CInfo(
        fileName = "Transforms/Scalar.c",
        include = {
                "<llvm-c/Transforms/Scalar.h>",
                "../llvm-java.h"
        }
)
public final class Scalar extends LLVMLoader {
    private Scalar() {
    }

    public static native void LLVMAddAggressiveDCEPass(@LLVMPassManagerRef long PM);

    public static native void LLVMAddDCEPass(@LLVMPassManagerRef long PM);

    public static native void LLVMAddBitTrackingDCEPass(@LLVMPassManagerRef long PM);

    public static native void LLVMAddAlignmentFromAssumptionsPass(@LLVMPassManagerRef long PM);

    public static native void LLVMAddCFGSimplificationPass(@LLVMPassManagerRef long PM);

    public static native void LLVMAddDeadStoreEliminationPass(@LLVMPassManagerRef long PM);

    public static native void LLVMAddScalarizerPass(@LLVMPassManagerRef long PM);

    public static native void LLVMAddMergedLoadStoreMotionPass(@LLVMPassManagerRef long PM);

    public static native void LLVMAddGVNPass(@LLVMPassManagerRef long PM);

    public static native void LLVMAddNewGVNPass(@LLVMPassManagerRef long PM);

    public static native void LLVMAddIndVarSimplifyPass(@LLVMPassManagerRef long PM);

    public static native void LLVMAddInstructionCombiningPass(@LLVMPassManagerRef long PM);

    public static native void LLVMAddJumpThreadingPass(@LLVMPassManagerRef long PM);

    public static native void LLVMAddLICMPass(@LLVMPassManagerRef long PM);

    public static native void LLVMAddLoopDeletionPass(@LLVMPassManagerRef long PM);

    public static native void LLVMAddLoopIdiomPass(@LLVMPassManagerRef long PM);

    public static native void LLVMAddLoopRotatePass(@LLVMPassManagerRef long PM);

    public static native void LLVMAddLoopRerollPass(@LLVMPassManagerRef long PM);

    public static native void LLVMAddLoopUnrollPass(@LLVMPassManagerRef long PM);

    public static native void LLVMAddLoopUnrollAndJamPass(@LLVMPassManagerRef long PM);

    public static native void LLVMAddLoopUnswitchPass(@LLVMPassManagerRef long PM);

    public static native void LLVMAddLowerAtomicPass(@LLVMPassManagerRef long PM);

    public static native void LLVMAddMemCpyOptPass(@LLVMPassManagerRef long PM);

    public static native void LLVMAddPartiallyInlineLibCallsPass(@LLVMPassManagerRef long PM);

    public static native void LLVMAddReassociatePass(@LLVMPassManagerRef long PM);

    public static native void LLVMAddSCCPPass(@LLVMPassManagerRef long PM);

    public static native void LLVMAddScalarReplAggregatesPass(@LLVMPassManagerRef long PM);

    public static native void LLVMAddScalarReplAggregatesPassSSA(@LLVMPassManagerRef long PM);

    public static native void LLVMAddScalarReplAggregatesPassWithThreshold(
            @LLVMPassManagerRef long PM, @Signed("int") int Threshold
    );

    public static native void LLVMAddSimplifyLibCallsPass(@LLVMPassManagerRef long PM);

    public static native void LLVMAddTailCallEliminationPass(@LLVMPassManagerRef long PM);

    public static native void LLVMAddConstantPropagationPass(@LLVMPassManagerRef long PM);

    public static native void LLVMAddDemoteMemoryToRegisterPass(@LLVMPassManagerRef long PM);

    public static native void LLVMAddVerifierPass(@LLVMPassManagerRef long PM);

    public static native void LLVMAddCorrelatedValuePropagationPass(@LLVMPassManagerRef long PM);

    public static native void LLVMAddEarlyCSEPass(@LLVMPassManagerRef long PM);

    public static native void LLVMAddEarlyCSEMemSSAPass(@LLVMPassManagerRef long PM);

    public static native void LLVMAddLowerExpectIntrinsicPass(@LLVMPassManagerRef long PM);

    public static native void LLVMAddLowerConstantIntrinsicsPass(@LLVMPassManagerRef long PM);

    public static native void LLVMAddTypeBasedAliasAnalysisPass(@LLVMPassManagerRef long PM);

    public static native void LLVMAddScopedNoAliasAAPass(@LLVMPassManagerRef long PM);

    public static native void LLVMAddBasicAliasAnalysisPass(@LLVMPassManagerRef long PM);

    public static native void LLVMAddUnifyFunctionExitNodesPass(@LLVMPassManagerRef long PM);

}
