package asia.kala.llvm.binding;

import asia.kala.llvm.binding.annotations.*;

import java.lang.annotation.*;
import java.nio.ByteBuffer;

import static asia.kala.llvm.binding.Types.*;

@CInfo(
        fileName = "Core.c",
        include = {
                "<llvm-c/Core.h>",
                "llvm-java.h"
        }
)
public final class Core extends LLVMLoader {
    private Core() {
    }

    //region LLVMCCore Core

    /*
     * This modules provide an interface to libLLVMCore, which implements
     * the LLVM intermediate representation as well as other related types
     * and utilities.
     *
     * Many exotic languages can interoperate with C code but have a harder time
     * with C++ due to name mangling. So in addition to C, this interface enables
     * tools written in such languages.
     */

    //region LLVMCCoreTypes Types and Enumerations

    @CEnum("LLVMOpcode")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMOpcode {

        /* Terminator Instructions */
        int LLVMRet = 1;
        int LLVMBr = 2;
        int LLVMSwitch = 3;
        int LLVMIndirectBr = 4;
        int LLVMInvoke = 5;
        /* removed 6 due to API changes */
        int LLVMUnreachable = 7;
        int LLVMCallBr = 67;

        /* Standard Unary Operators */
        int LLVMFNeg = 66;

        /* Standard Binary Operators */
        int LLVMAdd = 8;
        int LLVMFAdd = 9;
        int LLVMSub = 10;
        int LLVMFSub = 11;
        int LLVMMul = 12;
        int LLVMFMul = 13;
        int LLVMUDiv = 14;
        int LLVMSDiv = 15;
        int LLVMFDiv = 16;
        int LLVMURem = 17;
        int LLVMSRem = 18;
        int LLVMFRem = 19;

        /* Logical Operators */
        int LLVMShl = 20;
        int LLVMLShr = 21;
        int LLVMAShr = 22;
        int LLVMAnd = 23;
        int LLVMOr = 24;
        int LLVMXor = 25;

        /* Memory Operators */
        int LLVMAlloca = 26;
        int LLVMLoad = 27;
        int LLVMStore = 28;
        int LLVMGetElementPtr = 29;

        /* Cast Operators */
        int LLVMTrunc = 30;
        int LLVMZExt = 31;
        int LLVMSExt = 32;
        int LLVMFPToUI = 33;
        int LLVMFPToSI = 34;
        int LLVMUIToFP = 35;
        int LLVMSIToFP = 36;
        int LLVMFPTrunc = 37;
        int LLVMFPExt = 38;
        int LLVMPtrToInt = 39;
        int LLVMIntToPtr = 40;
        int LLVMBitCast = 41;
        int LLVMAddrSpaceCast = 60;

        /* Other Operators */
        int LLVMICmp = 42;
        int LLVMFCmp = 43;
        int LLVMPHI = 44;
        int LLVMCall = 45;
        int LLVMSelect = 46;
        int LLVMUserOp1 = 47;
        int LLVMUserOp2 = 48;
        int LLVMVAArg = 49;
        int LLVMExtractElement = 50;
        int LLVMInsertElement = 51;
        int LLVMShuffleVector = 52;
        int LLVMExtractValue = 53;
        int LLVMInsertValue = 54;
        int LLVMFreeze = 68;

        /* Atomic operators */
        int LLVMFence = 55;
        int LLVMAtomicCmpXchg = 56;
        int LLVMAtomicRMW = 57;

        /* Exception Handling Operators */
        int LLVMResume = 58;
        int LLVMLandingPad = 59;
        int LLVMCleanupRet = 61;
        int LLVMCatchRet = 62;
        int LLVMCatchPad = 63;
        int LLVMCleanupPad = 64;
        int LLVMCatchSwitch = 65;
    }

    @CEnum("LLVMTypeKind")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMTypeKind {
        /**
         * type with no size
         */
        int LLVMVoidTypeKind = 0;
        /**
         * 16 bit floating point type
         */
        int LLVMHalfTypeKind = 1;
        /**
         * 32 bit floating point type
         */
        int LLVMFloatTypeKind = 2;
        /**
         * 64 bit floating point type
         */
        int LLVMDoubleTypeKind = 3;
        /**
         * 80 bit floating point type (X87)
         */
        int LLVMX86_FP80TypeKind = 4;
        /**
         * 128 bit floating point type (112-bit mantissa)
         */
        int LLVMFP128TypeKind = 5;
        /**
         * 128 bit floating point type (two 64-bits)
         */
        int LLVMPPC_FP128TypeKind = 6;
        /**
         * Labels
         */
        int LLVMLabelTypeKind = 7;
        /**
         * Arbitrary bit width integers
         */
        int LLVMIntegerTypeKind = 8;
        /**
         * Functions
         */
        int LLVMFunctionTypeKind = 9;
        /**
         * Structures
         */
        int LLVMStructTypeKind = 10;
        /**
         * Arrays
         */
        int LLVMArrayTypeKind = 11;
        /**
         * Pointers
         */
        int LLVMPointerTypeKind = 12;
        /**
         * SIMD 'packed' format, or other vector type
         */
        int LLVMVectorTypeKind = 13;
        /**
         * Metadata
         */
        int LLVMMetadataTypeKind = 14;
        /**
         * X86 MMX
         */
        int LLVMX86_MMXTypeKind = 15;
        /**
         * Tokens
         */
        int LLVMTokenTypeKind = 16;
    }

    @CEnum("LLVMLinkage")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMLinkage {
        /**
         * Externally visible function
         */
        int LLVMExternalLinkage = 0;
        int LLVMAvailableExternallyLinkage = 1;
        /**
         * Keep one copy of function when linking (inline)
         */
        int LLVMLinkOnceAnyLinkage = 2;
        /**
         * Same, but only replaced by something equivalent.
         */
        int LLVMLinkOnceODRLinkage = 3;
        /**
         * Obsolete
         */
        int LLVMLinkOnceODRAutoHideLinkage = 4;
        /**
         * Keep one copy of function when linking (weak)
         */
        int LLVMWeakAnyLinkage = 5;
        /**
         * Same, but only replaced by something equivalent.
         */
        int LLVMWeakODRLinkage = 6;
        /**
         * Special purpose, only applies to global arrays
         */
        int LLVMAppendingLinkage = 7;
        /**
         * Rename collisions when linking (static functions)
         */
        int LLVMInternalLinkage = 8;
        /**
         * Like Internal, but omit from symbol table
         */
        int LLVMPrivateLinkage = 9;
        /**
         * Obsolete
         */
        int LLVMDLLImportLinkage = 10;
        /**
         * Obsolete
         */
        int LLVMDLLExportLinkage = 11;
        /**
         * ExternalWeak linkage description
         */
        int LLVMExternalWeakLinkage = 12;
        /**
         * Obsolete
         */
        int LLVMGhostLinkage = 13;
        /**
         * Tentative definitions
         */
        int LLVMCommonLinkage = 14;
        /**
         * Like Private, but linker removes.
         */
        int LLVMLinkerPrivateLinkage = 15;
        /**
         * Like LinkerPrivate, but is weak.
         */
        int LLVMLinkerPrivateWeakLinkage = 16;
    }

    @CEnum("LLVMVisibility")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMVisibility {
        /**
         * The GV is visible
         */
        int LLVMDefaultVisibility = 0;
        /**
         * The GV is hidden
         */
        int LLVMHiddenVisibility = 1;
        /**
         * The GV is protected
         */
        int LLVMProtectedVisibility = 2;
    }

    @CEnum("LLVMUnnamedAddr")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMUnnamedAddr {
        /**
         * Address of the GV is significant.
         */
        int LLVMNoUnnamedAddr = 0;
        /**
         * Address of the GV is locally insignificant.
         */
        int LLVMLocalUnnamedAddr = 1;
        /**
         * Address of the GV is globally insignificant.
         */
        int LLVMGlobalUnnamedAddr = 2;
    }

    @CEnum("LLVMDLLStorageClass")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMDLLStorageClass {
        int LLVMDefaultStorageClass = 0;
        /**
         * Function to be imported from DLL.
         */
        int LLVMDLLImportStorageClass = 1;
        /**
         * Function to be accessible from DLL.
         */
        int LLVMDLLExportStorageClass = 2;
    }

    @CEnum("LLVMCallConv")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMCallConv {
        int LLVMCCallConv = 0;
        int LLVMFastCallConv = 8;
        int LLVMColdCallConv = 9;
        int LLVMGHCCallConv = 10;
        int LLVMHiPECallConv = 11;
        int LLVMWebKitJSCallConv = 12;
        int LLVMAnyRegCallConv = 13;
        int LLVMPreserveMostCallConv = 14;
        int LLVMPreserveAllCallConv = 15;
        int LLVMSwiftCallConv = 16;
        int LLVMCXXFASTTLSCallConv = 17;
        int LLVMX86StdcallCallConv = 64;
        int LLVMX86FastcallCallConv = 65;
        int LLVMARMAPCSCallConv = 66;
        int LLVMARMAAPCSCallConv = 67;
        int LLVMARMAAPCSVFPCallConv = 68;
        int LLVMMSP430INTRCallConv = 69;
        int LLVMX86ThisCallCallConv = 70;
        int LLVMPTXKernelCallConv = 71;
        int LLVMPTXDeviceCallConv = 72;
        int LLVMSPIRFUNCCallConv = 75;
        int LLVMSPIRKERNELCallConv = 76;
        int LLVMIntelOCLBICallConv = 77;
        int LLVMX8664SysVCallConv = 78;
        int LLVMWin64CallConv = 79;
        int LLVMX86VectorCallCallConv = 80;
        int LLVMHHVMCallConv = 81;
        int LLVMHHVMCCallConv = 82;
        int LLVMX86INTRCallConv = 83;
        int LLVMAVRINTRCallConv = 84;
        int LLVMAVRSIGNALCallConv = 85;
        int LLVMAVRBUILTINCallConv = 86;
        int LLVMAMDGPUVSCallConv = 87;
        int LLVMAMDGPUGSCallConv = 88;
        int LLVMAMDGPUPSCallConv = 89;
        int LLVMAMDGPUCSCallConv = 90;
        int LLVMAMDGPUKERNELCallConv = 91;
        int LLVMX86RegCallCallConv = 92;
        int LLVMAMDGPUHSCallConv = 93;
        int LLVMMSP430BUILTINCallConv = 94;
        int LLVMAMDGPULSCallConv = 95;
        int LLVMAMDGPUESCallConv = 96;
    }

    @CEnum("LLVMValueKind")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMValueKind {
        int LLVMArgumentValueKind = 0;
        int LLVMBasicBlockValueKind = 1;
        int LLVMMemoryUseValueKind = 2;
        int LLVMMemoryDefValueKind = 3;
        int LLVMMemoryPhiValueKind = 4;

        int LLVMFunctionValueKind = 5;
        int LLVMGlobalAliasValueKind = 6;
        int LLVMGlobalIFuncValueKind = 7;
        int LLVMGlobalVariableValueKind = 8;
        int LLVMBlockAddressValueKind = 9;
        int LLVMConstantExprValueKind = 10;
        int LLVMConstantArrayValueKind = 11;
        int LLVMConstantStructValueKind = 12;
        int LLVMConstantVectorValueKind = 13;

        int LLVMUndefValueValueKind = 14;
        int LLVMConstantAggregateZeroValueKind = 15;
        int LLVMConstantDataArrayValueKind = 16;
        int LLVMConstantDataVectorValueKind = 17;
        int LLVMConstantIntValueKind = 18;
        int LLVMConstantFPValueKind = 19;
        int LLVMConstantPointerNullValueKind = 20;
        int LLVMConstantTokenNoneValueKind = 21;

        int LLVMMetadataAsValueValueKind = 22;
        int LLVMInlineAsmValueKind = 23;

        int LLVMInstructionValueKind = 24;
    }

    @CEnum("LLVMIntPredicate")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMIntPredicate {
        /**
         * equal
         */
        int LLVMIntEQ = 32;
        /**
         * not equal
         */
        int LLVMIntNE = 33;
        /**
         * unsigned greater than
         */
        int LLVMIntUGT = 34;
        /**
         * unsigned greater or equal
         */
        int LLVMIntUGE = 35;
        /**
         * unsigned less than
         */
        int LLVMIntULT = 36;
        /**
         * unsigned less or equal
         */
        int LLVMIntULE = 37;
        /**
         * signed greater than
         */
        int LLVMIntSGT = 38;
        /**
         * signed greater or equal
         */
        int LLVMIntSGE = 39;
        /**
         * signed less than
         */
        int LLVMIntSLT = 40;
        /**
         * signed less or equal
         */
        int LLVMIntSLE = 41;
    }

    @CEnum("LLVMRealPredicate")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMRealPredicate {
        /**
         * Always false (always folded)
         */
        int LLVMRealPredicateFalse = 0;
        /**
         * True if ordered and equal
         */
        int LLVMRealOEQ = 1;
        /**
         * True if ordered and greater than
         */
        int LLVMRealOGT = 2;
        /**
         * True if ordered and greater than or equal
         */
        int LLVMRealOGE = 3;
        /**
         * True if ordered and less than
         */
        int LLVMRealOLT = 4;
        /**
         * True if ordered and less than or equal
         */
        int LLVMRealOLE = 5;
        /**
         * True if ordered and operands are unequal
         */
        int LLVMRealONE = 6;
        /**
         * True if ordered (no nans)
         */
        int LLVMRealORD = 7;
        /**
         * True if unordered: isnan(X) | isnan(Y)
         */
        int LLVMRealUNO = 8;
        /**
         * True if unordered or equal
         */
        int LLVMRealUEQ = 9;
        /**
         * True if unordered or greater than
         */
        int LLVMRealUGT = 10;
        /**
         * True if unordered, greater than, or equal
         */
        int LLVMRealUGE = 11;
        /**
         * True if unordered or less than
         */
        int LLVMRealULT = 12;
        /**
         * True if unordered, less than, or equal
         */
        int LLVMRealULE = 13;
        /**
         * True if unordered or not equal
         */
        int LLVMRealUNE = 14;
        /**
         * Always true (always folded)
         */
        int LLVMRealPredicateTrue = 15;
    }

    @CEnum("LLVMLandingPadClauseTy")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMLandingPadClauseTy {
        /**
         * A catch clause
         */
        int LLVMLandingPadCatch = 0;
        /**
         * A filter clause
         */
        int LLVMLandingPadFilter = 1;
    }

    @CEnum("LLVMThreadLocalMode")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMThreadLocalMode {
        int LLVMNotThreadLocal = 0;
        int LLVMGeneralDynamicTLSModel = 1;
        int LLVMLocalDynamicTLSModel = 2;
        int LLVMInitialExecTLSModel = 3;
        int LLVMLocalExecTLSModel = 4;
    }

    @CEnum("LLVMAtomicOrdering")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMAtomicOrdering {
        /**
         * A load or store which is not atomic
         */
        int LLVMAtomicOrderingNotAtomic = 0;
        /**
         * Lowest level of atomicity, guarantees somewhat sane results, lock free.
         */
        int LLVMAtomicOrderingUnordered = 1;
        /**
         * guarantees that if you take all the operations affecting a specific address, a consistent ordering exists
         */
        int LLVMAtomicOrderingMonotonic = 2;
        /**
         * Acquire provides a barrier of the sort necessary to acquire a lock to access other memory with normal loads and stores.
         */
        int LLVMAtomicOrderingAcquire = 4;
        /**
         * Release is similar to Acquire, but with a barrier of the sort necessary to release a lock.
         */
        int LLVMAtomicOrderingRelease = 5;
        /**
         * provides both an Acquire and a Release barrier (for fences and operations which both read and write memory).
         */
        int LLVMAtomicOrderingAcquireRelease = 6;
        /**
         * provides Acquire semantics
         * for loads and Release
         * semantics for stores.
         * Additionally, it guarantees
         * that a total ordering exists
         * between all
         * SequentiallyConsistent
         * operations.
         */
        int LLVMAtomicOrderingSequentiallyConsistent = 7;
    }

    @CEnum("LLVMAtomicRMWBinOp")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMAtomicRMWBinOp {
        /**
         * Set the new value and return the one old
         */
        int LLVMAtomicRMWBinOpXchg = 0;
        /**
         * Add a value and return the old one
         */
        int LLVMAtomicRMWBinOpAdd = 1;
        /**
         * Subtract a value and return the old one
         */
        int LLVMAtomicRMWBinOpSub = 2;
        /**
         * And a value and return the old one
         */
        int LLVMAtomicRMWBinOpAnd = 3;
        /**
         * Not-And a value and return the old one
         */
        int LLVMAtomicRMWBinOpNand = 4;
        /**
         * OR a value and return the old one
         */
        int LLVMAtomicRMWBinOpOr = 5;
        /**
         * Xor a value and return the old one
         */
        int LLVMAtomicRMWBinOpXor = 6;
        /**
         * Sets the value if it's greater than the original using a signed comparison and return the old one
         */
        int LLVMAtomicRMWBinOpMax = 7;
        /**
         * Sets the value if it's Smaller than the original using a signed comparison and return the old one
         */
        int LLVMAtomicRMWBinOpMin = 8;
        /**
         * Sets the value if it's greater than the original using an unsigned comparison and return the old one
         */
        int LLVMAtomicRMWBinOpUMax = 9;
        /**
         * Sets the value if it's greater than the original using an unsigned comparison and return the old one
         */
        int LLVMAtomicRMWBinOpUMin = 10;
        /**
         * Add a floating point value and return the old one
         */
        int LLVMAtomicRMWBinOpFAdd = 11;
        /**
         * Subtract a floating point value and return the old one
         */
        int LLVMAtomicRMWBinOpFSub = 12;
    }

    @CEnum("LLVMDiagnosticSeverity")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMDiagnosticSeverity {
        int LLVMDSError = 0;
        int LLVMDSWarning = 1;
        int LLVMDSRemark = 2;
        int LLVMDSNote = 3;
    }

    @CEnum("LLVMInlineAsmDialect")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMInlineAsmDialect {
        int LLVMInlineAsmDialectATT = 0;
        int LLVMInlineAsmDialectIntel = 1;
    }

