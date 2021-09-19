package com.dfsek.paralithic.operations.binary.number;

import com.dfsek.paralithic.operations.Node;
import com.dfsek.paralithic.operations.binary.BinaryNode;
import com.dfsek.paralithic.operations.Constant;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.DSUB;

public class SubtractionNode extends BinaryNode {
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
}
