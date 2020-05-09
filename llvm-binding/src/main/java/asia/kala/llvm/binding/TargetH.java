package asia.kala.llvm.binding;

import asia.kala.llvm.binding.annotations.*;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static asia.kala.llvm.binding.Types.*;

@CInfo(
        fileName = "Target.c",
        include = {
                "<llvm-c/Target.h>",
                "llvm-java.h"
        }
)
public final class TargetH extends LLVMLoader {
    private TargetH() {
    }

    @CEnum("LLVMByteOrdering")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMByteOrdering {
        int LLVMBigEndian = 0;
        int LLVMLittleEndian = 1;
    }

    @Pointer("LLVMTargetDataRef")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMTargetDataRef {
    }

    @Pointer("LLVMTargetLibraryInfoRef")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMTargetLibraryInfoRef {
    }

    public static native void LLVMInitializeAArch64TargetInfo();

    public static native void LLVMInitializeAMDGPUTargetInfo();

    public static native void LLVMInitializeARMTargetInfo();

    public static native void LLVMInitializeBPFTargetInfo();

    public static native void LLVMInitializeHexagonTargetInfo();

    public static native void LLVMInitializeLanaiTargetInfo();

    public static native void LLVMInitializeMipsTargetInfo();

    public static native void LLVMInitializeMSP430TargetInfo();

    public static native void LLVMInitializeNVPTXTargetInfo();

    public static native void LLVMInitializePowerPCTargetInfo();

    public static native void LLVMInitializeRISCVTargetInfo();

    public static native void LLVMInitializeSparcTargetInfo();

    public static native void LLVMInitializeSystemZTargetInfo();

    public static native void LLVMInitializeWebAssemblyTargetInfo();

    public static native void LLVMInitializeX86TargetInfo();

    public static native void LLVMInitializeXCoreTargetInfo();


    public static native void LLVMInitializeAArch64Target();

    public static native void LLVMInitializeAMDGPUTarget();

    public static native void LLVMInitializeARMTarget();

    public static native void LLVMInitializeBPFTarget();

    public static native void LLVMInitializeHexagonTarget();

    public static native void LLVMInitializeLanaiTarget();

    public static native void LLVMInitializeMipsTarget();

    public static native void LLVMInitializeMSP430Target();

    public static native void LLVMInitializeNVPTXTarget();

    public static native void LLVMInitializePowerPCTarget();

    public static native void LLVMInitializeRISCVTarget();

    public static native void LLVMInitializeSparcTarget();

    public static native void LLVMInitializeSystemZTarget();

    public static native void LLVMInitializeWebAssemblyTarget();

    public static native void LLVMInitializeX86Target();

    public static native void LLVMInitializeXCoreTarget();


    public static native void LLVMInitializeAArch64TargetMC();

    public static native void LLVMInitializeAMDGPUTargetMC();

    public static native void LLVMInitializeARMTargetMC();

    public static native void LLVMInitializeBPFTargetMC();

    public static native void LLVMInitializeHexagonTargetMC();

    public static native void LLVMInitializeLanaiTargetMC();

    public static native void LLVMInitializeMipsTargetMC();

    public static native void LLVMInitializeMSP430TargetMC();

    public static native void LLVMInitializeNVPTXTargetMC();

    public static native void LLVMInitializePowerPCTargetMC();

    public static native void LLVMInitializeRISCVTargetMC();

    public static native void LLVMInitializeSparcTargetMC();

    public static native void LLVMInitializeSystemZTargetMC();

    public static native void LLVMInitializeWebAssemblyTargetMC();

    public static native void LLVMInitializeX86TargetMC();

    public static native void LLVMInitializeXCoreTargetMC();


    public static native void LLVMInitializeAArch64AsmPrinter();

    public static native void LLVMInitializeAMDGPUAsmPrinter();

    public static native void LLVMInitializeARMAsmPrinter();

    public static native void LLVMInitializeBPFAsmPrinter();

    public static native void LLVMInitializeHexagonAsmPrinter();

    public static native void LLVMInitializeLanaiAsmPrinter();

    public static native void LLVMInitializeMipsAsmPrinter();

    public static native void LLVMInitializeMSP430AsmPrinter();

    public static native void LLVMInitializeNVPTXAsmPrinter();

    public static native void LLVMInitializePowerPCAsmPrinter();

    public static native void LLVMInitializeRISCVAsmPrinter();

    public static native void LLVMInitializeSparcAsmPrinter();

    public static native void LLVMInitializeSystemZAsmPrinter();

    public static native void LLVMInitializeWebAssemblyAsmPrinter();

