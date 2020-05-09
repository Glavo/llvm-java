package asia.kala.llvm.binding;

import asia.kala.llvm.binding.annotations.CInfo;
import asia.kala.llvm.binding.annotations.Pointer;
import asia.kala.llvm.binding.annotations.Signed;

import static asia.kala.llvm.binding.Types.*;

@CInfo(
        fileName = "BitWriter.c",
        include = {
                "<llvm-c/BitWriter.h>",
                "llvm-java.h"
        }
)
public final class BitWriter extends LLVMLoader {
    private BitWriter() {
    }

    /**
     * Writes a module to the specified path. Returns 0 on success.
     */
    public static native int LLVMWriteBitcodeToFile(@LLVMModuleRef long M, @Pointer("const char *") long Path);

    /**
     * Writes a module to an open file descriptor. Returns 0 on success.
     */
    public static native int LLVMWriteBitcodeToFD(
            @LLVMModuleRef long M, @Signed("int") int FD, @Signed("int") int ShouldClose, @Signed("int") int Unbuffered
    );

    /**
     * Writes a module to an open file descriptor. Returns 0 on success. Closes the Handle.
     *
     * @deprecated Ues LLVMWriteBitcodeToFD.
     */
    @Deprecated
    public static native int LLVMWriteBitcodeToFileHandle(@LLVMModuleRef long M, @Signed("int") int Handle);

    /**
     * Writes a module to a new memory buffer and returns it.
     */
    public static native @LLVMMemoryBufferRef long LLVMWriteBitcodeToMemoryBuffer(@LLVMModuleRef long M);
}
