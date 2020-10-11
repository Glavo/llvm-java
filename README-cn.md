# LLVM Java Binding 
[ ![Download](https://api.bintray.com/packages/glavo/maven/llvm/images/download.svg) ](https://bintray.com/glavo/maven/llvm/_latestVersion)

Note: The basic features of the project are available, but the project is not yet stable. Please report positively if you encounter bugs.

Note: English documents will be provided in the future, and currently only Chinese documents are available.

## Adding LLVM Java to your build

First, you need to add the jcenter repository to your build:

Maven:
```xml
<repositories>
  <repository>
    <id>jcenter</id>
    <url>https://jcenter.bintray.com</url>
  </repository>
</repositories>
```

Gradle:

```groovy
repositories {
    jcenter()
}
```

中国大陆用户可以考虑使用[阿里云 maven 镜像库](https://help.aliyun.com/document_detail/102512.html)。

Then add dependencies(replace `llvm_java_version` with the llvm java version you want to use):

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

**In addition, you also need to add the native library to the dependency,
please see [llvm-platform](#llvm-platform).**

## Modules

LLVM Java 被分割为多个子模块，除了用于分发本机库的 [`llvm-platform`](#llvm-platform) 模块，
其他模块都可以通过这样的方式单独添加(将 `moduleName` 替换为你需要的模块名称)：

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

更多文档请参见 [llvm-binding/README.md](llvm-binding/README.md)

## Roadmap

版本号规则：

预发布版： `{LLVM 版本}-RC{发布版本}` (例如：`10.0.0-RC1`)

发行版： `{LLVM 版本}-R{发布版本}` (例如：`10.0.0-R1`)

目前最基础的 LLVM-C API 映射已经基本完成，该项目现在的目标是基于此 API 构建一套更高级且易用的 API。
