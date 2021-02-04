package com.dfsek.paralithic.ops.unary;

import com.dfsek.paralithic.ops.Operation;
import com.dfsek.paralithic.ops.OperationUtils;
import com.dfsek.paralithic.ops.Simplifiable;
import com.dfsek.paralithic.ops.constant.Constant;
import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.MethodVisitor;

public abstract class UnaryOperation implements Operation, Simplifiable {
    protected final Operation op;

    protected UnaryOperation(Operation op) {
        this.op = OperationUtils.simplify(op);
    }

    public abstract void applyOperand(MethodVisitor visitor);

    @Override
    public void apply(@NotNull MethodVisitor visitor, String generatedImplementationName) {
        op.apply(visitor, generatedImplementationName); // Push operand result to stack
        applyOperand(visitor); // Apply operator
    }

    @Override
    public int canSimplify() {
        if(op instanceof Constant) return CONSTANT_OPERANDS;
        return NO_SIMPLIFY;
    }
}
