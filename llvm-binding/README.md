## llvm-binding module

English | [中文](README_zh_CN.md)

The `llvm-binding` module provides a set of low-level bindings to the LLVM-C API.

This module provides a simple and efficient mapping to the LLVM-C API.

Ease of use and security are not the purpose of this module. The method provided by this module is simply to forward the parameters to the LLVM-C API.
Minimize the extra overhead caused by JNI. The type mapping between C API and Java API is shown in the following table:

| C type                                       | Java type |
| -------------------------------------------- | --------- |
| Any Pointer Type                             | `long`    |
| `LLVMBool`                                   | `boolean` |
| `char`, `unsigned char`, `int8_t`, `uint8_t` | `byte`    |
| `int16_t`, `uint16_t`                        | `short`   |
| `int`, `unsigned`, `int32_t`, `uint32_t`     | `int`     |
| `long long`, `int64_t`, `uint64_t`, `size_t` | `long`    |
| `double`                                     | `double`  |

This module provides a series of easy-to-read annotations to help users understand the meaning of Java API types,
For more information about these annotations, see [Annotations](#Annotations).

### Simplified API

Although ease of use and safety are not goals, for some fixed-mode APIs, this module provides variant overloads:

1. The method of receiving the length of a string through a pointer:

    `@Pointer("const char *") long foo(..., @Pointer("size_t *") long Length, ...)`
    <=> 
    `ByteBuffer foo(...)`

2. The method of returning an array through a pointer:

    `@Unsigned int GetNumContainedXxx(...)`
    
    and
    
    `void GetXxx(..., @Pointer("Xxx *") long array, ...)`

    <=>
    
    `@Xxx long[] GetXxx(...)`
    
3. The method of passing the array by passing the pointer to the first element and the corresponding array length:

   `<some type> foo(..., @Pointer("Xxx *") long values, @Unsigned int count, ...)
   <=>`
   `<some type> foo(..., @Xxx long[] values, ...)`

At present, the corresponding overload method has not been declared for all methods that conform to the above pattern. If the missing method is found
Or other modes that should be simplified by overloading methods, please raise an issue in time.

### Header file corresponding list

The module uses a one-to-one correspondence with classes. The LLVM-C API contains header files of public functions or types. The following is the corresponding list of C header files and Java classes:

| C header Files                            | Java class                                              |
| ----------------------------------------- | ------------------------------------------------------- |
| llvm-c/Analysis.h                         | asia.kala.llvm.binding.Analysis                         |
| llvm-c/BitReader.h                        | asia.kala.llvm.binding.BitReader                        |
| llvm-c/BitWriter.h                        | asia.kala.llvm.binding.BitWriter                        |
| llvm-c/Comdat.h                           | asia.kala.llvm.binding.Comdat                           |
| llvm-c/Core.h                             | asia.kala.llvm.binding.Core                             |
| llvm-c/DataTypes.h                        | *There is no object to be mapped*                       |
| llvm-c/DebugInfo.h                        | asia.kala.llvm.binding.DebugInfo                        |
| llvm-c/Disassembler.h                     | asia.kala.llvm.binding.Disassembler                     |
| llvm-c/DisassemblerTypes.h                | asia.kala.llvm.binding.DisassemblerTypes                |
| llvm-c/Error.h                            | asia.kala.llvm.binding.ErrorH                           |
| llvm-c/ErrorHandling.h                    | asia.kala.llvm.binding.ErrorHandling                    |
| llvm-c/ExecutionEngine.h                  | asia.kala.llvm.binding.ExecutionEngine                  |
| llvm-c/Initialization.h                   | asia.kala.llvm.binding.Initialization                   |
| llvm-c/IRReader.h                         | asia.kala.llvm.binding.IRReader                         |
| llvm-c/Linker.h                           | asia.kala.llvm.binding.Linker                           |
| llvm-c/LinkTimeOptimizer.h                | **Not implemented**                                     |
| llvm-c/lto.h                              | asia.kala.llvm.binding.lto (Not fully realized)         |
| llvm-c/Object.h                           | asia.kala.llvm.binding.ObjectH                          |
| llvm-c/OrcBindings.h                      | asia.kala.llvm.binding.OrcBindings                      |
| llvm-c/Remarks.h                          | asia.kala.llvm.binding.Remarks                          |
| llvm-c/Support.h                          | asia.kala.llvm.binding.Support                          |
| llvm-c/Target.h                           | asia.kala.llvm.binding.TargetH                          |
| llvm-c/TargetMachine.h                    | asia.kala.llvm.binding.TargetMachine                    |
| llvm-c/Types.h                            | asia.kala.llvm.binding.Types                            |
| llvm-c/Transforms/AggressiveInstCombine.h | asia.kala.llvm.binding.transforms.AggressiveInstCombine |
| llvm-c/Transforms/Coroutines.h            | asia.kala.llvm.binding.transforms.Coroutines            |
| llvm-c/Transforms/InstCombine.h           | asia.kala.llvm.binding.transforms.InstCombine           |
| llvm-c/Transforms/IPO.h                   | asia.kala.llvm.binding.transforms.IPO                   |
| llvm-c/Transforms/PassManagerBuilder.h    | asia.kala.llvm.binding.transforms.PassManagerBuilder    |
| llvm-c/Transforms/Scalar.h                | asia.kala.llvm.binding.transforms.Scalar                |
| llvm-c/Transforms/Utils.h                 | asia.kala.llvm.binding.transforms.Utils                 |
| llvm-c/Transforms/Vectorize.h             | asia.kala.llvm.binding.transforms.Vectorize             |

### Annotations

The `llvm-binding` module is in the ʻasia.kala.llvm.binding.annotations` package
A series of annotations are provided to help users understand the corresponding C API parameters and return value types through these annotations.
And provide enough type information to `native-generator` to generate JNI code.

#### `@CInfo`

`@CInfo` is used to annotate classes that contain native methods and need to use `native-generator` to generate JNI code,
Prompt `native-generator` for the file name generated by this class, and the header file that needs to be imported with the `#include` macro.

#### `@Pointer`

The `@Pointer` annotation can be used for annotation types or other annotations.

When used to annotate types, the `@Pointer` annotation can only annotate the `long` type, which means that it corresponds to a pointer type in C.
`@Pointer` The only element of the annotation `value` represents the name of the corresponding pointer type.

When used to annotate other annotations, the annotated annotation type will become the alias of the corresponding `@Pointer` annotation.

#### `@CEnum`

The `@CEnum` annotation is a meta-annotation used to annotate other annotations.

The annotation type annotated by `@CEnum` is a C enum type, in which a series of constants should be declared to represent the corresponding C enum value,
`@CEnum` The only element of the annotation `value` represents the name of the corresponding enum type.

The annotation annotated by `@CEnum` is used to annotate the ʻint` type, indicating that it corresponds to the corresponding enumeration type in C.

#### `@Signed`

The `@Signed` annotation can be used for annotation types or other annotations.

When used for annotation types, the `@Signed` annotation can be used to annotate any integer type, meaning that it corresponds to a signed integer type in C.
`@Signed` The only element of the annotation `value` represents the name of the corresponding integer type.

When used to annotate other annotations, the annotated annotation type will become the alias of the corresponding `@Signed` annotation.

#### `@Unsigned`

The `@Unsigned` annotation can be used for annotation types or other annotations.

When used for annotation types, the `@Unsigned` annotation can be used to annotate any integer type, meaning that it corresponds to an unsigned integer type in C.
`@Unsigned` The only element of the annotation `value` represents the name of the corresponding integer type.

When used to annotate other annotations, the annotated annotation type will become the alias of the corresponding `@Unsigned` annotation.
