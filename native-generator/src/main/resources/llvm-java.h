#pragma once

#include <llvm-c/Types.h>
#include <jni.h>

#ifdef __cplusplus

#include <cstdint>

#else
#include <stdint.h>
#endif

#ifdef __cplusplus
extern "C" {
#endif

extern JavaVM *jvm;

#ifdef __cplusplus
}
#endif

#ifdef __cplusplus

template<typename T>
struct SizedArray {
    unsigned size;
    T *values;

    SizedArray(unsigned int size, T *values) : size(size), values(values) {}

    SizedArray(const SizedArray &) = delete;

    SizedArray(JNIEnv *env, jlongArray array) {
        auto size = (unsigned) env->GetArrayLength(array);
        auto values = new T[size];
        auto tem = env->GetLongArrayElements(array, nullptr);
        for (unsigned i = 0; i < size; ++i) {
            values[i] = (T) (uintptr_t) tem[i];
        }
        env->ReleaseLongArrayElements(array, tem, JNI_ABORT);
        this->size = size;
        this->values = values;
    }

    ~SizedArray() {
        delete[] values;
    }
};

#endif