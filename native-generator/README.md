## native-generator

`native-generator` 模块用于自动读取 Java 注解生成对应的 JNI 代码。
 
执行 `./gradlew generateNative` 会在项目根路径下生成 `native` 文件夹，
其中存放着全部 JNI 代码以及用于配置项目的 `CMakeLists.txt` 配置文件，
可以使用 CMake 构建生成对应的本机库文件。
 
注意：LLVM Java Binding 需要静态链接 LLVM，Windows 上还需要静态链接 vcruntime。
需要在 Windows 上构建能够静态链接 vcruntime 的 LLVM，请参见 [build-note.md](build-note.md)。 
