package asia.kala.llvm.binding;

import asia.kala.llvm.binding.annotations.CInfo;
import asia.kala.llvm.binding.annotations.Pointer;

import static asia.kala.llvm.binding.Types.*;


@CInfo(
        fileName = "BitReader.c",
        include = {
                "<llvm-c/BitReader.h>",
                "llvm-java.h"
        }
)
public final class BitReader extends LLVMLoader {

    /**
     * Builds a module from the bitcode in the specified memory buffer, returning a
     * reference to the module via the OutModule parameter. Returns 0 on success.
     * Optionally returns a human-readable error message via OutMessage.
     * <p>
     *
     * @deprecated Use {@link #LLVMParseBitcode2(long, long)}
     */
    @Deprecated
    public static native boolean LLVMParseBitcode(
            @LLVMMemoryBufferRef long MemBuf,
            @Pointer("LLVMModuleRef *") long OutModule,
            @Pointer("char **") long OutMessage
    );

    /**
     * Builds a module from the bitcode in the specified memory buffer, returning a
     * reference to the module via the OutModule parameter. Returns 0 on success.
     */
    public static native boolean LLVMParseBitcode2(
            @LLVMMemoryBufferRef long MemBuf,
            @Pointer("LLVMModuleRef *") long OutModule
    );

    /**
     * @deprecated Use {@link #LLVMParseBitcodeInContext2(long, long, long)}
     */
    @Deprecated
    public static native boolean LLVMParseBitcodeInContext(
            @LLVMContextRef long ContextRef,
            @LLVMMemoryBufferRef long MemBuf,
            @Pointer("LLVMModuleRef *") long OutModule, @Pointer("char **") long OutMessage
    );

    public static native boolean LLVMParseBitcodeInContext2(
            @LLVMContextRef long ContextRef,
            @LLVMMemoryBufferRef long MemBuf,
            @Pointer("LLVMModuleRef *") long OutModule
    );

    /**
     * Reads a module from the specified path, returning via the OutMP parameter
     * a module provider which performs lazy deserialization. Returns 0 on success.
     * Optionally returns a human-readable error message via OutMessage.
     *
     * @deprecated Use {@link #LLVMGetBitcodeModuleInContext2(long, long, long)}
     */
    @Deprecated
    public static native boolean LLVMGetBitcodeModuleInContext(
            @LLVMContextRef long ContextRef,
            @LLVMMemoryBufferRef long MemBuf,
            @Pointer("LLVMModuleRef *") long OutM, @Pointer("char **") long OutMessage
    );

    /**
     * Reads a module from the specified path, returning via the OutMP parameter a
     * module provider which performs lazy deserialization. Returns 0 on success.
     */
    public static native boolean LLVMGetBitcodeModuleInContext2(
            @LLVMContextRef long ContextRef,
            @LLVMMemoryBufferRef long MemBuf,
            @Pointer("LLVMModuleRef *") long OutM
    );

    /**
     * @deprecated Use {@link #LLVMGetBitcodeModule2(long, long)}
     */
    @Deprecated
    public static native boolean LLVMGetBitcodeModule(
            @LLVMMemoryBufferRef long MemBuf, @Pointer("LLVMModuleRef *") long OutM,
            @Pointer("char **") long OutMessage
    );

    public static native boolean LLVMGetBitcodeModule2(@LLVMMemoryBufferRef long MemBuf, @Pointer("LLVMModuleRef *") long OutM);
}
