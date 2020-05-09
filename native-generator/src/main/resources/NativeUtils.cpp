#include <cstdlib>
#include <cstdio>
#include <cstring>
#include "llvm-java.h"

#define LLVM_JAVA_NATIVE_UTILS(name) Java_asia_kala_llvm_binding_NativeUtils_ ## name

extern "C" {

JNIEXPORT jlong JNICALL LLVM_JAVA_NATIVE_UTILS(GetDirectBufferAddress)(JNIEnv *env, jclass, jobject buffer) {
    return (jlong) (uintptr_t) env->GetDirectBufferAddress(buffer);
}

JNIEXPORT jobject JNICALL LLVM_JAVA_NATIVE_UTILS(NewDirectByteBuffer)(JNIEnv *env, jclass, jlong address, jlong capacity) {
    return env->NewDirectByteBuffer((void *) (uintptr_t) address, capacity);
}

JNIEXPORT jlong JNICALL LLVM_JAVA_NATIVE_UTILS(Malloc)(JNIEnv *env, jclass, jlong size) {
    return (jlong) (uintptr_t) malloc((size_t) (uint64_t) size);
}

JNIEXPORT void JNICALL LLVM_JAVA_NATIVE_UTILS(Free)(JNIEnv *env, jclass, jlong block) {
    free((void *) (uintptr_t) block);
}

JNIEXPORT jlong JNICALL LLVM_JAVA_NATIVE_UTILS(NewSizeT)(JNIEnv *env, jclass) {
    return (jlong) (uintptr_t) malloc(sizeof(size_t));
}

JNIEXPORT jlong JNICALL LLVM_JAVA_NATIVE_UTILS(GetSizeT)(JNIEnv *env, jclass, jlong address) {
    return (jlong) (uint64_t) *((const size_t *) (uintptr_t) address);
}

JNIEXPORT void JNICALL LLVM_JAVA_NATIVE_UTILS(SetSizeT)(JNIEnv *env, jclass, jlong address, jlong value) {
    *((size_t *) (uintptr_t) address) = (size_t) (uint64_t) value;
}

JNIEXPORT jbyte JNICALL LLVM_JAVA_NATIVE_UTILS(GetByte)(JNIEnv *env, jclass, jlong address) {
    return (jbyte) (uint8_t) *((const uint8_t *) (uintptr_t) address);
}

JNIEXPORT void JNICALL LLVM_JAVA_NATIVE_UTILS(SetByte)(JNIEnv *env, jclass, jlong address, jbyte value) {
    *((uint8_t *) (uintptr_t) address) = (uint8_t) value;
}

JNIEXPORT jlong JNICALL LLVM_JAVA_NATIVE_UTILS(NewLLVMBool)(JNIEnv *env, jclass) {
    return (jlong) (uintptr_t) malloc(sizeof(LLVMBool));
}

JNIEXPORT jboolean JNICALL LLVM_JAVA_NATIVE_UTILS(GetLLVMBool)(JNIEnv *env, jclass, jlong address) {
    return (*(const LLVMBool *) (uintptr_t) address) ? JNI_TRUE : JNI_FALSE;
}

JNIEXPORT void JNICALL LLVM_JAVA_NATIVE_UTILS(SetLLVMBool)(JNIEnv *env, jclass, jlong address, jboolean value) {
    *((LLVMBool *) (uintptr_t) address) = value;
}

JNIEXPORT void JNICALL LLVM_JAVA_NATIVE_UTILS(DumpString)(JNIEnv *env, jclass, jlong string) {
    printf("%s", (const char *) (uintptr_t) string);
}

}