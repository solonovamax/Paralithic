package com.dfsek.paralithic.node.binary.number;

import com.dfsek.paralithic.node.Constant;
import com.dfsek.paralithic.node.Node;
import com.dfsek.paralithic.node.binary.BinaryNode;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.DSUB;

public final class SubtractionNode extends BinaryNode {
    public SubtractionNode(Node left, Node right) {
        super(left, right);
    }

    @Override
    public void applyOperand(MethodVisitor visitor, String generatedImplementationName) {
        visitor.visitInsn(DSUB);
    }

    @Override
    public Op getOp() {
        return Op.SUBTRACT;
    }

    @Override
    public Constant constantSimplify() {
        return Constant.of(((Constant) left).getValue() - ((Constant) right).getValue());
    }

    @Override
    public Node finalSimplify() {
        if(right instanceof Constant c && c.getValue() == 0) {
            return left;
        }
        return super.finalSimplify();
    }

    @Override
    public double eval(double... inputs) {
        return left.eval(inputs) - right.eval(inputs);
    }
}
