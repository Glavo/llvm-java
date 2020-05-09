package asia.kala.llvm.binding;

import asia.kala.llvm.binding.annotations.*;

import java.lang.annotation.*;

import static asia.kala.llvm.binding.Types.*;
import static asia.kala.llvm.binding.TargetH.*;
import static asia.kala.llvm.binding.TargetMachine.*;

@CInfo(
        fileName = "ExecutionEngine.c",
        include = {
                "<llvm-c/ExecutionEngine.h>",
                "llvm-java.h"
        }
)
public final class ExecutionEngine extends LLVMLoader {
    private ExecutionEngine() {
    }

    public static native void LLVMLinkInMCJIT();

    public static native void LLVMLinkInInterpreter();

    @Pointer("LLVMGenericValueRef")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMGenericValueRef {
    }

    @Pointer("LLVMExecutionEngineRef")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMExecutionEngineRef {
    }

    @Pointer("LLVMMCJITMemoryManagerRef")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMMCJITMemoryManagerRef {
    }

    @Pointer("struct LLVMMCJITCompilerOptions *")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMMCJITCompilerOptionsRef {
    }

    /*===-- Operations on generic values --------------------------------------===*/

    public static native @LLVMGenericValueRef long LLVMCreateGenericValueOfInt(
            @LLVMTypeRef long Ty,
            @Unsigned("unsigned long long") long N,
            boolean IsSigned
    );

    public static native @LLVMGenericValueRef long LLVMCreateGenericValueOfPointer(@Pointer("void *") long P);

    public static native @LLVMGenericValueRef long LLVMCreateGenericValueOfFloat(@LLVMTypeRef long Ty, double N);

    public static native @Unsigned int LLVMGenericValueIntWidth(@LLVMGenericValueRef long GenValRef);

    public static native @Unsigned("unsigned long long") long LLVMGenericValueToInt(
            @LLVMGenericValueRef long GenVal,
            boolean IsSigned
    );

    public static native @Pointer("void *") long LLVMGenericValueToPointer(@LLVMGenericValueRef long GenVal);

    public static native double LLVMGenericValueToFloat(@LLVMTypeRef long TyRef, @LLVMGenericValueRef long GenVal);

    public static native void LLVMDisposeGenericValue(@LLVMGenericValueRef long GenVal);

    /*===-- Operations on execution engines -----------------------------------===*/

    public static native boolean LLVMCreateExecutionEngineForModule(
            @Pointer("LLVMExecutionEngineRef *") long OutEE,
            @LLVMModuleRef long M,
            @Pointer("char **") long OutError
    );

    public static native boolean LLVMCreateInterpreterForModule(
            @Pointer("LLVMExecutionEngineRef *") long OutInterp,
            @LLVMModuleRef long M,
            @Pointer("char **") long OutError
    );

    public static native boolean LLVMCreateJITCompilerForModule(
            @Pointer("LLVMExecutionEngineRef *") long OutJIT,
            @LLVMModuleRef long M,
            @Unsigned int OptLevel,
            @Pointer("char **") long OutError
    );

    public static native void LLVMInitializeMCJITCompilerOptions(
            @LLVMMCJITCompilerOptionsRef long Options, @SizeT long SizeOfOptions
    );

    /**
     * Create an MCJIT execution engine for a module, with the given options. It is
     * the responsibility of the caller to ensure that all fields in Options up to
     * the given SizeOfOptions are initialized. It is correct to pass a smaller
     * value of SizeOfOptions that omits some fields. The canonical way of using
     * this is:
     * <p>
     * LLVMMCJITCompilerOptions options;
     * LLVMInitializeMCJITCompilerOptions(&options, sizeof(options));
     * ... fill in those options you care about
     * LLVMCreateMCJITCompilerForModule(&jit, mod, &options, sizeof(options),
     * &error);
     * <p>
     * Note that this is also correct, though possibly suboptimal:
     * <p>
     * LLVMCreateMCJITCompilerForModule(&jit, mod, 0, 0, &error);
     */
    public static native boolean LLVMCreateMCJITCompilerForModule(
            @Pointer("LLVMExecutionEngineRef *") long OutJIT, @LLVMModuleRef long M,
            @LLVMMCJITCompilerOptionsRef long Options, @SizeT long SizeOfOptions,
            @Pointer("char **") long OutError);

    public static native void LLVMDisposeExecutionEngine(@LLVMExecutionEngineRef long EE);

    public static native void LLVMRunStaticConstructors(@LLVMExecutionEngineRef long EE);

    public static native void LLVMRunStaticDestructors(@LLVMExecutionEngineRef long EE);

