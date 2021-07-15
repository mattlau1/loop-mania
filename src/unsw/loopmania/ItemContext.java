package unsw.loopmania;

public class ItemContext {
    private ItemStrategy itemStrategy;

    public ItemContext(ItemStrategy itemStrategy){
       this.itemStrategy = itemStrategy;
    }

    public double executeAtkMultiplier(BasicEnemy enemy){
       return itemStrategy.atkMuliplier(enemy);
    }

    public double executeDefMuliplier(BasicEnemy enemy){
        return itemStrategy.defMuliplier(enemy);
    }

    public double executeCritMuliplier(BasicEnemy enemy){
        return itemStrategy.critMuliplier(enemy);
    }

     public void  executeOnHitEffects(BasicEnemy enemy){
        itemStrategy.onHitEffects(enemy);
    }

}
