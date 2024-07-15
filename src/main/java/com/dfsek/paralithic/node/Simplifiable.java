package com.dfsek.paralithic.node;

import com.dfsek.paralithic.node.binary.BinaryNode;
import com.dfsek.paralithic.node.special.TernaryIfNode;
import com.dfsek.paralithic.node.special.function.FunctionNode;
import com.dfsek.paralithic.node.special.function.NativeFunctionNode;
import com.dfsek.paralithic.node.unary.UnaryNode;
import org.jetbrains.annotations.NotNull;

/**
 * A node that can be simplified.
 */
public sealed interface Simplifiable extends Node permits Constant, BinaryNode, TernaryIfNode, FunctionNode, NativeFunctionNode, UnaryNode {
    /**
     * Simplify this node
     * @return Simplified node
     */
    @NotNull
    Node simplify();
}
