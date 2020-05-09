
Build LLVM On Windows:

```
mkdir build
cd build
cmake -DCMAKE_BUILD_TYPE=Release -DCMAKE_INSTALL_PREFIX=<install_dir> -DLLVM_USE_CRT_RELEASE=MT -G Ninja ..
cmake --build .
cmake --install .
```