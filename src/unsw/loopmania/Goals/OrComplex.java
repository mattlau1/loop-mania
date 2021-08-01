package unsw.loopmania.Goals;

public class OrComplex extends ComplexGoal {
  @Override
  public boolean evaluate() {
    for (ComplexNode child : children)
      if (child.evaluate())
        return true;
    return false;
  }

  @Override
  public void updateValue(int quantitiy, String goalType) {
    for (ComplexNode child : children)
      child.updateValue(quantitiy, goalType);
  }

  @Override
  public String getValue() {
    StringBuilder result = new StringBuilder();
    result.append("(");
    for (ComplexNode child : children) {
      result.append(child.getValue());
      result.append(" ");
      result.append("OR ");
    }
    for (int i = 1; i <= 4; i++) {
      result.deleteCharAt(result.length() - 1);
    }
    result.append(")");
    return result.toString();
  }
}
