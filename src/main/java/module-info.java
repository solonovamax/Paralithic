module com.dfsek.paralithic {
    requires transitive org.jetbrains.annotations;
    requires transitive org.objectweb.asm;

    exports com.dfsek.paralithic;
    exports com.dfsek.paralithic.eval.parser;
    exports com.dfsek.paralithic.eval.tokenizer;

    exports com.dfsek.paralithic.functions;
    exports com.dfsek.paralithic.functions.dynamic;
    exports com.dfsek.paralithic.functions.natives;
    exports com.dfsek.paralithic.functions.node;

    exports com.dfsek.paralithic.node;
    exports com.dfsek.paralithic.node.binary;
    exports com.dfsek.paralithic.node.binary.booleans;
    exports com.dfsek.paralithic.node.binary.number;
    exports com.dfsek.paralithic.node.special;
    exports com.dfsek.paralithic.node.special.function;
    exports com.dfsek.paralithic.node.unary;
}
