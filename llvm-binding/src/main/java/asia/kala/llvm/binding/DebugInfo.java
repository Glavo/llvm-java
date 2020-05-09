package asia.kala.llvm.binding;

import asia.kala.llvm.binding.annotations.*;

import java.lang.annotation.*;

import static asia.kala.llvm.binding.Types.*;

@CInfo(
        fileName = "DebugInfo.c",
        include = {
                "<llvm-c/DebugInfo.h>",
                "llvm-java.h"
        }
)
public final class DebugInfo extends LLVMLoader {
    private DebugInfo() {
    }

    /**
     * Debug info flags.
     */
    @CEnum("LLVMDIFlags")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMDIFlags {
        int LLVMDIFlagZero = 0;
        int LLVMDIFlagPrivate = 1;
        int LLVMDIFlagProtected = 2;
        int LLVMDIFlagPublic = 3;
        int LLVMDIFlagFwdDecl = 1 << 2;
        int LLVMDIFlagAppleBlock = 1 << 3;
        int LLVMDIFlagReservedBit4 = 1 << 4;
        int LLVMDIFlagVirtual = 1 << 5;
        int LLVMDIFlagArtificial = 1 << 6;
        int LLVMDIFlagExplicit = 1 << 7;
        int LLVMDIFlagPrototyped = 1 << 8;
        int LLVMDIFlagObjcClassComplete = 1 << 9;
        int LLVMDIFlagObjectPointer = 1 << 10;
        int LLVMDIFlagVector = 1 << 11;
        int LLVMDIFlagStaticMember = 1 << 12;
        int LLVMDIFlagLValueReference = 1 << 13;
        int LLVMDIFlagRValueReference = 1 << 14;
        int LLVMDIFlagReserved = 1 << 15;
        int LLVMDIFlagSingleInheritance = 1 << 16;
        int LLVMDIFlagMultipleInheritance = 2 << 16;
        int LLVMDIFlagVirtualInheritance = 3 << 16;
        int LLVMDIFlagIntroducedVirtual = 1 << 18;
        int LLVMDIFlagBitField = 1 << 19;
        int LLVMDIFlagNoReturn = 1 << 20;
        int LLVMDIFlagTypePassByValue = 1 << 22;
        int LLVMDIFlagTypePassByReference = 1 << 23;
        int LLVMDIFlagEnumClass = 1 << 24;
        @Deprecated
        int LLVMDIFlagFixedEnum = LLVMDIFlagEnumClass; // Deprecated;
        int LLVMDIFlagThunk = 1 << 25;
        int LLVMDIFlagNonTrivial = 1 << 26;
        int LLVMDIFlagBigEndian = 1 << 27;
        int LLVMDIFlagLittleEndian = 1 << 28;
        int LLVMDIFlagIndirectVirtualBase = (1 << 2) | (1 << 5);
        int LLVMDIFlagAccessibility = LLVMDIFlagPrivate | LLVMDIFlagProtected | LLVMDIFlagPublic;
        int LLVMDIFlagPtrToMemberRep = LLVMDIFlagSingleInheritance
                | LLVMDIFlagMultipleInheritance
                | LLVMDIFlagVirtualInheritance;
    }

    /**
     * Source languages known by DWARF.
     */
    @CEnum("LLVMDWARFSourceLanguage")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMDWARFSourceLanguage {
        int LLVMDWARFSourceLanguageC89 = 0;
        int LLVMDWARFSourceLanguageC = 1;
        int LLVMDWARFSourceLanguageAda83 = 2;
        int LLVMDWARFSourceLanguageC_plus_plus = 3;
        int LLVMDWARFSourceLanguageCobol74 = 4;
        int LLVMDWARFSourceLanguageCobol85 = 5;
        int LLVMDWARFSourceLanguageFortran77 = 6;
        int LLVMDWARFSourceLanguageFortran90 = 7;
        int LLVMDWARFSourceLanguagePascal83 = 8;
        int LLVMDWARFSourceLanguageModula2 = 9;
        // New in DWARF v3:
        int LLVMDWARFSourceLanguageJava = 10;
        int LLVMDWARFSourceLanguageC99 = 11;
        int LLVMDWARFSourceLanguageAda95 = 12;
        int LLVMDWARFSourceLanguageFortran95 = 13;
        int LLVMDWARFSourceLanguagePLI = 14;
        int LLVMDWARFSourceLanguageObjC = 15;
        int LLVMDWARFSourceLanguageObjC_plus_plus = 16;
        int LLVMDWARFSourceLanguageUPC = 17;
        int LLVMDWARFSourceLanguageD = 18;
        // New in DWARF v4:
        int LLVMDWARFSourceLanguagePython = 19;
        // New in DWARF v5:
        int LLVMDWARFSourceLanguageOpenCL = 20;
        int LLVMDWARFSourceLanguageGo = 21;
        int LLVMDWARFSourceLanguageModula3 = 22;
        int LLVMDWARFSourceLanguageHaskell = 23;
        int LLVMDWARFSourceLanguageC_plus_plus_03 = 24;
        int LLVMDWARFSourceLanguageC_plus_plus_11 = 25;
        int LLVMDWARFSourceLanguageOCaml = 26;
        int LLVMDWARFSourceLanguageRust = 27;
        int LLVMDWARFSourceLanguageC11 = 28;
        int LLVMDWARFSourceLanguageSwift = 29;
        int LLVMDWARFSourceLanguageJulia = 30;
        int LLVMDWARFSourceLanguageDylan = 31;
        int LLVMDWARFSourceLanguageC_plus_plus_14 = 32;
        int LLVMDWARFSourceLanguageFortran03 = 33;
        int LLVMDWARFSourceLanguageFortran08 = 34;
        int LLVMDWARFSourceLanguageRenderScript = 35;
        int LLVMDWARFSourceLanguageBLISS = 36;
        // Vendor extensions:
        int LLVMDWARFSourceLanguageMips_Assembler = 37;
        int LLVMDWARFSourceLanguageGOOGLE_RenderScript = 38;
        int LLVMDWARFSourceLanguageBORLAND_Delphi = 39;
    }

    @CEnum("LLVMDWARFEmissionKind")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMDWARFEmissionKind {
        int LLVMDWARFEmissionNone = 0;
        int LLVMDWARFEmissionFull = 1;
        int LLVMDWARFEmissionLineTablesOnly = 2;
    }

    /**
     * The kind of metadata nodes.
     */
    public static final int
            LLVMMDStringMetadataKind = 0,
            LLVMConstantAsMetadataMetadataKind = 1,
            LLVMLocalAsMetadataMetadataKind = 2,
            LLVMDistinctMDOperandPlaceholderMetadataKind = 3,
            LLVMMDTupleMetadataKind = 4,
            LLVMDILocationMetadataKind = 5,
            LLVMDIExpressionMetadataKind = 6,
            LLVMDIGlobalVariableExpressionMetadataKind = 7,
            LLVMGenericDINodeMetadataKind = 8,
            LLVMDISubrangeMetadataKind = 9,
            LLVMDIEnumeratorMetadataKind = 10,
            LLVMDIBasicTypeMetadataKind = 11,
            LLVMDIDerivedTypeMetadataKind = 12,
            LLVMDICompositeTypeMetadataKind = 13,
            LLVMDISubroutineTypeMetadataKind = 14,
            LLVMDIFileMetadataKind = 15,
            LLVMDICompileUnitMetadataKind = 16,
            LLVMDISubprogramMetadataKind = 17,
            LLVMDILexicalBlockMetadataKind = 18,
            LLVMDILexicalBlockFileMetadataKind = 19,
            LLVMDINamespaceMetadataKind = 20,
            LLVMDIModuleMetadataKind = 21,
            LLVMDITemplateTypeParameterMetadataKind = 22,
            LLVMDITemplateValueParameterMetadataKind = 23,
            LLVMDIGlobalVariableMetadataKind = 24,
            LLVMDILocalVariableMetadataKind = 25,
            LLVMDILabelMetadataKind = 26,
            LLVMDIObjCPropertyMetadataKind = 27,
            LLVMDIImportedEntityMetadataKind = 28,
            LLVMDIMacroMetadataKind = 29,
            LLVMDIMacroFileMetadataKind = 30,
            LLVMDICommonBlockMetadataKind = 31;

