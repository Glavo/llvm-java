package asia.kala.llvm.generator;

import asia.kala.llvm.binding.*;
import asia.kala.llvm.binding.annotations.CInfo;
import asia.kala.llvm.binding.annotations.Extern;
import asia.kala.llvm.binding.annotations.Statement;
import asia.kala.llvm.binding.transforms.*;

import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static final Class<?>[] classes = {
            Core.class, Support.class, ErrorH.class, ObjectH.class, Analysis.class, BitReader.class,
            BitWriter.class, Comdat.class, DebugInfo.class, Disassembler.class, ErrorHandling.class,
            TargetH.class, TargetMachine.class, ExecutionEngine.class, Initialization.class,
            IRReader.class, Linker.class, lto.class, OrcBindings.class, Remarks.class,
            AggressiveInstCombine.class, Coroutines.class, InstCombine.class, IPO.class,
            PassManagerBuilder.class, Scalar.class, Utils.class, Vectorize.class
    };

    public static void main(String[] args) throws IOException {
        Path p = Paths.get("..", "native");
        Files.createDirectories(p.resolve("Transforms"));

        for (Class<?> cls : classes) {
            CInfo info = cls.getAnnotation(CInfo.class);
            try (BufferedWriter writer = Files.newBufferedWriter(p.resolve(info.fileName()))) {
                for (String include : info.include()) {
                    if (include.startsWith("<") && include.endsWith(">")) {
                        writer.append("#include ").append(include).append('\n');
                    } else {
                        writer.append("#include ").append('"').append(include).append('"').append('\n');
                    }
                }

                writer.append('\n');

                writer.append("#ifdef __cplusplus\n")
                        .append("extern \"C\" {\n")
                        .append("#endif\n")
                        .append('\n');


                for (Method method : cls.getDeclaredMethods()) {
                    if (!Modifier.isNative(method.getModifiers()) || method.getAnnotation(Extern.class) != null) {
                        continue;
                    }
                    try {
                        Parameter[] parameters = method.getParameters();
                        TypeMapper retType = TypeMapper.of(method.getAnnotatedReturnType());

                        TypeMapper[] parTypes = new TypeMapper[parameters.length];

                        writer.append("JNIEXPORT ").append(retType.jniType).append(" JNICALL ")
                                .append(methodName(method))
                                .append('(');

                        writer.append("JNIEnv *env, jclass jc");

                        for (int i = 0; i < parameters.length; i++) {
                            TypeMapper t = parTypes[i] = TypeMapper.of(parameters[i].getAnnotatedType());
                            writer.append(", ").append(parTypes[i].jniType).append(' ').append(parameters[i].getName());
                        }

                        writer.append(") {\n");

                        Statement statement = method.getAnnotation(Statement.class);
                        if (statement == null) {

                            StringBuilder builder = new StringBuilder();
                            for (int i = 0; i < parameters.length; i++) {
                                TypeMapper t = TypeMapper.of(parameters[i].getAnnotatedType());
                                if (i != 0) {
                                    builder.append(", ");
                                }

                                builder.append(t.castToC(parameters[i].getName()));
                            }

                            if (retType == TypeMapper.OfVoid) {
                                writer.append("    ").append(method.getName()).append('(').append(builder).append(')');
                            } else {
                                writer.append("    return ")
                                        .append(retType.castToJava(method.getName() + "(" + builder.toString() + ")"));
                            }

                            writer.append(";\n");
                        } else {
                            writer.append("    ").append(statement.value()).append('\n');
                        }
                        writer.append("}\n\n");
                    } catch (AssertionError e) {
                        System.err.println("method: " + method);
                        throw e;
                    }

                }


                writer.append("#ifdef __cplusplus\n")
                        .append("}\n")
                        .append("#endif\n");
            }
        }
    }

    public static String methodName(Method method) {
        String name = method.getName();
        Class<?> cls = method.getDeclaringClass();
        Method[] methods = cls.getDeclaredMethods();

        int c = 0;

        for (Method m : methods) {
            if (m.getName().equals(name) && Modifier.isNative(m.getModifiers())) {
                ++c;
            }
        }

        StringBuilder n = new StringBuilder("Java_" + cls.getName().replace('.', '_') + '_' + name);
        if (c > 1) {
            n.append("__");
            for (Parameter parameter : method.getParameters()) {
                Class<?> type = parameter.getType();
                if (type == boolean.class) {
                    n.append('Z');
                } else if (type == char.class) {
                    n.append('C');
                } else if (type == byte.class) {
                    n.append('B');
                } else if (type == short.class) {
                    n.append('S');
                } else if (type == int.class) {
                    n.append('I');
                } else if (type == long.class) {
                    n.append('J');
                } else {
                    throw new AssertionError(parameter);
                }
            }
        }
        return n.toString();
    }
}
