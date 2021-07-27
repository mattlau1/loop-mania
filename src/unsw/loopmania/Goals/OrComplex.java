package unsw.loopmania.Goals;

import unsw.loopmania.Character;

public class OrComplex extends ComplexGoal {
    @Override
    public boolean evaluate(Character character) {
      for (ComplexNode child : children)
        if (child.evaluate(character))
          return true;
      return false;
    }
  
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