    @Unsigned("LLVMMetadataKind")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMMetadataKind {
    }

    /**
     * An LLVM DWARF type encoding.
     */
    @Unsigned("LLVMDWARFTypeEncoding")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMDWARFTypeEncoding {
    }


    @CEnum("LLVMDWARFMacinfoRecordType")
    @Documented
    @java.lang.annotation.Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LLVMDWARFMacinfoRecordType {
        int LLVMDWARFMacinfoRecordTypeDefine = 0x01;
        int LLVMDWARFMacinfoRecordTypeMacro = 0x02;
        int LLVMDWARFMacinfoRecordTypeStartFile = 0x03;
        int LLVMDWARFMacinfoRecordTypeEndFile = 0x04;
        int LLVMDWARFMacinfoRecordTypeVendorExt = 0xff;
    }

    /**
     * The current debug metadata version number.
     */
    public static native @Unsigned int LLVMDebugMetadataVersion();

    /**
     * The version of debug metadata that's present in the provided \c Module.
     */
    public static native @Unsigned int LLVMGetModuleDebugMetadataVersion(@LLVMModuleRef long Module);

    /**
     * Strip debug info in the module if it exists.
     * To do this, we remove all calls to the debugger intrinsics and any named
     * metadata for debugging. We also remove debug locations for instructions.
     * Return true if module is modified.
     */
    public static native boolean LLVMStripModuleDebugInfo(@LLVMModuleRef long Module);

    /**
     * Construct a builder for a module, and do not allow for unresolved nodes
     * attached to the module.
     */
    public static native @LLVMDIBuilderRef long LLVMCreateDIBuilderDisallowUnresolved(@LLVMModuleRef long M);

    /**
     * Construct a builder for a module and collect unresolved nodes attached
     * to the module in order to resolve cycles during a call to
     * \c LLVMDIBuilderFinalize.
     */
    public static native @LLVMDIBuilderRef long LLVMCreateDIBuilder(@LLVMModuleRef long M);

    /**
     * Deallocates the \c DIBuilder and everything it owns.
     * <p>
     * You must call \c LLVMDIBuilderFinalize before this
     */
    public static native void LLVMDisposeDIBuilder(@LLVMDIBuilderRef long Builder);

    /**
     * Construct any deferred debug info descriptors.
     */
    public static native void LLVMDIBuilderFinalize(@LLVMDIBuilderRef long Builder);

    /**
     * A CompileUnit provides an anchor for all debugging
     * information generated during this instance of compilation.
     * \param Lang          Source programming language, eg.
     * \c LLVMDWARFSourceLanguageC99
     * \param FileRef       File info.
     * \param Producer      Identify the producer of debugging information
     * and code.  Usually this is a compiler
     * version string.
     * \param ProducerLen   The length of the C string passed to \c Producer.
     * \param isOptimized   A boolean flag which indicates whether optimization
     * is enabled or not.
     * \param Flags         This string lists command line options. This
     * string is directly embedded in debug info
     * output which may be used by a tool
     * analyzing generated debugging information.
     * \param FlagsLen      The length of the C string passed to \c Flags.
     * \param RuntimeVer    This indicates runtime version for languages like
     * Objective-C.
     * \param SplitName     The name of the file that we'll split debug info
     * out into.
     * \param SplitNameLen  The length of the C string passed to \c SplitName.
     * \param Kind          The kind of debug information to generate.
     * \param DWOId         The DWOId if this is a split skeleton compile unit.
     * \param SplitDebugInlining    Whether to emit inline debug info.
     * \param DebugInfoForProfiling Whether to emit extra debug info for
     * profile collection.
     */
    public static native @LLVMMetadataRef long LLVMDIBuilderCreateCompileUnit(
            @LLVMDIBuilderRef long Builder, @LLVMDWARFSourceLanguage int Lang,
            @LLVMMetadataRef long FileRef, @Pointer("const char *") long Producer, @SizeT long ProducerLen,
            boolean isOptimized, @Pointer("const char *") long Flags, @SizeT long FlagsLen,
            @Unsigned int RuntimeVer, @Pointer("const char *") long SplitName, @SizeT long SplitNameLen,
            @LLVMDWARFEmissionKind int Kind, @Unsigned int DWOId, boolean SplitDebugInlining,
            boolean DebugInfoForProfiling);

    /**
     * Create a file descriptor to hold debugging information for a file.
     * \param Builder      The \c DIBuilder.
     * \param Filename     File name.
     * \param FilenameLen  The length of the C string passed to \c Filename.
     * \param Directory    Directory.
     * \param DirectoryLen The length of the C string passed to \c Directory.
     */
    public static native @LLVMMetadataRef long LLVMDIBuilderCreateFile(
            @LLVMDIBuilderRef long Builder, @Pointer("const char *") long Filename,
            @SizeT long FilenameLen, @Pointer("const char *") long Directory,
            @SizeT long DirectoryLen
    );

    /**
     * Creates a new descriptor for a module with the specified parent scope.
     * \param Builder         The \c DIBuilder.
     * \param ParentScope     The parent scope containing this module declaration.
     * \param Name            Module name.
     * \param NameLen         The length of the C string passed to \c Name.
     * \param ConfigMacros    A space-separated shell-quoted list of -D macro
     * definitions as they would appear on a command line.
     * \param ConfigMacrosLen The length of the C string passed to \c ConfigMacros.
     * \param IncludePath     The path to the module map file.
     * \param IncludePathLen  The length of the C string passed to \c IncludePath.
     * \param SysRoot         The Clang system root (value of -isysroot).
     * \param SysRootLen      The length of the C string passed to \c SysRoot.
     */
    public static native @LLVMMetadataRef long LLVMDIBuilderCreateModule(
            @LLVMDIBuilderRef long Builder, @LLVMMetadataRef long ParentScope,
            @Pointer("const char *") long Name, @SizeT long NameLen,
            @Pointer("const char *") long ConfigMacros, @SizeT long ConfigMacrosLen,
            @Pointer("const char *") long IncludePath, @SizeT long IncludePathLen,
            @Pointer("const char *") long SysRoot, @SizeT long SysRootLen
    );

    /**
     * Creates a new descriptor for a namespace with the specified parent scope.
     * \param Builder          The \c DIBuilder.
     * \param ParentScope      The parent scope containing this module declaration.
     * \param Name             NameSpace name.
     * \param NameLen          The length of the C string passed to \c Name.
     * \param ExportSymbols    Whether or not the namespace exports symbols, e.g.
     * this is true of C++ inline namespaces.
     */
    public static native @LLVMMetadataRef long LLVMDIBuilderCreateNameSpace(
            @LLVMDIBuilderRef long Builder,
            @LLVMMetadataRef long ParentScope,
            @Pointer("const char *") long Name, @SizeT long NameLen,
            boolean ExportSymbols
    );

