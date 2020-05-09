package asia.kala.llvm.binding;

import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.*;
import java.util.Properties;

public class LLVMLoader {
    static boolean loaded = false;

    static Throwable ex;

    private static final String OS = detectOS();
    private static final String ARCH = detectArch();

    public static final String PLATFORM =
            (OS.equals("UNKNOWN") || ARCH.equals("UNKNOWN")) ? "UNKNOWN" : OS + "-" + ARCH;

    static Properties properties;

    static final boolean debug;


    static {
        debug = Boolean.getBoolean("asia.kala.llvm.debug");

        try (InputStream in = LLVMLoader.class.getResourceAsStream("llvm.properties")) {
            properties = new Properties();
            properties.load(in);

            if (debug) {
                System.out.println("[DEBUG] platform: " + PLATFORM);
                System.out.println("[DEBUG] llvm.properties: " + properties);
            }

        } catch (IOException e) {
            properties = null;
        }

        load();
    }

    private static String detectOS() {
        String osName = System.getProperty("os.name", "").toLowerCase().trim();
        String jvmName = System.getProperty("java.vm.name", "").toLowerCase().trim();

        if (osName.startsWith("windows ce")) {
            return "windowsce";
        }
        if (osName.startsWith("windows")) {
            return "windows";
        }
        if (osName.startsWith("mac")) {
            return "macos";
        }
        if (osName.startsWith("darwin")) {
            if ("robovm".equals(jvmName)) {
                return "ios";
            }
            return "darwin";
        }

        if (osName.startsWith("linux") || osName.equals("gnu")) {
            if (jvmName.equals("dalvik")) {
                return "android";
            }
            return "linux";
        }

        if (osName.startsWith("aix")) {
            return "aix";
        }
        if (osName.startsWith("solaris") || osName.startsWith("sunos")) {
            return "solaris";
        }
        if (osName.startsWith("freebsd")) {
            return "freebsd";
        }
        if (osName.startsWith("openbsd")) {
            return "openbsd";
        }
        if (osName.startsWith("netbsd")) {
            return "netbsd";
        }
        if (osName.startsWith("dragonfly")) {
            return "dragonfly";
        }
        if (osName.equals("gnu/kfreebsd")) {
            return "kfreebsd";
        }

        return "UNKNOWN";
    }

    private static String detectArch() {
        String arch = System.getProperty("os.arch").toLowerCase().trim();
        switch (arch) {
            case "x86":
            case "i386":
            case "i486":
            case "i586":
            case "i686":
                return "x86";
            case "x64":
            case "x86-64":
            case "amd64":
                return "x86_64";
            case "ppc":
            case "powerpc":
                return "ppc";
            case "ppc64":
            case "powerpc64":
                if ("little".equals(System.getProperty("sun.cpu.endian"))) {
                    return "ppc64le";
                }
                return "ppc64";
            case "ppc64le":
            case "powerpc64le":
                return "ppc64le";
            case "s390":
            case "s390x":
                return "s390x";
            case "sparc":
                return "sparc";
            case "sparv9c":
                return "sparcv9";
            case "mips":
                return "mips";
            case "mips64":
                return "mips64";
            case "mipsel":
                return "mipsel";
            case "mips64el":
                return "mips64el";
        }

        if (arch.startsWith("aarch64") || arch.startsWith("armv8") || arch.startsWith("arm64")) {
            return "arm64";
        } else if (arch.startsWith("arm")) {
            return "arm";
        }

        return "UNKNOWN";
    }

    private static void load() {
        try {
            if ("UNKNOWN".equals(PLATFORM)) {
                return;
            }
            Path temDir = null;

            if (properties != null) {
                String v = properties.getProperty("llvm.java.version");
                String tem = System.getProperty("java.io.tmpdir");

                if (v != null && !v.endsWith("-SNAPSHOT") && tem != null) {
                    Path p = Paths.get(tem);
                    if (Files.exists(p) && Files.isDirectory(p)) {
                        temDir = p.resolve("asia.kala.llvm").resolve(v).resolve(PLATFORM);
                        try {
                            Files.createDirectories(temDir);
                        } catch (IOException | SecurityException ignored) {
                            temDir = null;
                        }
                    }
                }
            }
            if (temDir == null) {
                temDir = Files.createTempDirectory("llvm");
            }

            final String libPrefix = OS.startsWith("windows") ? "" : "lib";
            final String libSuffix =
                    OS.startsWith("windows") ?
                            ".dll" : OS.equals("macos") ?
                            ".dylib" : ".so";

            final String libName = libPrefix + "llvm-java" + libSuffix;

            Path libPath = temDir.resolve(libName).toAbsolutePath();

            if (Files.exists(libPath)) {
                if (!Files.isRegularFile(libPath) || !Files.isReadable(libPath)) {
                    temDir = Files.createTempDirectory("llvm");
                    libPath = temDir.resolve(libName).toAbsolutePath();
                } else {
                    System.load(libPath.toString());
                    loaded = true;
                    return;
                }
            }

            try (InputStream input = LLVMLoader.class.getClassLoader().getResourceAsStream(
                    "asia/kala/llvm/platform/" + PLATFORM + "/" + libName)) {
                if (input != null) {
                    Files.copy(input, libPath);
                    System.load(libPath.toString());
                    loaded = true;
                }
            } catch (FileAlreadyExistsException e) {
                System.load(libPath.toString());
                loaded = true;
            }
        } catch (IOException | UnsatisfiedLinkError | SecurityException e) {
            ex = e;
            loaded = false;
        }
    }

    public static boolean LLVMIsLoader() {
        return loaded;
    }
}
