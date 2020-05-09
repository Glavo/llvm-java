package asia.kala.llvm.binding;

import asia.kala.llvm.binding.annotations.*;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static asia.kala.llvm.binding.Types.LLVMMemoryBufferRef;
import static asia.kala.llvm.binding.Types.LLVMModuleRef;

@CInfo(
        fileName = "lto.c",
        include = {
                "<llvm-c/lto.h>",
                "llvm-java.h"
        }
)
public final class lto extends LLVMLoader {
    private lto() {
    }

    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface lto_bool_t {
    }

    public static final int LTO_API_VERSION = LTO_API_VERSION();

    @Statement("return LTO_API_VERSION;")
    private static native int LTO_API_VERSION();

    /**
     * @since LTO_API_VERSION 3
     */
    @CEnum("lto_symbol_attributes")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface lto_symbol_attributes {
        int
                LTO_SYMBOL_ALIGNMENT_MASK = 0x0000001F, /* log2 of alignment */
                LTO_SYMBOL_PERMISSIONS_MASK = 0x000000E0,
                LTO_SYMBOL_PERMISSIONS_CODE = 0x000000A0,
                LTO_SYMBOL_PERMISSIONS_DATA = 0x000000C0,
                LTO_SYMBOL_PERMISSIONS_RODATA = 0x00000080,
                LTO_SYMBOL_DEFINITION_MASK = 0x00000700,
                LTO_SYMBOL_DEFINITION_REGULAR = 0x00000100,
                LTO_SYMBOL_DEFINITION_TENTATIVE = 0x00000200,
                LTO_SYMBOL_DEFINITION_WEAK = 0x00000300,
                LTO_SYMBOL_DEFINITION_UNDEFINED = 0x00000400,
                LTO_SYMBOL_DEFINITION_WEAKUNDEF = 0x00000500,
                LTO_SYMBOL_SCOPE_MASK = 0x00003800,
                LTO_SYMBOL_SCOPE_INTERNAL = 0x00000800,
                LTO_SYMBOL_SCOPE_HIDDEN = 0x00001000,
                LTO_SYMBOL_SCOPE_PROTECTED = 0x00002000,
                LTO_SYMBOL_SCOPE_DEFAULT = 0x00001800,
                LTO_SYMBOL_SCOPE_DEFAULT_CAN_BE_HIDDEN = 0x00002800,
                LTO_SYMBOL_COMDAT = 0x00004000,
                LTO_SYMBOL_ALIAS = 0x00008000;
    }

    @CEnum("lto_debug_model")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface lto_debug_model {
        int LTO_DEBUG_MODEL_NONE = 0;
        int LTO_DEBUG_MODEL_DWARF = 1;
    }

    @CEnum("lto_codegen_model")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface lto_codegen_model {
        int
                LTO_CODEGEN_PIC_MODEL_STATIC = 0,
                LTO_CODEGEN_PIC_MODEL_DYNAMIC = 1,
                LTO_CODEGEN_PIC_MODEL_DYNAMIC_NO_PIC = 2,
                LTO_CODEGEN_PIC_MODEL_DEFAULT = 3;
    }

    @Pointer("lto_module_t")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface lto_module_t {
    }

    @Pointer("lto_code_gen_t")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface lto_code_gen_t {
    }

    @Pointer("thinlto_code_gen_t")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface thinlto_code_gen_t {
    }

    // TODO
}
