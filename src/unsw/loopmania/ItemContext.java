package unsw.loopmania;

public class ItemContext {
    private ItemStrategy itemStrategy;

    public ItemContext(ItemStrategy itemStrategy){
       this.itemStrategy = itemStrategy;
    }

    public double executeAtkMultiplier(BasicEnemy enemy){
       return itemStrategy.atkMuliplier(enemy);
    }
}