    public static native void LLVMInitializeX86AsmPrinter();

    public static native void LLVMInitializeXCoreAsmPrinter();

    public static native void LLVMInitializeAArch64AsmParser();

    public static native void LLVMInitializeAMDGPUAsmParser();

    public static native void LLVMInitializeARMAsmParser();

    public static native void LLVMInitializeBPFAsmParser();

    public static native void LLVMInitializeHexagonAsmParser();

    public static native void LLVMInitializeLanaiAsmParser();

    public static native void LLVMInitializeMipsAsmParser();

    public static native void LLVMInitializeMSP430AsmParser();

    public static void LLVMInitializeNVPTXAsmParser() {
        throw new UnsupportedOperationException("LLVMInitializeNVPTXAsmParser");
    }

    public static native void LLVMInitializePowerPCAsmParser();

    public static native void LLVMInitializeRISCVAsmParser();

    public static native void LLVMInitializeSparcAsmParser();

    public static native void LLVMInitializeSystemZAsmParser();

    public static native void LLVMInitializeWebAssemblyAsmParser();

    public static native void LLVMInitializeX86AsmParser();

    public static void LLVMInitializeXCoreAsmParser() {
        throw new UnsupportedOperationException("LLVMInitializeXCoreAsmParser");
    }

    public static native void LLVMInitializeAArch64Disassembler();

    public static native void LLVMInitializeAMDGPUDisassembler();

    public static native void LLVMInitializeARMDisassembler();

    public static native void LLVMInitializeBPFDisassembler();

    public static native void LLVMInitializeHexagonDisassembler();

    public static native void LLVMInitializeLanaiDisassembler();

    public static native void LLVMInitializeMipsDisassembler();

    public static native void LLVMInitializeMSP430Disassembler();

    public static void LLVMInitializeNVPTXDisassembler() {
        throw new UnsupportedOperationException("LLVMInitializeNVPTXDisassembler");
    }

    public static native void LLVMInitializePowerPCDisassembler();

    public static native void LLVMInitializeRISCVDisassembler();

    public static native void LLVMInitializeSparcDisassembler();

    public static native void LLVMInitializeSystemZDisassembler();

    public static native void LLVMInitializeWebAssemblyDisassembler();

    public static native void LLVMInitializeX86Disassembler();

    public static native void LLVMInitializeXCoreDisassembler();


    public static native void LLVMInitializeAllTargets();

    public static native void LLVMInitializeAllTargetMCs();

    public static native void LLVMInitializeAllAsmPrinters();

    public static native void LLVMInitializeAllAsmParsers();

    public static native void LLVMInitializeAllDisassemblers();

    public static native boolean LLVMInitializeNativeTarget();

    public static native boolean LLVMInitializeNativeAsmParser();

    public static native boolean LLVMInitializeNativeAsmPrinter();

    public static native boolean LLVMInitializeNativeDisassembler();

    /*===-- Target Data -------------------------------------------------------===*/

    /**
     * Obtain the data layout for a module.
     */
    public static native @LLVMTargetDataRef long LLVMGetModuleDataLayout(@LLVMModuleRef long M);

    /**
     * Set the data layout for a module.
     */
    public static native void LLVMSetModuleDataLayout(@LLVMModuleRef long M, @LLVMTargetDataRef long DL);

    /**
     * Creates target data from a target layout string.
     * See the constructor llvm::DataLayout::DataLayout.
     */
    public static native @LLVMTargetDataRef long LLVMCreateTargetData(@Pointer("const char *") long StringRep);

    /**
     * Deallocates a TargetData.
     * See the destructor llvm::DataLayout::~DataLayout.
     */
    public static native void LLVMDisposeTargetData(@LLVMTargetDataRef long TD);

    /**
     * Adds target library information to a pass manager. This does not take
     * ownership of the target library info.
     * See the method llvm::PassManagerBase::add.
     */
    public static native void LLVMAddTargetLibraryInfo(
            @LLVMTargetLibraryInfoRef long TLI, @LLVMPassManagerRef long PM
    );

    /**
     * Converts target data to a target layout string. The string must be disposed
     * with LLVMDisposeMessage.
     * See the constructor llvm::DataLayout::DataLayout.
     */
    public static native @Pointer("char *") long LLVMCopyStringRepOfTargetData(@LLVMTargetDataRef long TD);

    /**
     * Returns the byte order of a target, either LLVMBigEndian or
     * LLVMLittleEndian.
     * See the method llvm::DataLayout::isLittleEndian.
     */
    public static native @LLVMByteOrdering int LLVMByteOrder(@LLVMTargetDataRef long TD);

