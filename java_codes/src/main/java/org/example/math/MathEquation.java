package org.example.math;

public class MathEquation {
	private MathOperation opCode;
	private double leftVal;
	private double rightVal;
	private double result;

	private static int numberOfCalculations;
	private static double sumOfResult;

	public MathEquation() {
	}

	public MathEquation(MathOperation opCode) {
		this.opCode = opCode;
	}

	public MathEquation(MathOperation opCode, double leftVal, double rightVal) {
		this(opCode);
		this.leftVal = leftVal;
		this.rightVal = rightVal;

	}

	public void execute() {
		switch (this.opCode) {
		case ADD:
			this.result = this.leftVal + this.rightVal;
			break;
		case SUBTRACT:
			this.result = this.leftVal - this.rightVal;
			break;
		case MULTIPLY:
			this.result = this.leftVal * this.rightVal;
			break;
		case DIVIDE:
			this.result = this.rightVal != 0.0d ? this.leftVal / this.rightVal : 0.0d;
			break;
		default:
			System.out.println("Invalid opCode: " + this.opCode);
			this.result = 0.0d;
			break;
		}
		numberOfCalculations++;
		sumOfResult += this.result;
	}

	public void execute(double leftVal, double rightVal) {
		this.leftVal = leftVal;
		this.rightVal = rightVal;

		execute();
	}

	public void execute(int leftVal, int rightVal) {
		this.leftVal = leftVal;
		this.rightVal = rightVal;

		execute();
		this.result = (int) this.result;
	}

	@Override
	public String toString() {
		char symbol = this.opCode.getSymbol();
		StringBuilder builder = new StringBuilder(20);
		builder.append(this.leftVal);
		builder.append(" ");
		builder.append(symbol);
		builder.append(" ");
		builder.append(this.rightVal);
		builder.append(" = ");
		builder.append(this.result);
		return builder.toString();
	}

	public static double getAverageResult() {
		return sumOfResult / numberOfCalculations;
	}

	public MathOperation getOpCode() {
		return this.opCode;
	}

	public void setOpCode(MathOperation opCode) {
		this.opCode = opCode;
	}

	public double getLeftVal() {
		return this.leftVal;
	}

	public void setLeftVal(double leftVal) {
		this.leftVal = leftVal;
	}

	public double getRightVal() {
		return this.rightVal;
	}

	public void setRightVal(double rightVal) {
		this.rightVal = rightVal;
	}

	public double getResult() {
		return this.result;
	}

	public void setResult(double result) {
		this.result = result;
	}

	public static int getNumberOfCalculations() {
		return numberOfCalculations;
	}

	public static void setNumberOfCalculations(int numberOfCalculations) {
		MathEquation.numberOfCalculations = numberOfCalculations;
	}

	public static double getSumOfResult() {
		return sumOfResult;
	}

	public static void setSumOfResult(double sumOfResult) {
		MathEquation.sumOfResult = sumOfResult;
	}

}
