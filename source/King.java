/**
 * Created by xingfanxia on 2/25/17.
 */

public class King {

    private float hp;

    private float armor;

    private float atk;

    private AtkType atkType = AtkType.Hero;

    private ArmorType armorType = ArmorType.HeroArmor;

    /*
    Accessors
     */
    public float getHp() {
        return hp;
    }

    public float getAtk() {
        return atk;
    }

    public AtkType getAtkType() {
        return atkType;
    }

    public ArmorType getArmorType() {
        return armorType;
    }

    /*
    Setters
     */

    public void setHp(float hp) {
        this.hp = hp;
    }

    public void setArmor(float armor) {
        this.armor = armor;
    }

    public void setAtk(float atk) {
        this.atk = atk;
    }

}
