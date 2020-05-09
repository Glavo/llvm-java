package asia.kala.llvm.binding;

import asia.kala.llvm.binding.annotations.*;

import java.lang.annotation.*;

import static asia.kala.llvm.binding.Types.*;

@CInfo(
        fileName = "Object.c",
        include = {
                "<llvm-c/Object.h>",
                "llvm-java.h"
        }
)
public final class ObjectH extends LLVMLoader {
    private ObjectH() {
    }

    @Pointer("LLVMSectionIteratorRef")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMSectionIteratorRef {
    }

    @Pointer("LLVMSymbolIteratorRef")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMSymbolIteratorRef {
    }

    @Pointer("LLVMRelocationIteratorRef")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMRelocationIteratorRef {
    }

    @CEnum("LLVMBinaryType")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMBinaryType {
        /**
         * Archive file.
         */
        int LLVMBinaryTypeArchive = 0;
        /**
         * Mach-O Universal Binary file.
         */
        int LLVMBinaryTypeMachOUniversalBinary = 1;
        /**
         * COFF Import file.
         */
        int LLVMBinaryTypeCOFFImportFile = 2;
        /**
         * LLVM IR.
         */
        int LLVMBinaryTypeIR = 3;
        /**
         * Windows resource (.res) file.
         */
        int LLVMBinaryTypeWinRes = 4;
        /**
         * COFF Object file.
         */
        int LLVMBinaryTypeCOFF = 5;
        /**
         * ELF 32-bit, little endian.
         */
        int LLVMBinaryTypeELF32L = 6;
        /**
         * ELF 32-bit, big endian.
         */
        int LLVMBinaryTypeELF32B = 7;
        /**
         * ELF 64-bit, little endian.
         */
        int LLVMBinaryTypeELF64L = 8;
        /**
         * ELF 64-bit, big endian.
         */
        int LLVMBinaryTypeELF64B = 9;
        /**
         * MachO 32-bit, little endian.
         */
        int LLVMBinaryTypeMachO32L = 10;
        /**
         * MachO 32-bit, big endian.
         */
        int LLVMBinaryTypeMachO32B = 11;
        /**
         * MachO 64-bit, little endian.
         */
        int LLVMBinaryTypeMachO64L = 12;
        /**
         * MachO 64-bit, big endian.
         */
        int LLVMBinaryTypeMachO64B = 13;
        /**
         * Web Assembly.
         */
        int LLVMBinaryTypeWasm = 14;
    }

    /**
     * Create a binary file from the given memory buffer.
     * <p>
     * The exact type of the binary file will be inferred automatically, and the
     * appropriate implementation selected.  The context may be NULL except if
     * the resulting file is an LLVM IR file.
     * <p>
     * The memory buffer is not consumed by this function.  It is the responsibilty
     * of the caller to free it with \c LLVMDisposeMemoryBuffer.
     * <p>
     * If NULL is returned, the \p ErrorMessage parameter is populated with the
     * error's description.  It is then the caller's responsibility to free this
     * message by calling \c LLVMDisposeMessage.
     */
    public static native @LLVMBinaryRef long LLVMCreateBinary(
            @LLVMMemoryBufferRef long MemBuf,
            @LLVMContextRef long Context,
            @Pointer("char **") long ErrorMessage
    );

    /**
     * Dispose of a binary file.
     * <p>
     * The binary file does not own its backing buffer.  It is the responsibilty
     * of the caller to free it with \c LLVMDisposeMemoryBuffer.
     */
    public static native void LLVMDisposeBinary(@LLVMBinaryRef long BR);

    /**
     * Retrieves a copy of the memory buffer associated with this object file.
     * <p>
     * The returned buffer is merely a shallow copy and does not own the actual
     * backing buffer of the binary. Nevertheless, it is the responsibility of the
     * caller to free it with \c LLVMDisposeMemoryBuffer.
     */
    public static native @LLVMMemoryBufferRef long LLVMBinaryCopyMemoryBuffer(@LLVMBinaryRef long BR);

    /**
     * Retrieve the specific type of a binary.
     */
    public static native @LLVMBinaryType int LLVMBinaryGetType(@LLVMBinaryRef long BR);

    /*
     * For a Mach-O universal binary file, retrieves the object file corresponding
     * to the given architecture if it is present as a slice.
     *
     * If NULL is returned, the \p ErrorMessage parameter is populated with the
     * error's description.  It is then the caller's responsibility to free this
     * message by calling \c LLVMDisposeMessage.
     *
     * It is the responsiblity of the caller to free the returned object file by
     * calling \c LLVMDisposeBinary.
     */
    public static native @LLVMBinaryRef long LLVMMachOUniversalBinaryCopyObjectForArch(
            @LLVMBinaryRef long BR,
            @Pointer("const char *") long Arch,
            @SizeT long ArchLen,
            @Pointer("char **") long ErrorMessage
    );

    /**
     * Retrieve a copy of the section iterator for this object file.
     * <p>
     * If there are no sections, the result is NULL.
     * <p>
     * The returned iterator is merely a shallow copy. Nevertheless, it is
     * the responsibility of the caller to free it with
     * \c LLVMDisposeSectionIterator.
     */
    public static native @LLVMSectionIteratorRef long LLVMObjectFileCopySectionIterator(@LLVMBinaryRef long BR);

    /**
     * Returns whether the given section iterator is at the end.
     */
    public static native boolean LLVMObjectFileIsSectionIteratorAtEnd(
            @LLVMBinaryRef long BR,
            @LLVMSectionIteratorRef long SI
    );

