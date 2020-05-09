package asia.kala.llvm.binding;

import asia.kala.llvm.binding.annotations.CEnum;
import asia.kala.llvm.binding.annotations.CInfo;
import asia.kala.llvm.binding.annotations.Pointer;

import java.lang.annotation.*;

import static asia.kala.llvm.binding.Types.*;

@CInfo(
        fileName = "Analysis.c",
        include = {
                "<llvm-c/Analysis.h>",
                "llvm-java.h"
        }
)
public final class Analysis extends LLVMLoader {
    private Analysis() {
    }


    @CEnum("LLVMVerifierFailureAction")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMVerifierFailureAction {
        int LLVMAbortProcessAction = 0; /* verifier will print to stderr and abort() */
        int LLVMPrintMessageAction = 1; /* verifier will print to stderr and return 1 */
        int LLVMReturnStatusAction = 2;  /* verifier will just return 1 */
    }

    /* Verifies that a module is valid, taking the specified action if not.
   Optionally returns a human-readable description of any invalid constructs.
   OutMessage must be disposed with LLVMDisposeMessage. */
    public static native boolean LLVMVerifyModule(
            @LLVMModuleRef long M, @LLVMVerifierFailureAction int Action,
            @Pointer("char **") long OutMessage
    );

    /* Verifies that a single function is valid, taking the specified action. Useful
       for debugging. */
    public static native boolean LLVMVerifyFunction(@LLVMValueRef long Fn, @LLVMVerifierFailureAction int Action);

    /* Open up a ghostview window that displays the CFG of the current function.
       Useful for debugging. */
    public static native void LLVMViewFunctionCFG(@LLVMValueRef long Fn);

    public static native void LLVMViewFunctionCFGOnly(@LLVMValueRef long Fn);

}
