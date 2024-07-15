package com.dfsek.paralithic.node;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public sealed class Constant implements Simplifiable {
    protected final double value;

    public static final Constant DCONST_0 = new DoubleConstantZero();

    public static final Constant DCONST_1 = new DoubleConstantOne();

    private Constant(double value) {
        this.value = value;
    }

    public static Constant of(double value) {
        return new Constant(value);
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }

    public void apply(@NotNull MethodVisitor visitor, String generatedImplementationName) {
        visitor.visitLdcInsn(value);
    }

    @Override
    public Statefulness statefulness() {
        return Statefulness.STATELESS; // Constants are stateless.
    }

    @Override
    public double eval(double... inputs) {
        return value;
    }

    @Override
    public @NotNull Node simplify() {
        if(value == 0) return DCONST_0;
        if(value == 1) return DCONST_1;
        return this;
    }

    private static final class DoubleConstantZero extends Constant {
        private DoubleConstantZero() {
            super(0);
        }

        @Override
        public void apply(@NotNull MethodVisitor visitor, String generatedImplementationName) {
            visitor.visitInsn(Opcodes.DCONST_0);
        }

        @Override
        public @NotNull Node simplify() {
            return this;
        }
    }

    private static final class DoubleConstantOne extends Constant {
        private DoubleConstantOne() {
            super(1);
        }

        @Override
        public void apply(@NotNull MethodVisitor visitor, String generatedImplementationName) {
            visitor.visitInsn(Opcodes.DCONST_1);
        }

        @Override
        public @NotNull Node simplify() {
            return this;
        }
    }
}
