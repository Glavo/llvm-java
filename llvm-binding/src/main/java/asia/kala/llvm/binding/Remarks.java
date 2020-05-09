package asia.kala.llvm.binding;

import asia.kala.llvm.binding.annotations.*;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static asia.kala.llvm.binding.Types.*;

@CInfo(
        fileName = "Remarks.c",
        include = {
                "<llvm-c/Remarks.h>",
                "llvm-java.h"
        }
)
public final class Remarks extends LLVMLoader {
    private Remarks() {
    }

    // 0 -> 1: Bitstream remarks support.
    public static final int REMARKS_API_VERSION = REMARKS_API_VERSION();

    @Statement("return REMARKS_API_VERSION;")
    private static native @Signed("int") int REMARKS_API_VERSION();

    @CEnum("LLVMRemarkType")
    @Documented
    @Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMRemarkType {
        int LLVMRemarkTypeUnknown = 0;
        int LLVMRemarkTypePassed = 1;
        int LLVMRemarkTypeMissed = 2;
        int LLVMRemarkTypeAnalysis = 3;
        int LLVMRemarkTypeAnalysisFPCommute = 4;
        int LLVMRemarkTypeAnalysisAliasing = 5;
        int LLVMRemarkTypeFailure = 6;
    }

    /**
     * String containing a buffer and a length. The buffer is not guaranteed to be
     * zero-terminated.
     *
     * @since REMARKS_API_VERSION 0
     */
    @Pointer("LLVMRemarkStringRef")
    @Documented
    @Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMRemarkStringRef {
    }


    /**
     * Returns the buffer holding the string.
     *
     * @since REMARKS_API_VERSION 0
     */
    public static native @Pointer("const char *") long LLVMRemarkStringGetData(@LLVMRemarkStringRef long String);

    /**
     * Returns the size of the string.
     * <p>
     * @since REMARKS_API_VERSION 0
     */
    public static native @Unsigned("uint32_t") int LLVMRemarkStringGetLen(@LLVMRemarkStringRef long String);

    /**
     * DebugLoc containing File, Line and Column.
     * <p>
     * @since REMARKS_API_VERSION 0
     */
    @Pointer("LLVMRemarkDebugLocRef")
    @Documented
    @Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMRemarkDebugLocRef {
    }

    /**
     * Return the path to the source file for a debug location.
     * <p>
     * @since REMARKS_API_VERSION 0
     */
    public static native @LLVMRemarkStringRef long LLVMRemarkDebugLocGetSourceFilePath(@LLVMRemarkDebugLocRef long DL);

    /**
     * Return the line in the source file for a debug location.
     * <p>
     * @since REMARKS_API_VERSION 0
     */
    public static native @Unsigned("uint32_t") int LLVMRemarkDebugLocGetSourceLine(@LLVMRemarkDebugLocRef long DL);

    /**
     * Return the column in the source file for a debug location.
     * <p>
     * @since REMARKS_API_VERSION 0
     */
    public static native @Unsigned("uint32_t") int LLVMRemarkDebugLocGetSourceColumn(@LLVMRemarkDebugLocRef long DL);

    /**
     * Element of the "Args" list. The key might give more information about what
     * the semantics of the value are, e.g. "Callee" will tell you that the value
     * is a symbol that names a function.
     * <p>
     * @since REMARKS_API_VERSION 0
     */
    @Pointer("LLVMRemarkArgRef")
    @Documented
    @Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMRemarkArgRef {
    }

    /**
     * Returns the key of an argument. The key defines what the value is, and the
     * same key can appear multiple times in the list of arguments.
     * <p>
     * @since REMARKS_API_VERSION 0
     */
    public static native @LLVMRemarkStringRef long LLVMRemarkArgGetKey(@LLVMRemarkArgRef long Arg);

    /**
     * Returns the value of an argument. This is a string that can contain newlines.
     * <p>
     * @since REMARKS_API_VERSION 0
     */
    public static native @LLVMRemarkStringRef long LLVMRemarkArgGetValue(@LLVMRemarkArgRef long Arg);

