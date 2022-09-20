package constraints;

public class NoConstraint implements Constraint{
    @Override
    public boolean checkValue(double value) {
        return true;
    }
}
