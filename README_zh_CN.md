# LLVM Java Binding 
[ ![Download](https://api.bintray.com/packages/glavo/maven/llvm/images/download.svg) ](https://bintray.com/glavo/maven/llvm/_latestVersion)

注意：这个项目基本功能已经可用，但依然处于实验状态，当遇到问题时，请您通过 issue 告诉我们。

## 将 LLVM Java 添加至您的项目中

首先，您需要将 jcenter 仓库添加至配置中：

Maven: 

```xml
<repositories>
  <repository>
    <id>jcenter</id>
    <url>https://jcenter.bintray.com</url>
    <!-- 或者使用阿里云提供的镜像仓库：
    <url>https://maven.aliyun.com/repository/jcenter</url> 
    -->
  </repository>
</repositories>
```

Gradle:

```groovy
repositories {
    jcenter()
    // 或者使用阿里云的镜像仓库：
    // maven { url 'https://maven.aliyun.com/repository/jcenter' }
}
```

然后添加依赖（使用时将 `llvm_java_version` 替换为本项目的版本号）：

Maven:
```xml
<dependency>
  <groupId>asia.kala</groupId>
  <artifactId>llvm</artifactId>
  <version>${llvm_java_version}</version>
</dependency>
```

Gradle:
```groovy
implementation group: 'asia.kala', name: 'llvm', version: llvm_java_version
```

**另外，想要正常使用本项目，还需要添加 native 依赖项，请参见 [llvm-platform](#llvm-platform).**

## 模块

LLVM Java 被分割为多个子模块，除了用于分发本机库的 [`llvm-platform`](#llvm-platform) 模块，
其他模块都可以通过这样的方式单独添加（将 `moduleName` 替换为你需要的模块名称）：

Maven:
```xml
<dependency>
  <groupId>asia.kala</groupId>
  <artifactId>${moduleName}</artifactId>
  <version>${llvm_java_version}</version>
</dependency>
```

Gradle:
```groovy
implementation group: 'asia.kala', name: moduleName, version: llvm_java_version
```

### llvm-platform

`llvm-platform` 模块用于分发各平台上预构建的二进制库文件。

目前支持的平台有：

* `windows-x86_64`
* `windows-x86`
* `linux-x86_64`

每个平台的库都会被包装至 jar 中，
其 Jigsaw 模块名为 `asia.kala.llvm.platform.<osName>.<archName>`
（譬如 `windows-x86_64` 平台对应 jar 模块名为 `asia.kala.llvm.platform.windows.x86_64`）。
在 Java 9 以上，以模块化方式运行或者用 jlink 生成映像时，请用 `--add-modules` 
添加对应平台的模块。

将平台 jar 添加至依赖中：

Maven:
```xml
<dependency>
  <groupId>asia.kala</groupId>
  <artifactId>llvm-platform</artifactId>
  <version>${llvm_java_version}</version>
  <classifier>${platformName}</classifier>
</dependency>
```

Gradle: 
```groovy
implementation 'asia.kala:llvm-platform:${llvm_java_version}:${platformName}'
```

### llvm-binding

`llvm-binding` 模块提供了一套对 LLVM-C API 的低级绑定，该模块提供了对 LLVM-C API 简单而高效的映射。

更多文档请参见 [llvm-binding/README](llvm-binding/README_zh_CN.md)

## Roadmap

版本号规则：

预发布版： `{LLVM 版本}-RC{发布版本}` (例如：`10.0.0-RC1`)

发行版： `{LLVM 版本}-R{发布版本}` (例如：`10.0.0-R1`)

目前最基础的 LLVM-C API 映射已经基本完成，该项目现在的目标是基于此 API 构建一套更高级且易用的 API。
