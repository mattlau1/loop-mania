package unsw.loopmania.Goals;

import java.util.ArrayList;
import java.util.List;

public abstract class ComplexGoal implements ComplexNode {
    public List<ComplexNode> children;

    public ComplexGoal() {
        this.children = new ArrayList<ComplexNode>();
    }

    public boolean hasChildren() {
    return !children.isEmpty();
    }

    public ComplexNode add(ComplexNode e) {
    children.add(e);
    return this;
    }
}
