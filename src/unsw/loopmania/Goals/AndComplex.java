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
      for (ComplexNode child : children) {
        result.append(child.getValue());
        result.append(" ");
        result.append("AND ");
      }
      for (int i = 1; i <= 5; i++) {
        result.deleteCharAt(result.length() - 1);
      }
      result.append(")");
      return result.toString();
    }
}
