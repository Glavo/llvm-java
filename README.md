# LLVM Java Binding

Note: The basic features of the project are available, but the project is not yet stable. Please report positively if you encounter bugs.

Note: English documents will be provided in the future, and currently only Chinese documents are available.

## Adding LLVM Java to your build

// TODO

## Modules

### llvm-platform

`llvm-platform` 模块用于分发各平台上预构建的二进制库文件。

目前支持的平台有：

* `windows-x86_64`
* `windows-x86`
* `linux-x86_64`

每个平台的库都会被包装至 jar 中，
其模块名为 `asia.kala.llvm.platform.<os name>.<arch name>`
（譬如 `windows-x86_64` 平台对应 jar 模块名为 `asia.kala.llvm.platform.windows.x86_64`）。
在 Java 9 以上，以模块化方式运行或者用 jlink 生成映像时，请用 `--add-modules` 
添加对应平台的模块。

将平台 jar 添加至 Gradle 依赖中：

```groovy
implementation 'asia.kala:llvm-platform:<version>:<platform name>'
```

### llvm-binding

`llvm-binding` 模块提供了一套对 LLVM-C API 的低级绑定，该模块提供了对 LLVM-C API 简单而高效的映射。

更多文档请参见 [llvm-binding/README.md](llvm-binding/README.md)

### native-generator

 `native-generator` 模块用于自动读取 Java 注解生成对应的 JNI 代码。
 
 执行 `./gradlew generateNative` 会在项目根路径下生成 `native` 文件夹，
 其中存放着全部 JNI 代码以及用于配置项目的 `CMakeLists.txt` 配置文件，
 可以使用 CMake 构建生成对应的本机库文件。
 
 注意：LLVM Java Binding 需要静态链接 LLVM，Windows 上还需要静态链接 vcruntime。
 需要在 Windows 上构建能够静态链接 vcruntime 的 LLVM，请参见 [note.md](note.md)。 

## Roadmap

版本号规则：

预发布版： `{LLVM 版本}-RC{发布版本}` (例如：`10.0.0-RC1`)

发行版： `{LLVM 版本}-R{发布版本}` (例如：`10.0.0-R1`)

目前最基础的 LLVM-C API 映射已经基本完成，该项目现在的目标是基于此 API 构建一套更高级且易用的 API。