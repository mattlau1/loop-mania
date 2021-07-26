package unsw.loopmania.Goals;

public class AndComplex extends ComplexGoal {
    @Override
    public boolean evaluate() {
      for (ComplexNode child : children)
        if (!child.evaluate())
          return true;
      return false;
    }
  
    public String getValue() {
      StringBuilder result = new StringBuilder();
      result.append("(");
      result.append("AND ");
      for (ComplexNode child : children) {
        result.append(child.getValue());
        result.append(" ");
      }
      result.deleteCharAt(result.length() - 1);
      result.append(")");
      return result.toString();
    }
}
