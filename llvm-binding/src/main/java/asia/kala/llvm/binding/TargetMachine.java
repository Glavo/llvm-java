package asia.kala.llvm.binding;

import asia.kala.llvm.binding.annotations.CEnum;
import asia.kala.llvm.binding.annotations.CInfo;
import asia.kala.llvm.binding.annotations.Pointer;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static asia.kala.llvm.binding.Types.*;
import static asia.kala.llvm.binding.TargetH.*;

@CInfo(
        fileName = "TargetMachine.c",
        include = {
                "<llvm-c/TargetMachine.h>",
                "llvm-java.h"
        }
)
public final class TargetMachine extends LLVMLoader {
    private TargetMachine() {
    }

    @Pointer("LLVMTargetMachineRef")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMTargetMachineRef {
    }

    @Pointer("LLVMTargetRef")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMTargetRef {
    }


    @CEnum("LLVMCodeGenOptLevel")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMCodeGenOptLevel {
        int LLVMCodeGenLevelNone = 0;
        int LLVMCodeGenLevelLess = 1;
        int LLVMCodeGenLevelDefault = 2;
        int LLVMCodeGenLevelAggressive = 3;
    }

    @CEnum("LLVMRelocMode")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMRelocMode {
        int LLVMRelocDefault = 0;
        int LLVMRelocStatic = 1;
        int LLVMRelocPIC = 2;
        int LLVMRelocDynamicNoPic = 3;
        int LLVMRelocROPI = 4;
        int LLVMRelocRWPI = 5;
        int LLVMRelocROPI_RWPI = 6;
    }

    @CEnum("LLVMCodeModel")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMCodeModel {
        int LLVMCodeModelDefault = 0;
        int LLVMCodeModelJITDefault = 1;
        int LLVMCodeModelTiny = 2;
        int LLVMCodeModelSmall = 3;
        int LLVMCodeModelKernel = 4;
        int LLVMCodeModelMedium = 5;
        int LLVMCodeModelLarge = 6;
    }

    @CEnum("LLVMCodeGenFileType")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMCodeGenFileType {
        int LLVMAssemblyFile = 0;
        int LLVMObjectFile = 1;
    }

    /**
     * Returns the first llvm::Target in the registered targets list.
     */
    public static native @LLVMTargetRef long LLVMGetFirstTarget();

    /**
     * Returns the next llvm::Target given a previous one (or null if there's none)
     */
    public static native @LLVMTargetRef long LLVMGetNextTarget(@LLVMTargetRef long T);

    /*===-- Target ------------------------------------------------------------===*/

    /**
     * Finds the target corresponding to the given name and stores it in \p T.
     * Returns 0 on success.
     */
    public static native @LLVMTargetRef long LLVMGetTargetFromName(@Pointer("const char *") long Name);

    /**
     * Finds the target corresponding to the given triple and stores it in \p T.
     * Returns 0 on success. Optionally returns any error in ErrorMessage.
     * Use LLVMDisposeMessage to dispose the message.
     */
    public static native boolean LLVMGetTargetFromTriple(
            @Pointer("const char *") long Triple, @Pointer("LLVMTargetRef *") long T,
                                                         @Pointer("char **") long ErrorMessage
    );

    /**
     * Returns the name of a target. See llvm::Target::getName
     */
    public static native @Pointer("const char *") long LLVMGetTargetName(@LLVMTargetRef long T);

    /**
     * Returns the description  of a target. See llvm::Target::getDescription
     */
    public static native @Pointer("const char *") long LLVMGetTargetDescription(@LLVMTargetRef long T);

    /**
     * Returns if the target has a JIT
     */
    public static native boolean LLVMTargetHasJIT(@LLVMTargetRef long T);

    /**
     * Returns if the target has a TargetMachine associated
     */
    public static native boolean LLVMTargetHasTargetMachine(@LLVMTargetRef long T);

    /**
     * Returns if the target as an ASM backend (required for emitting output)
     */
    public static native boolean LLVMTargetHasAsmBackend(@LLVMTargetRef long T);

    /*===-- Target Machine ----------------------------------------------------===*/

