package com.dfsek.paralithic.eval;

import com.dfsek.paralithic.ops.Operation;
import com.dfsek.paralithic.ops.binary.*;
import com.dfsek.paralithic.ops.binary.booleans.AndOperation;
import com.dfsek.paralithic.ops.binary.booleans.ComparisonOperation;
import com.dfsek.paralithic.ops.binary.booleans.OrOperation;
import com.dfsek.paralithic.ops.binary.number.AdditionOperation;
import com.dfsek.paralithic.ops.binary.number.DivisionOperation;
import com.dfsek.paralithic.ops.binary.number.ModuloOperation;
import com.dfsek.paralithic.ops.binary.number.MultiplicationOperation;
import com.dfsek.paralithic.ops.binary.number.SubtractionOperation;
import com.dfsek.paralithic.ops.binary.special.PowerOperation;

public class ParserUtil {
    public static BinaryOperation createBinaryOperation(BinaryOperation.Op op, Operation left, Operation right) {
        switch(op) {
            case ADD:
                return new AdditionOperation(left, right);
            case SUBTRACT:
                return new SubtractionOperation(left, right);
            case MULTIPLY:
                return new MultiplicationOperation(left, right);
            case DIVIDE:
                return new DivisionOperation(left, right);
            case POWER:
                return new PowerOperation(left, right);
            case MODULO:
                return new ModuloOperation(left, right);
            case LT:
            case LT_EQ:
            case GT:
            case GT_EQ:
            case EQ:
            case NEQ:
                return new ComparisonOperation(left, right, op);
            case AND:
                return new AndOperation(left, right);
            case OR:
                return new OrOperation(left, right);
            default:
                throw new UnsupportedOperationException(String.valueOf(op));
        }
    }
}