    /**
     * Returns the pointer size in bytes for a target.
     * See the method llvm::DataLayout::getPointerSize.
     */
    public static native @Unsigned int LLVMPointerSize(@LLVMTargetDataRef long TD);

    /**
     * Returns the pointer size in bytes for a target for a specified
     * address space.
     * See the method llvm::DataLayout::getPointerSize.
     */
    public static native @Unsigned int LLVMPointerSizeForAS(@LLVMTargetDataRef long TD, @Unsigned int AS);

    /**
     * Returns the integer type that is the same size as a pointer on a target.
     * See the method llvm::DataLayout::getIntPtrType.
     */
    public static native @LLVMTypeRef long LLVMIntPtrType(@LLVMTargetDataRef long TD);

    /**
     * Returns the integer type that is the same size as a pointer on a target.
     * This version allows the address space to be specified.
     * See the method llvm::DataLayout::getIntPtrType.
     */
    public static native @LLVMTypeRef long LLVMIntPtrTypeForAS(@LLVMTargetDataRef long TD, @Unsigned int AS);

    /**
     * Returns the integer type that is the same size as a pointer on a target.
     * See the method llvm::DataLayout::getIntPtrType.
     */
    public static native @LLVMTypeRef long LLVMIntPtrTypeInContext(@LLVMContextRef long C, @LLVMTargetDataRef long TD);

    /**
     * Returns the integer type that is the same size as a pointer on a target.
     * This version allows the address space to be specified.
     * See the method llvm::DataLayout::getIntPtrType.
     */
    public static native @LLVMTypeRef long LLVMIntPtrTypeForASInContext(@LLVMContextRef long C, @LLVMTargetDataRef long TD,
                                                                        @Unsigned int AS);

    /**
     * Computes the size of a type in bytes for a target.
     * See the method llvm::DataLayout::getTypeSizeInBits.
     */
    public static native @Unsigned("unsigned long long") long LLVMSizeOfTypeInBits(@LLVMTargetDataRef long TD, @LLVMTypeRef long Ty);

    /**
     * Computes the storage size of a type in bytes for a target.
     * See the method llvm::DataLayout::getTypeStoreSize.
     */
    public static native @Unsigned("unsigned long long") long LLVMStoreSizeOfType(@LLVMTargetDataRef long TD, @LLVMTypeRef long Ty);

    /**
     * Computes the ABI size of a type in bytes for a target.
     * See the method llvm::DataLayout::getTypeAllocSize.
     */
    public static native @Unsigned("unsigned long long") long LLVMABISizeOfType(@LLVMTargetDataRef long TD, @LLVMTypeRef long Ty);

    /**
     * Computes the ABI alignment of a type in bytes for a target.
     * See the method llvm::DataLayout::getTypeABISize.
     */
    public static native @Unsigned int LLVMABIAlignmentOfType(@LLVMTargetDataRef long TD, @LLVMTypeRef long Ty);

    /**
     * Computes the call frame alignment of a type in bytes for a target.
     * See the method llvm::DataLayout::getTypeABISize.
     */
    public static native @Unsigned int LLVMCallFrameAlignmentOfType(@LLVMTargetDataRef long TD, @LLVMTypeRef long Ty);

    /**
     * Computes the preferred alignment of a type in bytes for a target.
     * See the method llvm::DataLayout::getTypeABISize.
     */
    public static native @Unsigned int LLVMPreferredAlignmentOfType(@LLVMTargetDataRef long TD, @LLVMTypeRef long Ty);

    /**
     * Computes the preferred alignment of a global variable in bytes for a target.
     * See the method llvm::DataLayout::getPreferredAlignment.
     */
    public static native @Unsigned int LLVMPreferredAlignmentOfGlobal(
            @LLVMTargetDataRef long TD, @LLVMValueRef long GlobalVar
    );

    /**
     * Computes the structure element that contains the byte offset for a target.
     * See the method llvm::StructLayout::getElementContainingOffset.
     */
    public static native @Unsigned int LLVMElementAtOffset(
            @LLVMTargetDataRef long TD, @LLVMTypeRef long StructTy,
            @Unsigned("unsigned long long") long Offset
    );

    /**
     * Computes the byte offset of the indexed struct element for a target.
     * See the method llvm::StructLayout::getElementContainingOffset.
     */
    public static native @Unsigned("unsigned long long") long LLVMOffsetOfElement(
            @LLVMTargetDataRef long TD,
            @LLVMTypeRef long StructTy, @Unsigned int Element
    );
}
