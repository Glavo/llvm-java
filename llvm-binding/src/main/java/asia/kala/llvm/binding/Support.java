package asia.kala.llvm.binding;

import asia.kala.llvm.binding.annotations.CInfo;
import asia.kala.llvm.binding.annotations.Pointer;
import asia.kala.llvm.binding.annotations.Signed;

@CInfo(
        fileName = "Support.c",
        include = {
                "<llvm-c/Support.h>",
                "llvm-java.h"
        }
)
public final class Support extends LLVMLoader {

    private Support() {
    }

    /**
     * This function permanently loads the dynamic library at the given path.
     * It is safe to call this function multiple times for the same library.
     */
    public static native boolean LLVMLoadLibraryPermanently(@Pointer("const char *") long Filename);

    /**
     * This function parses the given arguments using the LLVM command line parser.
     * Note that the only stable thing about this function is its signature; you
     * cannot rely on any particular set of command line arguments being interpreted
     * the same way across LLVM versions.
     */
    public static native void LLVMParseCommandLineOptions(
            @Signed("int") int argc, @Pointer("const char *const *") long argv,
            @Pointer("const char *") long Overview
    );

    /**
     * This function will search through all previously loaded dynamic
     * libraries for the symbol \p symbolName. If it is found, the address of
     * that symbol is returned. If not, null is returned.
     */
    public static native @Pointer("void *") long LLVMSearchForAddressOfSymbol(@Pointer("const char *") long symbolName);

    /**
     * This functions permanently adds the symbol \p symbolName with the
     * value \p symbolValue.  These symbols are searched before any
     * libraries.
     */
    public static native void LLVMAddSymbol(@Pointer("const char *") long symbolName, @Pointer("void *") long symbolValue);
}
