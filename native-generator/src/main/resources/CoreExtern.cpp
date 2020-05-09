#include <llvm/IR/Type.h>
#include <llvm/IR/DerivedTypes.h>
#include <llvm-c/Core.h>
#include "llvm-java.h"

#define LLVM_JAVA_CORE(name) Java_asia_kala_llvm_binding_Core_ ## name


extern "C" {

JNIEXPORT jobject JNICALL LLVM_JAVA_CORE(LLVMGetModuleIdentifier0)(JNIEnv *env, jclass cls, jlong module) {
    size_t len;
    auto id = LLVMGetModuleIdentifier((LLVMModuleRef) (uintptr_t) module, &len);
    return id == nullptr ? nullptr : env->NewDirectByteBuffer((void *) id, (jlong) (uint64_t) len);
}

JNIEXPORT jobject JNICALL LLVM_JAVA_CORE(LLVMGetSourceFileName0)(JNIEnv *env, jclass cls, jlong module) {
    size_t len;
    auto id = LLVMGetSourceFileName((LLVMModuleRef) (uintptr_t) module, &len);
    return id == nullptr ? nullptr : env->NewDirectByteBuffer((void *) id, (jlong) (uint64_t) len);
}

JNIEXPORT jlong JNICALL LLVM_JAVA_CORE(LLVMFunctionType__J_3JZ)(
        JNIEnv *env, jclass cls,
        jlong returnType, jlongArray paramTypes, jboolean isVarArg
) {
    auto retTy = (LLVMTypeRef) (uintptr_t) returnType;

    SizedArray<LLVMTypeRef> pts(env, paramTypes);
    return (jlong) (uintptr_t) LLVMFunctionType(retTy, pts.values, pts.size, isVarArg);

}

JNIEXPORT jlongArray JNICALL LLVM_JAVA_CORE(LLVMGetParamTypes__J)(JNIEnv *env, jclass cls, jlong functionType) {
    auto ty = llvm::unwrap<llvm::FunctionType>((LLVMTypeRef) (uintptr_t) functionType);
    auto count = ty->getFunctionNumParams();

    auto res = env->NewLongArray((jsize) count);
    auto tem = env->GetLongArrayElements(res, nullptr);

    auto dest = tem;
    for (auto it = ty->param_begin(), end = ty->param_end(); it != end; ++it) {
        *dest++ = (jlong) (uintptr_t) llvm::wrap(*it);
    }
    env->ReleaseLongArrayElements(res, tem, 0);
    return res;
}

JNIEXPORT jlong JNICALL LLVM_JAVA_CORE(LLVMStructTypeInContext__J_3JZ)(
        JNIEnv *env, jclass cls,
        jlong context, jlongArray elementTypes, jboolean packed
) {
    auto ct = (LLVMContextRef) (uintptr_t) context;

    SizedArray<LLVMTypeRef> ets(env, elementTypes);
    return (jlong) (uintptr_t) LLVMStructTypeInContext(ct, ets.values, ets.size, packed);

}

JNIEXPORT void JNICALL LLVM_JAVA_CORE(LLVMStructSetBody__J_3JZ)(
        JNIEnv *env, jclass cls,
        jlong structType, jlongArray elementTypes, jboolean packed
) {
    auto st = (LLVMTypeRef) (uintptr_t) structType;
    SizedArray<LLVMTypeRef> ets(env, elementTypes);

    LLVMStructSetBody(st, ets.values, ets.size, packed);
}

JNIEXPORT jlongArray JNICALL LLVM_JAVA_CORE(LLVMGetStructElementTypes__J)(JNIEnv *env, jclass cls, jlong structType) {
    auto ty = llvm::unwrap<llvm::StructType>((LLVMTypeRef) (uintptr_t) structType);
    auto count = ty->getStructNumElements();

    auto res = env->NewLongArray((jsize) count);
    auto tem = env->GetLongArrayElements(res, nullptr);

    auto dest = tem;
    for (auto it = ty->element_begin(), end = ty->element_end(); it != end; ++it) {
        *dest++ = (jlong) (uintptr_t) llvm::wrap(*it);
    }
    env->ReleaseLongArrayElements(res, tem, 0);
    return res;
}

JNIEXPORT jlongArray JNICALL LLVM_JAVA_CORE(LLVMGetSubtypes__J)(JNIEnv *env, jclass cls, jlong type) {
    auto ty = llvm::unwrap<llvm::Type>((LLVMTypeRef) (uintptr_t) type);
    auto subtypes = ty->subtypes();
    auto count = subtypes.size();

    auto res = env->NewLongArray((jsize) count);
    auto tem = env->GetLongArrayElements(res, nullptr);

    for (size_t i = 0; i < count; ++i) {
        tem[i] = (jlong)(uintptr_t) llvm::wrap(subtypes[i]);
    }
    env->ReleaseLongArrayElements(res, tem, 0);
    return res;
}

JNIEXPORT jobject JNICALL LLVM_JAVA_CORE(_LLVMGetValueName2)(JNIEnv *env, jclass cls, jlong value) {
    size_t len;
    auto name = LLVMGetValueName2((LLVMValueRef) (uintptr_t) value, &len);
    return name == nullptr ? nullptr : env->NewDirectByteBuffer((void *) name, (jlong) (uint64_t) len);
}

JNIEXPORT jobject JNICALL LLVM_JAVA_CORE(LLVMGetAsString0)(JNIEnv *env, jclass cls, jlong c) {
    size_t len;
    auto res = LLVMGetAsString((LLVMValueRef) (uintptr_t) c, &len);
    return res == nullptr ? nullptr : env->NewDirectByteBuffer((void *) res, (jlong) (uint64_t) len);
}

JNIEXPORT jlong JNICALL LLVM_JAVA_CORE(LLVMConstStructInContext__J_3JZ)(
        JNIEnv *env, jclass cls,
        jlong context, jlongArray constantValues, jboolean packed
) {
    auto ct = (LLVMContextRef) (uintptr_t) context;

    SizedArray<LLVMValueRef> cvs(env, constantValues);
    return (jlong) (uintptr_t) LLVMConstStructInContext(ct, cvs.values, cvs.size, packed);
}

JNIEXPORT jlong JNICALL LLVM_JAVA_CORE(LLVMConstArray__J_3J)(
        JNIEnv *env, jclass cls,
        jlong elementType, jlongArray constantValues
) {
    auto et = (LLVMTypeRef) (uintptr_t) elementType;

    SizedArray<LLVMValueRef> cvs(env, constantValues);
    return (jlong) (uintptr_t) LLVMConstArray(et, cvs.values, cvs.size);
}

JNIEXPORT jlong JNICALL LLVM_JAVA_CORE(LLVMConstNamedStruct__J_3J)(
        JNIEnv *env, jclass cls,
        jlong structType, jlongArray constantValues
) {
    auto st = (LLVMTypeRef) (uintptr_t) structType;

    SizedArray<LLVMValueRef> cvs(env, constantValues);
    return (jlong) (uintptr_t) LLVMConstNamedStruct(st, cvs.values, cvs.size);
}

}