    /**
     * Returns the debug location that is attached to the value of this argument.
     * <p>
     * If there is no debug location, the return value will be `NULL`.
     * <p>
     * @since REMARKS_API_VERSION 0
     */
    public static native @LLVMRemarkDebugLocRef long LLVMRemarkArgGetDebugLoc(@LLVMRemarkArgRef long Arg);

    /**
     * A remark emitted by the compiler.
     * <p>
     * @since REMARKS_API_VERSION 0
     */
    @Pointer("LLVMRemarkEntryRef")
    @Documented
    @Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMRemarkEntryRef {
    }

    /**
     * Free the resources used by the remark entry.
     * <p>
     * @since REMARKS_API_VERSION 0
     */
    public static native void LLVMRemarkEntryDispose(@LLVMRemarkEntryRef long Remark);

    /**
     * The type of the remark. For example, it can allow users to only keep the
     * missed optimizations from the compiler.
     * <p>
     * @since REMARKS_API_VERSION 0
     */
    public static native @LLVMRemarkType int LLVMRemarkEntryGetType(@LLVMRemarkEntryRef long Remark);

    /**
     * Get the name of the pass that emitted this remark.
     * <p>
     * @since REMARKS_API_VERSION 0
     */
    public static native @LLVMRemarkStringRef long
    LLVMRemarkEntryGetPassName(@LLVMRemarkEntryRef long Remark);

    /**
     * Get an identifier of the remark.
     * <p>
     * @since REMARKS_API_VERSION 0
     */
    public static native @LLVMRemarkStringRef long LLVMRemarkEntryGetRemarkName(@LLVMRemarkEntryRef long Remark);

    /**
     * Get the name of the function being processed when the remark was emitted.
     * <p>
     * @since REMARKS_API_VERSION 0
     */
    public static native @LLVMRemarkStringRef long LLVMRemarkEntryGetFunctionName(@LLVMRemarkEntryRef long Remark);

    /**
     * Returns the debug location that is attached to this remark.
     * <p>
     * If there is no debug location, the return value will be `NULL`.
     * <p>
     * @since REMARKS_API_VERSION 0
     */
    public static native @LLVMRemarkDebugLocRef long LLVMRemarkEntryGetDebugLoc(@LLVMRemarkEntryRef long Remark);

    /**
     * Return the hotness of the remark.
     * <p>
     * A hotness of `0` means this value is not set.
     * <p>
     * @since REMARKS_API_VERSION 0
     */
    public static native @Unsigned("uint64_t") long LLVMRemarkEntryGetHotness(@LLVMRemarkEntryRef long Remark);

    /**
     * The number of arguments the remark holds.
     * <p>
     * @since REMARKS_API_VERSION 0
     */
    public static native @Unsigned("uint32_t") int LLVMRemarkEntryGetNumArgs(@LLVMRemarkEntryRef long Remark);

    /**
     * Get a new iterator to iterate over a remark's argument.
     * <p>
     * If there are no arguments in \p Remark, the return value will be `NULL`.
     * <p>
     * The lifetime of the returned value is bound to the lifetime of \p Remark.
     * <p>
     * @since REMARKS_API_VERSION 0
     */
    public static native @LLVMRemarkArgRef long LLVMRemarkEntryGetFirstArg(@LLVMRemarkEntryRef long Remark);

    /**
     * Get the next argument in \p Remark from the position of \p It.
     * <p>
     * Returns `NULL` if there are no more arguments available.
     * <p>
     * The lifetime of the returned value is bound to the lifetime of \p Remark.
     * <p>
     * @since REMARKS_API_VERSION 0
     */
    public static native @LLVMRemarkArgRef long LLVMRemarkEntryGetNextArg(
            @LLVMRemarkArgRef long It,
            @LLVMRemarkEntryRef long Remark
    );

    @Pointer("LLVMRemarkParserRef")
    @Documented
    @Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMRemarkParserRef {
    }

