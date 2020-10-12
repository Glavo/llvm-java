# LLVM Java Binding 
[ ![Download](https://api.bintray.com/packages/glavo/maven/llvm/images/download.svg) ](https://bintray.com/glavo/maven/llvm/_latestVersion)

Note: The basic features of the project are available, but the project is not yet stable. Please report positively if you encounter bugs.

English | [中文](./README_zh_CN.md)

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

LLVM Java is divided into multiple sub-modules, except for the [`llvm-platform`](#llvm-platform) module, which is used to distribute native libraries.
Other modules can be added separately in this way (replace `moduleName` with the module name you need):

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

The `llvm-platform` module is used to distribute pre-built binary library files on various platforms.

Currently supported platforms are:

* `windows-x86_64`
* `windows-x86`
* `linux-x86_64`

The libraries of each platform will be packaged in a jar,
Its Jigsaw module name is `asia.kala.llvm.platform.<osName>.<archName>`
(For example, the jar module corresponding to the `windows-x86_64` platform is named `asia.kala.llvm.platform.windows.x86_64`).
In Java 9 and above, when running in modular mode or generating images with jlink, please use `--add-modules`
add modules corresponding to the platform.

Add the platform jar to the dependencies:

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

The `llvm-binding` module provides a set of low-level bindings to the LLVM-C API. This module provides a simple and efficient mapping to the LLVM-C API.

For more documentation, please see [llvm-binding/README](llvm-binding/README.md)

## Roadmap

Version number rules:

Pre-release version: `{LLVM version}-RC{release version}` (for example: `10.0.0-RC1`)

Release version: `{LLVM version}-R{release version}` (for example: `10.0.0-R1`)

At present, the most basic LLVM-C API mapping has been basically completed. The current goal of the project is to build a more advanced and easy-to-use API based on this API.
