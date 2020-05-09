package asia.kala.llvm.binding;

import asia.kala.llvm.binding.annotations.CEnum;
import asia.kala.llvm.binding.annotations.CInfo;
import asia.kala.llvm.binding.annotations.Pointer;

import static asia.kala.llvm.binding.Types.*;

@CInfo(
        fileName = "Comdat.c",
        include = {
                "<llvm-c/Comdat.h>",
                "llvm-java.h"
        }
)
public final class Comdat extends LLVMLoader {
    private Comdat() {

    }

    @CEnum("LLVMComdatSelectionKind")
    public @interface LLVMComdatSelectionKind {
        /**
         * The linker may choose any COMDAT.
         */
        int LLVMAnyComdatSelectionKind = 0;
        /**
         * The data referenced by the COMDAT must be the same.
         */
        int LLVMExactMatchComdatSelectionKind = 1;
        /**
         * The linker will choose the largest COMDAT.
         */
        int LLVMLargestComdatSelectionKind = 2;
        /**
         * No other Module may specify this COMDAT.
         */
        int LLVMNoDuplicatesComdatSelectionKind = 3;
        /**
         * The data referenced by the COMDAT must be the same size.
         */
        int LLVMSameSizeComdatSelectionKind = 4;

    }

    /**
     * Return the Comdat in the module with the specified name. It is created
     * if it didn't already exist.
     */
    public static native @LLVMComdatRef long LLVMGetOrInsertComdat(@LLVMModuleRef long M, @Pointer("const char *") long Name);

    /**
     * Get the Comdat assigned to the given global object.
     */
    public static native @LLVMComdatRef long LLVMGetComdat(@LLVMValueRef long V);

    /**
     * Assign the Comdat to the given global object.
     */
    public static native void LLVMSetComdat(@LLVMValueRef long V, @LLVMComdatRef long C);

    /**
     * Get the conflict resolution selection kind for the Comdat.
     */
    public static native @LLVMComdatSelectionKind int LLVMGetComdatSelectionKind(@LLVMComdatRef long C);

    /**
     * Set the conflict resolution selection kind for the Comdat.
     */
    public static native void LLVMSetComdatSelectionKind(@LLVMComdatRef long C, @LLVMComdatSelectionKind int Kind);


}