    /**
     * Creates a new llvm::TargetMachine. See llvm::Target::createTargetMachine
     */
    public static native @LLVMTargetMachineRef long LLVMCreateTargetMachine(
            @LLVMTargetRef long T,
            @Pointer("const char *") long Triple, @Pointer("const char *") long CPU, @Pointer("const char *") long Features,
            @LLVMCodeGenOptLevel int Level, @LLVMRelocMode int Reloc, @LLVMCodeModel int CodeModel
    );

    /**
     * Dispose the LLVMTargetMachineRef instance generated by
     * LLVMCreateTargetMachine.
     */
    public static native void LLVMDisposeTargetMachine(@LLVMTargetMachineRef long T);

    /**
     * Returns the Target used in a TargetMachine
     */
    public static native @LLVMTargetRef long LLVMGetTargetMachineTarget(@LLVMTargetMachineRef long T);

    /**
     * Returns the triple used creating this target machine. See
     * llvm::TargetMachine::getTriple. The result needs to be disposed with
     * LLVMDisposeMessage.
     */
    public static native @Pointer("char *") long LLVMGetTargetMachineTriple(@LLVMTargetMachineRef long T);

    /**
     * Returns the cpu used creating this target machine. See
     * llvm::TargetMachine::getCPU. The result needs to be disposed with
     * LLVMDisposeMessage.
     */
    public static native @Pointer("char *") long LLVMGetTargetMachineCPU(@LLVMTargetMachineRef long T);

    /**
     * Returns the feature string used creating this target machine. See
     * llvm::TargetMachine::getFeatureString. The result needs to be disposed with
     * LLVMDisposeMessage.
     */
    public static native @Pointer("char *") long LLVMGetTargetMachineFeatureString(@LLVMTargetMachineRef long T);

    /**
     * Create a DataLayout based on the targetMachine.
     */
    public static native @LLVMTargetDataRef long LLVMCreateTargetDataLayout(@LLVMTargetMachineRef long T);

    /**
     * Set the target machine's ASM verbosity.
     */
    public static native void LLVMSetTargetMachineAsmVerbosity(@LLVMTargetMachineRef long T,
                                                               boolean VerboseAsm);

    /**
     * Emits an asm or object file for the given module to the filename. This
     * wraps several c++ only classes (among them a file stream). Returns any
     * error in ErrorMessage. Use LLVMDisposeMessage to dispose the message.
     */
    public static native boolean LLVMTargetMachineEmitToFile(
            @LLVMTargetMachineRef long T, @LLVMModuleRef long M,
            @Pointer("char *") long Filename, @LLVMCodeGenFileType int codegen, @Pointer("char **") long ErrorMessage
    );

    /**
     * Compile the LLVM IR stored in \p M and store the result in \p OutMemBuf.
     */
    public static native boolean LLVMTargetMachineEmitToMemoryBuffer(
            @LLVMTargetMachineRef long T, @LLVMModuleRef long M,
            @LLVMCodeGenFileType int codegen, @Pointer("char**") long ErrorMessage, @Pointer("LLVMMemoryBufferRef *") long OutMemBuf
    );

    /*===-- Triple ------------------------------------------------------------===*/

    /**
     * Get a triple for the host machine as a string. The result needs to be
     * disposed with LLVMDisposeMessage.
     */
    public static native @Pointer("char*") long LLVMGetDefaultTargetTriple();

    /**
     * Normalize a target triple. The result needs to be disposed with
     * LLVMDisposeMessage.
     */
    public static native @Pointer("char*") long LLVMNormalizeTargetTriple(@Pointer("const char *") long triple);

    /**
     * Get the host CPU as a string. The result needs to be disposed with
     * LLVMDisposeMessage.
     */
    public static native @Pointer("char*") long LLVMGetHostCPUName();

    /**
     * Get the host CPU's features as a string. The result needs to be disposed
     * with LLVMDisposeMessage.
     */
    public static native @Pointer("char*") long LLVMGetHostCPUFeatures();

    /**
     * Adds the target-specific analysis passes to the pass manager.
     */
    public static native void LLVMAddAnalysisPasses(@LLVMTargetMachineRef long T, @LLVMPassManagerRef long PM);
}