    @CEnum("LLVMModuleFlagBehavior")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMModuleFlagBehavior {
        /**
         * Emits an error if two values disagree, otherwise the resulting value is
         * that of the operands.
         *
         * @see Module::ModFlagBehavior::Error
         */
        int LLVMModuleFlagBehaviorError = 0;
        /**
         * Emits a warning if two values disagree. The result value will be the
         * operand for the flag from the first module being linked.
         *
         * @see Module::ModFlagBehavior::Warning
         */
        int LLVMModuleFlagBehaviorWarning = 1;
        /**
         * Adds a requirement that another module flag be present and have a
         * specified value after linking is performed. The value must be a metadata
         * pair, where the first element of the pair is the ID of the module flag
         * to be restricted, and the second element of the pair is the value the
         * module flag should be restricted to. This behavior can be used to
         * restrict the allowable results (via triggering of an error) of linking
         * IDs with the **Override** behavior.
         *
         * @see Module::ModFlagBehavior::Require
         */
        int LLVMModuleFlagBehaviorRequire = 2;
        /**
         * Uses the specified value, regardless of the behavior or value of the
         * other module. If both modules specify **Override**, but the values
         * differ, an error will be emitted.
         *
         * @see Module::ModFlagBehavior::Override
         */
        int LLVMModuleFlagBehaviorOverride = 3;
        /**
         * Appends the two values, which are required to be metadata nodes.
         *
         * @see Module::ModFlagBehavior::Append
         */
        int LLVMModuleFlagBehaviorAppend = 4;
        /**
         * Appends the two values, which are required to be metadata
         * nodes. However, duplicate entries in the second list are dropped
         * during the append operation.
         *
         * @see Module::ModFlagBehavior::AppendUnique
         */
        int LLVMModuleFlagBehaviorAppendUnique = 5;
    }

    @Unsigned("LLVMAttributeIndex")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMAttributeIndex {
    }

    //endregion

    public static native void LLVMInitializeCore(@LLVMPassRegistryRef long ref);

    public static native void LLVMShutdown();

    public static native @Pointer("char *") long LLVMCreateMessage(@Pointer("const char *") long message);

    public static native void LLVMDisposeMessage(@Pointer("char *") long message);


    //region LLVMCCoreContext Contexts

    /*
     * Contexts are execution states for the core LLVM IR system.
     *
     * Most types are tied to a context instance. Multiple contexts can
     * exist simultaneously. A single context is not thread safe. However,
     * different contexts can execute on different threads simultaneously.
     */

    @Pointer("LLVMDiagnosticHandler")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMDiagnosticHandler {
    }

    @Pointer("LLVMYieldCallback")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMYieldCallback {
    }

    /**
     * Create a new context.
     * <p>
     * Every call to this function should be paired with a call to
     * LLVMContextDispose() or the context will leak memory.
     */
    public static native @LLVMContextRef long LLVMContextCreate();

    /**
     * Obtain the global context instance.
     */
    public static native @LLVMContextRef long LLVMGetGlobalContext();

    /**
     * Set the diagnostic handler for this context.
     */
    public static native void LLVMContextSetDiagnosticHandler(
            @LLVMContextRef long context, @LLVMDiagnosticHandler long handler, @Pointer("void *") long diagnosticContext
    );

    /**
     * Get the diagnostic handler of this context.
     */
    public static native @LLVMDiagnosticHandler long LLVMContextGetDiagnosticHandler(@LLVMContextRef long context);

    /**
     * Get the diagnostic context of this context.
     */
    public static native @Pointer("void *") long LLVMContextGetDiagnosticContext(@LLVMContextRef long context);

    /**
     * Set the yield callback function for this context.
     */
    public static native void LLVMContextSetYieldCallback(
            @LLVMContextRef long context, @LLVMYieldCallback long callback, @Pointer("void *") long opaqueHandle
    );

    /**
     * Retrieve whether the given context is set to discard all value names.
     */
    public static native boolean LLVMContextShouldDiscardValueNames(@LLVMContextRef long context);

    /**
     * Set whether the given context discards all value names.
     * <p>
     * If true, only the names of GlobalValue objects will be available in the IR.
     * This can be used to save memory and runtime, especially in release mode.
     */
    public static native void LLVMContextSetDiscardValueNames(@LLVMContextRef long context, boolean discard);

    /**
     * Destroy a context instance.
     * <p>
     * This should be called for every call to LLVMContextCreate() or memory
     * will be leaked.
     */
    public static native void LLVMContextDispose(@LLVMContextRef long context);

    /**
     * Return a string representation of the DiagnosticInfo. Use
     * LLVMDisposeMessage to free the string.
     */
    public static native @Pointer("char *")
    long LLVMGetDiagInfoDescription(@LLVMDiagnosticInfoRef long di);

    /**
     * Return an enum LLVMDiagnosticSeverity.
     */
    public static native @LLVMDiagnosticSeverity int LLVMGetDiagInfoSeverity(@LLVMDiagnosticInfoRef long di);

    public static native @Unsigned int LLVMGetMDKindIDInContext(
            @LLVMContextRef long context, @Pointer("const char *") long name, @Unsigned int sLen
    );

    public static native @Unsigned int LLVMGetMDKindID(@Pointer("const char *") long name, @Unsigned int sLen);

    public static native @Unsigned int LLVMGetEnumAttributeKindForName(@Pointer("const char *") long name, @SizeT long sLen);

    public static native @Unsigned int LLVMGetLastEnumAttributeKind();

    /**
     * Create an enum attribute.
     */
    public static native @LLVMAttributeRef long LLVMCreateEnumAttribute(
            @LLVMContextRef long context,
            @Unsigned int kindID,
            @Unsigned("uint64_t") long val
    );


    /**
     * Get the unique id corresponding to the enum attribute
     * passed as argument.
     */
    public static native @Unsigned int LLVMGetEnumAttributeKind(@LLVMAttributeRef long attribute);

    /**
     * Get the enum attribute's value. 0 is returned if none exists.
     */
    public static native @Unsigned("uint64_t") long LLVMGetEnumAttributeValue(@LLVMAttributeRef long attribute);

    /**
     * Create a string attribute.
     */
    public static native @LLVMAttributeRef long LLVMCreateStringAttribute(
            @LLVMContextRef long context,
            @Pointer("const char *") long k, @Unsigned int kLength,
            @Pointer("const char *") long v, @Unsigned int vLength
    );

    /**
     * Get the string attribute's kind.
     */
    public static native @Pointer("const char *")
    long LLVMGetStringAttributeKind(
            @LLVMAttributeRef long attribute,
            @Pointer("unsigned *") long length
    );

    /**
     * Get the string attribute's value.
     */
    public static native @Pointer("const char *")
    long LLVMGetStringAttributeValue(
            @LLVMAttributeRef long attribute,
            @Pointer("unsigned *") long length
    );

    public static native boolean LLVMIsEnumAttribute(@LLVMAttributeRef long attribute);

    public static native boolean LLVMIsStringAttribute(@LLVMAttributeRef long attribute);

    //endregion

    //region LLVMCCoreModule Modules

    /*
     * Modules represent the top-level structure in an LLVM program. An LLVM
     * module is effectively a translation unit or a collection of
     * translation units merged together.
     */

    /**
     * Create a new, empty module in the global context.
     * <p>
     * This is equivalent to calling LLVMModuleCreateWithNameInContext with
     * LLVMGetGlobalContext() as the context parameter.
     * <p>
     * Every invocation should be paired with LLVMDisposeModule() or memory
     * will be leaked.
     */
    public static native @LLVMModuleRef long LLVMModuleCreateWithName(@Pointer("const char *") long moduleID);

    /**
     * Create a new, empty module in a specific context.
     * <p>
     * Every invocation should be paired with LLVMDisposeModule() or memory
     * will be leaked.
     */
    public static native @LLVMModuleRef long LLVMModuleCreateWithNameInContext(
            @Pointer("const char *") long moduleID, @LLVMContextRef long context
    );

    /**
     * Return an exact copy of the specified module.
     */
    public static native @LLVMModuleRef long LLVMCloneModule(@LLVMModuleRef long module);

    /**
     * Destroy a module instance.
     * <p>
     * This must be called for every created module or memory will be
     * leaked.
     */
    public static native void LLVMDisposeModule(@LLVMModuleRef long module);

    /**
     * Obtain the identifier of a module.
     *
     * @param module Module to obtain identifier of
     * @param length Out parameter which holds the length of the returned string.
     * @return The identifier of module.
     */
    public static native @Pointer("const char *") long LLVMGetModuleIdentifier(@LLVMModuleRef long module, @Pointer("size_t *") long length);

    public static ByteBuffer LLVMGetModuleIdentifier(@LLVMModuleRef long module) {
        ByteBuffer id = LLVMGetModuleIdentifier0(module);
        return id == null ? null : id.asReadOnlyBuffer();
    }

    @Extern
    private static native ByteBuffer LLVMGetModuleIdentifier0(@LLVMModuleRef long module);

    /**
     * Set the identifier of a module to a string Ident with length Len.
     *
     * @param module     The module to set identifier
     * @param identifier The string to set modules's identifier to
     * @param length     Length of identifier
     */
    public static native void LLVMSetModuleIdentifier(
            @LLVMModuleRef long module, @Pointer("const char *") long identifier, @SizeT long length
    );

    /**
     * Obtain the module's original source file name.
     *
     * @param module Module to obtain the name of
     * @param length Out parameter which holds the length of the returned string
     * @return The original source file name of module
     */
    public static native @Pointer("const char *") long LLVMGetSourceFileName(
            @LLVMModuleRef long module, @Pointer("size_t *") long length
    );

    public static ByteBuffer LLVMGetSourceFileName(@LLVMModuleRef long module) {
        ByteBuffer name = LLVMGetSourceFileName0(module);
        return name == null ? null : name.asReadOnlyBuffer();
    }

    @Extern
    private static native ByteBuffer LLVMGetSourceFileName0(@LLVMModuleRef long module);

    /**
     * Set the original source file name of a module to a string Name with length
     * Len.
     *
     * @param module The module to set the source file name of
     * @param name   The string to set M's source file name to
     * @param length Length of Name
     * @see Module::setSourceFileName()
     */
    public static native void LLVMSetSourceFileName(@LLVMModuleRef long module, @Pointer("const char *") long name, @SizeT long length);

    /**
     * Obtain the data layout for a module.
     *
     * @see Module::getDataLayoutStr()
     * <p>
     * LLVMGetDataLayout is DEPRECATED, as the name is not only incorrect,
     * but match the name of another method on the module. Prefer the use
     * of LLVMGetDataLayoutStr, which is not ambiguous.
     */
    public static native @Pointer("const char *") long LLVMGetDataLayoutStr(@LLVMModuleRef long module);

    /**
     * @see #LLVMGetDataLayoutStr(long)
     */
    @Deprecated
    public static @Pointer("const char *") long LLVMGetDataLayout(@LLVMModuleRef long module) {
        return LLVMGetDataLayoutStr(module);
    }

    /**
     * Set the data layout for a module.
     */
    public static native void LLVMSetDataLayout(@LLVMModuleRef long module, @Pointer("const char *") long dataLayoutStr);

    /**
     * Obtain the target triple for a module.
     */
    public static native @Pointer("const char *") long LLVMGetTarget(@LLVMModuleRef long module);

    /**
     * Set the target triple for a module.
     */
    public static native void LLVMSetTarget(@LLVMModuleRef long module, @Pointer("const char *") long triple);

    /**
     * Returns the module flags as an array of flag-key-value triples.  The caller
     * is responsible for freeing this array by calling {@link #LLVMDisposeModuleFlagsMetadata}.
     */
    public static native @LLVMModuleFlagEntryRef long LLVMCopyModuleFlagsMetadata(
            @LLVMModuleRef long module, @Pointer("size_t *") long length
    );

    /**
     * Destroys module flags metadata entries.
     */
    public static native void LLVMDisposeModuleFlagsMetadata(@LLVMModuleFlagEntryRef long entries);

    /**
     * Returns the flag behavior for a module flag entry at a specific index.
     */
    public static native @LLVMModuleFlagBehavior int LLVMModuleFlagEntriesGetFlagBehavior(
            @LLVMModuleFlagEntryRef long entries, @Unsigned int index
    );

    /**
     * Returns the key for a module flag entry at a specific index.
     *
     * @see Module::ModuleFlagEntry::Key
     */
    public static native @Pointer("const char *") long LLVMModuleFlagEntriesGetKey(
            @LLVMModuleFlagEntryRef long entries, @Unsigned int index, @Pointer("size_t *") long length
    );

    /**
     * Returns the metadata for a module flag entry at a specific index.
     */
    public static native @LLVMMetadataRef long LLVMModuleFlagEntriesGetMetadata(
            @LLVMModuleFlagEntryRef long entries, @Unsigned int index
    );

    /**
     * Add a module-level flag to the module-level flags metadata if it doesn't
     * already exist.
     *
     * @see Module::getModuleFlag()
     */
    public static native @LLVMMetadataRef long LLVMGetModuleFlag(
            @LLVMModuleRef long module, @Pointer("const char *") long key, @SizeT long keyLen
    );

    /**
     * Add a module-level flag to the module-level flags metadata if it doesn't
     * already exist.
     *
     * @see Module::addModuleFlag()
     */
    public static native void LLVMAddModuleFlag(
            @LLVMModuleRef long module, @LLVMModuleFlagBehavior int behavior,
            @Pointer("const char *") long key, @SizeT long keyLen,
            @LLVMMetadataRef long val
    );

    /**
     * Dump a representation of a module to stderr.
     */
    public static native void LLVMDumpModule(@LLVMModuleRef long module);

    /**
     * Print a representation of a module to a file. The ErrorMessage needs to be
     * disposed with LLVMDisposeMessage. Returns 0 on success, 1 otherwise.
     */
    public static native boolean LLVMPrintModuleToFile(
            @LLVMModuleRef long module, @Pointer("const char *") long filename, @Pointer("char **") long errorMessage
    );

    /**
     * Return a string representation of the module. Use
     * LLVMDisposeMessage to free the string.
     */
    public static native @Pointer("char *") long LLVMPrintModuleToString(@LLVMModuleRef long module);

    /**
     * Get inline assembly for a module.
     */
    public static native @Pointer("const char *") long LLVMGetModuleInlineAsm(
            @LLVMModuleRef long module, @Pointer("size_t *") long length
    );

    /**
     * Set inline assembly for a module.
     */
    public static native void LLVMSetModuleInlineAsm2(
            @LLVMModuleRef long module, @Pointer("const char *") long asmStr, @SizeT long length
    );

    /**
     * Append inline assembly to a module.
     *
     * @see Module::appendModuleInlineAsm()
     */
    public static native void LLVMAppendModuleInlineAsm(
            @LLVMModuleRef long module, @Pointer("const char *") long asmStr, @SizeT long length
    );

    /**
     * Create the specified uniqued inline asm string.
     */
    public static native @LLVMValueRef long LLVMGetInlineAsm(
            @LLVMTypeRef long type,
            @Pointer("char *") long asmString, @SizeT long asmStringSize,
            @Pointer("char *") long constraints, @SizeT long constraintsSize,
            boolean hasSideEffects, boolean isAlignStack,
            @LLVMInlineAsmDialect int dialect);

    /**
     * Obtain the context to which this module is associated.
     */
    public static native @LLVMContextRef long LLVMGetModuleContext(@LLVMModuleRef long module);

    /**
     * Obtain a Type from a module by its registered name.
     */
    public static native @LLVMTypeRef long LLVMGetTypeByName(@LLVMModuleRef long module, @Pointer("const char *") long name);

    /**
     * Obtain an iterator to the first NamedMDNode in a Module.
     */
    public static native @LLVMNamedMDNodeRef long LLVMGetFirstNamedMetadata(@LLVMModuleRef long module);

    /**
     * Obtain an iterator to the last NamedMDNode in a Module.
     */
    public static native @LLVMNamedMDNodeRef long LLVMGetLastNamedMetadata(@LLVMModuleRef long module);

    /**
     * Advance a NamedMDNode iterator to the next NamedMDNode.
     * <p>
     * Returns NULL if the iterator was already at the end and there are no more
     * named metadata nodes.
     */
    public static native @LLVMNamedMDNodeRef long LLVMGetNextNamedMetadata(@LLVMNamedMDNodeRef long namedMDNode);

    /**
     * Decrement a NamedMDNode iterator to the previous NamedMDNode.
     * <p>
     * Returns NULL if the iterator was already at the beginning and there are
     * no previous named metadata nodes.
     */
    public static native @LLVMNamedMDNodeRef long LLVMGetPreviousNamedMetadata(@LLVMNamedMDNodeRef long namedMDNode);

    /**
     * Retrieve a NamedMDNode with the given name, returning NULL if no such
     * node exists.
     */
    public static native @LLVMNamedMDNodeRef long LLVMGetNamedMetadata(
            @LLVMModuleRef long module, @Pointer("const char *") long name, @SizeT long nameLen
    );

    /**
     * Retrieve a NamedMDNode with the given name, creating a new node if no such
     * node exists.
     */
    public static native @LLVMNamedMDNodeRef long LLVMGetOrInsertNamedMetadata(
            @LLVMModuleRef long module, @Pointer("const char *") long name, @SizeT long nameLen
    );

    /**
     * Retrieve the name of a NamedMDNode.
     */
    public static native @Pointer("const char *") long LLVMGetNamedMetadataName(
            @LLVMNamedMDNodeRef long namedMD, @Pointer("size_t *") long nameLen
    );

    /**
     * Obtain the number of operands for named metadata in a module.
     */
    public static native @Unsigned int LLVMGetNamedMetadataNumOperands(@LLVMModuleRef long module, @Pointer("const char *") long name);

    /**
     * Obtain the named metadata operands for a module.
     * <p>
     * The passed LLVMValueRef pointer should refer to an array of
     * LLVMValueRef at least LLVMGetNamedMetadataNumOperands long. This
     * array will be populated with the LLVMValueRef instances. Each
     * instance corresponds to a llvm::MDNode.
     */
    public static native void LLVMGetNamedMetadataOperands(
            @LLVMModuleRef long module, @Pointer("const char *") long name, @Pointer("LLVMValueRef *") long dest
    );

    /**
     * Add an operand to named metadata.
     */
    public static native void LLVMAddNamedMetadataOperand(
            @LLVMModuleRef long module, @Pointer("const char *") long name, @LLVMValueRef long val
    );

    /**
     * Return the directory of the debug location for this value, which must be
     * an llvm::Instruction, llvm::GlobalVariable, or llvm::Function.
     */
    public static native @Pointer("const char *") long LLVMGetDebugLocDirectory(
            @LLVMValueRef long val, @Pointer("unsigned *") long length
    );

    /**
     * Return the filename of the debug location for this value, which must be
     * an llvm::Instruction, llvm::GlobalVariable, or llvm::Function.
     */
    public static native @Pointer("const char *") long LLVMGetDebugLocFilename(
            @LLVMValueRef long val, @Pointer("unsigned *") long length
    );

    /**
     * Return the line number of the debug location for this value, which must be
     * an llvm::Instruction, llvm::GlobalVariable, or llvm::Function.
     */
    public static native @Unsigned int LLVMGetDebugLocLine(@LLVMValueRef long val);

    /**
     * Return the column number of the debug location for this value, which must be
     * an llvm::Instruction.
     */
    public static native @Unsigned int LLVMGetDebugLocColumn(@LLVMValueRef long val);

    /**
     * Add a function to a module under a specified name.
     */
    public static native @LLVMValueRef long LLVMAddFunction(
            @LLVMModuleRef long module, @Pointer("const char *") long name, @LLVMTypeRef long functionTy
    );

