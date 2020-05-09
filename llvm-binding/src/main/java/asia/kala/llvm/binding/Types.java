package asia.kala.llvm.binding;

import asia.kala.llvm.binding.annotations.Pointer;

import java.lang.annotation.*;

public final class Types {
    private Types() {
    }

    @Pointer("LLVMMemoryBufferRef")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMMemoryBufferRef {
    }

    @Pointer("LLVMContextRef")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMContextRef {
    }

    @Pointer("LLVMModuleRef")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMModuleRef {
    }

    @Pointer("LLVMTypeRef")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMTypeRef {
    }

    @Pointer("LLVMValueRef")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMValueRef {
    }

    @Pointer("LLVMBasicBlockRef")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMBasicBlockRef {
    }

    @Pointer("LLVMMetadataRef")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMMetadataRef {
    }

    @Pointer("LLVMNamedMDNodeRef")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMNamedMDNodeRef {
    }

    @Pointer("LLVMValueMetadataEntry")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMValueMetadataEntry {
    }

    @Pointer("LLVMBuilderRef")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMBuilderRef {
    }

    @Pointer("LLVMDIBuilderRef")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMDIBuilderRef {
    }

    @Pointer("LLVMModuleProviderRef")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMModuleProviderRef {
    }

    @Pointer("LLVMPassManagerRef")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMPassManagerRef {
    }

    @Pointer("LLVMPassRegistryRef")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMPassRegistryRef {
    }

    @Pointer("LLVMUseRef")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMUseRef {
    }

    @Pointer("LLVMAttributeRef")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMAttributeRef {
    }

    @Pointer("LLVMDiagnosticInfoRef")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMDiagnosticInfoRef {
    }

    @Pointer("LLVMComdatRef")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMComdatRef {
    }

    @Pointer("LLVMModuleFlagEntry *")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMModuleFlagEntryRef {
    }

    @Pointer("LLVMJITEventListenerRef")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMJITEventListenerRef {
    }

    @Pointer("LLVMBinaryRef")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMBinaryRef {
    }
}
