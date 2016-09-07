package com.omt.webservice.utility;

public enum EnumOperation {
    PLUS,
    MINUS,
    TIMES,
    DIVIDE;

    double calculate(double x, double y) {
        switch (this) {
            case PLUS:
                return x + y;
            case MINUS:
                return x - y;
            case TIMES:
                return x * y;
            case DIVIDE:
                return x / y;
            default:
                throw new AssertionError("Unknown operations " + this);
        }
    }
    
    public static void main(String[] args) {

        double result = EnumOperation.PLUS.calculate(1, 2);
        System.out.println(result); //3.0

    }
}