    /**
     * Create a new descriptor for the specified subprogram.
     * \param Builder         The \c DIBuilder.
     * \param Scope           Function scope.
     * \param Name            Function name.
     * \param NameLen         Length of enumeration name.
     * \param LinkageName     Mangled function name.
     * \param LinkageNameLen  Length of linkage name.
     * \param File            File where this variable is defined.
     * \param LineNo          Line number.
     * \param Ty              Function type.
     * \param IsLocalToUnit   True if this function is not externally visible.
     * \param IsDefinition    True if this is a function definition.
     * \param ScopeLine       Set to the beginning of the scope this starts
     * \param Flags           E.g.: \c LLVMDIFlagLValueReference. These flags are
     * used to emit dwarf attributes.
     * \param IsOptimized     True if optimization is ON.
     */
    public static native @LLVMMetadataRef long LLVMDIBuilderCreateFunction(
            @LLVMDIBuilderRef long Builder, @LLVMMetadataRef long Scope, @Pointer("const char *") long Name,
            @SizeT long NameLen, @Pointer("const char *") long LinkageName, @SizeT long LinkageNameLen,
            @LLVMMetadataRef long File, @Unsigned int LineNo, @LLVMMetadataRef long Ty,
            boolean IsLocalToUnit, boolean IsDefinition,
            @Unsigned int ScopeLine, @LLVMDIFlags int Flags, boolean IsOptimized
    );

    /**
     * Create a descriptor for a lexical block with the specified parent context.
     * \param Builder      The \c DIBuilder.
     * \param Scope        Parent lexical block.
     * \param File         Source file.
     * \param Line         The line in the source file.
     * \param Column       The column in the source file.
     */
    public static native @LLVMMetadataRef long LLVMDIBuilderCreateLexicalBlock(
            @LLVMDIBuilderRef long Builder, @LLVMMetadataRef long Scope,
            @LLVMMetadataRef long File, @Unsigned int Line, @Unsigned int Column
    );

    /**
     * Create a descriptor for a lexical block with a new file attached.
     * \param Builder        The \c DIBuilder.
     * \param Scope          Lexical block.
     * \param File           Source file.
     * \param Discriminator  DWARF path discriminator value.
     */
    public static native @LLVMMetadataRef long LLVMDIBuilderCreateLexicalBlockFile(
            @LLVMDIBuilderRef long Builder,
            @LLVMMetadataRef long Scope,
            @LLVMMetadataRef long File,
            @Unsigned int Discriminator
    );

    /**
     * Create a descriptor for an imported namespace. Suitable for e.g. C++
     * using declarations.
     * \param Builder    The \c DIBuilder.
     * \param Scope      The scope this module is imported into
     * \param File       File where the declaration is located.
     * \param Line       Line number of the declaration.
     */
    public static native @LLVMMetadataRef long LLVMDIBuilderCreateImportedModuleFromNamespace(
            @LLVMDIBuilderRef long Builder,
            @LLVMMetadataRef long Scope,
            @LLVMMetadataRef long NS,
            @LLVMMetadataRef long File,
            @Unsigned int Line
    );

    /**
     * Create a descriptor for an imported module that aliases another
     * imported entity descriptor.
     * \param Builder        The \c DIBuilder.
     * \param Scope          The scope this module is imported into
     * \param ImportedEntity Previous imported entity to alias.
     * \param File           File where the declaration is located.
     * \param Line           Line number of the declaration.
     */
    public static native @LLVMMetadataRef long LLVMDIBuilderCreateImportedModuleFromAlias(
            @LLVMDIBuilderRef long Builder,
            @LLVMMetadataRef long Scope,
            @LLVMMetadataRef long ImportedEntity,
            @LLVMMetadataRef long File,
            @Unsigned int Line
    );

    /**
     * Create a descriptor for an imported module.
     * \param Builder    The \c DIBuilder.
     * \param Scope      The scope this module is imported into
     * \param M          The module being imported here
     * \param File       File where the declaration is located.
     * \param Line       Line number of the declaration.
     */
    public static native @LLVMMetadataRef long LLVMDIBuilderCreateImportedModuleFromModule(
            @LLVMDIBuilderRef long Builder,
            @LLVMMetadataRef long Scope,
            @LLVMMetadataRef long M,
            @LLVMMetadataRef long File,
            @Unsigned int Line
    );

    /**
     * Create a descriptor for an imported function, type, or variable.  Suitable
     * for e.g. FORTRAN-style USE declarations.
     * \param Builder    The DIBuilder.
     * \param Scope      The scope this module is imported into.
     * \param Decl       The declaration (or definition) of a function, type,
     * or variable.
     * \param File       File where the declaration is located.
     * \param Line       Line number of the declaration.
     * \param Name       A name that uniquely identifies this imported declaration.
     * \param NameLen    The length of the C string passed to \c Name.
     */
    public static native @LLVMMetadataRef long LLVMDIBuilderCreateImportedDeclaration(
            @LLVMDIBuilderRef long Builder,
            @LLVMMetadataRef long Scope,
            @LLVMMetadataRef long Decl,
            @LLVMMetadataRef long File,
            @Unsigned int Line,
            @Pointer("const char *") long Name, @SizeT long NameLen
    );

    /**
     * Creates a new DebugLocation that describes a source location.
     * \param Line The line in the source file.
     * \param Column The column in the source file.
     * \param Scope The scope in which the location resides.
     * \param InlinedAt The scope where this location was inlined, if at all.
     * (optional).
     * \note If the item to which this location is attached cannot be
     * attributed to a source line, pass 0 for the line and column.
     */
    public static native @LLVMMetadataRef long LLVMDIBuilderCreateDebugLocation(
            @LLVMContextRef long Ctx, @Unsigned int Line,
            @Unsigned int Column, @LLVMMetadataRef long Scope,
            @LLVMMetadataRef long InlinedAt
    );

    /**
     * Get the line number of this debug location.
     * \param Location     The debug location.
     */
    public static native @Unsigned int LLVMDILocationGetLine(@LLVMMetadataRef long Location);

    /**
     * Get the column number of this debug location.
     * \param Location     The debug location.
     */
    public static native @Unsigned int LLVMDILocationGetColumn(@LLVMMetadataRef long Location);

    /**
     * Get the local scope associated with this debug location.
     * \param Location     The debug location.
     */
    public static native @LLVMMetadataRef long LLVMDILocationGetScope(@LLVMMetadataRef long Location);

    /**
     * Get the "inline at" location associated with this debug location.
     * \param Location     The debug location.
     */
    public static native @LLVMMetadataRef long LLVMDILocationGetInlinedAt(@LLVMMetadataRef long Location);

    /**
     * Get the metadata of the file associated with a given scope.
     * \param Scope     The scope object.
     */
    public static native @LLVMMetadataRef long LLVMDIScopeGetFile(@LLVMMetadataRef long Scope);

    /**
     * Get the directory of a given file.
     * \param File     The file object.
     * \param Len      The length of the returned string.
     */
    public static native @Pointer("const char *") long LLVMDIFileGetDirectory(
            @LLVMMetadataRef long File, @Pointer("unsigned *") long Len
    );

    /**
     * Get the name of a given file.
     * \param File     The file object.
     * \param Len      The length of the returned string.
     */
    public static native @Pointer("const char *") long LLVMDIFileGetFilename(
            @LLVMMetadataRef long File, @Pointer("unsigned *") long Len
    );

    /**
     * Get the source of a given file.
     * \param File     The file object.
     * \param Len      The length of the returned string.
     */
    public static native @Pointer("const char *") long LLVMDIFileGetSource(
            @LLVMMetadataRef long File, @Pointer("unsigned *") long Len
    );

    /**
     * Create a type array.
     * \param Builder        The DIBuilder.
     * \param Data           The type elements.
     * \param NumElements    Number of type elements.
     */
    public static native @LLVMMetadataRef long LLVMDIBuilderGetOrCreateTypeArray(
            @LLVMDIBuilderRef long Builder,
            @Pointer("LLVMMetadataRef *") long Data,
            @SizeT long NumElements
    );