    /**
     * Obtain a Function value from a Module by its name.
     * <p>
     * The returned value corresponds to a llvm::Function value.
     */
    public static native @LLVMValueRef long LLVMGetNamedFunction(@LLVMModuleRef long module, @Pointer("const char *") long name);

    /**
     * Obtain an iterator to the first Function in a Module.
     */
    public static native @LLVMValueRef long LLVMGetFirstFunction(@LLVMModuleRef long module);

    /**
     * Obtain an iterator to the last Function in a Module.
     */
    public static native @LLVMValueRef long LLVMGetLastFunction(@LLVMModuleRef long module);

    /**
     * Advance a Function iterator to the next Function.
     * <p>
     * Returns NULL if the iterator was already at the end and there are no more
     * functions.
     */
    public static native @LLVMValueRef long LLVMGetNextFunction(@LLVMValueRef long fn);

    /**
     * Decrement a Function iterator to the previous Function.
     * <p>
     * Returns NULL if the iterator was already at the beginning and there are
     * no previous functions.
     */
    public static native @LLVMValueRef long LLVMGetPreviousFunction(@LLVMValueRef long fn);

    /**
     * @deprecated Use LLVMSetModuleInlineAsm2 instead.
     */
    @Deprecated
    public static native void LLVMSetModuleInlineAsm(@LLVMModuleRef long module, @Pointer("const char *") long asmStr);

    //endregion

    //region LLVMCCoreType Types

    /*
     * Types represent the type of a value.
     *
     * Types are associated with a context instance. The context internally
     * deduplicates types so there is only 1 instance of a specific type
     * alive at a time. In other words, a unique type is shared among all
     * consumers within a context.
     *
     * A Type in the C API corresponds to llvm::Type.
     *
     * Types have the following hierarchy:
     *
     *   types:
     *     integer type
     *     real type
     *     function type
     *     sequence types:
     *       array type
     *       pointer type
     *       vector type
     *     void type
     *     label type
     *     opaque type
     */

    /**
     * Obtain the enumerated type of a Type instance.
     */
    public static native @LLVMTypeKind int LLVMGetTypeKind(@LLVMTypeRef long type);

    /**
     * Whether the type has a known size.
     * <p>
     * Things that don't have a size are abstract types, labels, and void.a
     */
    public static native boolean LLVMTypeIsSized(@LLVMTypeRef long type);

    /**
     * Obtain the context to which this type instance is associated.
     */
    public static native @LLVMContextRef long LLVMGetTypeContext(@LLVMTypeRef long type);

    /**
     * Dump a representation of a type to stderr.
     */
    public static native void LLVMDumpType(@LLVMTypeRef long val);

    /**
     * Return a string representation of the type. Use
     * LLVMDisposeMessage to free the string.
     */
    public static native @Pointer("char *") long LLVMPrintTypeToString(@LLVMTypeRef long val);


    //region LLVMCCoreTypeInt Integer Types

    /* Functions in this section operate on integer types.*/

    public static native @LLVMTypeRef long LLVMInt1TypeInContext(@LLVMContextRef long context);

    public static native @LLVMTypeRef long LLVMInt8TypeInContext(@LLVMContextRef long context);

    public static native @LLVMTypeRef long LLVMInt16TypeInContext(@LLVMContextRef long context);

    public static native @LLVMTypeRef long LLVMInt32TypeInContext(@LLVMContextRef long context);

    public static native @LLVMTypeRef long LLVMInt64TypeInContext(@LLVMContextRef long context);

    public static native @LLVMTypeRef long LLVMInt128TypeInContext(@LLVMContextRef long context);

    public static native @LLVMTypeRef long LLVMIntTypeInContext(@LLVMContextRef long context, @Unsigned int numBits);


    public static native @LLVMTypeRef long LLVMInt1Type();

    public static native @LLVMTypeRef long LLVMInt8Type();

    public static native @LLVMTypeRef long LLVMInt16Type();

    public static native @LLVMTypeRef long LLVMInt32Type();

    public static native @LLVMTypeRef long LLVMInt64Type();

    public static native @LLVMTypeRef long LLVMInt128Type();

    public static native @LLVMTypeRef long LLVMIntType(@Unsigned int numBits);

    public static native @Unsigned int LLVMGetIntTypeWidth(@LLVMTypeRef long integerTy);

    //endregion

    //region LLVMCCoreTypeFloat Floating Point Types

    /**
     * Obtain a 16-bit floating point type from a context.
     */
    public static native @LLVMTypeRef long LLVMHalfTypeInContext(@LLVMContextRef long context);

    /**
     * Obtain a 32-bit floating point type from a context.
     */
    public static native @LLVMTypeRef long LLVMFloatTypeInContext(@LLVMContextRef long context);

    /**
     * Obtain a 64-bit floating point type from a context.
     */
    public static native @LLVMTypeRef long LLVMDoubleTypeInContext(@LLVMContextRef long context);

    /**
     * Obtain a 80-bit floating point type (X87) from a context.
     */
    public static native @LLVMTypeRef long LLVMX86FP80TypeInContext(@LLVMContextRef long context);

    /**
     * Obtain a 128-bit floating point type (112-bit mantissa) from a context.
     */
    public static native @LLVMTypeRef long LLVMFP128TypeInContext(@LLVMContextRef long context);

    /**
     * Obtain a 128-bit floating point type (two 64-bits) from a context.
     */
    public static native @LLVMTypeRef long LLVMPPCFP128TypeInContext(@LLVMContextRef long context);

    /**
     * Obtain a 16-bit floating point type from the global context.
     */
    public static native @LLVMTypeRef long LLVMHalfType();

    /**
     * Obtain a 32-bit floating point type from the global context.
     */
    public static native @LLVMTypeRef long LLVMFloatType();

    /**
     * Obtain a 64-bit floating point type from the global context.
     */
    public static native @LLVMTypeRef long LLVMDoubleType();

    /**
     * Obtain a 80-bit floating point type (X87) from the global context.
     */
    public static native @LLVMTypeRef long LLVMX86FP80Type();

    /**
     * Obtain a 128-bit floating point type (112-bit mantissa) from the global context.
     */
    public static native @LLVMTypeRef long LLVMFP128Type();

    /**
     * Obtain a 128-bit floating point type (two 64-bits) from the global context.
     */
    public static native @LLVMTypeRef long LLVMPPCFP128Type();

    //endregion

    //region LLVMCCoreTypeFunction Function Types

    /**
     * Obtain a function type consisting of a specified signature.
     * <p>
     * The function is defined as a tuple of a return Type, a list of
     * parameter types, and whether the function is variadic.
     */
    public static native @LLVMTypeRef long LLVMFunctionType(
            @LLVMTypeRef long returnType,
            @Pointer("LLVMTypeRef *") long paramTypes, @Unsigned int paramCount,
            boolean isVarArg
    );

    /**
     * Obtain a function type consisting of a specified signature.
     * <p>
     * The function is defined as a tuple of a return Type, a list of
     * parameter types, and whether the function is variadic.
     */
    @Extern
    public static native @LLVMTypeRef long LLVMFunctionType(
            @LLVMTypeRef long returnType, @LLVMTypeRef long[] paramTypes, boolean isVarArg
    );

    /**
     * Returns whether a function type is variadic.
     */
    public static native boolean LLVMIsFunctionVarArg(@LLVMTypeRef long functionTy);

    /**
     * Obtain the Type this function Type returns.
     */
    public static native @LLVMTypeRef long LLVMGetReturnType(@LLVMTypeRef long functionTy);

    /**
     * Obtain the number of parameters this function accepts.
     */
    public static native @Unsigned int LLVMCountParamTypes(@LLVMTypeRef long functionTy);

    /**
     * Obtain the types of a function's parameters.
     * <p>
     * The Dest parameter should point to a pre-allocated array of
     * LLVMTypeRef at least LLVMCountParamTypes() large. On return, the
     * first LLVMCountParamTypes() entries in the array will be populated
     * with LLVMTypeRef instances.
     *
     * @param functionType The function type to operate on.
     * @param dest         Memory address of an array to be filled with result.
     */
    public static native void LLVMGetParamTypes(@LLVMTypeRef long functionType, @Pointer("LLVMTypeRef *") long dest);

    /**
     * Obtain the types of a function's parameters.
     *
     * @param functionType The function type to operate on.
     */
    @Extern
    public static native @LLVMTypeRef long[] LLVMGetParamTypes(@LLVMTypeRef long functionType);

    //endregion

    //region LLVMCCoreTypeStruct Structure Types

    /**
     * Create a new structure type in a context.
     * <p>
     * A structure is specified by a list of inner elements/types and
     * whether these can be packed together.
     */
    public static native @LLVMTypeRef long LLVMStructTypeInContext(
            @LLVMContextRef long context,
            @Pointer("LLVMTypeRef *") long elementTypes, @Unsigned int elementCount,
            boolean packed
    );

    /**
     * Create a new structure type in a context.
     * <p>
     * A structure is specified by a list of inner elements/types and
     * whether these can be packed together.
     */
    @Extern
    public static native @LLVMTypeRef long LLVMStructTypeInContext(
            @LLVMContextRef long context,
            @LLVMTypeRef long[] elementTypes,
            boolean packed
    );

    /**
     * Create a new structure type in the global context.
     */
    public static native @LLVMTypeRef long LLVMStructType(
            @Pointer("LLVMTypeRef *") long elementTypes, @Unsigned int elementCount, boolean packed
    );

    public static @LLVMTypeRef long LLVMStructType(
            @LLVMTypeRef long[] elementTypes, boolean packed
    ) {
        return LLVMStructTypeInContext(LLVMGetGlobalContext(), elementTypes, packed);
    }

    /**
     * Create an empty structure in a context having a specified name.
     */
    public static native @LLVMTypeRef long LLVMStructCreateNamed(
            @LLVMContextRef long context, @Pointer("const char *") long name
    );

    /**
     * Obtain the name of a structure.
     */
    public static native @Pointer("const char *") long LLVMGetStructName(@LLVMTypeRef long type);

    /**
     * Set the contents of a structure type.
     */
    public static native void LLVMStructSetBody(
            @LLVMTypeRef long structType,
            @Pointer("LLVMTypeRef *") long elementTypes, @Unsigned int elementCount,
            boolean packed
    );

    /**
     * Set the contents of a structure type.
     */
    @Extern
    public static native void LLVMStructSetBody(
            @LLVMTypeRef long structType,
            @LLVMTypeRef long[] elementTypes,
            boolean packed
    );

    /**
     * Get the number of elements defined inside the structure.
     */
    public static native @Unsigned int LLVMCountStructElementTypes(@LLVMTypeRef long structType);

    /**
     * Get the elements within a structure.
     * <p>
     * The function is passed the address of a pre-allocated array of
     * LLVMTypeRef at least LLVMCountStructElementTypes() long. After
     * invocation, this array will be populated with the structure's
     * elements. The objects in the destination array will have a lifetime
     * of the structure type itself, which is the lifetime of the context it
     * is contained in.
     */
    public static native void LLVMGetStructElementTypes(@LLVMTypeRef long structType, @Pointer("LLVMTypeRef *") long dest);

    /**
     * Get the elements within a structure.
     */
    @Extern
    public static native @LLVMTypeRef long[] LLVMGetStructElementTypes(@LLVMTypeRef long structType);

    /**
     * Get the type of the element at a given index in the structure.
     */
    public static native @LLVMTypeRef long LLVMStructGetTypeAtIndex(@LLVMTypeRef long structType, @Unsigned int i);

    /**
     * Determine whether a structure is packed.
     */
    public static native boolean LLVMIsPackedStruct(@LLVMTypeRef long structType);

    /**
     * Determine whether a structure is opaque.
     */
    public static native boolean LLVMIsOpaqueStruct(@LLVMTypeRef long structType);

    /**
     * Determine whether a structure is literal.
     */
    public static native boolean LLVMIsLiteralStruct(@LLVMTypeRef long structType);

    //endregion

    //region LLVMCCoreTypeSequential Sequential Types

    /**
     * Obtain the type of elements within a sequential type.
     * <p>
     * This works on array, vector, and pointer types.
     */
    public static native @LLVMTypeRef long LLVMGetElementType(@LLVMTypeRef long type);

    /**
     * Returns type's subtypes
     */
    public static native void LLVMGetSubtypes(@LLVMTypeRef long type, @Pointer("LLVMTypeRef *") long array);

    /**
     * Returns type's subtypes
     */
    @Extern
    public static native @LLVMTypeRef long[] LLVMGetSubtypes(@LLVMTypeRef long type);

    /**
     * Return the number of types in the derived type.
     */
    public static native @Unsigned int LLVMGetNumContainedTypes(@LLVMTypeRef long type);

    /**
     * Create a fixed size array type that refers to a specific type.
     * <p>
     * The created type will exist in the context that its element type
     * exists in.
     */
    public static native @LLVMTypeRef long LLVMArrayType(@LLVMTypeRef long elementType, @Unsigned int elementCount);

    /**
     * Obtain the length of an array type.
     * <p>
     * This only works on types that represent arrays.
     */
    public static native @Unsigned int LLVMGetArrayLength(@LLVMTypeRef long arrayType);

    /**
     * Create a pointer type that points to a defined type.
     * <p>
     * The created type will exist in the context that its pointee type
     * exists in.
     */
    public static native @LLVMTypeRef long LLVMPointerType(@LLVMTypeRef long elementType, @Unsigned int addressSpace);

    /**
     * Obtain the address space of a pointer type.
     * <p>
     * This only works on types that represent pointers.
     */
    public static native @Unsigned int LLVMGetPointerAddressSpace(@LLVMTypeRef long pointerType);

    /**
     * Create a vector type that contains a defined type and has a specific
     * number of elements.
     * <p>
     * The created type will exist in the context thats its element type
     * exists in.
     */
    public static native @LLVMTypeRef long LLVMVectorType(@LLVMTypeRef long elementType, @Unsigned int elementCount);

    /**
     * Obtain the number of elements in a vector type.
     * <p>
     * This only works on types that represent vectors.
     */
    public static native @Unsigned int LLVMGetVectorSize(@LLVMTypeRef long vectorType);

    //endregion

    //region LLVMCCoreTypeOther Other Types

    /**
     * Create a void type in a context.
     */
    public static native @LLVMTypeRef long LLVMVoidTypeInContext(@LLVMContextRef long context);

    /**
     * Create a label type in a context.
     */
    public static native @LLVMTypeRef long LLVMLabelTypeInContext(@LLVMContextRef long context);

    /**
     * Create a X86 MMX type in a context.
     */
    public static native @LLVMTypeRef long LLVMX86MMXTypeInContext(@LLVMContextRef long context);

    /**
     * Create a token type in a context.
     */
    public static native @LLVMTypeRef long LLVMTokenTypeInContext(@LLVMContextRef long context);

    /**
     * Create a metadata type in a context.
     */
    public static native @LLVMTypeRef long LLVMMetadataTypeInContext(@LLVMContextRef long context);

    public static native @LLVMTypeRef long LLVMVoidType();

    public static native @LLVMTypeRef long LLVMLabelType();

    public static native @LLVMTypeRef long LLVMX86MMXType();

    //endregion

    //endregion

    //region LLVMCCoreValues Values

    //region LLVMCCoreValueGeneral General APIs

    /*
     * Functions in this section work on all LLVMValueRef instances,
     * regardless of their sub-type. They correspond to functions available
     * on llvm::Value.
     */

    /**
     * Obtain the type of a value.
     */
    public static native @LLVMTypeRef long LLVMTypeOf(@LLVMValueRef long value);

    /**
     * Obtain the enumerated type of a Value instance.
     */
    public static native @LLVMValueKind int LLVMGetValueKind(@LLVMValueRef long value);

    /**
     * Obtain the string name of a value.
     */
    public static native @Pointer("const char *") long LLVMGetValueName2(@LLVMValueRef long value, @Pointer("size_t *") long length);


    /**
     * Obtain the string name of a value.
     */
    public static ByteBuffer LLVMGetValueName2(@LLVMValueRef long value) {
        ByteBuffer res = _LLVMGetValueName2(value);
        return res == null ? null : res.asReadOnlyBuffer();
    }

    @Extern
    private static native ByteBuffer _LLVMGetValueName2(@LLVMValueRef long value);

    /**
     * Set the string name of a value.
     */
    public static native void LLVMSetValueName2(@LLVMValueRef long value, @Pointer("const char *") long name, @SizeT long nameLen);

    /**
     * Set the string name of a value.
     */
    public static void LLVMSetValueName2(@LLVMValueRef long value, ByteBuffer name) {
        int position = name.position();
        int length = name.remaining();

        LLVMSetValueName2(value, NativeUtils.GetDirectBufferAddress(name) + position, length);
    }

    /**
     * Dump a representation of a value to stderr.
     */
    public static native void LLVMDumpValue(@LLVMValueRef long value);

    /**
     * Return a string representation of the value. Use
     * LLVMDisposeMessage to free the string.
     */
    public static native @Pointer("char *") long LLVMPrintValueToString(@LLVMValueRef long value);

    /**
     * Replace all uses of a value with another one.
     */
    public static native void LLVMReplaceAllUsesWith(@LLVMValueRef long oldVal, @LLVMValueRef long NewVal);

    /**
     * Determine whether the specified value instance is constant.
     */
    public static native boolean LLVMIsConstant(@LLVMValueRef long value);

