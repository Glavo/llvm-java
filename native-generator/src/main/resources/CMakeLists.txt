cmake_minimum_required(VERSION 3.15)
project(llvm-java-native)

set(CMAKE_CXX_STANDARD 11)

find_package(LLVM REQUIRED CONFIG)
find_package(JNI REQUIRED)

include_directories(${LLVM_INCLUDE_DIRS} ${JAVA_INCLUDE_PATH} ${JAVA_INCLUDE_PATH2})
add_definitions(${LLVM_DEFINITIONS})

llvm_map_components_to_libnames(llvm_libs
        support core irreader transformutils analysis bitreader bitwriter mc mcdisassembler executionengine
        target mcjit interpreter objcarcopts irreader lto linker orcjit remarks coroutines
        instcombine ipo vectorize

        aarch64asmparser aarch64codegen aarch64desc aarch64disassembler aarch64info aarch64utils
        amdgpuasmparser amdgpucodegen amdgpudesc amdgpudisassembler amdgpuinfo amdgpuutils
        armasmparser armcodegen armdesc armdisassembler arminfo armutils
        bpfasmparser bpfcodegen bpfdesc bpfdisassembler bpfinfo
        hexagonasmparser hexagoncodegen hexagondesc hexagondisassembler hexagoninfo
        lanaiasmparser lanaicodegen lanaidesc lanaidisassembler lanaiinfo
        mipsasmparser mipscodegen mipsdesc mipsdisassembler mipsinfo
        msp430asmparser msp430codegen msp430desc msp430disassembler msp430info
        nvptxcodegen nvptxdesc nvptxinfo
        powerpcasmparser powerpccodegen powerpcdesc powerpcdisassembler powerpcinfo
        riscvasmparser riscvcodegen riscvdesc riscvdisassembler riscvinfo riscvutils
        sparcasmparser sparccodegen sparcdesc sparcdisassembler sparcinfo
        systemzasmparser systemzcodegen systemzdesc systemzdisassembler systemzinfo
        webassemblyasmparser webassemblycodegen webassemblydesc webassemblydisassembler webassemblyinfo
        x86asmparser x86codegen x86desc x86disassembler x86info x86utils
        xcorecodegen xcoredesc xcoredisassembler xcoreinfo
        )

add_library(llvm-java SHARED
        llvm-java.h llvm-java.cpp
        Core.c Support.c Error.c Object.c Analysis.c BitReader.c BitWriter.c Comdat.c DebugInfo.c Disassembler.c
        ErrorHandling.c Target.c TargetMachine.c ExecutionEngine.c Initialization.c IRReader.c Linker.c
        lto.c OrcBindings.c Remarks.c
        Transforms/AggressiveInstCombine.c Transforms/Coroutines.c Transforms/InstCombine.c Transforms/IPO.c
        Transforms/PassManagerBuilder.c Transforms/Scalar.c Transforms/Utils.c Transforms/Vectorize.c
        CoreExtern.cpp NativeUtils.cpp
        )

target_link_libraries(llvm-java ${llvm_libs})

set_property(TARGET llvm-java PROPERTY MSVC_RUNTIME_LIBRARY "MultiThreaded$<$<CONFIG:Debug>:Debug>")