    /**
     * Create subroutine type.
     * \param Builder        The DIBuilder.
     * \param File            The file in which the subroutine resides.
     * \param ParameterTypes  An array of subroutine parameter types. This
     * includes return type at 0th index.
     * \param NumParameterTypes The number of parameter types in \c ParameterTypes
     * \param Flags           E.g.: \c LLVMDIFlagLValueReference.
     * These flags are used to emit dwarf attributes.
     */
    public static native @LLVMMetadataRef long LLVMDIBuilderCreateSubroutineType(
            @LLVMDIBuilderRef long Builder,
            @LLVMMetadataRef long File,
            @Pointer("LLVMMetadataRef *") long ParameterTypes,
            @Unsigned int NumParameterTypes,
            @LLVMDIFlags int Flags
    );

    /**
     * Create debugging information entry for a macro.
     *
     * @param Builder         The DIBuilder.
     * @param ParentMacroFile Macro parent (could be NULL).
     * @param Line            Source line number where the macro is defined.
     * @param RecordType      DW_MACINFO_define or DW_MACINFO_undef.
     * @param Name            Macro name.
     * @param NameLen         Macro name length.
     * @param Value           Macro value.
     * @param ValueLen        Macro value length.
     */
    public static native @LLVMMetadataRef long LLVMDIBuilderCreateMacro(
            @LLVMDIBuilderRef long Builder,
            @LLVMMetadataRef long ParentMacroFile,
            @Unsigned int Line,
            @LLVMDWARFMacinfoRecordType int RecordType,
            @Pointer("const char *") long Name, @SizeT long NameLen,
            @Pointer("const char *") long Value, @SizeT long ValueLen
    );

    /**
     * Create debugging information temporary entry for a macro file.
     * List of macro node direct children will be calculated by DIBuilder,
     * using the \p ParentMacroFile relationship.
     *
     * @param Builder         The DIBuilder.
     * @param ParentMacroFile Macro parent (could be NULL).
     * @param Line            Source line number where the macro file is included.
     * @param File            File descriptor containing the name of the macro file.
     */
    public static native @LLVMMetadataRef long LLVMDIBuilderCreateTempMacroFile(
            @LLVMDIBuilderRef long Builder,
            @LLVMMetadataRef long ParentMacroFile, @Unsigned int Line,
            @LLVMMetadataRef long File
    );

    /**
     * Create debugging information entry for an enumerator.
     *
     * @param Builder    The DIBuilder.
     * @param Name       Enumerator name.
     * @param NameLen    Length of enumerator name.
     * @param Value      Enumerator value.
     * @param IsUnsigned True if the value is unsigned.
     */
    public static native @LLVMMetadataRef long LLVMDIBuilderCreateEnumerator(
            @LLVMDIBuilderRef long Builder,
            @Pointer("const char *") long Name, @SizeT long NameLen,
            @Signed("int64_t") long Value,
            boolean IsUnsigned
    );

    /**
     * Create debugging information entry for an enumeration.
     * \param Builder        The DIBuilder.
     * \param Scope          Scope in which this enumeration is defined.
     * \param Name           Enumeration name.
     * \param NameLen        Length of enumeration name.
     * \param File           File where this member is defined.
     * \param LineNumber     Line number.
     * \param SizeInBits     Member size.
     * \param AlignInBits    Member alignment.
     * \param Elements       Enumeration elements.
     * \param NumElements    Number of enumeration elements.
     * \param ClassTy        Underlying type of a C++11/ObjC fixed enum.
     */
    public static native @LLVMMetadataRef long LLVMDIBuilderCreateEnumerationType(
            @LLVMDIBuilderRef long Builder, @LLVMMetadataRef long Scope, @Pointer("const char *") long Name,
            @SizeT long NameLen, @LLVMMetadataRef long File, @Unsigned int LineNumber,
            @Unsigned("uint64_t") long SizeInBits, @Unsigned("uint32_t") int AlignInBits, @Pointer("LLVMMetadataRef *") long Elements,
            @Unsigned int NumElements, @LLVMMetadataRef long ClassTy
    );

    /**
     * Create debugging information entry for a union.
     * \param Builder      The DIBuilder.
     * \param Scope        Scope in which this union is defined.
     * \param Name         Union name.
     * \param NameLen      Length of union name.
     * \param File         File where this member is defined.
     * \param LineNumber   Line number.
     * \param SizeInBits   Member size.
     * \param AlignInBits  Member alignment.
     * \param Flags        Flags to encode member attribute, e.g. private
     * \param Elements     Union elements.
     * \param NumElements  Number of union elements.
     * \param RunTimeLang  Optional parameter, Objective-C runtime version.
     * \param UniqueId     A unique identifier for the union.
     * \param UniqueIdLen  Length of unique identifier.
     */
    public static native @LLVMMetadataRef long LLVMDIBuilderCreateUnionType(
            @LLVMDIBuilderRef long Builder, @LLVMMetadataRef long Scope, @Pointer("const char *") long Name,
            @SizeT long NameLen, @LLVMMetadataRef long File, @Unsigned int LineNumber,
            @Unsigned("uint64_t") long SizeInBits, @Unsigned("uint32_t") int AlignInBits, @LLVMDIFlags int Flags,
            @Pointer("LLVMMetadataRef *") long Elements, @Unsigned int NumElements, @Unsigned int RunTimeLang,
            @Pointer("const char *") long UniqueId, @SizeT long UniqueIdLen
    );


    /**
     * Create debugging information entry for an array.
     * \param Builder      The DIBuilder.
     * \param Size         Array size.
     * \param AlignInBits  Alignment.
     * \param Ty           Element type.
     * \param Subscripts   Subscripts.
     * \param NumSubscripts Number of subscripts.
     */
    public static native @LLVMMetadataRef long LLVMDIBuilderCreateArrayType(
            @LLVMDIBuilderRef long Builder, @Unsigned("uint64_t") long Size,
            @Unsigned("uint32_t") int AlignInBits, @LLVMMetadataRef long Ty,
            @Pointer("LLVMMetadataRef *") long Subscripts,
            @Unsigned int NumSubscripts
    );

    /**
     * Create debugging information entry for a vector type.
     * \param Builder      The DIBuilder.
     * \param Size         Vector size.
     * \param AlignInBits  Alignment.
     * \param Ty           Element type.
     * \param Subscripts   Subscripts.
     * \param NumSubscripts Number of subscripts.
     */
    public static native @LLVMMetadataRef long LLVMDIBuilderCreateVectorType(
            @LLVMDIBuilderRef long Builder, @Unsigned("uint64_t") long Size,
            @Unsigned("uint32_t") int AlignInBits, @LLVMMetadataRef long Ty,
            @Pointer("LLVMMetadataRef *") long Subscripts,
            @Unsigned int NumSubscripts
    );

    /**
     * Create a DWARF unspecified type.
     * \param Builder   The DIBuilder.
     * \param Name      The unspecified type's name.
     * \param NameLen   Length of type name.
     */
    public static native @LLVMMetadataRef long
    LLVMDIBuilderCreateUnspecifiedType(@LLVMDIBuilderRef long Builder, @Pointer("const char *") long Name,
                                       @SizeT long NameLen);

    /**
     * Create debugging information entry for a basic
     * type.
     * \param Builder     The DIBuilder.
     * \param Name        Type name.
     * \param NameLen     Length of type name.
     * \param SizeInBits  Size of the type.
     * \param Encoding    DWARF encoding code, e.g. \c LLVMDWARFTypeEncoding_float.
     * \param Flags       Flags to encode optional attribute like endianity
     */
    public static native @LLVMMetadataRef long
    LLVMDIBuilderCreateBasicType(@LLVMDIBuilderRef long Builder, @Pointer("const char *") long Name,
                                 @SizeT long NameLen, @Unsigned("uint64_t") long SizeInBits,
                                 @LLVMDWARFTypeEncoding int Encoding,
                                 @LLVMDIFlags int Flags);