    /**
     * Creates a remark parser that can be used to parse the buffer located in \p
     * Buf of size \p Size bytes.
     * <p>
     * \p Buf cannot be `NULL`.
     * <p>
     * This function should be paired with LLVMRemarkParserDispose() to avoid
     * leaking resources.
     * <p>
     * @since REMARKS_API_VERSION 0
     */
    public static native @LLVMRemarkParserRef long LLVMRemarkParserCreateYAML(
            @Pointer("const void *") long Buf,
            @Unsigned("uint64_t") long Size
    );

    /**
     * Creates a remark parser that can be used to parse the buffer located in \p
     * Buf of size \p Size bytes.
     * <p>
     * \p Buf cannot be `NULL`.
     * <p>
     * This function should be paired with LLVMRemarkParserDispose() to avoid
     * leaking resources.
     * <p>
     * @since REMARKS_API_VERSION 1
     */
    public static native @LLVMRemarkParserRef long LLVMRemarkParserCreateBitstream(
            @Pointer("const void *") long Buf,
            @Unsigned("uint64_t") long Size
    );

    /**
     * Returns the next remark in the file.
     * <p>
     * The value pointed to by the return value needs to be disposed using a call to
     * LLVMRemarkEntryDispose().
     * <p>
     * All the entries in the returned value that are of LLVMRemarkStringRef type
     * will become invalidated once a call to LLVMRemarkParserDispose is made.
     * <p>
     * If the parser reaches the end of the buffer, the return value will be `NULL`.
     * <p>
     * In the case of an error, the return value will be `NULL`, and:
     * <p>
     * 1) LLVMRemarkParserHasError() will return `1`.
     * <p>
     * 2) LLVMRemarkParserGetErrorMessage() will return a descriptive error
     * message.
     * <p>
     * An error may occur if:
     * <p>
     * 1) An argument is invalid.
     * <p>
     * 2) There is a parsing error. This can occur on things like malformed YAML.
     * <p>
     * 3) There is a Remark semantic error. This can occur on well-formed files with
     * missing or extra fields.
     * <p>
     * Here is a quick example of the usage:
     * <p>
     * ```
     * LLVMRemarkParserRef Parser = LLVMRemarkParserCreateYAML(Buf, Size);
     * LLVMRemarkEntryRef Remark = NULL;
     * while ((Remark = LLVMRemarkParserGetNext(Parser))) {
     * // use Remark
     * LLVMRemarkEntryDispose(Remark); // Release memory.
     * }
     * bool HasError = LLVMRemarkParserHasError(Parser);
     * LLVMRemarkParserDispose(Parser);
     * ```
     * <p>
     * @since REMARKS_API_VERSION 0
     */
    public static native @LLVMRemarkEntryRef long LLVMRemarkParserGetNext(@LLVMRemarkParserRef long Parser);

    /**
     * Returns `1` if the parser encountered an error while parsing the buffer.
     * <p>
     * @since REMARKS_API_VERSION 0
     */
    public static native boolean LLVMRemarkParserHasError(@LLVMRemarkParserRef long Parser);

    /**
     * Returns a null-terminated string containing an error message.
     * <p>
     * In case of no error, the result is `NULL`.
     * <p>
     * The memory of the string is bound to the lifetime of \p Parser. If
     * LLVMRemarkParserDispose() is called, the memory of the string will be
     * released.
     * <p>
     * @since REMARKS_API_VERSION 0
     */
    public static native @Pointer("const char *") long LLVMRemarkParserGetErrorMessage(@LLVMRemarkParserRef long Parser);

    /**
     * Releases all the resources used by \p Parser.
     * <p>
     * @since REMARKS_API_VERSION 0
     */
    public static native void LLVMRemarkParserDispose(@LLVMRemarkParserRef long Parser);

    /**
     * Returns the version of the remarks library.
     * <p>
     * @since REMARKS_API_VERSION 0
     */
    public static @Unsigned("uint32_t") int LLVMRemarkVersion() {
        return REMARKS_API_VERSION;
    }
}
