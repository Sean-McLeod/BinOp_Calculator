package Model;

import Model.Expressions.BinOp;
import Model.Expressions.IExpression;
import Model.Expressions.Value;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExpressionInterpreter {
    public IExpression InterpretExpression(String expression) {
        List<String> splitExpression = SplitStringIntoListOfExpressions(expression);
        ArrayList<IExpression> expressionsSplit = ConvertListOfStringToExpressions(splitExpression);
        return ConvertListToExpression(expressionsSplit);
    }

    private ArrayList<IExpression> ConvertListOfStringToExpressions(List<String> splitExpression) {
        ArrayList<IExpression> expressionsSplit = new ArrayList<>();
        for (String s : splitExpression) {
            try {
                double value = Double.parseDouble(s);
                expressionsSplit.add(new Value(value));
            } catch (Exception e) {
                expressionsSplit.add(new BinOp(s));
            }
        }
        return expressionsSplit;
    }

    private IExpression ConvertListToExpression(ArrayList<IExpression> splitExpression) {
        IExpression expression;

        while (ContainsSymbol(splitExpression, "^") && splitExpression.size() > 1) {
            ConstructExpression(splitExpression, "^");
        }
        while ((ContainsSymbol(splitExpression, "*") || (ContainsSymbol(splitExpression, "/"))) && splitExpression.size() > 1) {
            if (ContainsSymbol(splitExpression, "*") && (ContainsSymbol(splitExpression, "/"))) {
                String firstAppears = FirstAppearingSymbol(splitExpression, "*", "/");
                ConstructExpression(splitExpression, firstAppears);
            } else if (ContainsSymbol(splitExpression, "*")) {
                ConstructExpression(splitExpression, "*");
            } else {
                ConstructExpression(splitExpression, "/");
            }
        }
        while ((ContainsSymbol(splitExpression, "+") || (ContainsSymbol(splitExpression, "-"))) && splitExpression.size() > 1) {
            if (ContainsSymbol(splitExpression, "+") && (ContainsSymbol(splitExpression, "-"))) {
                String firstAppears = FirstAppearingSymbol(splitExpression, "+", "-");
                ConstructExpression(splitExpression, firstAppears);
            } else if (ContainsSymbol(splitExpression, "+")) {
                ConstructExpression(splitExpression, "+");
            } else {
                ConstructExpression(splitExpression, "-");
            }
        }
        return splitExpression.get(0);
    }

    private String FirstAppearingSymbol(ArrayList<IExpression> splitExpression, String symbolX, String symbolY) {
        for (IExpression exp: splitExpression) {
            if (exp.toString().equals(symbolX)) {
                return symbolX;
            } else if (exp.toString().equals(symbolY)) {
                return symbolY;
            }
        }
        return null;
    }

    private ArrayList<IExpression> ConstructExpression(ArrayList<IExpression> splitExpression, String symbol) {
        int firstIndex = IndexOf(splitExpression, symbol);
        IExpression right = splitExpression.remove(firstIndex + 1);
        IExpression middle = splitExpression.remove(firstIndex);
        IExpression left = splitExpression.remove(firstIndex - 1);
        IExpression exp = new BinOp(left, middle.toString(), right);
        splitExpression.add(firstIndex - 1, exp);
        return splitExpression;
    }

    private int IndexOf(ArrayList<IExpression> splitExpression, String symbol) {
        for (int i = 0; i < splitExpression.size(); i++) {
            if (splitExpression.get(i).toString().equals(symbol)) {
                return i;
            }
        }
        return -1;
    }

    private boolean ContainsSymbol(ArrayList<IExpression> splitExpression, String symbol) {
        for (IExpression exp: splitExpression) {
            if (exp.toString().equals(symbol)){
                return true;
            }
        }
        return false;
    }

    private boolean isNotDouble(String value) {
        try {
            // Try to parse the value as a double
            Double.parseDouble(value);

            // If parsing succeeds, it's a double
            return false;
        } catch (NumberFormatException e) {
            // If parsing fails, it's not a double
            return true;
        }
    }

    private List<String> SplitStringIntoListOfExpressions(String expression) {
        // Remove all the spaces
        String exp = expression.replaceAll("\\s", "");

        // Define regular expressions for splitting numbers and operations
        String numberRegex = "(?<=[^\\.\\d])|(?=[^\\.\\d])"; // Split on non-digit characters
        String operationRegex = "(?<=\\d)|(?=\\d)";          // Split on digits

        // Combine the regular expressions
        String regex = String.format("(%s)|(%s)", numberRegex, operationRegex);

        // Split the expression using the regex
        String[] elementsArray = exp.split(regex);

        // Convert the array to a list
        List<String> listOfStringsToConvertToListExpressions = Arrays.asList(elementsArray);

        // Remove any empty strings from the list
        listOfStringsToConvertToListExpressions.removeIf(String::isEmpty);

        return listOfStringsToConvertToListExpressions;
    }
}
