package asia.kala.llvm.binding;

import asia.kala.llvm.binding.annotations.*;

import static asia.kala.llvm.binding.DisassemblerTypes.*;

@CInfo(
        fileName = "Disassembler.c",
        include = {
                "<llvm-c/Disassembler.h>",
                "llvm-java.h"
        }
)
public final class Disassembler extends LLVMLoader {
    private Disassembler() {
    }

    /**
     * Create a disassembler for the TripleName.  Symbolic disassembly is supported
     * by passing a block of information in the DisInfo parameter and specifying the
     * TagType and callback functions as described above.  These can all be passed
     * as NULL.  If successful, this returns a disassembler context.  If not, it
     * returns NULL. This function is equivalent to calling
     * LLVMCreateDisasmCPUFeatures() with an empty CPU name and feature set.
     */
    public static native @LLVMDisasmContextRef long LLVMCreateDisasm(
            @Pointer("const char *") long TripleName, @Pointer("void *") long DisInfo,
            @Signed("int") int TagType, @LLVMOpInfoCallback long GetOpInfo,
            @LLVMSymbolLookupCallback long SymbolLookUp
    );

    /**
     * Create a disassembler for the TripleName and a specific CPU.  Symbolic
     * disassembly is supported by passing a block of information in the DisInfo
     * parameter and specifying the TagType and callback functions as described
     * above.  These can all be passed * as NULL.  If successful, this returns a
     * disassembler context.  If not, it returns NULL. This function is equivalent
     * to calling LLVMCreateDisasmCPUFeatures() with an empty feature set.
     */
    public static native @LLVMDisasmContextRef long LLVMCreateDisasmCPU(
            @Pointer("const char *") long Triple, @Pointer("const char *") long CPU,
            @Pointer("void *") long DisInfo, @Signed("int") int TagType,
            @LLVMOpInfoCallback long GetOpInfo,
            @LLVMSymbolLookupCallback long SymbolLookUp
    );

    /**
     * Create a disassembler for the TripleName, a specific CPU and specific feature
     * string.  Symbolic disassembly is supported by passing a block of information
     * in the DisInfo parameter and specifying the TagType and callback functions as
     * described above.  These can all be passed * as NULL.  If successful, this
     * returns a disassembler context.  If not, it returns NULL.
     */
    public static native @LLVMDisasmContextRef long LLVMCreateDisasmCPUFeatures(
            @Pointer("const char *") long Triple, @Pointer("const char *") long CPU,
            @Pointer("const char *") long Features, @Pointer("void *") long DisInfo, @Signed("int") int TagType,
            @LLVMOpInfoCallback long GetOpInfo,
            @LLVMSymbolLookupCallback long SymbolLookUp
    );

    /**
     * Set the disassembler's options.  Returns 1 if it can set the Options and 0
     * otherwise.
     */
    public static native int LLVMSetDisasmOptions(@LLVMDisasmContextRef long DC, @Unsigned("uint64_t") long Options);

    /**
     * The option to produce marked up assembly.
     */
    public static final int LLVMDisassembler_Option_UseMarkup = 1;
    /**
     * The option to print immediates as hex.
     */
    public static final int LLVMDisassembler_Option_PrintImmHex = 2;
    /**
     * The option use the other assembler printer variant
     */
    public static final int LLVMDisassembler_Option_AsmPrinterVariant = 4;
    /**
     * The option to set comment on instructions
     */
    public static final int LLVMDisassembler_Option_SetInstrComments = 8;
    /**
     * The option to print latency information alongside instructions
     */
    public static final int LLVMDisassembler_Option_PrintLatency = 16;

    /**
     * Dispose of a disassembler context.
     */
    public static native void LLVMDisasmDispose(@LLVMDisasmContextRef long DC);

    /**
     * Disassemble a single instruction using the disassembler context specified in
     * the parameter DC.  The bytes of the instruction are specified in the
     * parameter Bytes, and contains at least BytesSize number of bytes.  The
     * instruction is at the address specified by the PC parameter.  If a valid
     * instruction can be disassembled, its string is returned indirectly in
     * OutString whose size is specified in the parameter OutStringSize.  This
     * function returns the number of bytes in the instruction or zero if there was
     * no valid instruction.
     */
    public static native @SizeT long LLVMDisasmInstruction(
            @LLVMDisasmContextRef long DC, @Pointer("uint8_t *") long Bytes,
            @Unsigned("uint64_t") long BytesSize, @Unsigned("uint64_t") long PC,
            @Pointer("char *") long OutString, @SizeT long OutStringSize
    );

}
