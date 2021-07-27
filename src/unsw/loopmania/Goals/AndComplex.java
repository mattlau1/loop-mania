package unsw.loopmania.Goals;

import unsw.loopmania.Character;

public class AndComplex extends ComplexGoal {
    @Override
    public boolean evaluate(Character character) {
      for (ComplexNode child : children)
        if (!child.evaluate(character))
          return false;
      return true;
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
