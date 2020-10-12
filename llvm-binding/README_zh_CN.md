## llvm-binding module

`llvm-binding` 模块提供了一套对 LLVM-C API 的低级绑定. 

该模块提供了对 LLVM-C API 简单而高效的映射。

易用和安全不是该模块的目的，该模块提供的方法只是简单的把参数转发给 LLVM-C API，
尽可能降低了 JNI 造成的额外开销。C API 与 Java API 的类型映射参见下表：

| C 类型                                       | Java 类型 |
| -------------------------------------------- | --------- |
| 任何指针类型                                  | `long`    |
| `LLVMBool`                                   | `boolean` |
| `char`, `unsigned char`, `int8_t`, `uint8_t` | `byte`    |
| `int16_t`, `uint16_t`                        | `short`   |
| `int`, `unsigned`, `int32_t`, `uint32_t`     | `int`     |
| `long long`, `int64_t`, `uint64_t`, `size_t` | `long`    |
| `double`                                     | `double`  |

该模块提供了一系列易读的注解帮助用户理解 Java API 类型的含义，
关于这些注解的详细信息，请参阅 [Annotations](#Annotations)。

### 简化的 API

虽然易用和安全并非目标，但对于部分固定模式的 API，该模块提供了变形重载：

1. 通过指针接受字符串长度的方法：

    `@Pointer("const char *") long foo(..., @Pointer("size_t *") long Length, ...)`
    <=> 
    `ByteBuffer foo(...)`

2. 通过指针返回数组的方法：

    `@Unsigned int GetNumContainedXxx(...)`
    
    and
    
    `void GetXxx(..., @Pointer("Xxx *") long array, ...)`

    <=>
    
    `@Xxx long[] GetXxx(...)`
    
3. 通过传递指向首元素指针以及对应的数组长度来传递数组的方法：

   `<some type> foo(..., @Pointer("Xxx *") long values, @Unsigned int count, ...)
   <=>`
   `<some type> foo(..., @Xxx long[] values, ...)`

目前尚未给所有符合上述模式的方法声明了对应的重载方法，如果发现了遗漏的方法
或者其他应该通过重载方法简化的模式，请及时提出 issue。

### 头文件对应列表

该模块使用类一一对应 LLVM-C API 包含公共函数或类型的头文件，下面是 C 头文件与 Java 类的对应列表：

| C 头文件                                  | Java 类                                                 |
| ----------------------------------------- | ------------------------------------------------------- |
| llvm-c/Analysis.h                         | asia.kala.llvm.binding.Analysis                         |
| llvm-c/BitReader.h                        | asia.kala.llvm.binding.BitReader                        |
| llvm-c/BitWriter.h                        | asia.kala.llvm.binding.BitWriter                        |
| llvm-c/Comdat.h                           | asia.kala.llvm.binding.Comdat                           |
| llvm-c/Core.h                             | asia.kala.llvm.binding.Core                             |
| llvm-c/DataTypes.h                        | *没有需要映射的对象*                                    |
| llvm-c/DebugInfo.h                        | asia.kala.llvm.binding.DebugInfo                        |
| llvm-c/Disassembler.h                     | asia.kala.llvm.binding.Disassembler                     |
| llvm-c/DisassemblerTypes.h                | asia.kala.llvm.binding.DisassemblerTypes                |
| llvm-c/Error.h                            | asia.kala.llvm.binding.ErrorH                           |
| llvm-c/ErrorHandling.h                    | asia.kala.llvm.binding.ErrorHandling                    |
| llvm-c/ExecutionEngine.h                  | asia.kala.llvm.binding.ExecutionEngine                  |
| llvm-c/Initialization.h                   | asia.kala.llvm.binding.Initialization                   |
| llvm-c/IRReader.h                         | asia.kala.llvm.binding.IRReader                         |
| llvm-c/Linker.h                           | asia.kala.llvm.binding.Linker                           |
| llvm-c/LinkTimeOptimizer.h                | **未实现**                                              |
| llvm-c/lto.h                              | asia.kala.llvm.binding.lto (未完全实现)                 |
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

`llvm-binding` 模块在 `asia.kala.llvm.binding.annotations` 包中
提供了一系列注解，通过这些注解帮助用户了解对应的 C API 参数以及返回值类型，
并提供给 `native-generator` 足够的类型信息来生成 JNI 代码。

#### `@CInfo`

`@CInfo` 用于注解包含本机方法并需要使用 `native-generator` 生成 JNI 代码的类，
提示 `native-generator` 为该类生成的文件名称，以及需要用 `#include` 宏导入的头文件。

#### `@Pointer`

`@Pointer` 注解可以用于注解类型或其他注解。

用于注解类型时，`@Pointer` 注解只能注解 `long` 类型，表示它在 C 中对应一个指针类型。
`@Pointer` 注解唯一的元素 `value` 表示对应的指针类型名称。

用于注解其他注解时，被注解的注解类型会成为对应的 `@Pointer` 注解的别名。

#### `@CEnum`

`@CEnum` 注解是用于注解其他注解的元注解。

被 `@CEnum` 注解的注解类型对应一个 C enum 类型，在其中应该声明一系列常量表示对应的 C enum 的值，
`@CEnum` 注解唯一的元素 `value` 表示对应的 enum 类型名称。

被 `@CEnum` 注解的注解用于注解 `int` 类型，表示它在 C 中对应相应的枚举类型。

#### `@Signed`

`@Signed` 注解可以用于注解类型或其他注解。

用于注解类型时，`@Signed` 注解可以用于注解任意整数类型，表示它在 C 中对应一个有符号整数类型。
`@Signed` 注解唯一的元素 `value` 表示对应的整数类型名称。

用于注解其他注解时，被注解的注解类型会成为对应的 `@Signed` 注解的别名。

#### `@Unsigned`

`@Unsigned` 注解可以用于注解类型或其他注解。

用于注解类型时，`@Unsigned` 注解可以用于注解任意整数类型，表示它在 C 中对应一个无符号整数类型。
`@Unsigned` 注解唯一的元素 `value` 表示对应的整数类型名称。

用于注解其他注解时，被注解的注解类型会成为对应的 `@Unsigned` 注解的别名。
