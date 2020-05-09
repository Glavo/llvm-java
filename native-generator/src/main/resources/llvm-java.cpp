#include "llvm-java.h"

extern "C" {

JavaVM *jvm = nullptr;

JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *vm, void *reserved) {
    jvm = vm;
    return JNI_VERSION_1_6;
}

JNIEXPORT void JNICALL JNI_OnUnload(JavaVM *vm, void *reserved) {
    if (vm == jvm) {
        jvm = nullptr;
    }
}

}