    /**
     * Create debugging information entry for a pointer.
     * \param Builder     The DIBuilder.
     * \param PointeeTy         Type pointed by this pointer.
     * \param SizeInBits        Size.
     * \param AlignInBits       Alignment. (optional, pass 0 to ignore)
     * \param AddressSpace      DWARF address space. (optional, pass 0 to ignore)
     * \param Name              Pointer type name. (optional)
     * \param NameLen           Length of pointer type name. (optional)
     */
    public static native @LLVMMetadataRef long LLVMDIBuilderCreatePointerType(
            @LLVMDIBuilderRef long Builder, @LLVMMetadataRef long PointeeTy,
            @Unsigned("uint64_t") long SizeInBits, @Unsigned("uint32_t") int AlignInBits, @Unsigned int AddressSpace,
            @Pointer("const char *") long Name, @SizeT long NameLen);

    /**
     * Create debugging information entry for a struct.
     * \param Builder     The DIBuilder.
     * \param Scope        Scope in which this struct is defined.
     * \param Name         Struct name.
     * \param NameLen      Struct name length.
     * \param File         File where this member is defined.
     * \param LineNumber   Line number.
     * \param SizeInBits   Member size.
     * \param AlignInBits  Member alignment.
     * \param Flags        Flags to encode member attribute, e.g. private
     * \param Elements     Struct elements.
     * \param NumElements  Number of struct elements.
     * \param RunTimeLang  Optional parameter, Objective-C runtime version.
     * \param VTableHolder The object containing the vtable for the struct.
     * \param UniqueId     A unique identifier for the struct.
     * \param UniqueIdLen  Length of the unique identifier for the struct.
     */
    public static native @LLVMMetadataRef long LLVMDIBuilderCreateStructType(
            @LLVMDIBuilderRef long Builder, @LLVMMetadataRef long Scope, @Pointer("const char *") long Name,
            @SizeT long NameLen, @LLVMMetadataRef long File, @Unsigned int LineNumber,
            @Unsigned("uint64_t") long SizeInBits, @Unsigned("uint32_t") int AlignInBits, @LLVMDIFlags int Flags,
            @LLVMMetadataRef long DerivedFrom, @Pointer("LLVMMetadataRef *") long Elements,
            @Unsigned int NumElements, @Unsigned int RunTimeLang, @LLVMMetadataRef long VTableHolder,
            @Pointer("const char *") long UniqueId, @SizeT long UniqueIdLen
    );

    /**
     * Create debugging information entry for a member.
     * \param Builder      The DIBuilder.
     * \param Scope        Member scope.
     * \param Name         Member name.
     * \param NameLen      Length of member name.
     * \param File         File where this member is defined.
     * \param LineNo       Line number.
     * \param SizeInBits   Member size.
     * \param AlignInBits  Member alignment.
     * \param OffsetInBits Member offset.
     * \param Flags        Flags to encode member attribute, e.g. private
     * \param Ty           Parent type.
     */
    public static native @LLVMMetadataRef long LLVMDIBuilderCreateMemberType(
            @LLVMDIBuilderRef long Builder, @LLVMMetadataRef long Scope, @Pointer("const char *") long Name,
            @SizeT long NameLen, @LLVMMetadataRef long File, @Unsigned int LineNo,
            @Unsigned("uint64_t") long SizeInBits, @Unsigned("uint32_t") int AlignInBits, @Unsigned("uint64_t") long OffsetInBits,
            @LLVMDIFlags int Flags, @LLVMMetadataRef long Ty
    );

    /**
     * Create debugging information entry for a
     * C++ static data member.
     * \param Builder      The DIBuilder.
     * \param Scope        Member scope.
     * \param Name         Member name.
     * \param NameLen      Length of member name.
     * \param File         File where this member is declared.
     * \param LineNumber   Line number.
     * \param Type         Type of the static member.
     * \param Flags        Flags to encode member attribute, e.g. private.
     * \param ConstantVal  Const initializer of the member.
     * \param AlignInBits  Member alignment.
     */
    public static native @LLVMMetadataRef long LLVMDIBuilderCreateStaticMemberType(
            @LLVMDIBuilderRef long Builder, @LLVMMetadataRef long Scope, @Pointer("const char *") long Name,
            @SizeT long NameLen, @LLVMMetadataRef long File, @Unsigned int LineNumber,
            @LLVMMetadataRef long Type, @LLVMDIFlags int Flags, @LLVMValueRef long ConstantVal,
            @Unsigned("uint32_t") int AlignInBits
    );

    /**
     * Create debugging information entry for a pointer to member.
     * \param Builder      The DIBuilder.
     * \param PointeeType  Type pointed to by this pointer.
     * \param ClassType    Type for which this pointer points to members of.
     * \param SizeInBits   Size.
     * \param AlignInBits  Alignment.
     * \param Flags        Flags.
     */
    public static native @LLVMMetadataRef long LLVMDIBuilderCreateMemberPointerType(
            @LLVMDIBuilderRef long Builder,
            @LLVMMetadataRef long PointeeType,
            @LLVMMetadataRef long ClassType,
            @Unsigned("uint64_t") long SizeInBits,
            @Unsigned("uint32_t") int AlignInBits,
            @LLVMDIFlags int Flags
    );

    /**
     * Create debugging information entry for Objective-C instance variable.
     * \param Builder      The DIBuilder.
     * \param Name         Member name.
     * \param NameLen      The length of the C string passed to \c Name.
     * \param File         File where this member is defined.
     * \param LineNo       Line number.
     * \param SizeInBits   Member size.
     * \param AlignInBits  Member alignment.
     * \param OffsetInBits Member offset.
     * \param Flags        Flags to encode member attribute, e.g. private
     * \param Ty           Parent type.
     * \param PropertyNode Property associated with this ivar.
     */
    public static native @LLVMMetadataRef long LLVMDIBuilderCreateObjCIVar(
            @LLVMDIBuilderRef long Builder,
            @Pointer("const char *") long Name, @SizeT long NameLen,
            @LLVMMetadataRef long File, @Unsigned int LineNo,
            @Unsigned("uint64_t") long SizeInBits, @Unsigned("uint32_t") int AlignInBits,
            @Unsigned("uint64_t") long OffsetInBits, @LLVMDIFlags int Flags,
            @LLVMMetadataRef long Ty, @LLVMMetadataRef long PropertyNode
    );

    /**
     * Create debugging information entry for Objective-C property.
     * \param Builder            The DIBuilder.
     * \param Name               Property name.
     * \param NameLen            The length of the C string passed to \c Name.
     * \param File               File where this property is defined.
     * \param LineNo             Line number.
     * \param GetterName         Name of the Objective C property getter selector.
     * \param GetterNameLen      The length of the C string passed to \c GetterName.
     * \param SetterName         Name of the Objective C property setter selector.
     * \param SetterNameLen      The length of the C string passed to \c SetterName.
     * \param PropertyAttributes Objective C property attributes.
     * \param Ty                 Type.
     */
    public static native @LLVMMetadataRef long LLVMDIBuilderCreateObjCProperty(
            @LLVMDIBuilderRef long Builder,
            @Pointer("const char *") long Name, @SizeT long NameLen,
            @LLVMMetadataRef long File, @Unsigned int LineNo,
            @Pointer("const char *") long GetterName, @SizeT long GetterNameLen,
            @Pointer("const char *") long SetterName, @SizeT long SetterNameLen,
            @Unsigned int PropertyAttributes,
            @LLVMMetadataRef long Ty
    );

