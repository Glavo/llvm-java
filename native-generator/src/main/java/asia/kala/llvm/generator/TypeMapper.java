package asia.kala.llvm.generator;

import asia.kala.llvm.binding.annotations.CEnum;
import asia.kala.llvm.binding.annotations.Pointer;
import asia.kala.llvm.binding.annotations.Signed;
import asia.kala.llvm.binding.annotations.Unsigned;
import asia.kala.llvm.binding.lto;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;

public abstract class TypeMapper {
    public final Class<?> javaType;
    public final String cType;

    public final String jniType;

    public abstract String castToJava(String expr);

    public abstract String castToC(String expr);

    protected TypeMapper(Class<?> javaType, String cType) {
        this.javaType = javaType;
        this.cType = cType;
        if (javaType == void.class) {
            this.jniType = "void";
        } else if (javaType.isPrimitive()) {
            this.jniType = 'j' + javaType.getName();
        } else {
            this.jniType = "jobject";
        }
    }

    public static final TypeMapper OfBoolean = new TypeMapper(boolean.class, "LLVMBool") {
        @Override
        public String castToJava(String expr) {
            return expr + " ? JNI_TRUE : JNI_FALSE";
        }

        @Override
        public String castToC(String expr) {
            return expr;
        }
    };

    public static final TypeMapper OfLtoBool = new TypeMapper(boolean.class, "lto_bool_t") {
        @Override
        public String castToJava(String expr) {
            return expr + " ? JNI_TRUE : JNI_FALSE";
        }

        @Override
        public String castToC(String expr) {
            return "(lto_bool_t) " + expr;
        }
    };

    public static final TypeMapper OfInt = new TypeMapper(int.class, "int") {
        @Override
        public String castToJava(String expr) {
            return "(jint)(int32_t) " + expr;
        }

        @Override
        public String castToC(String expr) {
            return "(int)(int32_t) " + expr;
        }
    };

    public static final TypeMapper OfDouble = new TypeMapper(double.class, "double") {
        @Override
        public String castToJava(String expr) {
            return "(jdouble) " + expr;
        }

        @Override
        public String castToC(String expr) {
            return "(double) " + expr;
        }
    };

    public static final TypeMapper OfVoid = new TypeMapper(void.class, "void") {
        @Override
        public String castToJava(String expr) {
            throw new AssertionError();
        }

        @Override
        public String castToC(String expr) {
            throw new AssertionError();
        }
    };

    public static final class OfPointer extends TypeMapper {
        public OfPointer(String cType) {
            super(long.class, cType);
        }

        @Override
        public String castToJava(String expr) {
            return "(jlong)(uintptr_t) " + expr;
        }

        @Override
        public String castToC(String expr) {
            return String.format("(%s)(uintptr_t) %s", cType, expr);
        }
    }

    public static final class OfEnum extends TypeMapper {
        public OfEnum(String cType) {
            super(int.class, cType);
        }

        @Override
        public String castToJava(String expr) {
            return "(jint) " + expr;
        }

        @Override
        public String castToC(String expr) {
            return String.format("(%s) %s", cType, expr);
        }
    }

    public static final class OfUnsigned extends TypeMapper {

        private final String temType;

        public OfUnsigned(Class<?> javaType, String cType) {
            super(javaType, cType);

            if (javaType == byte.class) {
                temType = "uint8_t";
            } else if (javaType == short.class) {
                temType = "uint16_t";
            } else if (javaType == int.class) {
                temType = "uint32_t";
            } else if (javaType == long.class) {
                temType = "uint64_t";
            } else {
                throw new AssertionError();
            }
        }

        @Override
        public String castToJava(String expr) {
            return String.format("(%s)(%s) %s", jniType, temType, expr);
        }

        @Override
        public String castToC(String expr) {
            return String.format("(%s)(%s) %s", cType, temType, expr);
        }
    }

    public static final class OfSigned extends TypeMapper {

        private final String temType;

        public OfSigned(Class<?> javaType, String cType) {
            super(javaType, cType);

            if (javaType == byte.class) {
                temType = "int8_t";
            } else if (javaType == short.class) {
                temType = "int16_t";
            } else if (javaType == int.class) {
                temType = "int32_t";
            } else if (javaType == long.class) {
                temType = "int64_t";
            } else {
                throw new AssertionError();
            }
        }

        @Override
        public String castToJava(String expr) {
            return String.format("(%s)(%s) %s", jniType, temType, expr);
        }

        @Override
        public String castToC(String expr) {
            return String.format("(%s)(%s) %s", cType, temType, expr);
        }
    }

    private static boolean checkAnnotated(Annotation ann) {
        return ann instanceof CEnum
                || ann instanceof Unsigned
                || ann instanceof Signed
                || ann instanceof Pointer
                || ann instanceof lto.lto_bool_t;
    }

    public static TypeMapper of(AnnotatedType type) {
        if (type.getType() == void.class) {
            return OfVoid;
        }

        Annotation annotation = null;

        for (Annotation ann : type.getAnnotations()) {
            if (checkAnnotated(ann)) {
                annotation = ann;
                break;
            }
        }
        if (annotation == null) {
            out:
            for (Annotation ann : type.getAnnotations()) {
                for (Annotation a : ann.annotationType().getAnnotations()) {
                    if (checkAnnotated(a)) {
                        annotation = a;
                        break out;
                    }
                }
            }
        }

        if (annotation == null) {
            if (type.getType() == boolean.class) {
                return OfBoolean;
            }
            if (type.getType() == int.class) {
                return OfInt;
            }
            if (type.getType() == double.class) {
                return OfDouble;
            }
            throw new AssertionError(type);
        }

        if (annotation instanceof lto.lto_bool_t) {
            if (type.getType() != boolean.class) {
                throw new AssertionError(type);
            }
            return OfLtoBool;
        }

        if (annotation instanceof Pointer) {
            if (type.getType() != long.class) {
                throw new AssertionError("error pointer type: " + type.getType());
            }
            return new OfPointer(((Pointer) annotation).value());
        }

        if (annotation instanceof CEnum) {
            if (type.getType() != int.class) {
                throw new AssertionError("error enum type: " + type.getType());
            }
            return new OfEnum(((CEnum) annotation).value());
        }

        if (annotation instanceof Unsigned) {
            return new OfUnsigned(((Class<?>) type.getType()), ((Unsigned) annotation).value());
        }

        if (annotation instanceof Signed) {
            return new OfSigned(((Class<?>) type.getType()), ((Signed) annotation).value());
        }

        throw new AssertionError();
    }
}
