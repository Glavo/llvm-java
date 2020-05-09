package asia.kala.llvm.binding;

import asia.kala.llvm.binding.annotations.Pointer;
import asia.kala.llvm.binding.annotations.SizeT;
import asia.kala.llvm.binding.annotations.Unsigned;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public final class NativeUtils extends LLVMLoader {
    public static @Pointer("void *") long nullptr = 0L;

    public static native @Pointer("void *") long GetDirectBufferAddress(ByteBuffer buffer);

    public static native ByteBuffer NewDirectByteBuffer(@Pointer("void *") long address, long capacity);

    public static native @Pointer("void *") long Malloc(@SizeT long size);

    public static native void Free(@Pointer("void *") long block);

    public static native @Unsigned("uint8_t") byte GetByte(@Pointer("const uint8_t *") long address);

    public static native void SetByte(@Pointer("uint8_t *") long address, @Unsigned("uint8_t") byte value);

    public static native @Pointer("size_t *") long NewSizeT();

    public static native @SizeT long GetSizeT(@Pointer("const size_t *") long address);

    public static native void SetSizeT(@Pointer("size_t *") long address, @SizeT long value);

    public static native @Pointer("LLVMBool *") long NewLLVMBool();

    public static native boolean GetLLVMBool(@Pointer("const LLVMBool *") long address);

    public static native void SetLLVMBool(@Pointer("LLVMBool *") long address, boolean value);

    public static native void DumpString(@Pointer("const char *") long string);

    public static @Pointer("char *") long CopyStringToNative(String string) {
        return CopyStringToNative(string, null);
    }

    public static @Pointer("char *") long CopyStringToNative(String string, Charset charset) {
        if (string == null) {
            return 0L;
        }
        if (charset == null) {
            charset = Charset.defaultCharset();
        }

        ByteBuffer encoded = charset.encode(string);
        int length = encoded.remaining();

        final long res = Malloc(length + 1);

        long ptr = res;
        while (encoded.hasRemaining()) {
            SetByte(ptr++, encoded.get());
        }
        SetByte(ptr, (byte) 0);
        return res;
    }
}