    public static native int LLVMRunFunctionAsMain(
            @LLVMExecutionEngineRef long EE, @LLVMValueRef long F,
            @Unsigned int ArgC, @Pointer("const char * const *") long ArgV,
            @Pointer("const char * const *") long EnvP
    );

    public static native @LLVMGenericValueRef long LLVMRunFunction(
            @LLVMExecutionEngineRef long EE, @LLVMValueRef long F,
            @Unsigned int NumArgs,
            @Pointer("LLVMGenericValueRef *") long Args
    );

    public static native void LLVMFreeMachineCodeForFunction(@LLVMExecutionEngineRef long EE, @LLVMValueRef long F);

    public static native void LLVMAddModule(@LLVMExecutionEngineRef long EE, @LLVMModuleRef long M);

    public static native boolean LLVMRemoveModule(
            @LLVMExecutionEngineRef long EE, @LLVMModuleRef long M,
            @Pointer("LLVMModuleRef *") long OutMod, @Pointer("char **") long OutError
    );

    public static native boolean LLVMFindFunction(
            @LLVMExecutionEngineRef long EE, @Pointer("const char *") long Name,
            @Pointer("LLVMValueRef *") long OutFn
    );

    public static native @Pointer("void *") long LLVMRecompileAndRelinkFunction(
            @LLVMExecutionEngineRef long EE,
            @LLVMValueRef long Fn
    );

    public static native @LLVMTargetDataRef long LLVMGetExecutionEngineTargetData(@LLVMExecutionEngineRef long EE);

    public static native @LLVMTargetMachineRef long LLVMGetExecutionEngineTargetMachine(@LLVMExecutionEngineRef long EE);

    public static native void LLVMAddGlobalMapping(
            @LLVMExecutionEngineRef long EE, @LLVMValueRef long Global,
            @Pointer("void *") long Addr
    );

    public static native @Pointer("void *") long LLVMGetPointerToGlobal(
            @LLVMExecutionEngineRef long EE, @LLVMValueRef long Global
    );

    public static native @Pointer("uint64_t") long LLVMGetGlobalValueAddress(
            @LLVMExecutionEngineRef long EE, @Pointer("const char *") long Name
    );

    public static native @Pointer("uint64_t") long LLVMGetFunctionAddress(
            @LLVMExecutionEngineRef long EE, @Pointer("const char *") long Name
    );

    /*===-- Operations on memory managers -------------------------------------===*/

    @Pointer("LLVMMemoryManagerAllocateCodeSectionCallback")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMMemoryManagerAllocateCodeSectionCallback {
    }

    @Pointer("LLVMMemoryManagerAllocateDataSectionCallback")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMMemoryManagerAllocateDataSectionCallback {
    }

    @Pointer("LLVMMemoryManagerFinalizeMemoryCallback")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMMemoryManagerFinalizeMemoryCallback {
    }

    @Pointer("LLVMMemoryManagerDestroyCallback")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMMemoryManagerDestroyCallback {
    }


    /**
     * Create a simple custom MCJIT memory manager. This memory manager can
     * intercept allocations in a module-oblivious way. This will return NULL
     * if any of the passed functions are NULL.
     *
     * @param Opaque              An opaque client object to pass back to the callbacks.
     * @param AllocateCodeSection Allocate a block of memory for executable code.
     * @param AllocateDataSection Allocate a block of memory for data.
     * @param FinalizeMemory      Set page permissions and flush cache. Return 0 on
     *                            success, 1 on error.
     */
    public static native @LLVMMCJITMemoryManagerRef long LLVMCreateSimpleMCJITMemoryManager(
            @Pointer("void *") long Opaque,
            @LLVMMemoryManagerAllocateCodeSectionCallback long AllocateCodeSection,
            @LLVMMemoryManagerAllocateDataSectionCallback long AllocateDataSection,
            @LLVMMemoryManagerFinalizeMemoryCallback long FinalizeMemory,
            @LLVMMemoryManagerDestroyCallback long Destroy);

    public static native void LLVMDisposeMCJITMemoryManager(@LLVMMCJITMemoryManagerRef long MM);

    /*===-- JIT Event Listener functions -------------------------------------===*/

    public static native @LLVMJITEventListenerRef long LLVMCreateGDBRegistrationListener();

    public static native @LLVMJITEventListenerRef long LLVMCreateIntelJITEventListener();

    public static native @LLVMJITEventListenerRef long LLVMCreateOProfileJITEventListener();

    public static native @LLVMJITEventListenerRef long LLVMCreatePerfJITEventListener();
}
