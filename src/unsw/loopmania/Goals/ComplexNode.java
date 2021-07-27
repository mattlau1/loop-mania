package unsw.loopmania.Goals;

import unsw.loopmania.Character;

public interface ComplexNode {
    public ComplexNode add(ComplexNode e);

    public String getValue();

    public boolean evaluate(Character character);
}
