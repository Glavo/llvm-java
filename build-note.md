Build LLVM On Windows:

```
mkdir build
cd build
cmake -DCMAKE_BUILD_TYPE=Release -DLLVM_USE_CRT_RELEASE=MT -DCMAKE_INSTALL_PREFIX=<install_dir> -G Ninja ..
cmake --build .
cmake --install .
```