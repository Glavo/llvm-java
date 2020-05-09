package asia.kala.llvm.binding;

import asia.kala.llvm.binding.annotations.CInfo;
import asia.kala.llvm.binding.annotations.Pointer;
import asia.kala.llvm.binding.annotations.Unsigned;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static asia.kala.llvm.binding.Types.*;
import static asia.kala.llvm.binding.ErrorH.*;
import static asia.kala.llvm.binding.TargetMachine.*;

@CInfo(
        fileName = "OrcBindings.c",
        include = {
                "<llvm-c/OrcBindings.h>",
                "llvm-java.h"
        }
)
public final class OrcBindings extends LLVMLoader {
    private OrcBindings() {
    }

    @Pointer("LLVMOrcJITStackRef")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMOrcJITStackRef {
    }

    @Unsigned("uint64_t")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMOrcModuleHandle {
    }

    @Unsigned("uint64_t")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMOrcTargetAddress {
    }

    @Pointer("LLVMOrcSymbolResolverFn")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMOrcSymbolResolverFn {
    }

    @Pointer("LLVMOrcLazyCompileCallbackFn")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMOrcLazyCompileCallbackFn {
    }

    /**
     * Create an ORC JIT stack.
     * <p>
     * The client owns the resulting stack, and must call OrcDisposeInstance(...)
     * to destroy it and free its memory. The JIT stack will take ownership of the
     * TargetMachine, which will be destroyed when the stack is destroyed. The
     * client should not attempt to dispose of the Target Machine, or it will result
     * in a double-free.
     */
    public static native @LLVMOrcJITStackRef long LLVMOrcCreateInstance(@LLVMTargetMachineRef long TM);

    /**
     * Get the error message for the most recent error (if any).
     * <p>
     * This message is owned by the ORC JIT Stack and will be freed when the stack
     * is disposed of by LLVMOrcDisposeInstance.
     */
    public static native @Pointer("const char*") long LLVMOrcGetErrorMsg(@LLVMOrcJITStackRef long JITStack);

    /**
     * Mangle the given symbol.
     * Memory will be allocated for MangledSymbol to hold the result. The client
     */
    public static native void LLVMOrcGetMangledSymbol(
            @LLVMOrcJITStackRef long JITStack, @Pointer("char **") long MangledSymbol,
            @Pointer("const char*") long Symbol
    );

    /**
     * Dispose of a mangled symbol.
     */
    public static native void LLVMOrcDisposeMangledSymbol(@Pointer("char *") long MangledSymbol);

    /**
     * Create a lazy compile callback.
     */
    public static native @LLVMErrorRef long LLVMOrcCreateLazyCompileCallback(
            @LLVMOrcJITStackRef long JITStack, @Pointer("LLVMOrcTargetAddress *") long RetAddr,
            @LLVMOrcLazyCompileCallbackFn long Callback, @Pointer("void *") long CallbackCtx
    );

    /**
     * Create a named indirect call stub.
     */
    public static native @LLVMErrorRef long LLVMOrcCreateIndirectStub(
            @LLVMOrcJITStackRef long JITStack,
            @Pointer("const char*") long StubName,
            @LLVMOrcTargetAddress long InitAddr
    );

    /**
     * Set the pointer for the given indirect stub.
     */
    public static native @LLVMErrorRef long LLVMOrcSetIndirectStubPointer(
            @LLVMOrcJITStackRef long JITStack,
            @Pointer("const char*") long StubName,
            @LLVMOrcTargetAddress long NewAddr
    );

    /**
     * Add module to be eagerly compiled.
     */
    public static native @LLVMErrorRef long LLVMOrcAddEagerlyCompiledIR(
            @LLVMOrcJITStackRef long JITStack,
            @Pointer("LLVMOrcModuleHandle *") long RetHandle,
            @LLVMModuleRef long Mod,
            @LLVMOrcSymbolResolverFn long SymbolResolver,
            @Pointer("void *") long SymbolResolverCtx
    );

    /**
     * Add module to be lazily compiled one function at a time.
     */
    public static native @LLVMErrorRef long LLVMOrcAddLazilyCompiledIR(
            @LLVMOrcJITStackRef long JITStack,
            @Pointer("LLVMOrcModuleHandle *") long RetHandle,
            @LLVMModuleRef long Mod,
            @LLVMOrcSymbolResolverFn long SymbolResolver,
            @Pointer("void *") long SymbolResolverCtx
    );

    /**
     * Add an object file.
     * <p>
     * This method takes ownership of the given memory buffer and attempts to add
     * it to the JIT as an object file.
     * Clients should *not* dispose of the 'Obj' argument: the JIT will manage it
     * from this call onwards.
     */
    public static native @LLVMErrorRef long LLVMOrcAddObjectFile(
            @LLVMOrcJITStackRef long JITStack,
            @Pointer("LLVMOrcModuleHandle *") long RetHandle,
            @LLVMMemoryBufferRef long Obj,
            @LLVMOrcSymbolResolverFn long SymbolResolver,
            @Pointer("void *") long SymbolResolverCtx
    );

    /**
     * Remove a module set from the JIT.
     * <p>
     * This works for all modules that can be added via OrcAdd*, including object
     * files.
     */
    public static native @LLVMErrorRef long LLVMOrcRemoveModule(
            @LLVMOrcJITStackRef long JITStack,
            @LLVMOrcModuleHandle long H
    );

    /**
     * Get symbol address from JIT instance.
     */
    public static native @LLVMErrorRef long LLVMOrcGetSymbolAddress(
            @LLVMOrcJITStackRef long JITStack,
            @Pointer("LLVMOrcTargetAddress *") long RetAddr,
            @Pointer("const char*") long SymbolName
    );

    /**
     * Get symbol address from JIT instance, searching only the specified
     * handle.
     */
    public static native @LLVMErrorRef long LLVMOrcGetSymbolAddressIn(
            @LLVMOrcJITStackRef long JITStack,
            @Pointer("LLVMOrcTargetAddress *") long RetAddr,
            @LLVMOrcModuleHandle long H,
            @Pointer("const char*") long SymbolName
    );

    /**
     * Dispose of an ORC JIT stack.
     */
    public static native @LLVMErrorRef long LLVMOrcDisposeInstance(@LLVMOrcJITStackRef long JITStack);

    /**
     * Register a JIT Event Listener.
     * <p>
     * A NULL listener is ignored.
     */
    public static native void LLVMOrcRegisterJITEventListener(
            @LLVMOrcJITStackRef long JITStack, @LLVMJITEventListenerRef long L
    );

    /**
     * Unegister a JIT Event Listener.
     * <p>
     * A NULL listener is ignored.
     */
    public static native void LLVMOrcUnregisterJITEventListener(
            @LLVMOrcJITStackRef long JITStack, @LLVMJITEventListenerRef long L
    );
}
