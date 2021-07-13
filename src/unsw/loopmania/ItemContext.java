package unsw.loopmania;

public class ItemContext {
    private ItemStrategy itemStrategy;

    public ItemContext(ItemStrategy itemStrategy){
       this.itemStrategy = itemStrategy;
    }

    public int executeStrategy(int num1, int num2){
       return itemStrategy.attackMuliplier();
    }
}