    /**
     * Retrieve a copy of the symbol iterator for this object file.
     * <p>
     * If there are no symbols, the result is NULL.
     * <p>
     * The returned iterator is merely a shallow copy. Nevertheless, it is
     * the responsibility of the caller to free it with
     * \c LLVMDisposeSymbolIterator.
     */
    public static native @LLVMSymbolIteratorRef long LLVMObjectFileCopySymbolIterator(@LLVMBinaryRef long BR);

    /**
     * Returns whether the given symbol iterator is at the end.
     */
    public static native boolean LLVMObjectFileIsSymbolIteratorAtEnd(
            @LLVMBinaryRef long BR,
            @LLVMSymbolIteratorRef long SI
    );

    public static native void LLVMDisposeSectionIterator(@LLVMSectionIteratorRef long SI);

    public static native void LLVMMoveToNextSection(@LLVMSectionIteratorRef long SI);

    public static native void LLVMMoveToContainingSection(@LLVMSectionIteratorRef long Sect,
                                                          @LLVMSymbolIteratorRef long Sym);

    // ObjectFile Symbol iterators
    public static native void LLVMDisposeSymbolIterator(@LLVMSymbolIteratorRef long SI);

    public static native void LLVMMoveToNextSymbol(@LLVMSymbolIteratorRef long SI);

    // SectionRef accessors
    public static native @Pointer("const char *") long LLVMGetSectionName(@LLVMSectionIteratorRef long SI);

    public static native @Unsigned("uint64_t") long LLVMGetSectionSize(@LLVMSectionIteratorRef long SI);

    public static native @Pointer("const char *") long LLVMGetSectionContents(@LLVMSectionIteratorRef long SI);

    public static native @Unsigned("uint64_t") long LLVMGetSectionAddress(@LLVMSectionIteratorRef long SI);

    public static native boolean LLVMGetSectionContainsSymbol(
            @LLVMSectionIteratorRef long SI,
            @LLVMSymbolIteratorRef long Sym
    );

    // Section Relocation iterators
    public static native @LLVMRelocationIteratorRef long LLVMGetRelocations(@LLVMSectionIteratorRef long Section);

    public static native void LLVMDisposeRelocationIterator(@LLVMRelocationIteratorRef long RI);

    public static native boolean LLVMIsRelocationIteratorAtEnd(
            @LLVMSectionIteratorRef long Section,
            @LLVMRelocationIteratorRef long RI
    );

    public static native void LLVMMoveToNextRelocation(@LLVMRelocationIteratorRef long RI);


    // SymbolRef accessors
    public static native @Pointer("const char *") long LLVMGetSymbolName(@LLVMSymbolIteratorRef long SI);

    public static native @Unsigned("uint64_t") long LLVMGetSymbolAddress(@LLVMSymbolIteratorRef long SI);

    public static native @Unsigned("uint64_t") long LLVMGetSymbolSize(@LLVMSymbolIteratorRef long SI);

    // RelocationRef accessors
    public static native @Unsigned("uint64_t") long LLVMGetRelocationOffset(@LLVMRelocationIteratorRef long RI);

    public static native @LLVMSymbolIteratorRef long LLVMGetRelocationSymbol(@LLVMRelocationIteratorRef long RI);

    public static native @Unsigned("uint64_t") long LLVMGetRelocationType(@LLVMRelocationIteratorRef long RI);

    // NOTE: Caller takes ownership of returned string of the two
    // following functions.
    public static native @Pointer("const char *") long LLVMGetRelocationTypeName(@LLVMRelocationIteratorRef long RI);

    public static native @Pointer("const char *") long LLVMGetRelocationValueString(@LLVMRelocationIteratorRef long RI);

    /**
     * @deprecated Use @LLVMBinaryRef long instead.
     */
    @Deprecated
    @Pointer("LLVMObjectFileRef")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMObjectFileRef {
    }

    /**
     * @deprecated Use LLVMCreateBinary instead.
     */
    @Deprecated
    public static native @LLVMObjectFileRef long LLVMCreateObjectFile(@LLVMMemoryBufferRef long MemBuf);

    /**
     * @deprecated Use LLVMDisposeBinary instead.
     */
    @Deprecated
    public static native void LLVMDisposeObjectFile(@LLVMObjectFileRef long ObjectFile);

    /**
     * @deprecated Use LLVMObjectFileCopySectionIterator instead.
     */
    @Deprecated
    public static native @LLVMSectionIteratorRef long LLVMGetSections(@LLVMObjectFileRef long ObjectFile);

    /**
     * @deprecated Use LLVMObjectFileIsSectionIteratorAtEnd instead.
     */
    @Deprecated
    public static native boolean LLVMIsSectionIteratorAtEnd(
            @LLVMObjectFileRef long ObjectFile,
            @LLVMSectionIteratorRef long SI
    );

    /**
     * @deprecated Use LLVMObjectFileCopySymbolIterator instead.
     */
    @Deprecated
    public static native @LLVMSymbolIteratorRef long LLVMGetSymbols(@LLVMObjectFileRef long ObjectFile);

    /**
     * @deprecated Use LLVMObjectFileIsSymbolIteratorAtEnd instead.
     */
    @Deprecated
    public static native boolean LLVMIsSymbolIteratorAtEnd(
            @LLVMObjectFileRef long ObjectFile,
            @LLVMSymbolIteratorRef long SI
    );
}
