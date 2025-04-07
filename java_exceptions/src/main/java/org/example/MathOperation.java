package org.example;

public enum MathOperation {
	ADD('+'), SUBTRACT('-'), MULTIPLY('*'), DIVIDE('/');

	private char symbol;

	public char getSymbol() {
		return this.symbol;
	}

	private MathOperation(char symbol) {
		this.symbol = symbol;
	}

	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}

}