    /**
     * Determine whether a value instance is undefined.
     */
    public static native boolean LLVMIsUndef(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAArgument(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsABasicBlock(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAInlineAsm(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAUser(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAConstant(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsABlockAddress(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAConstantAggregateZero(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAConstantArray(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAConstantDataSequential(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAConstantDataArray(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAConstantDataVector(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAConstantExpr(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAConstantFP(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAConstantInt(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAConstantPointerNull(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAConstantStruct(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAConstantTokenNone(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAConstantVector(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAGlobalValue(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAGlobalAlias(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAGlobalIFunc(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAGlobalObject(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAFunction(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAGlobalVariable(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAUndefValue(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAInstruction(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAUnaryOperator(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsABinaryOperator(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsACallInst(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAIntrinsicInst(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsADbgInfoIntrinsic(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsADbgVariableIntrinsic(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsADbgDeclareInst(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsADbgLabelInst(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAMemIntrinsic(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAMemCpyInst(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAMemMoveInst(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAMemSetInst(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsACmpInst(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAFCmpInst(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAICmpInst(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAExtractElementInst(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAGetElementPtrInst(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAInsertElementInst(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAInsertValueInst(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsALandingPadInst(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAPHINode(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsASelectInst(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAShuffleVectorInst(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAStoreInst(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsABranchInst(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAIndirectBrInst(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAInvokeInst(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAReturnInst(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsASwitchInst(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAUnreachableInst(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAResumeInst(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsACleanupReturnInst(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsACatchReturnInst(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsACatchSwitchInst(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsACallBrInst(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAFuncletPadInst(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsACatchPadInst(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsACleanupPadInst(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAUnaryInstruction(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAAllocaInst(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsACastInst(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAAddrSpaceCastInst(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsABitCastInst(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAFPExtInst(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAFPToSIInst(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAFPToUIInst(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAFPTruncInst(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAIntToPtrInst(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAPtrToIntInst(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsASExtInst(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsASIToFPInst(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsATruncInst(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAUIToFPInst(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAZExtInst(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAExtractValueInst(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsALoadInst(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAVAArgInst(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAFreezeInst(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAAtomicCmpXchgInst(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAAtomicRMWInst(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAFenceInst(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAMDNode(@LLVMValueRef long value);

    public static native @LLVMValueRef long LLVMIsAMDString(@LLVMValueRef long value);

    /**
     * @deprecated Use LLVMGetValueName2 instead.
     */
    @Deprecated
    public static native @Pointer("const char *") long LLVMGetValueName(@LLVMValueRef long value);

    /**
     * @deprecated Use LLVMSetValueName2 instead.
     */
    @Deprecated
    public static native void LLVMSetValueName(@LLVMValueRef long value, @Pointer("const char *") long name);

    //endregion

    //region LLVMCCoreValueUses Usage

    /*
     * This module defines functions that allow you to inspect the uses of a
     * LLVMValueRef.
     *
     * It is possible to obtain an LLVMUseRef for any LLVMValueRef instance.
     * Each LLVMUseRef (which corresponds to a llvm::Use instance) holds a
     * llvm::User and llvm::Value.
     */

    /**
     * Obtain the first use of a value.
     * <p>
     * Uses are obtained in an iterator fashion. First, call this function
     * to obtain a reference to the first use. Then, call LLVMGetNextUse()
     * on that instance and all subsequently obtained instances until
     * LLVMGetNextUse() returns NULL.
     */
    public static native @LLVMUseRef long LLVMGetFirstUse(@LLVMValueRef long value);

    /**
     * Obtain the next use of a value.
     * <p>
     * This effectively advances the iterator. It returns NULL if you are on
     * the final use and no more are available.
     */
    public static native @LLVMUseRef long LLVMGetNextUse(@LLVMUseRef long U);

    /**
     * Obtain the user value for a user.
     * <p>
     * The returned value corresponds to a llvm::User type.
     */
    public static native @LLVMValueRef long LLVMGetUser(@LLVMUseRef long use);

    /**
     * Obtain the value this use corresponds to.
     */
    public static native @LLVMValueRef long LLVMGetUsedValue(@LLVMUseRef long use);

    //endregion

    //region LLVMCCoreValueUser User value

    /*
     * Function in this group pertain to LLVMValueRef instances that descent
     * from llvm::User. This includes constants, instructions, and
     * operators.
     */

    /**
     * Obtain an operand at a specific index in a llvm::User value.
     */
    public static native @LLVMValueRef long LLVMGetOperand(@LLVMValueRef long value, @Unsigned int index);

    /**
     * Obtain the use of an operand at a specific index in a llvm::User value.
     */
    public static native @LLVMUseRef long LLVMGetOperandUse(@LLVMValueRef long value, @Unsigned int index);

    /**
     * Set an operand at a specific index in a llvm::User value.
     */
    public static native void LLVMSetOperand(@LLVMValueRef long user, @Unsigned int index, @LLVMValueRef long value);

    /**
     * Obtain the number of operands in a llvm::User value.
     */
    public static native int LLVMGetNumOperands(@LLVMValueRef long value);

    //endregion

    //region LLVMCCoreValueConstant Constants

    /*
     * This section contains APIs for interacting with LLVMValueRef that
     * correspond to llvm::Constant instances.
     *
     * These functions will work for any LLVMValueRef in the llvm::Constant
     * class hierarchy.
     */

    /**
     * Obtain a constant value referring to the null instance of a type.
     */
    public static native @LLVMValueRef long LLVMConstNull(@LLVMTypeRef long type); /* all zeroes */

    /**
     * Obtain a constant value referring to the instance of a type
     * consisting of all ones.
     * <p>
     * This is only valid for integer types.
     */
    public static native @LLVMValueRef long LLVMConstAllOnes(@LLVMTypeRef long type);

    /**
     * Obtain a constant value referring to an undefined value of a type.
     */
    public static native @LLVMValueRef long LLVMGetUndef(@LLVMTypeRef long type);

    /**
     * Determine whether a value instance is null.
     */
    public static native boolean LLVMIsNull(@LLVMValueRef long vauel);

    /**
     * Obtain a constant that is a constant pointer pointing to NULL for a
     * specified type.
     */
    public static native @LLVMValueRef long LLVMConstPointerNull(@LLVMTypeRef long type);

    //region LLVMCCoreValueConstantScalar Scalar constants

    /*
     * Functions in this group model LLVMValueRef instances that correspond
     * to constants referring to scalar types.
     *
     * For integer types, the LLVMTypeRef parameter should correspond to a
     * llvm::IntegerType instance and the returned LLVMValueRef will
     * correspond to a llvm::ConstantInt.
     *
     * For floating point types, the LLVMTypeRef returned corresponds to a
     * llvm::ConstantFP.
     */

    /**
     * Obtain a constant value for an integer type.
     * <p>
     * The returned value corresponds to a llvm::ConstantInt.
     *
     * @param integerType Integer type to obtain value of.
     * @param n           The value the returned instance should refer to.
     * @param signExtend  Whether to sign extend the produced value.
     */
    public static native @LLVMValueRef long LLVMConstInt(
            @LLVMTypeRef long integerType, @LongLong long n, boolean signExtend
    );

    /**
     * Obtain a constant value for an integer of arbitrary precision.
     */
    public static native @LLVMValueRef long LLVMConstIntOfArbitraryPrecision(
            @LLVMTypeRef long integerType, @Unsigned int numWords, @Pointer("const uint64_t *") long words
    );

    /**
     * Obtain a constant value for an integer parsed from a string.
     * <p>
     * A similar API, LLVMConstIntOfStringAndSize is also available. If the
     * string's length is available, it is preferred to call that function
     * instead.
     */
    public static native @LLVMValueRef long LLVMConstIntOfString(
            @LLVMTypeRef long integerType, @Pointer("const char *") long text, @Unsigned("uint8_t") byte radix
    );

    /**
     * Obtain a constant value for an integer parsed from a string with
     * specified length.
     */
    public static native @LLVMValueRef long LLVMConstIntOfStringAndSize(
            @LLVMTypeRef long integerType,
            @Pointer("const char *") long text,
            @Unsigned int sLen,
            @Unsigned("uint8_t") byte radix
    );

    /**
     * Obtain a constant value referring to a double floating point value.
     */
    public static native @LLVMValueRef long LLVMConstReal(@LLVMTypeRef long realType, double n);

    /**
     * Obtain a constant for a floating point value parsed from a string.
     * <p>
     * A similar API, LLVMConstRealOfStringAndSize is also available. It
     * should be used if the input string's length is known.
     */
    public static native @LLVMValueRef long LLVMConstRealOfString(
            @LLVMTypeRef long realType, @Pointer("const char *") long text
    );

    /**
     * Obtain a constant for a floating point value parsed from a string.
     */
    public static native @LLVMValueRef long LLVMConstRealOfStringAndSize(
            @LLVMTypeRef long realType, @Pointer("const char *") long text, @Unsigned int sLen
    );

    /**
     * Obtain the zero extended value for an integer constant value.
     */
    public static native @Unsigned("unsigned long long") long LLVMConstIntGetZExtValue(
            @LLVMValueRef long constantValue
    );

    /**
     * Obtain the sign extended value for an integer constant value.
     */
    public static native @LongLong long LLVMConstIntGetSExtValue(@LLVMValueRef long constantValue);

    /**
     * Obtain the double value for an floating point constant value.
     * losesInfo indicates if some precision was lost in the conversion.
     */
    public static native double LLVMConstRealGetDouble(@LLVMValueRef long constantValue, @Pointer("LLVMBool *") long losesInfo);

    //endregion

    //region LLVMCCoreValueConstantComposite Composite Constants

    /**
     * Create a ConstantDataSequential and initialize it with a string.
     */
    public static native @LLVMValueRef long LLVMConstStringInContext(
            @LLVMContextRef long context, @Pointer("const char *") long str, @Unsigned int length, boolean dontNullTerminate
    );

    /**
     * Create a ConstantDataSequential and initialize it with a string.
     */
    public static @LLVMValueRef long LLVMConstStringInContext(
            @LLVMContextRef long context, ByteBuffer str, boolean dontNullTerminate
    ) {
        int position = str.position();
        int length = str.remaining();
        return LLVMConstStringInContext(context, NativeUtils.GetDirectBufferAddress(str) + position, length, dontNullTerminate);
    }

    /**
     * Create a ConstantDataSequential with string content in the global context.
     * <p>
     * This is the same as LLVMConstStringInContext except it operates on the
     * global context.
     */
    public static native @LLVMValueRef long LLVMConstString(
            @Pointer("const char *") long str, @Unsigned int length, boolean DontNullTerminate
    );

    /**
     * Create a ConstantDataSequential with string content in the global context.
     * <p>
     * This is the same as LLVMConstStringInContext except it operates on the
     * global context.
     */
    public static @LLVMValueRef long LLVMConstString(
            ByteBuffer str, boolean dontNullTerminate
    ) {
        int position = str.position();
        int length = str.remaining();
        return LLVMConstString(NativeUtils.GetDirectBufferAddress(str) + position, length, dontNullTerminate);
    }

    /**
     * Returns true if the specified constant is an array of i8.
     */
    public static native boolean LLVMIsConstantString(@LLVMValueRef long c);

    /**
     * Get the given constant data sequential as a string.
     */
    public static native @Pointer("const char *") long LLVMGetAsString(@LLVMValueRef long c, @Pointer("size_t *") long length);

    /**
     * Get the given constant data sequential as a string.
     */
    public static ByteBuffer LLVMGetAsString(@LLVMValueRef long c) {
        ByteBuffer res = LLVMGetAsString0(c);
        return res == null ? null : res.asReadOnlyBuffer();
    }

    @Extern
    public static native ByteBuffer LLVMGetAsString0(@LLVMValueRef long c);

    /**
     * Create an anonymous ConstantStruct with the specified values.
     */
    public static native @LLVMValueRef long LLVMConstStructInContext(
            @LLVMContextRef long context,
            @Pointer("LLVMValueRef *") long constantValues, @Unsigned int count,
            boolean Packed
    );

    /**
     * Create an anonymous ConstantStruct with the specified values.
     */
    @Extern
    public static native @LLVMValueRef long LLVMConstStructInContext(
            @LLVMContextRef long context,
            @LLVMValueRef long[] constantValues,
            boolean Packed
    );

    /**
     * Create a ConstantStruct in the global Context.
     * <p>
     * This is the same as LLVMConstStructInContext except it operates on the
     * global Context.
     */
    public static native @LLVMValueRef long LLVMConstStruct(
            @Pointer("LLVMValueRef *") long constantValues, @Unsigned int count, boolean packed
    );

    /**
     * Create a ConstantStruct in the global Context.
     * <p>
     * This is the same as LLVMConstStructInContext except it operates on the
     * global Context.
     */
    public static @LLVMValueRef long LLVMConstStruct(
            @LLVMValueRef long[] constantValues, boolean packed
    ) {
        return LLVMConstStructInContext(LLVMGetGlobalContext(), constantValues, packed);
    }

    /**
     * Create a ConstantArray from values.
     */
    public static native @LLVMValueRef long LLVMConstArray(
            @LLVMTypeRef long elementType, @Pointer("LLVMValueRef *") long constantValues, @Unsigned int length
    );

    /**
     * Create a ConstantArray from values.
     */
    @Extern
    public static native @LLVMValueRef long LLVMConstArray(
            @LLVMTypeRef long elementType, @LLVMValueRef long[] constantValues
    );

    /**
     * Create a non-anonymous ConstantStruct from values.
     */
    public static native @LLVMValueRef long LLVMConstNamedStruct(
            @LLVMTypeRef long structType,
            @Pointer("LLVMValueRef *") long constantValues,
            @Unsigned int count
    );

    /**
     * Create a non-anonymous ConstantStruct from values.
     */
    @Extern
    public static native @LLVMValueRef long LLVMConstNamedStruct(
            @LLVMTypeRef long structType,
            @LLVMValueRef long[] constantValues
    );

    /**
     * Get an element at specified index as a constant.
     */
    public static native @LLVMValueRef long LLVMGetElementAsConstant(@LLVMValueRef long c, @Unsigned int index);

    /**
     * Create a ConstantVector from values.
     */
    public static native @LLVMValueRef long LLVMConstVector(
            @Pointer("LLVMValueRef *") long scalarConstantValues, @Unsigned int size
    );

    //endregion

    //region LLVMCCoreValueConstantExpressions Constant Expressions

    /* Functions in this group correspond to APIs on llvm::ConstantExpr. */

    public static native @LLVMOpcode int LLVMGetConstOpcode(@LLVMValueRef long constantVal);

    public static native @LLVMValueRef long LLVMAlignOf(@LLVMTypeRef long type);

    public static native @LLVMValueRef long LLVMSizeOf(@LLVMTypeRef long type);

    public static native @LLVMValueRef long LLVMConstNeg(@LLVMValueRef long constantVal);

    public static native @LLVMValueRef long LLVMConstNSWNeg(@LLVMValueRef long constantVal);

    public static native @LLVMValueRef long LLVMConstNUWNeg(@LLVMValueRef long constantVal);

    public static native @LLVMValueRef long LLVMConstFNeg(@LLVMValueRef long constantVal);

    public static native @LLVMValueRef long LLVMConstNot(@LLVMValueRef long constantVal);

    public static native @LLVMValueRef long LLVMConstAdd(@LLVMValueRef long LHSConstant, @LLVMValueRef long RHSConstant);

    public static native @LLVMValueRef long LLVMConstNSWAdd(@LLVMValueRef long LHSConstant, @LLVMValueRef long RHSConstant);

    public static native @LLVMValueRef long LLVMConstNUWAdd(@LLVMValueRef long LHSConstant, @LLVMValueRef long RHSConstant);

    public static native @LLVMValueRef long LLVMConstFAdd(@LLVMValueRef long LHSConstant, @LLVMValueRef long RHSConstant);

    public static native @LLVMValueRef long LLVMConstSub(@LLVMValueRef long LHSConstant, @LLVMValueRef long RHSConstant);

    public static native @LLVMValueRef long LLVMConstNSWSub(@LLVMValueRef long LHSConstant, @LLVMValueRef long RHSConstant);

    public static native @LLVMValueRef long LLVMConstNUWSub(@LLVMValueRef long LHSConstant, @LLVMValueRef long RHSConstant);

    public static native @LLVMValueRef long LLVMConstFSub(@LLVMValueRef long LHSConstant, @LLVMValueRef long RHSConstant);

    public static native @LLVMValueRef long LLVMConstMul(@LLVMValueRef long LHSConstant, @LLVMValueRef long RHSConstant);

    public static native @LLVMValueRef long LLVMConstNSWMul(@LLVMValueRef long LHSConstant, @LLVMValueRef long RHSConstant);

    public static native @LLVMValueRef long LLVMConstNUWMul(@LLVMValueRef long LHSConstant, @LLVMValueRef long RHSConstant);

    public static native @LLVMValueRef long LLVMConstFMul(@LLVMValueRef long LHSConstant, @LLVMValueRef long RHSConstant);

    public static native @LLVMValueRef long LLVMConstUDiv(@LLVMValueRef long LHSConstant, @LLVMValueRef long RHSConstant);

    public static native @LLVMValueRef long LLVMConstExactUDiv(@LLVMValueRef long LHSConstant, @LLVMValueRef long RHSConstant);

    public static native @LLVMValueRef long LLVMConstSDiv(@LLVMValueRef long LHSConstant, @LLVMValueRef long RHSConstant);

    public static native @LLVMValueRef long LLVMConstExactSDiv(@LLVMValueRef long LHSConstant, @LLVMValueRef long RHSConstant);

    public static native @LLVMValueRef long LLVMConstFDiv(@LLVMValueRef long LHSConstant, @LLVMValueRef long RHSConstant);

    public static native @LLVMValueRef long LLVMConstURem(@LLVMValueRef long LHSConstant, @LLVMValueRef long RHSConstant);

    public static native @LLVMValueRef long LLVMConstSRem(@LLVMValueRef long LHSConstant, @LLVMValueRef long RHSConstant);

    public static native @LLVMValueRef long LLVMConstFRem(@LLVMValueRef long LHSConstant, @LLVMValueRef long RHSConstant);

    public static native @LLVMValueRef long LLVMConstAnd(@LLVMValueRef long LHSConstant, @LLVMValueRef long RHSConstant);

    public static native @LLVMValueRef long LLVMConstOr(@LLVMValueRef long LHSConstant, @LLVMValueRef long RHSConstant);

    public static native @LLVMValueRef long LLVMConstXor(@LLVMValueRef long LHSConstant, @LLVMValueRef long RHSConstant);

    public static native @LLVMValueRef long LLVMConstICmp(
            @LLVMIntPredicate int predicate, @LLVMValueRef long LHSConstant, @LLVMValueRef long RHSConstant
    );

    public static native @LLVMValueRef long LLVMConstFCmp(
            @LLVMRealPredicate int predicate, @LLVMValueRef long LHSConstant, @LLVMValueRef long RHSConstant
    );

    public static native @LLVMValueRef long LLVMConstShl(@LLVMValueRef long LHSConstant, @LLVMValueRef long RHSConstant);

    public static native @LLVMValueRef long LLVMConstLShr(@LLVMValueRef long LHSConstant, @LLVMValueRef long RHSConstant);

    public static native @LLVMValueRef long LLVMConstAShr(@LLVMValueRef long LHSConstant, @LLVMValueRef long RHSConstant);

    public static native @LLVMValueRef long LLVMConstGEP(
            @LLVMValueRef long constantVal,
            @Pointer("LLVMValueRef *") long constantIndices, @Unsigned int numIndices
    );

    /*
    public static native @LLVMValueRef long LLVMConstGEP2(
            @LLVMTypeRef long type, @LLVMValueRef long constantVal,
            @Pointer("LLVMValueRef *") long constantIndices, @Unsigned int numIndices
    );
    */

    public static native @LLVMValueRef long LLVMConstInBoundsGEP(
            @LLVMValueRef long constantVal,
            @Pointer("LLVMValueRef *") long constantIndices,
            @Unsigned int numIndices
    );

    /*
    public static native @LLVMValueRef long LLVMConstInBoundsGEP2(
            @LLVMTypeRef long type, @LLVMValueRef long constantVal,
            @Pointer("LLVMValueRef *") long constantIndices, @Unsigned int numIndices
    );
    */

    public static native @LLVMValueRef long LLVMConstTrunc(@LLVMValueRef long constantVal, @LLVMTypeRef long toType);

    public static native @LLVMValueRef long LLVMConstSExt(@LLVMValueRef long constantVal, @LLVMTypeRef long toType);

    public static native @LLVMValueRef long LLVMConstZExt(@LLVMValueRef long constantVal, @LLVMTypeRef long toType);

    public static native @LLVMValueRef long LLVMConstFPTrunc(@LLVMValueRef long constantVal, @LLVMTypeRef long toType);

    public static native @LLVMValueRef long LLVMConstFPExt(@LLVMValueRef long constantVal, @LLVMTypeRef long toType);

    public static native @LLVMValueRef long LLVMConstUIToFP(@LLVMValueRef long constantVal, @LLVMTypeRef long toType);

    public static native @LLVMValueRef long LLVMConstSIToFP(@LLVMValueRef long constantVal, @LLVMTypeRef long toType);

    public static native @LLVMValueRef long LLVMConstFPToUI(@LLVMValueRef long constantVal, @LLVMTypeRef long toType);

    public static native @LLVMValueRef long LLVMConstFPToSI(@LLVMValueRef long constantVal, @LLVMTypeRef long toType);

    public static native @LLVMValueRef long LLVMConstPtrToInt(@LLVMValueRef long constantVal, @LLVMTypeRef long toType);

    public static native @LLVMValueRef long LLVMConstIntToPtr(@LLVMValueRef long constantVal, @LLVMTypeRef long toType);

    public static native @LLVMValueRef long LLVMConstBitCast(@LLVMValueRef long constantVal, @LLVMTypeRef long toType);

    public static native @LLVMValueRef long LLVMConstAddrSpaceCast(@LLVMValueRef long constantVal, @LLVMTypeRef long toType);

    public static native @LLVMValueRef long LLVMConstZExtOrBitCast(@LLVMValueRef long constantVal, @LLVMTypeRef long toType);

    public static native @LLVMValueRef long LLVMConstSExtOrBitCast(@LLVMValueRef long constantVal, @LLVMTypeRef long toType);

    public static native @LLVMValueRef long LLVMConstTruncOrBitCast(@LLVMValueRef long constantVal, @LLVMTypeRef long toType);

    public static native @LLVMValueRef long LLVMConstPointerCast(@LLVMValueRef long constantVal, @LLVMTypeRef long toType);

    public static native @LLVMValueRef long LLVMConstIntCast(
            @LLVMValueRef long constantVal, @LLVMTypeRef long toType,
            boolean isSigned
    );

    public static native @LLVMValueRef long LLVMConstFPCast(@LLVMValueRef long constantVal, @LLVMTypeRef long toType);

    public static native @LLVMValueRef long LLVMConstSelect(
            @LLVMValueRef long constantCondition,
            @LLVMValueRef long constantIfTrue,
            @LLVMValueRef long constantIfFalse
    );

    public static native @LLVMValueRef long LLVMConstExtractElement(
            @LLVMValueRef long vectorConstant,
            @LLVMValueRef long indexConstant
    );

    public static native @LLVMValueRef long LLVMConstInsertElement(
            @LLVMValueRef long vectorConstant,
            @LLVMValueRef long elementValueConstant,
            @LLVMValueRef long indexConstant
    );

    public static native @LLVMValueRef long LLVMConstShuffleVector(
            @LLVMValueRef long vectorAConstant,
            @LLVMValueRef long vectorBConstant,
            @LLVMValueRef long maskConstant
    );

    public static native @LLVMValueRef long LLVMConstExtractValue(
            @LLVMValueRef long aggConstant, @Pointer("unsigned *") long indexList, @Unsigned int numIndex
    );

    public static native @LLVMValueRef long LLVMConstInsertValue(
            @LLVMValueRef long aggConstant, @LLVMValueRef long elementValueConstant,
            @Pointer("unsigned *") long indexList, @Unsigned int numIndex
    );

    public static native @LLVMValueRef long LLVMBlockAddress(@LLVMValueRef long f, @LLVMBasicBlockRef long bb);

    /**
     * @deprecated Use LLVMGetInlineAsm instead.
     */
    @Deprecated
    public static native @LLVMValueRef long LLVMConstInlineAsm(
            @LLVMTypeRef long type,
            @Pointer("const char *") long asmString, @Pointer("const char *") long constraints,
            boolean hasSideEffects, boolean isAlignStack
    );

    //endregion

    //region LLVMCCoreValueConstantGlobals Global Values

    /*
     * This group contains functions that operate on global values. Functions in
     * this group relate to functions in the llvm::GlobalValue class tree.
     */

    public static native @LLVMModuleRef long LLVMGetGlobalParent(@LLVMValueRef long global);

    public static native boolean LLVMIsDeclaration(@LLVMValueRef long global);

    public static native @LLVMLinkage int LLVMGetLinkage(@LLVMValueRef long global);

    public static native void LLVMSetLinkage(@LLVMValueRef long global, @LLVMLinkage int linkage);

    public static native @Pointer("const char *") long LLVMGetSection(@LLVMValueRef long global);

    public static native void LLVMSetSection(@LLVMValueRef long global, @Pointer("const char *") long section);

    public static native @LLVMVisibility int LLVMGetVisibility(@LLVMValueRef long global);

    public static native void LLVMSetVisibility(@LLVMValueRef long global, @LLVMVisibility int viz);

    public static native @LLVMDLLStorageClass int LLVMGetDLLStorageClass(@LLVMValueRef long global);

    public static native void LLVMSetDLLStorageClass(@LLVMValueRef long global, @LLVMDLLStorageClass int scls);

    public static native @LLVMUnnamedAddr int LLVMGetUnnamedAddress(@LLVMValueRef long global);

    public static native void LLVMSetUnnamedAddress(@LLVMValueRef long global, @LLVMUnnamedAddr int unnamedAddress);

    /**
     * Returns the "value type" of a global value.  This differs from the formal
     * type of a global value which is always a pointer type.
     */
    public static native @LLVMTypeRef long LLVMGlobalGetValueType(@LLVMValueRef long global);

    /**
     * @deprecated Use {@link #LLVMGetUnnamedAddress(long)} instead.
     */
    @Deprecated
    public static native boolean LLVMHasUnnamedAddr(@LLVMValueRef long global);

    /**
     * @deprecated Use {@link #LLVMSetUnnamedAddress(long, int)}  instead.
     */
    @Deprecated
    public static native void LLVMSetUnnamedAddr(@LLVMValueRef long global, boolean hasUnnamedAddress);

    //region LLVMCCoreValueWithAlignment Values with alignment

    /*
     * Functions in this group only apply to values with alignment, i.e.
     * global variables, load and store instructions.
     */

    /**
     * Obtain the preferred alignment of the value.
     */
    public static native @Unsigned int LLVMGetAlignment(@LLVMValueRef long v);

    /**
     * Set the preferred alignment of the value.
     */
    public static native void LLVMSetAlignment(@LLVMValueRef long v, @Unsigned int bytes);

    /**
     * Sets a metadata attachment, erasing the existing metadata attachment if
     * it already exists for the given kind.
     */
    public static native void LLVMGlobalSetMetadata(
            @LLVMValueRef long global, @Unsigned int kind, @LLVMMetadataRef long md
    );

    /**
     * Erases a metadata attachment of the given kind if it exists.
     */
    public static native void LLVMGlobalEraseMetadata(@LLVMValueRef long global, @Unsigned int kind);

    /**
     * Removes all metadata attachments from this value.
     */
    public static native void LLVMGlobalClearMetadata(@LLVMValueRef long global);

    /**
     * Retrieves an array of metadata entries representing the metadata attached to
     * this value. The caller is responsible for freeing this array by calling
     * \c LLVMDisposeValueMetadataEntries.
     */
    public static native @Pointer("LLVMValueMetadataEntry *") long LLVMGlobalCopyAllMetadata(
            @LLVMValueRef long value, @Pointer("size_t *") long numEntries
    );

    /**
     * Destroys value metadata entries.
     */
    public static native void LLVMDisposeValueMetadataEntries(@Pointer("LLVMValueMetadataEntry *") long entries);

    /**
     * Returns the kind of a value metadata entry at a specific index.
     */
    public static native @Unsigned int LLVMValueMetadataEntriesGetKind(
            @Pointer("LLVMValueMetadataEntry *") long entries, @Unsigned int index
    );

    /**
     * Returns the underlying metadata node of a value metadata entry at a
     * specific index.
     */
    public static native @LLVMMetadataRef long LLVMValueMetadataEntriesGetMetadata(
            @Pointer("LLVMValueMetadataEntry *") long entries,
            @Unsigned int index
    );

    //endregion

    //endregion

    //region LLVMCoreValueConstantGlobalAlias Global Aliases

    /* This group contains function that operate on global alias values. */

    public static native @LLVMValueRef long LLVMAddAlias(
            @LLVMModuleRef long module, @LLVMTypeRef long type,
            @LLVMValueRef long aliasee, @Pointer("const char *") long name
    );

    /**
     * Obtain a GlobalAlias value from a Module by its name.
     * <p>
     * The returned value corresponds to a llvm::GlobalAlias value.
     */
    public static native @LLVMValueRef long LLVMGetNamedGlobalAlias(
            @LLVMModuleRef long module, @Pointer("const char *") long name, @SizeT long nameLength
    );

    /**
     * Obtain an iterator to the first GlobalAlias in a Module.
     */
    public static native @LLVMValueRef long LLVMGetFirstGlobalAlias(@LLVMModuleRef long module);

    /**
     * Obtain an iterator to the last GlobalAlias in a Module.
     */
    public static native @LLVMValueRef long LLVMGetLastGlobalAlias(@LLVMModuleRef long module);

    /**
     * Advance a GlobalAlias iterator to the next GlobalAlias.
     * <p>
     * Returns NULL if the iterator was already at the end and there are no more
     * global aliases.
     */
    public static native @LLVMValueRef long LLVMGetNextGlobalAlias(@LLVMValueRef long GA);

    /**
     * Decrement a GlobalAlias iterator to the previous GlobalAlias.
     * <p>
     * Returns NULL if the iterator was already at the beginning and there are
     * no previous global aliases.
     */
    public static native @LLVMValueRef long LLVMGetPreviousGlobalAlias(@LLVMValueRef long GA);

    /**
     * Retrieve the target value of an alias.
     */
    public static native @LLVMValueRef long LLVMAliasGetAliasee(@LLVMValueRef long alias);

    /**
     * Set the target value of an alias.
     */
    public static native void LLVMAliasSetAliasee(@LLVMValueRef long alias, @LLVMValueRef long aliasee);

    //endregion

    //region LLVMCCoreValueFunction Function values

    /*
     * Functions in this group operate on LLVMValueRef instances that
     * correspond to llvm::Function instances.
     */

    /**
     * Remove a function from its containing module and deletes it.
     */
    public static native void LLVMDeleteFunction(@LLVMValueRef long fn);

    /**
     * Check whether the given function has a personality function.
     */
    public static native boolean LLVMHasPersonalityFn(@LLVMValueRef long fn);

    /**
     * Obtain the personality function attached to the function.
     */
    public static native @LLVMValueRef long LLVMGetPersonalityFn(@LLVMValueRef long fn);

    /**
     * Set the personality function attached to the function.
     */
    public static native void LLVMSetPersonalityFn(@LLVMValueRef long fn, @LLVMValueRef long personalityFn);

    /**
     * Obtain the intrinsic ID number which matches the given function name.
     */
    public static native @Unsigned int LLVMLookupIntrinsicID(@Pointer("const char *") long name, @SizeT long nameLength);

    /**
     * Obtain the ID number from a function instance.
     */
    public static native @Unsigned int LLVMGetIntrinsicID(@LLVMValueRef long fn);

    /**
     * Create or insert the declaration of an intrinsic.  For overloaded intrinsics,
     * parameter types must be provided to uniquely identify an overload.
     */
    public static native @LLVMValueRef long LLVMGetIntrinsicDeclaration(
            @LLVMModuleRef long module,
            @Unsigned int id,
            @Pointer("LLVMTypeRef *") long paramTypes,
            @SizeT long paramCount
    );

    /**
     * Retrieves the type of an intrinsic.  For overloaded intrinsics, parameter
     * types must be provided to uniquely identify an overload.
     */
    public static native @LLVMTypeRef long LLVMIntrinsicGetType(
            @LLVMContextRef long context, @Unsigned int id,
            @Pointer("LLVMTypeRef *") long paramTypes,
            @SizeT long paramCount
    );

    /**
     * Retrieves the name of an intrinsic.
     */
    public static native @Pointer("const char *") long LLVMIntrinsicGetName(
            @Unsigned int id, @Pointer("size_t *") long nameLength
    );

    /**
     * Copies the name of an overloaded intrinsic identified by a given list of
     * parameter types.
     * <p>
     * Unlike LLVMIntrinsicGetName, the caller is responsible for freeing the
     * returned string.
     */
    public static native @Pointer("const char *") long LLVMIntrinsicCopyOverloadedName(
            @Unsigned int id,
            @Pointer("LLVMTypeRef *") long paramTypes,
            @SizeT long paramCount,
            @Pointer("size_t *") long nameLength);

    /**
     * Obtain if the intrinsic identified by the given ID is overloaded.
     */
    public static native boolean LLVMIntrinsicIsOverloaded(@Unsigned int id);

    /**
     * Obtain the calling function of a function.
     * <p>
     * The returned value corresponds to the LLVMCallConv enumeration.
     */
    public static native @Unsigned int LLVMGetFunctionCallConv(@LLVMValueRef long fn);

    /**
     * Set the calling convention of a function.
     *
     * @param fn Function to operate on
     * @param cc LLVMCallConv to set calling convention to
     */
    public static native void LLVMSetFunctionCallConv(@LLVMValueRef long fn, @Unsigned int cc);

    /**
     * Obtain the name of the garbage collector to use during code
     * generation.
     */
    public static native @Pointer("const char *") long LLVMGetGC(@LLVMValueRef long fn);

    /**
     * Define the garbage collector to use during code generation.
     */
    public static native void LLVMSetGC(@LLVMValueRef long fn, @Pointer("const char *") long name);

    /**
     * Add an attribute to a function.
     */
    public static native void LLVMAddAttributeAtIndex(
            @LLVMValueRef long f, @LLVMAttributeIndex int index, @LLVMAttributeRef long a
    );

    public static native @Unsigned int LLVMGetAttributeCountAtIndex(@LLVMValueRef long f, @LLVMAttributeIndex int index);

    public static native void LLVMGetAttributesAtIndex(
            @LLVMValueRef long f, @LLVMAttributeIndex int index, @Pointer("LLVMAttributeRef *") long attrs
    );

    public static native @LLVMAttributeRef long LLVMGetEnumAttributeAtIndex(
            @LLVMValueRef long f,
            @LLVMAttributeIndex int index,
            @Unsigned int kindID
    );

    public static native @LLVMAttributeRef long LLVMGetStringAttributeAtIndex(
            @LLVMValueRef long f,
            @LLVMAttributeIndex int index,
            @Pointer("const char *") long k, @Unsigned int KLen);

    public static native void LLVMRemoveEnumAttributeAtIndex(
            @LLVMValueRef long f, @LLVMAttributeIndex int index,
            @Unsigned int kindID);

    public static native void LLVMRemoveStringAttributeAtIndex(
            @LLVMValueRef long f, @LLVMAttributeIndex int index,
            @Pointer("const char *") long k, @Unsigned int kLen
    );

    /**
     * Add a target-dependent attribute to a function
     */
    public static native void LLVMAddTargetDependentFunctionAttr(
            @LLVMValueRef long fn, @Pointer("const char *") long a,
            @Pointer("const char *") long v
    );

    //region LLVMCCoreValueFunctionParameters Function Parameters

    /*
     * Functions in this group relate to arguments/parameters on functions.
     *
     * Functions in this group expect LLVMValueRef instances that correspond
     * to llvm::Function instances.
     */

    /**
     * Obtain the number of parameters in a function.
     */
    public static native @Unsigned int LLVMCountParams(@LLVMValueRef long fn);

    /**
     * Obtain the parameters in a function.
     * <p>
     * The takes a pointer to a pre-allocated array of LLVMValueRef that is
     * at least LLVMCountParams() long. This array will be filled with
     * LLVMValueRef instances which correspond to the parameters the
     * function receives. Each LLVMValueRef corresponds to a llvm::Argument
     * instance.
     */
    public static native void LLVMGetParams(@LLVMValueRef long fn, @Pointer("LLVMValueRef *") long params);

    /**
     * Obtain the parameter at the specified index.
     * <p>
     * Parameters are indexed from 0.
     */
    public static native @LLVMValueRef long LLVMGetParam(@LLVMValueRef long fn, @Unsigned int index);

    /**
     * Obtain the function to which this argument belongs.
     * <p>
     * Unlike other functions in this group, this one takes an LLVMValueRef
     * that corresponds to a llvm::Attribute.
     * <p>
     * The returned LLVMValueRef is the llvm::Function to which this
     * argument belongs.
     */
    public static native @LLVMValueRef long LLVMGetParamParent(@LLVMValueRef long inst);

    /**
     * Obtain the first parameter to a function.
     */
    public static native @LLVMValueRef long LLVMGetFirstParam(@LLVMValueRef long fn);

    /**
     * Obtain the last parameter to a function.
     */
    public static native @LLVMValueRef long LLVMGetLastParam(@LLVMValueRef long fn);

    /**
     * Obtain the next parameter to a function.
     * <p>
     * This takes an LLVMValueRef obtained from LLVMGetFirstParam() (which is
     * actually a wrapped iterator) and obtains the next parameter from the
     * underlying iterator.
     */
    public static native @LLVMValueRef long LLVMGetNextParam(@LLVMValueRef long arg);

    /**
     * Obtain the previous parameter to a function.
     * <p>
     * This is the opposite of LLVMGetNextParam().
     */
    public static native @LLVMValueRef long LLVMGetPreviousParam(@LLVMValueRef long arg);

    /**
     * Set the alignment for a function parameter.
     */
    public static native void LLVMSetParamAlignment(@LLVMValueRef long arg, @Unsigned int align);

    //endregion

    //region LLVMCCoreValueGlobalIFunc IFuncs

    /*
     * Functions in this group relate to indirect functions.
     *
     * Functions in this group expect LLVMValueRef instances that correspond
     * to llvm::GlobalIFunc instances.
     */

    /**
     * Add a global indirect function to a module under a specified name.
     */
    public static native @LLVMValueRef long LLVMAddGlobalIFunc(
            @LLVMModuleRef long module,
            @Pointer("const char *") long name, @SizeT long nameLength,
            @LLVMTypeRef long type, @Unsigned int addressSpace,
            @LLVMValueRef long resolver
    );

    /**
     * Obtain a GlobalIFunc value from a Module by its name.
     * <p>
     * The returned value corresponds to a llvm::GlobalIFunc value.
     */
    public static native @LLVMValueRef long LLVMGetNamedGlobalIFunc(
            @LLVMModuleRef long module,
            @Pointer("const char *") long name, @SizeT long nameLength
    );

    /**
     * Obtain an iterator to the first GlobalIFunc in a Module.
     */
    public static native @LLVMValueRef long LLVMGetFirstGlobalIFunc(@LLVMModuleRef long module);

    /**
     * Obtain an iterator to the last GlobalIFunc in a Module.
     */
    public static native @LLVMValueRef long LLVMGetLastGlobalIFunc(@LLVMModuleRef long module);

    /**
     * Advance a GlobalIFunc iterator to the next GlobalIFunc.
     * <p>
     * Returns NULL if the iterator was already at the end and there are no more
     * global aliases.
     */
    public static native @LLVMValueRef long LLVMGetNextGlobalIFunc(@LLVMValueRef long iFunc);

    /**
     * Decrement a GlobalIFunc iterator to the previous GlobalIFunc.
     * <p>
     * Returns NULL if the iterator was already at the beginning and there are
     * no previous global aliases.
     */
    public static native @LLVMValueRef long LLVMGetPreviousGlobalIFunc(@LLVMValueRef long iFunc);

    /**
     * Retrieves the resolver function associated with this indirect function, or
     * NULL if it doesn't not exist.
     */
    public static native @LLVMValueRef long LLVMGetGlobalIFuncResolver(@LLVMValueRef long iFunc);

    /**
     * Sets the resolver function associated with this indirect function.
     */
    public static native void LLVMSetGlobalIFuncResolver(@LLVMValueRef long iFunc, @LLVMValueRef long resolver);

    /**
     * Remove a global indirect function from its parent module and delete it.
     */
    public static native void LLVMEraseGlobalIFunc(@LLVMValueRef long iFunc);

    /**
     * Remove a global indirect function from its parent module.
     * <p>
     * This unlinks the global indirect function from its containing module but
     * keeps it alive.
     */
    public static native void LLVMRemoveGlobalIFunc(@LLVMValueRef long iFunc);

    //endregion

    //endregion

    //endregion

    //endregion

    //region LLVMCCoreValueMetadata Metadata

    /**
     * Create an MDString value from a given string value.
     * <p>
     * The MDString value does not take ownership of the given string, it remains
     * the responsibility of the caller to free it.
     */
    public static native @LLVMMetadataRef long LLVMMDStringInContext2(
            @LLVMContextRef long context, @Pointer("const char *") long str,
            @SizeT long sLen
    );

    /**
     * Create an MDNode value with the given array of operands.
     */
    public static native @LLVMMetadataRef long LLVMMDNodeInContext2(
            @LLVMContextRef long context, @Pointer("LLVMMetadataRef *") long mds,
            @SizeT long count
    );

    /**
     * Obtain a Metadata as a Value.
     */
    public static native @LLVMValueRef long LLVMMetadataAsValue(@LLVMContextRef long context, @LLVMMetadataRef long md);

    /**
     * Obtain a Value as a Metadata.
     */
    public static native @LLVMMetadataRef long LLVMValueAsMetadata(@LLVMValueRef long val);

    /**
     * Obtain the underlying string from a MDString value.
     *
     * @param V      Instance to obtain string from.
     * @param length Memory address which will hold length of returned string.
     * @return String data in MDString.
     */
    public static native @Pointer("const char *") long LLVMGetMDString(
            @LLVMValueRef long V, @Pointer("unsigned *") long length
    );

    /**
     * Obtain the number of operands from an MDNode value.
     *
     * @param v MDNode to get number of operands from.
     * @return Number of operands of the MDNode.
     */
    public static native @Unsigned int LLVMGetMDNodeNumOperands(@LLVMValueRef long v);

    /**
     * Obtain the given MDNode's operands.
     * <p>
     * The passed LLVMValueRef pointer should point to enough memory to hold all of
     * the operands of the given MDNode (see LLVMGetMDNodeNumOperands) as
     * LLVMValueRefs. This memory will be populated with the LLVMValueRefs of the
     * MDNode's operands.
     *
     * @param v    MDNode to get the operands from.
     * @param dest Destination array for operands.
     */
    public static native void LLVMGetMDNodeOperands(@LLVMValueRef long v, @Pointer("LLVMValueRef *") long dest);

    /**
     * @deprecated Use LLVMMDStringInContext2 instead.
     */
    @Deprecated
    public static native @LLVMValueRef long LLVMMDStringInContext(
            @LLVMContextRef long context, @Pointer("const char *") long str, @Unsigned int sLen);

    /**
     * @deprecated Use LLVMMDStringInContext2 instead.
     */
    @Deprecated
    public static native @LLVMValueRef long LLVMMDString(@Pointer("const char *") long str, @Unsigned int sLen);

    /**
     * @deprecated Use LLVMMDNodeInContext2 instead.
     */
    @Deprecated
    public static native @LLVMValueRef long LLVMMDNodeInContext(
            @LLVMContextRef long context, @Pointer("LLVMValueRef *") long vals, @Unsigned int count
    );

    /**
     * @deprecated Use LLVMMDNodeInContext2 instead.
     */
    @Deprecated
    public static native @LLVMValueRef long LLVMMDNode(@Pointer("LLVMValueRef *") long vals, @Unsigned int count);

    //endregion

    //region LLVMCCoreValueBasicBlock Basic Block

    /*
     * A basic block represents a single entry single exit section of code.
     * Basic blocks contain a list of instructions which form the body of
     * the block.
     *
     * Basic blocks belong to functions. They have the type of label.
     *
     * Basic blocks are themselves values. However, the C API models them as
     * LLVMBasicBlockRef.
     */

    /**
     * Convert a basic block instance to a value type.
     */
    public static native @LLVMValueRef long LLVMBasicBlockAsValue(@LLVMBasicBlockRef long bb);

    /**
     * Determine whether an LLVMValueRef is itself a basic block.
     */
    public static native boolean LLVMValueIsBasicBlock(@LLVMValueRef long val);

    /**
     * Convert an LLVMValueRef to an LLVMBasicBlockRef instance.
     */
    public static native @LLVMBasicBlockRef long LLVMValueAsBasicBlock(@LLVMValueRef long val);

    /**
     * Obtain the string name of a basic block.
     */
    public static native @Pointer("const char *") long LLVMGetBasicBlockName(@LLVMBasicBlockRef long bb);

    /**
     * Obtain the function to which a basic block belongs.
     */
    public static native @LLVMValueRef long LLVMGetBasicBlockParent(@LLVMBasicBlockRef long bb);

    /**
     * Obtain the terminator instruction for a basic block.
     * <p>
     * If the basic block does not have a terminator (it is not well-formed
     * if it doesn't), then NULL is returned.
     * <p>
     * The returned LLVMValueRef corresponds to an llvm::Instruction.
     */
    public static native @LLVMValueRef long LLVMGetBasicBlockTerminator(@LLVMBasicBlockRef long bb);

    /**
     * Obtain the number of basic blocks in a function.
     *
     * @param fn Function value to operate on.
     */
    public static native @Unsigned int LLVMCountBasicBlocks(@LLVMValueRef long fn);

    /**
     * Obtain all of the basic blocks in a function.
     * <p>
     * This operates on a function value. The BasicBlocks parameter is a
     * pointer to a pre-allocated array of LLVMBasicBlockRef of at least
     * LLVMCountBasicBlocks() in length. This array is populated with
     * LLVMBasicBlockRef instances.
     */
    public static native void LLVMGetBasicBlocks(
            @LLVMValueRef long fn, @Pointer("LLVMBasicBlockRef *") long basicBlocks
    );

    /**
     * Obtain the first basic block in a function.
     * <p>
     * The returned basic block can be used as an iterator. You will likely
     * eventually call into LLVMGetNextBasicBlock() with it.
     */
    public static native @LLVMBasicBlockRef long LLVMGetFirstBasicBlock(@LLVMValueRef long fn);

    /**
     * Obtain the last basic block in a function.
     */
    public static native @LLVMBasicBlockRef long LLVMGetLastBasicBlock(@LLVMValueRef long fn);

    /**
     * Advance a basic block iterator.
     */
    public static native @LLVMBasicBlockRef long LLVMGetNextBasicBlock(@LLVMBasicBlockRef long bb);

    /**
     * Go backwards in a basic block iterator.
     */
    public static native @LLVMBasicBlockRef long LLVMGetPreviousBasicBlock(@LLVMBasicBlockRef long bb);

    /**
     * Obtain the basic block that corresponds to the entry point of a
     * function.
     */
    public static native @LLVMBasicBlockRef long LLVMGetEntryBasicBlock(@LLVMValueRef long fn);

    /**
     * Insert the given basic block after the insertion point of the given builder.
     * <p>
     * The insertion point must be valid.
     */
    public static native void LLVMInsertExistingBasicBlockAfterInsertBlock(
            @LLVMBuilderRef long builder, @LLVMBasicBlockRef long bb
    );

    /**
     * Append the given basic block to the basic block list of the given function.
     */
    public static native void LLVMAppendExistingBasicBlock(
            @LLVMValueRef long fn, @LLVMBasicBlockRef long bb
    );

    /**
     * Create a new basic block without inserting it into a function.
     */
    public static native @LLVMBasicBlockRef long LLVMCreateBasicBlockInContext(
            @LLVMContextRef long context, @Pointer("const char *") long name
    );

    /**
     * Append a basic block to the end of a function.
     */
    public static native @LLVMBasicBlockRef long LLVMAppendBasicBlockInContext(
            @LLVMContextRef long context,
            @LLVMValueRef long fn,
            @Pointer("const char *") long name);

    /**
     * Append a basic block to the end of a function using the global
     * context.
     */
    public static native @LLVMBasicBlockRef long LLVMAppendBasicBlock(
            @LLVMValueRef long fn, @Pointer("const char *") long name
    );

    /**
     * Insert a basic block in a function before another basic block.
     * <p>
     * The function to add to is determined by the function of the
     * passed basic block.
     */
    public static native @LLVMBasicBlockRef long LLVMInsertBasicBlockInContext(
            @LLVMContextRef long context, @LLVMBasicBlockRef long bb, @Pointer("const char *") long name
    );

    /**
     * Insert a basic block in a function using the global context.
     */
    public static native @LLVMBasicBlockRef long LLVMInsertBasicBlock(
            @LLVMBasicBlockRef long insertBeforeBB, @Pointer("const char *") long name
    );

    /**
     * Remove a basic block from a function and delete it.
     * <p>
     * This deletes the basic block from its containing function and deletes
     * the basic block itself.
     */
    public static native void LLVMDeleteBasicBlock(@LLVMBasicBlockRef long bb);

    /**
     * Remove a basic block from a function.
     * <p>
     * This deletes the basic block from its containing function but keep
     * the basic block alive.
     */
    public static native void LLVMRemoveBasicBlockFromParent(@LLVMBasicBlockRef long bb);

    /**
     * Move a basic block to before another one.
     */
    public static native void LLVMMoveBasicBlockBefore(@LLVMBasicBlockRef long bb, @LLVMBasicBlockRef long movePos);

    /**
     * Move a basic block to after another one.
     */
    public static native void LLVMMoveBasicBlockAfter(@LLVMBasicBlockRef long bb, @LLVMBasicBlockRef long movePos);

    /**
     * Obtain the first instruction in a basic block.
     * <p>
     * The returned LLVMValueRef corresponds to a llvm::Instruction
     * instance.
     */
    public static native @LLVMValueRef long LLVMGetFirstInstruction(@LLVMBasicBlockRef long bb);

    /**
     * Obtain the last instruction in a basic block.
     * <p>
     * The returned LLVMValueRef corresponds to an LLVM:Instruction.
     */
    public static native @LLVMValueRef long LLVMGetLastInstruction(@LLVMBasicBlockRef long bb);

    //endregion

    //region LLVMCCoreValueInstruction Instructions

    /*
     * Functions in this group relate to the inspection and manipulation of
     * individual instructions.
     *
     * In the C++ API, an instruction is modeled by llvm::Instruction. This
     * class has a large number of descendents. llvm::Instruction is a
     * llvm::Value and in the C API, instructions are modeled by
     * LLVMValueRef.
     *
     * This group also contains sub-groups which operate on specific
     * llvm::Instruction types, e.g. llvm::CallInst.
     */

    /**
     * Determine whether an instruction has any metadata attached.
     */
    public static native boolean LLVMHasMetadata(@LLVMValueRef long val);

    /**
     * Return metadata associated with an instruction value.
     */
    public static native @LLVMValueRef long LLVMGetMetadata(@LLVMValueRef long val, @Unsigned int kindID);

    /**
     * Set metadata associated with an instruction value.
     */
    public static native void LLVMSetMetadata(@LLVMValueRef long val, @Unsigned int kindID, @LLVMValueRef long node);

    /**
     * Returns the metadata associated with an instruction value, but filters out
     * all the debug locations.
     */
    public static native @Pointer("LLVMValueMetadataEntry *") long LLVMInstructionGetAllMetadataOtherThanDebugLoc(
            @LLVMValueRef long instr, @Pointer("size_t *") long numEntries
    );

    /**
     * Obtain the basic block to which an instruction belongs.
     */
    public static native @LLVMBasicBlockRef long LLVMGetInstructionParent(@LLVMValueRef long inst);

    /**
     * Obtain the instruction that occurs after the one specified.
     * <p>
     * The next instruction will be from the same basic block.
     * <p>
     * If this is the last instruction in a basic block, NULL will be
     * returned.
     */
    public static native @LLVMValueRef long LLVMGetNextInstruction(@LLVMValueRef long inst);

    /**
     * Obtain the instruction that occurred before this one.
     * <p>
     * If the instruction is the first instruction in a basic block, NULL
     * will be returned.
     */
    public static native @LLVMValueRef long LLVMGetPreviousInstruction(@LLVMValueRef long inst);

    /**
     * Remove and delete an instruction.
     * <p>
     * The instruction specified is removed from its containing building
     * block but is kept alive.
     */
    public static native void LLVMInstructionRemoveFromParent(@LLVMValueRef long inst);

    /**
     * Remove and delete an instruction.
     * <p>
     * The instruction specified is removed from its containing building
     * block and then deleted.
     */
    public static native void LLVMInstructionEraseFromParent(@LLVMValueRef long inst);

    /**
     * Obtain the code opcode for an individual instruction.
     */
    public static native @LLVMOpcode int LLVMGetInstructionOpcode(@LLVMValueRef long inst);

    /**
     * Obtain the predicate of an instruction.
     * <p>
     * This is only valid for instructions that correspond to llvm::ICmpInst
     * or llvm::ConstantExpr whose opcode is llvm::Instruction::ICmp.
     */
    public static native @LLVMIntPredicate int LLVMGetICmpPredicate(@LLVMValueRef long inst);

    /**
     * Obtain the float predicate of an instruction.
     * <p>
     * This is only valid for instructions that correspond to llvm::FCmpInst
     * or llvm::ConstantExpr whose opcode is llvm::Instruction::FCmp.
     */
    public static native @LLVMRealPredicate int LLVMGetFCmpPredicate(@LLVMValueRef long inst);

    /**
     * Create a copy of 'this' instruction that is identical in all ways
     * except the following:
     * * The instruction has no parent
     * * The instruction has no name
     */
    public static native @LLVMValueRef long LLVMInstructionClone(@LLVMValueRef long inst);

    /**
     * Determine whether an instruction is a terminator. This routine is named to
     * be compatible with historical functions that did this by querying the
     * underlying C++ type.
     */
    public static native @LLVMValueRef long LLVMIsATerminatorInst(@LLVMValueRef long inst);

    //region LLVMCCoreValueInstructionCall Call Sites and Invocations

    /*
     * Functions in this group apply to instructions that refer to call
     * sites and invocations. These correspond to C++ types in the
     * llvm::CallInst class tree.
     */

    /**
     * Obtain the argument count for a call instruction.
     * <p>
     * This expects an LLVMValueRef that corresponds to a llvm::CallInst,
     * llvm::InvokeInst, or llvm:FuncletPadInst.
     */
    public static native @Unsigned int LLVMGetNumArgOperands(@LLVMValueRef long instr);

    /**
     * Set the calling convention for a call instruction.
     * <p>
     * This expects an LLVMValueRef that corresponds to a llvm::CallInst or
     * llvm::InvokeInst.
     */
    public static native void LLVMSetInstructionCallConv(@LLVMValueRef long instr, @Unsigned int cc);

    /**
     * Obtain the calling convention for a call instruction.
     * <p>
     * This is the opposite of LLVMSetInstructionCallConv(). Reads its
     * usage.
     */
    public static native @Unsigned int LLVMGetInstructionCallConv(@LLVMValueRef long instr);

    public static native void LLVMSetInstrParamAlignment(@LLVMValueRef long instr, @Unsigned int index,
                                                         @Unsigned int Align);

    public static native void LLVMAddCallSiteAttribute(
            @LLVMValueRef long c, @LLVMAttributeIndex int index, @LLVMAttributeRef long a
    );

    public static native @Unsigned int LLVMGetCallSiteAttributeCount(
            @LLVMValueRef long c, @LLVMAttributeIndex int index
    );

    public static native void LLVMGetCallSiteAttributes(
            @LLVMValueRef long c, @LLVMAttributeIndex int index, @Pointer("LLVMAttributeRef *") long attrs
    );

    public static native @LLVMAttributeRef long LLVMGetCallSiteEnumAttribute(
            @LLVMValueRef long c, @LLVMAttributeIndex int index, @Unsigned int kindID
    );

    public static native @LLVMAttributeRef long LLVMGetCallSiteStringAttribute(
            @LLVMValueRef long c, @LLVMAttributeIndex int index,
            @Pointer("const char *") long k, @Unsigned int KLen
    );

    public static native void LLVMRemoveCallSiteEnumAttribute(
            @LLVMValueRef long c, @LLVMAttributeIndex int index,
            @Unsigned int KindID
    );

    public static native void LLVMRemoveCallSiteStringAttribute(
            @LLVMValueRef long c, @LLVMAttributeIndex int index,
            @Pointer("const char *") long k, @Unsigned int KLen
    );

    /**
     * Obtain the function type called by this instruction.
     */
    public static native @LLVMTypeRef long LLVMGetCalledFunctionType(@LLVMValueRef long c);

    /**
     * Obtain the pointer to the function invoked by this instruction.
     * <p>
     * This expects an LLVMValueRef that corresponds to a llvm::CallInst or
     * llvm::InvokeInst.
     */
    public static native @LLVMValueRef long LLVMGetCalledValue(@LLVMValueRef long instr);

    /**
     * Obtain whether a call instruction is a tail call.
     * <p>
     * This only works on llvm::CallInst instructions.
     */
    public static native boolean LLVMIsTailCall(@LLVMValueRef long callInst);

    /**
     * Set whether a call instruction is a tail call.
     * <p>
     * This only works on llvm::CallInst instructions.
     */
    public static native void LLVMSetTailCall(@LLVMValueRef long callInst, boolean isTailCall);

    /**
     * Return the normal destination basic block.
     * <p>
     * This only works on llvm::InvokeInst instructions.
     */
    public static native @LLVMBasicBlockRef long LLVMGetNormalDest(@LLVMValueRef long invokeInst);

    /**
     * Return the unwind destination basic block.
     * <p>
     * Works on llvm::InvokeInst, llvm::CleanupReturnInst, and
     * llvm::CatchSwitchInst instructions.
     */
    public static native @LLVMBasicBlockRef long LLVMGetUnwindDest(@LLVMValueRef long invokeInst);

    /**
     * Set the normal destination basic block.
     * <p>
     * This only works on llvm::InvokeInst instructions.
     */
    public static native void LLVMSetNormalDest(@LLVMValueRef long invokeInst, @LLVMBasicBlockRef long b);

    /**
     * Set the unwind destination basic block.
     * <p>
     * Works on llvm::InvokeInst, llvm::CleanupReturnInst, and
     * llvm::CatchSwitchInst instructions.
     */
    public static native void LLVMSetUnwindDest(@LLVMValueRef long invokeInst, @LLVMBasicBlockRef long b);

    //endregion

    //region LLVMCCoreValueInstructionTerminator Terminators

    /*
     * Functions in this group only apply to instructions for which
     * LLVMIsATerminatorInst returns true.
     */

    /**
     * Return the number of successors that this terminator has.
     */
    public static native @Unsigned int LLVMGetNumSuccessors(@LLVMValueRef long term);

    /**
     * Return the specified successor.
     */
    public static native @LLVMBasicBlockRef long LLVMGetSuccessor(@LLVMValueRef long term, @Unsigned int i);

    /**
     * Update the specified successor to point at the provided block.
     */
    public static native void LLVMSetSuccessor(@LLVMValueRef long term, @Unsigned int i, @LLVMBasicBlockRef long block);

    /**
     * Return if a branch is conditional.
     * <p>
     * This only works on llvm::BranchInst instructions.
     */
    public static native boolean LLVMIsConditional(@LLVMValueRef long branch);

    /**
     * Return the condition of a branch instruction.
     * <p>
     * This only works on llvm::BranchInst instructions.
     */
    public static native @LLVMValueRef long LLVMGetCondition(@LLVMValueRef long branch);

    /**
     * Set the condition of a branch instruction.
     * <p>
     * This only works on llvm::BranchInst instructions.
     */
    public static native void LLVMSetCondition(@LLVMValueRef long branch, @LLVMValueRef long cond);

    /**
     * Obtain the default destination basic block of a switch instruction.
     * <p>
     * This only works on llvm::SwitchInst instructions.
     */
    public static native @LLVMBasicBlockRef long LLVMGetSwitchDefaultDest(@LLVMValueRef long switchInstr);

    //endregion

    //region LLVMCCoreValueInstructionAlloca Allocas

    /*
     * Functions in this group only apply to instructions that map to
     * llvm::AllocaInst instances.
     */

    /**
     * Obtain the type that is being allocated by the alloca instruction.
     */
    public static native @LLVMTypeRef long LLVMGetAllocatedType(@LLVMValueRef long alloca);

    //endregion

    //region LLVMCCoreValueInstructionGetElementPointer GEPs

    /**
     * Check whether the given GEP instruction is inbounds.
     */
    public static native boolean LLVMIsInBounds(@LLVMValueRef long gep);

    /**
     * Set the given GEP instruction to be inbounds or not.
     */
    public static native void LLVMSetIsInBounds(@LLVMValueRef long gep, boolean inBounds);

    //endregion

    //region LLVMCCoreValueInstructionPHINode PHI Nodes

    /*
     * Functions in this group only apply to instructions that map to
     * llvm::PHINode instances.
     */

    /**
     * Add an incoming value to the end of a PHI list.
     */
    public static native void LLVMAddIncoming(
            @LLVMValueRef long phiNode, @Pointer("LLVMValueRef *") long incomingValues,
            @Pointer("LLVMBasicBlockRef *") long incomingBlocks, @Unsigned int count
    );

    /**
     * Obtain the number of incoming basic blocks to a PHI node.
     */
    public static native @Unsigned int LLVMCountIncoming(@LLVMValueRef long phiNode);

    /**
     * Obtain an incoming value to a PHI node as an LLVMValueRef.
     */
    public static native @LLVMValueRef long LLVMGetIncomingValue(@LLVMValueRef long phiNode, @Unsigned int index);

    /**
     * Obtain an incoming value to a PHI node as an LLVMBasicBlockRef.
     */
    public static native @LLVMBasicBlockRef long LLVMGetIncomingBlock(@LLVMValueRef long phiNode, @Unsigned int index);

    //endregion

    //region    LLVMCCoreValueInstructionInsertValue  InsertValue
    //          LLVMCCoreValueInstructionExtractValue ExtractValue

    /*
     * Functions in this group only apply to instructions that map to
     * llvm::ExtractValue and llvm::InsertValue instances.
     */

    /**
     * Obtain the number of indices.
     * NB: This also works on GEP.
     */
    public static native @Unsigned int LLVMGetNumIndices(@LLVMValueRef long inst);

    /**
     * Obtain the indices as an array.
     */
    public static native @Pointer("const unsigned *") long LLVMGetIndices(@LLVMValueRef long inst);

    //endregion

    //endregion

    //endregion

    //region LLVMCCoreInstructionBuilder Instruction Builders

    public static native @LLVMBuilderRef long LLVMCreateBuilderInContext(@LLVMContextRef long C);

    public static native @LLVMBuilderRef long LLVMCreateBuilder();

    public static native void LLVMPositionBuilder(
            @LLVMBuilderRef long Builder, @LLVMBasicBlockRef long Block, @LLVMValueRef long Instr
    );

    public static native void LLVMPositionBuilderBefore(@LLVMBuilderRef long Builder, @LLVMValueRef long Instr);

    public static native void LLVMPositionBuilderAtEnd(@LLVMBuilderRef long Builder, @LLVMBasicBlockRef long Block);

    public static native @LLVMBasicBlockRef long LLVMGetInsertBlock(@LLVMBuilderRef long Builder);

    public static native void LLVMClearInsertionPosition(@LLVMBuilderRef long Builder);

    public static native void LLVMInsertIntoBuilder(@LLVMBuilderRef long Builder, @LLVMValueRef long Instr);

    public static native void LLVMInsertIntoBuilderWithName(
            @LLVMBuilderRef long Builder, @LLVMValueRef long Instr, @Pointer("const char *") long Name
    );

    public static native void LLVMDisposeBuilder(@LLVMBuilderRef long Builder);

    /* Metadata */

    /**
     * Get location information used by debugging information.
     */
    public static native @LLVMMetadataRef long LLVMGetCurrentDebugLocation2(@LLVMBuilderRef long Builder);

    /**
     * Set location information used by debugging information.
     * <p>
     * To clear the location metadata of the given instruction, pass NULL to \p Loc.
     */
    public static native void LLVMSetCurrentDebugLocation2(@LLVMBuilderRef long Builder, @LLVMMetadataRef long Loc);

    /**
     * Attempts to set the debug location for the given instruction using the
     * current debug location for the given builder.  If the builder has no current
     * debug location, this function is a no-op.
     */
    public static native void LLVMSetInstDebugLocation(@LLVMBuilderRef long Builder, @LLVMValueRef long Inst);

    /**
     * Get the dafult floating-point math metadata for a given builder.
     */
    public static native @LLVMMetadataRef long LLVMBuilderGetDefaultFPMathTag(@LLVMBuilderRef long Builder);

    /**
     * Set the default floating-point math metadata for the given builder.
     * <p>
     * To clear the metadata, pass NULL to \p FPMathTag.
     */
    public static native void LLVMBuilderSetDefaultFPMathTag(
            @LLVMBuilderRef long Builder, @LLVMMetadataRef long FPMathTag
    );

    /**
     * Deprecated: Passing the NULL location will crash.
     * Use LLVMGetCurrentDebugLocation2 instead.
     */
    public static native void LLVMSetCurrentDebugLocation(@LLVMBuilderRef long Builder, @LLVMValueRef long L);

    /**
     * Deprecated: Returning the NULL location will crash.
     * Use LLVMGetCurrentDebugLocation2 instead.
     */
    public static native @LLVMValueRef long LLVMGetCurrentDebugLocation(@LLVMBuilderRef long Builder);

    /* Terminators */
    public static native @LLVMValueRef long LLVMBuildRetVoid(@LLVMBuilderRef long Builder);

    public static native @LLVMValueRef long LLVMBuildRet(@LLVMBuilderRef long Builder, @LLVMValueRef long V);

    public static native @LLVMValueRef long LLVMBuildAggregateRet(
            @LLVMBuilderRef long Builder, @Pointer("LLVMValueRef *") long RetVals,
            @Unsigned int N
    );

    public static native @LLVMValueRef long LLVMBuildBr(@LLVMBuilderRef long Builder, @LLVMBasicBlockRef long Dest);

    public static native @LLVMValueRef long LLVMBuildCondBr(
            @LLVMBuilderRef long Builder, @LLVMValueRef long If,
            @LLVMBasicBlockRef long Then, @LLVMBasicBlockRef long Else
    );

    public static native @LLVMValueRef long LLVMBuildSwitch(
            @LLVMBuilderRef long Builder, @LLVMValueRef long V,
            @LLVMBasicBlockRef long Else, @Unsigned int NumCases
    );

    public static native @LLVMValueRef long LLVMBuildIndirectBr(
            @LLVMBuilderRef long B, @LLVMValueRef long Addr,
            @Unsigned int NumDests
    );

    // LLVMBuildInvoke is deprecated in favor of LLVMBuildInvoke2, in preparation
    // for opaque pointer types.
    public static native @LLVMValueRef long LLVMBuildInvoke(
            @LLVMBuilderRef long Builder, @LLVMValueRef long Fn,
            @Pointer("LLVMValueRef *") long Args, @Unsigned int NumArgs,
            @LLVMBasicBlockRef long Then, @LLVMBasicBlockRef long Catch,
            @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildInvoke2(
            @LLVMBuilderRef long Builder, @LLVMTypeRef long Ty, @LLVMValueRef long Fn,
            @Pointer("LLVMValueRef *") long Args, @Unsigned int NumArgs,
            @LLVMBasicBlockRef long Then, @LLVMBasicBlockRef long Catch,
            @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildUnreachable(@LLVMBuilderRef long Builder);

    /* Exception Handling */
    public static native @LLVMValueRef long LLVMBuildResume(@LLVMBuilderRef long B, @LLVMValueRef long Exn);

    public static native @LLVMValueRef long LLVMBuildLandingPad(
            @LLVMBuilderRef long B, @LLVMTypeRef long Ty,
            @LLVMValueRef long PersFn, @Unsigned int NumClauses,
            @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildCleanupRet(
            @LLVMBuilderRef long B, @LLVMValueRef long CatchPad, @LLVMBasicBlockRef long BB
    );

    public static native @LLVMValueRef long LLVMBuildCatchRet(
            @LLVMBuilderRef long B, @LLVMValueRef long CatchPad, @LLVMBasicBlockRef long BB
    );

    public static native @LLVMValueRef long LLVMBuildCatchPad(
            @LLVMBuilderRef long B, @LLVMValueRef long ParentPad,
            @Pointer("LLVMValueRef *") long Args, @Unsigned int NumArgs,
            @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildCleanupPad(
            @LLVMBuilderRef long B, @LLVMValueRef long ParentPad,
            @Pointer("LLVMValueRef *") long Args, @Unsigned int NumArgs,
            @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildCatchSwitch(
            @LLVMBuilderRef long B, @LLVMValueRef long ParentPad,
            @LLVMBasicBlockRef long UnwindBB,
            @Unsigned int NumHandlers, @Pointer("const char *") long Name
    );

    /* Add a case to the switch instruction */
    public static native void LLVMAddCase(
            @LLVMValueRef long Switch, @LLVMValueRef long OnVal,
            @LLVMBasicBlockRef long Dest
    );

    /* Add a destination to the indirectbr instruction */
    public static native void LLVMAddDestination(@LLVMValueRef long IndirectBr, @LLVMBasicBlockRef long Dest);

    /* Get the number of clauses on the landingpad instruction */
    public static native @Unsigned int LLVMGetNumClauses(@LLVMValueRef long LandingPad);

    /* Get the value of the clause at idnex Idx on the landingpad instruction */
    public static native @LLVMValueRef long LLVMGetClause(@LLVMValueRef long LandingPad, @Unsigned int Idx);

    /* Add a catch or filter clause to the landingpad instruction */
    public static native void LLVMAddClause(@LLVMValueRef long LandingPad, @LLVMValueRef long ClauseVal);

    /* Get the 'cleanup' flag in the landingpad instruction */
    public static native boolean LLVMIsCleanup(@LLVMValueRef long LandingPad);

    /* Set the 'cleanup' flag in the landingpad instruction */
    public static native void LLVMSetCleanup(@LLVMValueRef long LandingPad, boolean Val);

    /* Add a destination to the catchswitch instruction */
    public static native void LLVMAddHandler(@LLVMValueRef long CatchSwitch, @LLVMBasicBlockRef long Dest);

    /* Get the number of handlers on the catchswitch instruction */
    public static native @Unsigned int LLVMGetNumHandlers(@LLVMValueRef long CatchSwitch);

    /**
     * Obtain the basic blocks acting as handlers for a catchswitch instruction.
     * <p>
     * The Handlers parameter should point to a pre-allocated array of
     * LLVMBasicBlockRefs at least LLVMGetNumHandlers() large. On return, the
     * first LLVMGetNumHandlers() entries in the array will be populated
     * with LLVMBasicBlockRef instances.
     *
     * @param CatchSwitch The catchswitch instruction to operate on.
     * @param Handlers    Memory address of an array to be filled with basic blocks.
     */
    public static native void LLVMGetHandlers(@LLVMValueRef long CatchSwitch, @Pointer("LLVMBasicBlockRef *") long Handlers);

    /* Funclets */

    /* Get the number of funcletpad arguments. */
    public static native @LLVMValueRef long LLVMGetArgOperand(@LLVMValueRef long Funclet, @Unsigned int i);

    /* Set a funcletpad argument at the given index. */
    public static native void LLVMSetArgOperand(@LLVMValueRef long Funclet, @Unsigned int i, @LLVMValueRef long value);

    /**
     * Get the parent catchswitch instruction of a catchpad instruction.
     * <p>
     * This only works on llvm::CatchPadInst instructions.
     */
    public static native @LLVMValueRef long LLVMGetParentCatchSwitch(@LLVMValueRef long CatchPad);

    /**
     * Set the parent catchswitch instruction of a catchpad instruction.
     * <p>
     * This only works on llvm::CatchPadInst instructions.
     */
    public static native void LLVMSetParentCatchSwitch(@LLVMValueRef long CatchPad, @LLVMValueRef long CatchSwitch);

    /* Arithmetic */
    public static native @LLVMValueRef long LLVMBuildAdd(
            @LLVMBuilderRef long Builder, @LLVMValueRef long LHS, @LLVMValueRef long RHS, @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildNSWAdd(
            @LLVMBuilderRef long Builder, @LLVMValueRef long LHS, @LLVMValueRef long RHS,
            @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildNUWAdd(
            @LLVMBuilderRef long Builder, @LLVMValueRef long LHS, @LLVMValueRef long RHS,
            @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildFAdd(
            @LLVMBuilderRef long Builder, @LLVMValueRef long LHS, @LLVMValueRef long RHS,
            @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildSub(
            @LLVMBuilderRef long Builder, @LLVMValueRef long LHS, @LLVMValueRef long RHS,
            @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildNSWSub(
            @LLVMBuilderRef long Builder, @LLVMValueRef long LHS, @LLVMValueRef long RHS,
            @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildNUWSub(
            @LLVMBuilderRef long Builder, @LLVMValueRef long LHS, @LLVMValueRef long RHS,
            @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildFSub(
            @LLVMBuilderRef long Builder, @LLVMValueRef long LHS, @LLVMValueRef long RHS,
            @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildMul(
            @LLVMBuilderRef long Builder, @LLVMValueRef long LHS, @LLVMValueRef long RHS,
            @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildNSWMul(
            @LLVMBuilderRef long Builder, @LLVMValueRef long LHS, @LLVMValueRef long RHS,
            @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildNUWMul(
            @LLVMBuilderRef long Builder, @LLVMValueRef long LHS, @LLVMValueRef long RHS,
            @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildFMul(
            @LLVMBuilderRef long Builder, @LLVMValueRef long LHS, @LLVMValueRef long RHS,
            @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildUDiv(
            @LLVMBuilderRef long Builder, @LLVMValueRef long LHS, @LLVMValueRef long RHS,
            @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildExactUDiv(
            @LLVMBuilderRef long Builder, @LLVMValueRef long LHS, @LLVMValueRef long RHS,
            @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildSDiv(
            @LLVMBuilderRef long Builder, @LLVMValueRef long LHS, @LLVMValueRef long RHS,
            @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildExactSDiv(
            @LLVMBuilderRef long Builder, @LLVMValueRef long LHS, @LLVMValueRef long RHS,
            @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildFDiv(
            @LLVMBuilderRef long Builder, @LLVMValueRef long LHS, @LLVMValueRef long RHS,
            @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildURem(
            @LLVMBuilderRef long Builder, @LLVMValueRef long LHS, @LLVMValueRef long RHS,
            @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildSRem(
            @LLVMBuilderRef long Builder, @LLVMValueRef long LHS, @LLVMValueRef long RHS,
            @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildFRem(
            @LLVMBuilderRef long Builder, @LLVMValueRef long LHS, @LLVMValueRef long RHS,
            @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildShl(
            @LLVMBuilderRef long Builder, @LLVMValueRef long LHS, @LLVMValueRef long RHS,
            @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildLShr(
            @LLVMBuilderRef long Builder, @LLVMValueRef long LHS, @LLVMValueRef long RHS,
            @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildAShr(
            @LLVMBuilderRef long Builder, @LLVMValueRef long LHS, @LLVMValueRef long RHS,
            @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildAnd(
            @LLVMBuilderRef long Builder, @LLVMValueRef long LHS, @LLVMValueRef long RHS,
            @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildOr(
            @LLVMBuilderRef long Builder, @LLVMValueRef long LHS, @LLVMValueRef long RHS,
            @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildXor(
            @LLVMBuilderRef long Builder, @LLVMValueRef long LHS, @LLVMValueRef long RHS,
            @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildBinOp(
            @LLVMBuilderRef long B, @LLVMOpcode int Op,
            @LLVMValueRef long LHS, @LLVMValueRef long RHS,
            @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildNeg(@LLVMBuilderRef long Builder, @LLVMValueRef long V, @Pointer("const char *") long Name);

    public static native @LLVMValueRef long LLVMBuildNSWNeg(
            @LLVMBuilderRef long B, @LLVMValueRef long V,
            @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildNUWNeg(
            @LLVMBuilderRef long B, @LLVMValueRef long V,
            @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildFNeg(
            @LLVMBuilderRef long Builder, @LLVMValueRef long V,
            @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildNot(
            @LLVMBuilderRef long Builder, @LLVMValueRef long V, @Pointer("const char *") long Name
    );

    /* Memory */
    public static native @LLVMValueRef long LLVMBuildMalloc(
            @LLVMBuilderRef long Builder, @LLVMTypeRef long Ty, @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildArrayMalloc(
            @LLVMBuilderRef long Builder, @LLVMTypeRef long Ty,
            @LLVMValueRef long Val, @Pointer("const char *") long Name
    );

    /**
     * Creates and inserts a memset to the specified pointer and the
     * specified value.
     */
    public static native @LLVMValueRef long LLVMBuildMemSet(
            @LLVMBuilderRef long B, @LLVMValueRef long Ptr,
            @LLVMValueRef long Val, @LLVMValueRef long Len,
            @Unsigned long Align
    );

    /**
     * Creates and inserts a memcpy between the specified pointers.
     */
    public static native @LLVMValueRef long LLVMBuildMemCpy(
            @LLVMBuilderRef long B,
            @LLVMValueRef long Dst, @Unsigned long DstAlign,
            @LLVMValueRef long Src, @Unsigned long SrcAlign,
            @LLVMValueRef long Size
    );

    /**
     * Creates and inserts a memmove between the specified pointers.
     */
    public static native @LLVMValueRef long LLVMBuildMemMove(
            @LLVMBuilderRef long B,
            @LLVMValueRef long Dst, @Unsigned long DstAlign,
            @LLVMValueRef long Src, @Unsigned long SrcAlign,
            @LLVMValueRef long Size
    );

    public static native @LLVMValueRef long LLVMBuildAlloca(
            @LLVMBuilderRef long Builder,
            @LLVMTypeRef long Ty,
            @Pointer("const char *") long Name);

    public static native @LLVMValueRef long LLVMBuildArrayAlloca(
            @LLVMBuilderRef long Builder, @LLVMTypeRef long Ty,
            @LLVMValueRef long Val, @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildFree(
            @LLVMBuilderRef long Builder, @LLVMValueRef long PointerVal
    );

    // LLVMBuildLoad is deprecated in favor of LLVMBuildLoad2, in preparation for
    // opaque pointer types.
    public static native @LLVMValueRef long LLVMBuildLoad(
            @LLVMBuilderRef long Builder, @LLVMValueRef long PointerVal,
            @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildLoad2(
            @LLVMBuilderRef long Builder, @LLVMTypeRef long Ty,
            @LLVMValueRef long PointerVal, @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildStore(
            @LLVMBuilderRef long Builder, @LLVMValueRef long Val, @LLVMValueRef long Ptr
    );

    // LLVMBuildGEP, LLVMBuildInBoundsGEP, and LLVMBuildStructGEP are deprecated in
    // favor of LLVMBuild*GEP2, in preparation for opaque pointer types.
    public static native @LLVMValueRef long LLVMBuildGEP(
            @LLVMBuilderRef long B, @LLVMValueRef long Pointer,
            @Pointer("LLVMValueRef *") long Indices, @Unsigned long NumIndices,
            @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildInBoundsGEP(
            @LLVMBuilderRef long B, @LLVMValueRef long Pointer,
            @Pointer("LLVMValueRef *") long Indices, @Unsigned long NumIndices,
            @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildStructGEP(
            @LLVMBuilderRef long B, @LLVMValueRef long Pointer,
            @Unsigned long Idx, @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildGEP2(@LLVMBuilderRef long B, @LLVMTypeRef long Ty,
                                                          @LLVMValueRef long Pointer, @Pointer("LLVMValueRef *") long Indices,
                                                          @Unsigned long NumIndices, @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildInBoundsGEP2(
            @LLVMBuilderRef long B, @LLVMTypeRef long Ty,
            @LLVMValueRef long Pointer, @Pointer("LLVMValueRef *") long Indices,
            @Unsigned long NumIndices, @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildStructGEP2(
            @LLVMBuilderRef long B, @LLVMTypeRef long Ty,
            @LLVMValueRef long Pointer, @Unsigned long Idx,
            @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildGlobalString(
            @LLVMBuilderRef long B, @Pointer("const char *") long Str,
            @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildGlobalStringPtr(
            @LLVMBuilderRef long B, @Pointer("const char *") long Str,
            @Pointer("const char *") long Name
    );

    public static native boolean LLVMGetVolatile(@LLVMValueRef long MemoryAccessInst);

    public static native void LLVMSetVolatile(@LLVMValueRef long MemoryAccessInst, boolean IsVolatile);

    public static native boolean LLVMGetWeak(@LLVMValueRef long CmpXchgInst);

    public static native void LLVMSetWeak(@LLVMValueRef long CmpXchgInst, boolean IsWeak);

    public static native @LLVMAtomicOrdering int LLVMGetOrdering(@LLVMValueRef long MemoryAccessInst);

    public static native void LLVMSetOrdering(@LLVMValueRef long MemoryAccessInst, @LLVMAtomicOrdering int Ordering);

    public static native @LLVMAtomicRMWBinOp int LLVMGetAtomicRMWBinOp(@LLVMValueRef long AtomicRMWInst);

    public static native void LLVMSetAtomicRMWBinOp(@LLVMValueRef long AtomicRMWInst, @LLVMAtomicRMWBinOp int BinOp);

    /* Casts */
    public static native @LLVMValueRef long LLVMBuildTrunc(
            @LLVMBuilderRef long Builder, @LLVMValueRef long Val,
            @LLVMTypeRef long DestTy, @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildZExt(
            @LLVMBuilderRef long Builder, @LLVMValueRef long Val,
            @LLVMTypeRef long DestTy, @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildSExt(
            @LLVMBuilderRef long Builder, @LLVMValueRef long Val,
            @LLVMTypeRef long DestTy, @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildFPToUI(
            @LLVMBuilderRef long Builder, @LLVMValueRef long Val,
            @LLVMTypeRef long DestTy, @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildFPToSI(
            @LLVMBuilderRef long Builder, @LLVMValueRef long Val,
            @LLVMTypeRef long DestTy, @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildUIToFP(
            @LLVMBuilderRef long Builder, @LLVMValueRef long Val,
            @LLVMTypeRef long DestTy, @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildSIToFP(
            @LLVMBuilderRef long Builder, @LLVMValueRef long Val,
            @LLVMTypeRef long DestTy, @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildFPTrunc(
            @LLVMBuilderRef long Builder, @LLVMValueRef long Val,
            @LLVMTypeRef long DestTy, @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildFPExt(
            @LLVMBuilderRef long Builder, @LLVMValueRef long Val,
            @LLVMTypeRef long DestTy, @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildPtrToInt(
            @LLVMBuilderRef long Builder, @LLVMValueRef long Val,
            @LLVMTypeRef long DestTy, @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildIntToPtr(@LLVMBuilderRef long Builder, @LLVMValueRef long Val,
                                                              @LLVMTypeRef long DestTy, @Pointer("const char *") long Name)
            ;

    public static native @LLVMValueRef long LLVMBuildBitCast(@LLVMBuilderRef long Builder, @LLVMValueRef long Val,
                                                             @LLVMTypeRef long DestTy, @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildAddrSpaceCast(@LLVMBuilderRef long Builder, @LLVMValueRef long Val,
                                                                   @LLVMTypeRef long DestTy, @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildZExtOrBitCast(
            @LLVMBuilderRef long Builder, @LLVMValueRef long Val,
            @LLVMTypeRef long DestTy, @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildSExtOrBitCast(
            @LLVMBuilderRef long Builder, @LLVMValueRef long Val,
            @LLVMTypeRef long DestTy, @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildTruncOrBitCast(
            @LLVMBuilderRef long Builder, @LLVMValueRef long Val,
            @LLVMTypeRef long DestTy, @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildCast(
            @LLVMBuilderRef long B, @LLVMOpcode int Op, @LLVMValueRef long Val,
            @LLVMTypeRef long DestTy, @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildPointerCast(
            @LLVMBuilderRef long Builder, @LLVMValueRef long Val,
            @LLVMTypeRef long DestTy, @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildIntCast2(
            @LLVMBuilderRef long Builder, @LLVMValueRef long Val,
            @LLVMTypeRef long DestTy, boolean IsSigned,
            @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildFPCast(
            @LLVMBuilderRef long Builder, @LLVMValueRef long Val,
            @LLVMTypeRef long DestTy, @Pointer("const char *") long Name
    );

    /**
     * @deprecated This cast is always signed. Use LLVMBuildIntCast2 instead.
     */
    @Deprecated
    public static native @LLVMValueRef long LLVMBuildIntCast(
            @LLVMBuilderRef long Builder, @LLVMValueRef long Val, /*Signed cast!*/
            @LLVMTypeRef long DestTy, @Pointer("const char *") long Name
    );

    /* Comparisons */
    public static native @LLVMValueRef long LLVMBuildICmp(
            @LLVMBuilderRef long Builder, @LLVMIntPredicate int Op,
            @LLVMValueRef long LHS, @LLVMValueRef long RHS,
            @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildFCmp(
            @LLVMBuilderRef long Builder, @LLVMRealPredicate int Op,
            @LLVMValueRef long LHS, @LLVMValueRef long RHS,
            @Pointer("const char *") long Name
    );

    /* Miscellaneous instructions */
    public static native @LLVMValueRef long LLVMBuildPhi(
            @LLVMBuilderRef long Builder, @LLVMTypeRef long Ty, @Pointer("const char *") long Name
    );

    // LLVMBuildCall is deprecated in favor of LLVMBuildCall2, in preparation for
    // opaque pointer types.
    public static native @LLVMValueRef long LLVMBuildCall(
            @LLVMBuilderRef long Builder, @LLVMValueRef long Fn,
            @Pointer("LLVMValueRef *") long Args, @Unsigned int NumArgs,
            @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildCall2(
            @LLVMBuilderRef long Builder, @LLVMTypeRef long Ty, @LLVMValueRef long Fn,
            @Pointer("LLVMValueRef *") long Args, @Unsigned int NumArgs,
            @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildSelect(
            @LLVMBuilderRef long Builder, @LLVMValueRef long If,
            @LLVMValueRef long Then, @LLVMValueRef long Else,
            @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildVAArg(
            @LLVMBuilderRef long Builder, @LLVMValueRef long List, @LLVMTypeRef long Ty,
            @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildExtractElement(
            @LLVMBuilderRef long Builder, @LLVMValueRef long VecVal,
            @LLVMValueRef long Index, @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildInsertElement(
            @LLVMBuilderRef long Builder, @LLVMValueRef long VecVal,
            @LLVMValueRef long EltVal, @LLVMValueRef long Index,
            @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildShuffleVector(
            @LLVMBuilderRef long Builder,
            @LLVMValueRef long V1, @LLVMValueRef long V2, @LLVMValueRef long Mask,
            @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildExtractValue(
            @LLVMBuilderRef long Builder, @LLVMValueRef long AggVal,
            @Unsigned int Index, @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildInsertValue(
            @LLVMBuilderRef long Builder,
            @LLVMValueRef long AggVal, @LLVMValueRef long EltVal, @Unsigned int Index,
            @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildFreeze(
            @LLVMBuilderRef long Builder, @LLVMValueRef long Val,
            @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildIsNull(
            @LLVMBuilderRef long Builder, @LLVMValueRef long Val,
            @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildIsNotNull(
            @LLVMBuilderRef long Builder, @LLVMValueRef long Val,
            @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildPtrDiff(
            @LLVMBuilderRef long Builder, @LLVMValueRef long LHS,
            @LLVMValueRef long RHS, @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildFence(
            @LLVMBuilderRef long B, @LLVMAtomicOrdering int ordering,
            boolean singleThread, @Pointer("const char *") long Name
    );

    public static native @LLVMValueRef long LLVMBuildAtomicRMW(
            @LLVMBuilderRef long B, @LLVMAtomicRMWBinOp int op,
            @LLVMValueRef long PTR, @LLVMValueRef long Val,
            @LLVMAtomicOrdering int ordering,
            boolean singleThread
    );

    public static native @LLVMValueRef long LLVMBuildAtomicCmpXchg(
            @LLVMBuilderRef long B, @LLVMValueRef long Ptr,
            @LLVMValueRef long Cmp, @LLVMValueRef long New,
            @LLVMAtomicOrdering int SuccessOrdering,
            @LLVMAtomicOrdering int FailureOrdering,
            boolean SingleThread
    );

    public static native boolean LLVMIsAtomicSingleThread(@LLVMValueRef long AtomicInst);

    public static native void LLVMSetAtomicSingleThread(@LLVMValueRef long AtomicInst, boolean SingleThread);

    public static native @LLVMAtomicOrdering int LLVMGetCmpXchgSuccessOrdering(@LLVMValueRef long CmpXchgInst);

    public static native void LLVMSetCmpXchgSuccessOrdering(
            @LLVMValueRef long CmpXchgInst,
            @LLVMAtomicOrdering int Ordering
    );

    public static native @LLVMAtomicOrdering int LLVMGetCmpXchgFailureOrdering(@LLVMValueRef long CmpXchgInst);

    public static native void LLVMSetCmpXchgFailureOrdering(
            @LLVMValueRef long CmpXchgInst,
            @LLVMAtomicOrdering int Ordering
    );

    //endregion

    //region LLVMCCoreModuleProvider Module Providers

    /**
     * Changes the type of M so it can be passed to FunctionPassManagers and the
     * JIT.  They take ModuleProviders for historical reasons.
     */
    public static native @LLVMModuleProviderRef long LLVMCreateModuleProviderForExistingModule(@LLVMModuleRef long M);

    /**
     * Destroys the module M.
     */
    public static native void LLVMDisposeModuleProvider(@LLVMModuleProviderRef long M);

    //endregion

    //region LLVMCCoreMemoryBuffers Memory Buffers

    public static native boolean LLVMCreateMemoryBufferWithContentsOfFile(
            @Pointer("const char *") long Path,
            @Pointer("LLVMMemoryBufferRef *") long OutMemBuf,
            @Pointer("char **") long OutMessage
    );

    public static native boolean LLVMCreateMemoryBufferWithSTDIN(
            @Pointer("LLVMMemoryBufferRef *") long OutMemBuf,
            @Pointer("char **") long OutMessage
    );

    public static native @LLVMMemoryBufferRef long LLVMCreateMemoryBufferWithMemoryRange(
            @Pointer("const char *") long InputData,
            @SizeT long InputDataLength,
            @Pointer("const char *") long BufferName,
            boolean RequiresNullTerminator
    );

    public static native @LLVMMemoryBufferRef long LLVMCreateMemoryBufferWithMemoryRangeCopy(
            @Pointer("const char *") long InputData,
            @SizeT long InputDataLength,
            @Pointer("const char *") long BufferName
    );

    public static native @Pointer("const char *") long LLVMGetBufferStart(@LLVMMemoryBufferRef long MemBuf);

    public static native @SizeT long LLVMGetBufferSize(@LLVMMemoryBufferRef long MemBuf);

    public static native void LLVMDisposeMemoryBuffer(@LLVMMemoryBufferRef long MemBuf);

    //endregion

    //region LLVMCCorePassRegistry Pass Registry

    /**
     * Return the global pass registry, for use with initialization functions.
     */
    public static native @LLVMPassRegistryRef long LLVMGetGlobalPassRegistry();

    //endregion

    //region LLVMCCorePassManagers Pass Managers

    /**
     * Constructs a new whole-module pass pipeline. This type of pipeline is
     * suitable for link-time optimization and whole-module transformations.
     */
    public static native @LLVMPassManagerRef long LLVMCreatePassManager();

    /**
     * Constructs a new function-by-function pass pipeline over the module
     * provider. It does not take ownership of the module provider. This type of
     * pipeline is suitable for code generation and JIT compilation tasks.
     */
    public static native @LLVMPassManagerRef long LLVMCreateFunctionPassManagerForModule(@LLVMModuleRef long M);

    /**
     * Deprecated: Use LLVMCreateFunctionPassManagerForModule instead.
     */
    public static native @LLVMPassManagerRef long LLVMCreateFunctionPassManager(@LLVMModuleProviderRef long MP);

    /**
     * Initializes, executes on the provided module, and finalizes all of the
     * passes scheduled in the pass manager. Returns 1 if any of the passes
     * modified the module, 0 otherwise.
     */
    public static native boolean LLVMRunPassManager(@LLVMPassManagerRef long PM, @LLVMModuleRef long M);

    /**
     * Initializes all of the function passes scheduled in the function pass
     * manager. Returns 1 if any of the passes modified the module, 0 otherwise.
     */
    public static native boolean LLVMInitializeFunctionPassManager(@LLVMPassManagerRef long FPM);

    /**
     * Executes all of the function passes scheduled in the function pass manager
     * on the provided function. Returns 1 if any of the passes modified the
     * function, false otherwise.
     */
    public static native boolean LLVMRunFunctionPassManager(@LLVMPassManagerRef long FPM, @LLVMValueRef long F);

    /**
     * Finalizes all of the function passes scheduled in the function pass
     * manager. Returns 1 if any of the passes modified the module, 0 otherwise.
     */
    public static native boolean LLVMFinalizeFunctionPassManager(@LLVMPassManagerRef long FPM);

    /**
     * Frees the memory of a pass pipeline. For function pipelines, does not free
     * the module provider.
     */
    public static native void LLVMDisposePassManager(@LLVMPassManagerRef long PM);

    //endregion

    //region LLVMCCoreThreading Threading

    /* Handle the structures needed to make LLVM safe for multithreading. */

    /**
     * @deprecated Multi-threading can only be enabled/disabled with the compile
     * time define LLVM_ENABLE_THREADS.  This function always returns
     * LLVMIsMultithreaded().
     */
    @Deprecated
    public static native boolean LLVMStartMultithreaded();

    /**
     * @deprecated Multi-threading can only be enabled/disabled with the compile
     * time define LLVM_ENABLE_THREADS.
     */
    @Deprecated
    public static native void LLVMStopMultithreaded();

    /**
     * Check whether LLVM is executing in thread-safe mode or not.
     */
    public static native boolean LLVMIsMultithreaded();

    //endregion
}
