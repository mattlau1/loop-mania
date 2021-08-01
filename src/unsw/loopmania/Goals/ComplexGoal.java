package unsw.loopmania.Goals;

import java.util.ArrayList;
import java.util.List;

public abstract class ComplexGoal implements ComplexNode {
    public List<ComplexNode> children;

    /**
     * Constructor for complex goal
     */
    public ComplexGoal() {
        this.children = new ArrayList<ComplexNode>();
    }

    /**
     * Checks if complex goal has child nodes
     *
     * @return true if complex goal has children
     */
    public boolean hasChildren() {
        return !children.isEmpty();
    }

    /**
     * Adds a goal to complex node
     *
     * @return complex goal
     */
    public ComplexNode add(ComplexNode e) {
        children.add(e);
        return this;
    }

    /**
     * Gets children of complex goal
     *
     * @return complex goal's children
     */
    public List<ComplexNode> getChildren() {
        return this.children;
    }
}
