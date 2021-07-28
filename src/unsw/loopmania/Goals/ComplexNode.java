package unsw.loopmania.Goals;

public interface ComplexNode {
    public ComplexNode add(ComplexNode e);

    public String getValue();

    public void updateValue(int quantitiy, String goalType);

    public boolean evaluate();
}
