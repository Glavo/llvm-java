package asia.kala.llvm.binding;

import asia.kala.llvm.binding.annotations.CInfo;
import asia.kala.llvm.binding.annotations.Pointer;

import static asia.kala.llvm.binding.Types.*;

@CInfo(
        fileName = "IRReader.c",
        include = {
                "<llvm-c/IRReader.h>",
                "llvm-java.h"
        }
)
public final class IRReader extends LLVMLoader {
    private IRReader() {
    }

    /**
     * Read LLVM IR from a memory buffer and convert it into an in-memory Module
     * object. Returns 0 on success.
     * Optionally returns a human-readable description of any errors that
     * occurred during parsing IR. OutMessage must be disposed with
     * LLVMDisposeMessage.
     */
    public static native boolean LLVMParseIRInContext(
            @LLVMContextRef long ContextRef,
            @LLVMMemoryBufferRef long MemBuf, @Pointer("LLVMModuleRef *") long OutM,
            @Pointer("char **") long OutMessage
    );
}