    /**
     * Create a uniqued DIType* clone with FlagObjectPointer and FlagArtificial set.
     * \param Builder   The DIBuilder.
     * \param Type      The underlying type to which this pointer points.
     */
    public static native @LLVMMetadataRef long LLVMDIBuilderCreateObjectPointerType(
            @LLVMDIBuilderRef long Builder, @LLVMMetadataRef long Type
    );

    /**
     * Create debugging information entry for a qualified
     * type, e.g. 'const int'.
     * \param Builder     The DIBuilder.
     * \param Tag         Tag identifying type,
     * e.g. LLVMDWARFTypeQualifier_volatile_type
     * \param Type        Base Type.
     */
    public static native @LLVMMetadataRef long LLVMDIBuilderCreateQualifiedType(
            @LLVMDIBuilderRef long Builder, @Unsigned int Tag,
            @LLVMMetadataRef long Type
    );

    /**
     * Create debugging information entry for a c++
     * style reference or rvalue reference type.
     * \param Builder   The DIBuilder.
     * \param Tag       Tag identifying type,
     * \param Type      Base Type.
     */
    public static native @LLVMMetadataRef long LLVMDIBuilderCreateReferenceType(
            @LLVMDIBuilderRef long Builder, @Unsigned int Tag,
            @LLVMMetadataRef long Type
    );

    /**
     * Create C++11 nullptr type.
     * \param Builder   The DIBuilder.
     */
    public static native @LLVMMetadataRef long
    LLVMDIBuilderCreateNullPtrType(@LLVMDIBuilderRef long Builder);

    /**
     * Create debugging information entry for a typedef.
     * \param Builder    The DIBuilder.
     * \param Type       Original type.
     * \param Name       Typedef name.
     * \param File       File where this type is defined.
     * \param LineNo     Line number.
     * \param Scope      The surrounding context for the typedef.
     */
    public static native @LLVMMetadataRef long LLVMDIBuilderCreateTypedef(
            @LLVMDIBuilderRef long Builder, @LLVMMetadataRef long Type,
            @Pointer("const char *") long Name, @SizeT long NameLen,
            @LLVMMetadataRef long File, @Unsigned int LineNo,
            @LLVMMetadataRef long Scope, @Unsigned("uint32_t") int AlignInBits
    );

    /**
     * Create debugging information entry to establish inheritance relationship
     * between two types.
     * \param Builder       The DIBuilder.
     * \param Ty            Original type.
     * \param BaseTy        Base type. Ty is inherits from base.
     * \param BaseOffset    Base offset.
     * \param VBPtrOffset  Virtual base pointer offset.
     * \param Flags         Flags to describe inheritance attribute, e.g. private
     */
    public static native @LLVMMetadataRef long LLVMDIBuilderCreateInheritance(
            @LLVMDIBuilderRef long Builder,
            @LLVMMetadataRef long Ty, @LLVMMetadataRef long BaseTy,
            @Unsigned("uint64_t") long BaseOffset, @Unsigned("uint32_t") int VBPtrOffset,
            @LLVMDIFlags int Flags
    );

    /**
     * Create a permanent forward-declared type.
     * \param Builder             The DIBuilder.
     * \param Tag                 A unique tag for this type.
     * \param Name                Type name.
     * \param NameLen             Length of type name.
     * \param Scope               Type scope.
     * \param File                File where this type is defined.
     * \param Line                Line number where this type is defined.
     * \param RuntimeLang         Indicates runtime version for languages like
     * Objective-C.
     * \param SizeInBits          Member size.
     * \param AlignInBits         Member alignment.
     * \param UniqueIdentifier    A unique identifier for the type.
     * \param UniqueIdentifierLen Length of the unique identifier.
     */
    public static native @LLVMMetadataRef long LLVMDIBuilderCreateForwardDecl(
            @LLVMDIBuilderRef long Builder, @Unsigned int Tag, @Pointer("const char *") long Name,
            @SizeT long NameLen, @LLVMMetadataRef long Scope, @LLVMMetadataRef long File, @Unsigned int Line,
            @Unsigned int RuntimeLang, @Unsigned("uint64_t") long SizeInBits, @Unsigned("uint32_t") int AlignInBits,
            @Pointer("const char *") long UniqueIdentifier, @SizeT long UniqueIdentifierLen);

    /**
     * Create a temporary forward-declared type.
     * \param Builder             The DIBuilder.
     * \param Tag                 A unique tag for this type.
     * \param Name                Type name.
     * \param NameLen             Length of type name.
     * \param Scope               Type scope.
     * \param File                File where this type is defined.
     * \param Line                Line number where this type is defined.
     * \param RuntimeLang         Indicates runtime version for languages like
     * Objective-C.
     * \param SizeInBits          Member size.
     * \param AlignInBits         Member alignment.
     * \param Flags               Flags.
     * \param UniqueIdentifier    A unique identifier for the type.
     * \param UniqueIdentifierLen Length of the unique identifier.
     */
    public static native @LLVMMetadataRef long
    LLVMDIBuilderCreateReplaceableCompositeType(
            @LLVMDIBuilderRef long Builder, @Unsigned int Tag, @Pointer("const char *") long Name,
            @SizeT long NameLen, @LLVMMetadataRef long Scope, @LLVMMetadataRef long File, @Unsigned int Line,
            @Unsigned int RuntimeLang, @Unsigned("uint64_t") long SizeInBits, @Unsigned("uint32_t") int AlignInBits,
            @LLVMDIFlags int Flags, @Pointer("const char *") long UniqueIdentifier,
            @SizeT long UniqueIdentifierLen
    );

    /**
     * Create debugging information entry for a bit field member.
     * \param Builder             The DIBuilder.
     * \param Scope               Member scope.
     * \param Name                Member name.
     * \param NameLen             Length of member name.
     * \param File                File where this member is defined.
     * \param LineNumber          Line number.
     * \param SizeInBits          Member size.
     * \param OffsetInBits        Member offset.
     * \param StorageOffsetInBits Member storage offset.
     * \param Flags               Flags to encode member attribute.
     * \param Type                Parent type.
     */
    public static native @LLVMMetadataRef long
    LLVMDIBuilderCreateBitFieldMemberType(
            @LLVMDIBuilderRef long Builder,
            @LLVMMetadataRef long Scope,
            @Pointer("const char *") long Name, @SizeT long NameLen,
            @LLVMMetadataRef long File, @Unsigned int LineNumber,
            @Unsigned("uint64_t") long SizeInBits,
            @Unsigned("uint64_t") long OffsetInBits,
            @Unsigned("uint64_t") long StorageOffsetInBits,
            @LLVMDIFlags int Flags, @LLVMMetadataRef long Type
    );

    /**
     * Create debugging information entry for a class.
     * \param Scope               Scope in which this class is defined.
     * \param Name                Class name.
     * \param NameLen             The length of the C string passed to \c Name.
     * \param File                File where this member is defined.
     * \param LineNumber          Line number.
     * \param SizeInBits          Member size.
     * \param AlignInBits         Member alignment.
     * \param OffsetInBits        Member offset.
     * \param Flags               Flags to encode member attribute, e.g. private.
     * \param DerivedFrom         Debug info of the base class of this type.
     * \param Elements            Class members.
     * \param NumElements         Number of class elements.
     * \param VTableHolder        Debug info of the base class that contains vtable
     * for this type. This is used in
     * DW_AT_containing_type. See DWARF documentation
     * for more info.
     * \param TemplateParamsNode  Template type parameters.
     * \param UniqueIdentifier    A unique identifier for the type.
     * \param UniqueIdentifierLen Length of the unique identifier.
     */
    public static native @LLVMMetadataRef long LLVMDIBuilderCreateClassType(
            @LLVMDIBuilderRef long Builder,
            @LLVMMetadataRef long Scope, @Pointer("const char *") long Name, @SizeT long NameLen,
            @LLVMMetadataRef long File, @Unsigned int LineNumber, @Unsigned("uint64_t") long SizeInBits,
            @Unsigned("uint32_t") int AlignInBits, @Unsigned("uint64_t") long OffsetInBits, @LLVMDIFlags int Flags,
            @LLVMMetadataRef long DerivedFrom,
            @Pointer("LLVMMetadataRef *") long Elements, @Unsigned int NumElements,
            @LLVMMetadataRef long VTableHolder, @LLVMMetadataRef long TemplateParamsNode,
            @Pointer("const char *") long UniqueIdentifier, @SizeT long UniqueIdentifierLen);

