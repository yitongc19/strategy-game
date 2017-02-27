/**
 * Created by xingfanxia on 2/25/17.
 */
public class CupCakeWorrior implements Minion {

    private float hp;
    private float armor;
    private float atk;
    private float maxhp;
    private ArmorType armorType;
    private AtkType atkType;
    private float moveSpeed;
    private float healthRegen;
    private float attackSpeed;
    private int rangeOrMelee;

    @Override
    public float getHP() {
        return hp;
    }

    @Override
    public float getMaxHp() {
        return maxhp;
    }

    @Override
    public float getATK() {
        return atk;
    }

    @Override
    public AtkType getAttackType() {
        return atkType;
    }

    @Override
    public float getArmor() {
        return armor;
    }

    @Override
    public ArmorType getArmorType() {
        return armorType;
    }

    @Override
    public float getAS() {
        return attackSpeed;
    }

    @Override
    public int range_melee() {
        return rangeOrMelee;
    }

    @Override
    public float move_speed() {
        return moveSpeed;
    }

    @Override
    public float health_regen() {
        return healthRegen;
    }

    @Override
    public void setStaticAnimation() {

    }

    @Override
    public void setAtkAnimation() {

    }

    @Override
    public void setProjectile() {

    }

    @Override
    public void setSpecial() {

    }

}
