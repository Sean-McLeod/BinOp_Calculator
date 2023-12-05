package Model;

import Model.Expressions.BinOp;
import Model.Expressions.IExpression;
import Model.Expressions.Value;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExpressionInterpreter {
    /***
     * Take a string and convert it to an expression that can be evaluated
     * @param expression a string EX: "5+5"
     * @return An IExpression object
     */
    public IExpression InterpretExpression(String expression) {
        List<String> splitExpression = SplitStringIntoListOfExpressions(expression);
        ArrayList<IExpression> expressionsSplit = ConvertListOfStringToExpressions(splitExpression);
        return ConvertListToExpression(expressionsSplit);
    }

    /***
     * Turn every string in a list of string to an expression representation of that string
     * Ex: [5,+,5] ==> [Value(5), BinOp(+), Value(5)]
     * @param splitExpression A valid list of strings
     * @return A valid ArrayList of expressoins
     */
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

    /***
     * Converst an ArrayList of expressions to a valid expression and return it using BEDMAS rules
     */
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

    /***
     * Check which symbol/operation appears first in the the arraylist of expressions
     * @param splitExpression An ArrayList of valid expressions
     * @param symbolX a string representing a symbol/expression
     * @param symbolY a string representing a symbol/expression
     * @return the first appearing symbol/expression
     */
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

    /***
     * Construct a valid expression from the first three elements of the list were the sybol is found and
     * place it back in place of those three elements back in the list in there place
     * @param splitExpression An ArrayList of valid expressions
     * @param symbol A string of one of the following options [+,-,*,/,^]
     * @return An ArrayList of valid expressions
     */
    private ArrayList<IExpression> ConstructExpression(ArrayList<IExpression> splitExpression, String symbol) {
        int firstIndex = IndexOf(splitExpression, symbol);
        IExpression right = splitExpression.remove(firstIndex + 1);
        IExpression middle = splitExpression.remove(firstIndex);
        IExpression left = splitExpression.remove(firstIndex - 1);
        IExpression exp = new BinOp(left, middle.toString(), right);
        splitExpression.add(firstIndex - 1, exp);
        return splitExpression;
    }

    /***
     * Returns the index of a symbol located in the root node of one of the elements in an array list
     * @param splitExpression An ArrayList of valid expressions
     * @param symbol A string of one of the following options [+,-,*,/,^]
     * @return An int representing the index or -1 for invalid index
     */
    private int IndexOf(ArrayList<IExpression> splitExpression, String symbol) {
        for (int i = 0; i < splitExpression.size(); i++) {
            if (splitExpression.get(i).toString().equals(symbol)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Check is the symbol is contained in any of the exressions of the Array list
     * @param splitExpression An array list of valid expressions
     * @param symbol A string of one of the following options [+,-,*,/,^]
     * @return A true or false checking if the symbol exists in the root of any of the expressions
     */
    private boolean ContainsSymbol(ArrayList<IExpression> splitExpression, String symbol) {
        for (IExpression exp: splitExpression) {
            if (exp.toString().equals(symbol)){
                return true;
            }
        }
        return false;
    }

    /***
     * Splits the string expression into a list of string were each string is either a number or an operation
     * as a string (does not change the order)
     * @param expression A string of the expression typed by user Ex: "5+5"
     * @return A list of string were each element is one of the numbers or operations in the string
     */
    private List<String> SplitStringIntoListOfExpressions(String expression) {
        // Remove all the spaces
        String exp = expression.replaceAll("\\s", "");

        // Define regular expressions for splitting numbers and operations
        String numberRegex = "(?<=[^\\.\\d])|(?=[^\\.\\d])"; // Split on non-digit characters
        String operationRegex = "[+\\-*/]";          // Split on digits

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
