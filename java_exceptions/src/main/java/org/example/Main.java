package org.example;

import org.example.exception.InvalidStatementException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {


        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
           processFile(reader);
        } catch (FileNotFoundException ex) {
            System.out.println("File not found: " + args[0]);
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error processing file - " + ex.getMessage());
        }
    }

    private static void processFile(BufferedReader reader) throws IOException {
        String inputLine = null;
        while ((inputLine = reader.readLine()) != null) {
            try {
                performOperation(inputLine);
            } catch (InvalidStatementException ex) {
                System.out.println("Error invalid statement - " + inputLine);
                writeInvalidStatementExceptionToLog(ex, inputLine);
            }
        }
    }

    static void writeInvalidStatementExceptionToLog(InvalidStatementException ex, String inputLine) {
        System.err.println("");
        System.err.println("*********************************");
        System.err.println("Information written to log system");
        System.err.println("*********************************");

        System.err.println(ex.getMessage() + " - " + inputLine);
        if (ex.getCause() != null) {
            System.err.println("  caused by " + ex.getCause());
            ex.printStackTrace(System.err);

        }

    }

    private static void performOperation(String inputLine) throws InvalidStatementException {
        try {
            String[] parts = inputLine.split(" ");
            if (parts.length != 3) {
                throw new InvalidStatementException("Statement must have 3 parts: operation leftVal rightVal");
            }
            MathOperation operation = MathOperation.valueOf(parts[0].toUpperCase());
            int leftVal = valueFromWord(parts[1]);
            int rightVal = valueFromWord(parts[2]);

            int result = execute(operation, leftVal, rightVal);

            String response = String.format("%d %s %d = %d", leftVal, operation.getSymbol(), rightVal, result);

            System.out.println(response);
        } catch (InvalidStatementException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InvalidStatementException("Error processing statement ", ex);
        }
    }

    private static int valueFromWord(String part) {
        String[] numberWords = {"zero", "one", "two", "three", "four", "five", "six", "serven", "eight", "nine",
                "ten"};
        boolean isValueSert = false;
        int value = 0;
        for (int index = 0; index < numberWords.length; index++) {
            if (part.equals(numberWords[index])) {
                value = index;
                isValueSert = true;
                break;
            }
        }
        if (!isValueSert) {
            value = Integer.parseInt(part);
        }
        return value;

    }

    static int execute(MathOperation operation, int leftVal, int rightVal) {
        int result = 0;

        switch (operation) {
            case ADD:
                result = leftVal + rightVal;
                break;
            case SUBTRACT:
                result = leftVal - rightVal;
                break;
            case MULTIPLY:
                result = leftVal * rightVal;
                break;
            case DIVIDE:
                if (rightVal == 0) {
                    throw new IllegalArgumentException("Zero rightVal not permitted with divide operation");
                }
                result = rightVal / rightVal;
                break;

        }
        return result;
    }


}