    /**
     * Create a uniqued DIType* clone with FlagArtificial set.
     * \param Builder     The DIBuilder.
     * \param Type        The underlying type.
     */
    public static native @LLVMMetadataRef long
    LLVMDIBuilderCreateArtificialType(@LLVMDIBuilderRef long Builder,
                                      @LLVMMetadataRef long Type);

    /**
     * Get the name of this DIType.
     * \param DType     The DIType.
     * \param Length    The length of the returned string.
     */
    public static native @Pointer("const char *") long LLVMDITypeGetName(@LLVMMetadataRef long DType, @Pointer("size_t *") long Length);

    /**
     * Get the size of this DIType in bits.
     * \param DType     The DIType.
     */
    public static native @Unsigned("uint64_t") long LLVMDITypeGetSizeInBits(@LLVMMetadataRef long DType);

    /**
     * Get the offset of this DIType in bits.
     * \param DType     The DIType.
     */
    public static native @Unsigned("uint64_t") long LLVMDITypeGetOffsetInBits(@LLVMMetadataRef long DType);

    /**
     * Get the alignment of this DIType in bits.
     * \param DType     The DIType.
     */
    public static native @Unsigned("uint32_t") int LLVMDITypeGetAlignInBits(@LLVMMetadataRef long DType);

    /**
     * Get the source line where this DIType is declared.
     * \param DType     The DIType.
     */
    public static native @Unsigned int LLVMDITypeGetLine(@LLVMMetadataRef long DType);

    /**
     * Get the flags associated with this DIType.
     * \param DType     The DIType.
     */
    public static native @LLVMDIFlags int LLVMDITypeGetFlags(@LLVMMetadataRef long DType);

    /**
     * Create a descriptor for a value range.
     * \param Builder    The DIBuilder.
     * \param LowerBound Lower bound of the subrange, e.g. 0 for C, 1 for Fortran.
     * \param Count      Count of elements in the subrange.
     */
    public static native @LLVMMetadataRef long LLVMDIBuilderGetOrCreateSubrange(
            @LLVMDIBuilderRef long Builder,
            @Signed("int64_t") long LowerBound,
            @Signed("int64_t") long Count
    );

    /**
     * Create an array of DI Nodes.
     * \param Builder        The DIBuilder.
     * \param Data           The DI Node elements.
     * \param NumElements    Number of DI Node elements.
     */
    public static native @LLVMMetadataRef long LLVMDIBuilderGetOrCreateArray(
            @LLVMDIBuilderRef long Builder,
            @Pointer("LLVMMetadataRef *") long Data,
            @SizeT long NumElements
    );

    /**
     * Create a new descriptor for the specified variable which has a complex
     * address expression for its address.
     * \param Builder     The DIBuilder.
     * \param Addr        An array of complex address operations.
     * \param Length      Length of the address operation array.
     */
    public static native @LLVMMetadataRef long LLVMDIBuilderCreateExpression(
            @LLVMDIBuilderRef long Builder,
            @Pointer("int64_t *") long Addr, @SizeT long Length
    );

    /**
     * Create a new descriptor for the specified variable that does not have an
     * address, but does have a constant value.
     * \param Builder     The DIBuilder.
     * \param Value       The constant value.
     */
    public static native @LLVMMetadataRef long LLVMDIBuilderCreateConstantValueExpression(
            @LLVMDIBuilderRef long Builder,
            @Signed("int64_t") long Value
    );

    /**
     * Create a new descriptor for the specified variable.
     * \param Scope       Variable scope.
     * \param Name        Name of the variable.
     * \param NameLen     The length of the C string passed to \c Name.
     * \param Linkage     Mangled  name of the variable.
     * \param LinkLen     The length of the C string passed to \c Linkage.
     * \param File        File where this variable is defined.
     * \param LineNo      Line number.
     * \param Ty          Variable Type.
     * \param LocalToUnit Boolean flag indicate whether this variable is
     * externally visible or not.
     * \param Expr        The location of the global relative to the attached
     * GlobalVariable.
     * \param Decl        Reference to the corresponding declaration.
     * variables.
     * \param AlignInBits Variable alignment(or 0 if no alignment attr was
     * specified)
     */
    public static native @LLVMMetadataRef long LLVMDIBuilderCreateGlobalVariableExpression(
            @LLVMDIBuilderRef long Builder, @LLVMMetadataRef long Scope, @Pointer("const char *") long Name,
            @SizeT long NameLen, @Pointer("const char *") long Linkage, @SizeT long LinkLen, @LLVMMetadataRef long File,
            @Unsigned int LineNo, @LLVMMetadataRef long Ty, boolean LocalToUnit,
            @LLVMMetadataRef long Expr, @LLVMMetadataRef long Decl, @Unsigned("uint32_t") int AlignInBits);

    /**
     * Retrieves the \c DIVariable associated with this global variable expression.
     * \param GVE    The global variable expression.
     */
    public static native @LLVMMetadataRef long LLVMDIGlobalVariableExpressionGetVariable(@LLVMMetadataRef long GVE);

    /**
     * Retrieves the \c DIExpression associated with this global variable expression.
     * \param GVE    The global variable expression.
     */
    public static native @LLVMMetadataRef long LLVMDIGlobalVariableExpressionGetExpression(
            @LLVMMetadataRef long GVE
    );

    /**
     * Get the metadata of the file associated with a given variable.
     * \param Var     The variable object.
     */
    public static native @LLVMMetadataRef long LLVMDIVariableGetFile(@LLVMMetadataRef long Var);

    /**
     * Get the metadata of the scope associated with a given variable.
     * \param Var     The variable object.
     */
    public static native @LLVMMetadataRef long LLVMDIVariableGetScope(@LLVMMetadataRef long Var);

    /**
     * Get the source line where this \c DIVariable is declared.
     * \param Var     The DIVariable.
     */
    public static native @Unsigned int LLVMDIVariableGetLine(@LLVMMetadataRef long Var);

    /**
     * Create a new temporary \c MDNode.  Suitable for use in constructing cyclic
     * \c MDNode structures. A temporary \c MDNode is not uniqued, may be RAUW'd,
     * and must be manually deleted with \c LLVMDisposeTemporaryMDNode.
     * \param Ctx            The context in which to construct the temporary node.
     * \param Data           The metadata elements.
     * \param NumElements    Number of metadata elements.
     */
    public static native @LLVMMetadataRef long LLVMTemporaryMDNode(@LLVMContextRef long Ctx, @Pointer("LLVMMetadataRef *") long Data,
                                                                   @SizeT long NumElements);

    /**
     * Deallocate a temporary node.
     * <p>
     * Calls \c replaceAllUsesWith(nullptr) before deleting, so any remaining
     * references will be reset.
     * \param TempNode    The temporary metadata node.
     */
    public static native void LLVMDisposeTemporaryMDNode(@LLVMMetadataRef long TempNode);

    /**
     * Replace all uses of temporary metadata.
     * \param TempTargetMetadata    The temporary metadata node.
     * \param Replacement           The replacement metadata node.
     */
    public static native void LLVMMetadataReplaceAllUsesWith(@LLVMMetadataRef long TempTargetMetadata,
                                                             @LLVMMetadataRef long Replacement);

