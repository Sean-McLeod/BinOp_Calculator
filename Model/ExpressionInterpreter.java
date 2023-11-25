package Model;

import Model.Expressions.IExpression;
import Model.Expressions.Value;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExpressionInterpreter {
    public IExpression InterpretExpression(String expression) {
        // "450+0/8" -> [450, +, 0, /, 8]
        return null;
    }

    private ArrayList<?> ConvertToNestedList(List<String> brokenExpression) {
        ArrayList<Value> nestedList = new ArrayList<>();

        for (String element: brokenExpression) {
            if (isNotDouble(element)) {

            }
            else {
                nestedList.add(new Value(Double.parseDouble(element)));
            }
        }
        return null;
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

    public List<String> SplitStringIntoListOfExpressions(String expression) {
        // Define regular expressions for splitting numbers and operations
        String numberRegex = "(?<=[^\\.\\d])|(?=[^\\.\\d])"; // Split on non-digit characters
        String operationRegex = "(?<=\\d)|(?=\\d)";           // Split on digits

        // Combine the regular expressions
        String regex = String.format("(%s)|(%s)", numberRegex, operationRegex);

        // Split the expression using the regex
        String[] elementsArray = expression.split(regex);

        // Convert the array to a list
        List<String> listOfStringsToConvertToListExpressions = Arrays.asList(elementsArray);

        // Remove any empty strings from the list
        listOfStringsToConvertToListExpressions.removeIf(String::isEmpty);

        return listOfStringsToConvertToListExpressions;
    }
}
