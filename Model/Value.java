package Model;

public class Value
{
    private double value;

    public Value(double value) {
        this.value = value;
    }

    public double evaluate() {
        return this.value;
    }

    @Override
    public String toString() {
        return "" + this.value;
    }
}