    /**
     * Create a new descriptor for the specified global variable that is temporary
     * and meant to be RAUWed.
     * \param Scope       Variable scope.
     * \param Name        Name of the variable.
     * \param NameLen     The length of the C string passed to \c Name.
     * \param Linkage     Mangled  name of the variable.
     * \param LnkLen      The length of the C string passed to \c Linkage.
     * \param File        File where this variable is defined.
     * \param LineNo      Line number.
     * \param Ty          Variable Type.
     * \param LocalToUnit Boolean flag indicate whether this variable is
     * externally visible or not.
     * \param Decl        Reference to the corresponding declaration.
     * \param AlignInBits Variable alignment(or 0 if no alignment attr was
     * specified)
     */
    public static native @LLVMMetadataRef long LLVMDIBuilderCreateTempGlobalVariableFwdDecl(
            @LLVMDIBuilderRef long Builder, @LLVMMetadataRef long Scope, @Pointer("const char *") long Name,
            @SizeT long NameLen, @Pointer("const char *") long Linkage, @SizeT long LnkLen, @LLVMMetadataRef long File,
            @Unsigned int LineNo, @LLVMMetadataRef long Ty, boolean LocalToUnit,
            @LLVMMetadataRef long Decl, @Unsigned("uint32_t") int AlignInBits);

    /**
     * Insert a new llvm.dbg.declare intrinsic call before the given instruction.
     * \param Builder     The DIBuilder.
     * \param Storage     The storage of the variable to declare.
     * \param VarInfo     The variable's debug info descriptor.
     * \param Expr        A complex location expression for the variable.
     * \param DebugLoc    Debug info location.
     * \param Instr       Instruction acting as a location for the new intrinsic.
     */
    public static native @LLVMValueRef long LLVMDIBuilderInsertDeclareBefore(
            @LLVMDIBuilderRef long Builder, @LLVMValueRef long Storage, @LLVMMetadataRef long VarInfo,
            @LLVMMetadataRef long Expr, @LLVMMetadataRef long DebugLoc, @LLVMValueRef long Instr);

    /**
     * Insert a new llvm.dbg.declare intrinsic call at the end of the given basic
     * block. If the basic block has a terminator instruction, the intrinsic is
     * inserted before that terminator instruction.
     * \param Builder     The DIBuilder.
     * \param Storage     The storage of the variable to declare.
     * \param VarInfo     The variable's debug info descriptor.
     * \param Expr        A complex location expression for the variable.
     * \param DebugLoc    Debug info location.
     * \param Block       Basic block acting as a location for the new intrinsic.
     */
    public static native @LLVMValueRef long LLVMDIBuilderInsertDeclareAtEnd(
            @LLVMDIBuilderRef long Builder, @LLVMValueRef long Storage, @LLVMMetadataRef long VarInfo,
            @LLVMMetadataRef long Expr, @LLVMMetadataRef long DebugLoc, @LLVMBasicBlockRef long Block);

    /**
     * Insert a new llvm.dbg.value intrinsic call before the given instruction.
     * \param Builder     The DIBuilder.
     * \param Val         The value of the variable.
     * \param VarInfo     The variable's debug info descriptor.
     * \param Expr        A complex location expression for the variable.
     * \param DebugLoc    Debug info location.
     * \param Instr       Instruction acting as a location for the new intrinsic.
     */
    public static native @LLVMValueRef long LLVMDIBuilderInsertDbgValueBefore(
            @LLVMDIBuilderRef long Builder,
            @LLVMValueRef long Val,
            @LLVMMetadataRef long VarInfo,
            @LLVMMetadataRef long Expr,
            @LLVMMetadataRef long DebugLoc,
            @LLVMValueRef long Instr
    );

    /**
     * Insert a new llvm.dbg.value intrinsic call at the end of the given basic
     * block. If the basic block has a terminator instruction, the intrinsic is
     * inserted before that terminator instruction.
     * \param Builder     The DIBuilder.
     * \param Val         The value of the variable.
     * \param VarInfo     The variable's debug info descriptor.
     * \param Expr        A complex location expression for the variable.
     * \param DebugLoc    Debug info location.
     * \param Block       Basic block acting as a location for the new intrinsic.
     */
    public static native @LLVMValueRef long LLVMDIBuilderInsertDbgValueAtEnd(
            @LLVMDIBuilderRef long Builder,
            @LLVMValueRef long Val,
            @LLVMMetadataRef long VarInfo,
            @LLVMMetadataRef long Expr,
            @LLVMMetadataRef long DebugLoc,
            @LLVMBasicBlockRef long Block
    );

    /**
     * Create a new descriptor for a local auto variable.
     * \param Builder         The DIBuilder.
     * \param Scope           The local scope the variable is declared in.
     * \param Name            Variable name.
     * \param NameLen         Length of variable name.
     * \param File            File where this variable is defined.
     * \param LineNo          Line number.
     * \param Ty              Metadata describing the type of the variable.
     * \param AlwaysPreserve  If true, this descriptor will survive optimizations.
     * \param Flags           Flags.
     * \param AlignInBits     Variable alignment.
     */
    public static native @LLVMMetadataRef long LLVMDIBuilderCreateAutoVariable(
            @LLVMDIBuilderRef long Builder, @LLVMMetadataRef long Scope, @Pointer("const char *") long Name,
            @SizeT long NameLen, @LLVMMetadataRef long File, @Unsigned int LineNo, @LLVMMetadataRef long Ty,
            boolean AlwaysPreserve, @LLVMDIFlags int Flags, @Unsigned("uint32_t") int AlignInBits);

    /**
     * Create a new descriptor for a function parameter variable.
     * \param Builder         The DIBuilder.
     * \param Scope           The local scope the variable is declared in.
     * \param Name            Variable name.
     * \param NameLen         Length of variable name.
     * \param ArgNo           Unique argument number for this variable; starts at 1.
     * \param File            File where this variable is defined.
     * \param LineNo          Line number.
     * \param Ty              Metadata describing the type of the variable.
     * \param AlwaysPreserve  If true, this descriptor will survive optimizations.
     * \param Flags           Flags.
     */
    public static native @LLVMMetadataRef long LLVMDIBuilderCreateParameterVariable(
            @LLVMDIBuilderRef long Builder, @LLVMMetadataRef long Scope, @Pointer("const char *") long Name,
            @SizeT long NameLen, @Unsigned int ArgNo, @LLVMMetadataRef long File, @Unsigned int LineNo,
            @LLVMMetadataRef long Ty, boolean AlwaysPreserve, @LLVMDIFlags int Flags);

    /**
     * Get the metadata of the subprogram attached to a function.
     */
    public static native @LLVMMetadataRef long LLVMGetSubprogram(@LLVMValueRef long Func);

    /**
     * Set the subprogram attached to a function.
     */
    public static native void LLVMSetSubprogram(@LLVMValueRef long Func, @LLVMMetadataRef long SP);

    /**
     * Get the line associated with a given subprogram.
     * \param Subprogram     The subprogram object.
     */
    public static native @Unsigned int LLVMDISubprogramGetLine(@LLVMMetadataRef long Subprogram);

    /**
     * Get the debug location for the given instruction.
     */
    public static native @LLVMMetadataRef long LLVMInstructionGetDebugLoc(@LLVMValueRef long Inst);

    /**
     * Set the debug location for the given instruction.
     * <p>
     * To clear the location metadata of the given instruction, pass NULL to \p Loc.
     */
    public static native void LLVMInstructionSetDebugLoc(@LLVMValueRef long Inst, @LLVMMetadataRef long Loc);

    /**
     * Obtain the enumerated type of a Metadata instance.
     */
    public static native @LLVMMetadataKind int LLVMGetMetadataKind(@LLVMMetadataRef long Metadata);
}
