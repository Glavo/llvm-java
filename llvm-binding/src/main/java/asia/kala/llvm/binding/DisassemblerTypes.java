package asia.kala.llvm.binding;

import asia.kala.llvm.binding.annotations.Pointer;

import java.lang.annotation.*;

public final class DisassemblerTypes extends LLVMLoader {
    private DisassemblerTypes() {
    }

    @Pointer("LLVMDisasmContextRef")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMDisasmContextRef {
    }

    @Pointer("LLVMOpInfoCallback")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMOpInfoCallback {
    }

    /*
        struct LLVMOpInfoSymbol1 {
            uint64_t Present;
            const char *Name;
            uint64_t Value;
        };

        struct LLVMOpInfo1 {
            struct LLVMOpInfoSymbol1 AddSymbol;
            struct LLVMOpInfoSymbol1 SubtractSymbol;
            uint64_t Value;
            uint64_t VariantKind;
        };
     */

    /*
     * The operand VariantKinds for symbolic disassembly.
     */

    public static final int LLVMDisassembler_VariantKind_None = 0; /* all targets */

    /*
     * The ARM target VariantKinds.
     */
    public static final int LLVMDisassembler_VariantKind_ARM_HI16 = 1; /* :upper16: */
    public static final int LLVMDisassembler_VariantKind_ARM_LO16 = 2; /* :lower16: */

    /*
     * The ARM64 target VariantKinds.
     */

    public static final int LLVMDisassembler_VariantKind_ARM64_PAGE = 1; /* @page */
    public static final int LLVMDisassembler_VariantKind_ARM64_PAGEOFF = 2; /* @pageoff */
    public static final int LLVMDisassembler_VariantKind_ARM64_GOTPAGE = 3; /* @gotpage */
    public static final int LLVMDisassembler_VariantKind_ARM64_GOTPAGEOFF = 4; /* @gotpageoff */
    public static final int LLVMDisassembler_VariantKind_ARM64_TLVP = 5; /* @tvlppage */
    public static final int LLVMDisassembler_VariantKind_ARM64_TLVOFF = 6; /* @tvlppageoff */

    @Pointer("LLVMSymbolLookupCallback")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMSymbolLookupCallback {
    }

    /*
     * The reference types on input and output.
     */

    /**
     * No input reference type or no output reference type.
     */
    public static final int LLVMDisassembler_ReferenceType_InOut_None = 0;

    /**
     * The input reference is from a branch instruction.
     */
    public static final int LLVMDisassembler_ReferenceType_In_Branch = 1;
    /**
     * The input reference is from a PC relative load instruction.
     */
    public static final int LLVMDisassembler_ReferenceType_In_PCrel_Load = 2;

    /**
     * The input reference is from an ARM64::ADRP instruction.
     */
    public static final long LLVMDisassembler_ReferenceType_In_ARM64_ADRP = 0x100000001L;
    /**
     * The input reference is from an ARM64::ADDXri instruction.
     */
    public static final long LLVMDisassembler_ReferenceType_In_ARM64_ADDXri = 0x100000002L;
    /**
     * The input reference is from an ARM64::LDRXui instruction.
     */
    public static final long LLVMDisassembler_ReferenceType_In_ARM64_LDRXui = 0x100000003L;
    /**
     * The input reference is from an ARM64::LDRXl instruction.
     */
    public static final long LLVMDisassembler_ReferenceType_In_ARM64_LDRXl = 0x100000004L;
    /**
     * The input reference is from an ARM64::ADR instruction.
     */
    public static final long LLVMDisassembler_ReferenceType_In_ARM64_ADR = 0x100000005L;

    /**
     * The output reference is to as symbol stub.
     */
    public static final int LLVMDisassembler_ReferenceType_Out_SymbolStub = 1;
    /**
     * The output reference is to a symbol address in a literal pool.
     */
    public static final int LLVMDisassembler_ReferenceType_Out_LitPool_SymAddr = 2;
    /**
     * The output reference is to a cstring address in a literal pool.
     */
    public static final int LLVMDisassembler_ReferenceType_Out_LitPool_CstrAddr = 3;

    /**
     * The output reference is to a Objective-C CoreFoundation string.
     */
    public static final int LLVMDisassembler_ReferenceType_Out_Objc_CFString_Ref = 4;
    /**
     * The output reference is to a Objective-C message.
     */
    public static final int LLVMDisassembler_ReferenceType_Out_Objc_Message = 5;
    /**
     * The output reference is to a Objective-C message ref.
     */
    public static final int LLVMDisassembler_ReferenceType_Out_Objc_Message_Ref = 6;
    /**
     * The output reference is to a Objective-C selector ref.
     */
    public static final int LLVMDisassembler_ReferenceType_Out_Objc_Selector_Ref = 7;
    /**
     * The output reference is to a Objective-C class ref.
     */
    public static final int LLVMDisassembler_ReferenceType_Out_Objc_Class_Ref = 8;

    /**
     * The output reference is to a C++ symbol name.
     */
    public static final int LLVMDisassembler_ReferenceType_DeMangled_Name = 9;
}
