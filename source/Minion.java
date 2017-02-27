/**
 * Created by xingfanxia on 2/25/17.
 */
public interface Minion {
    /*
    accessors methods
     */

    //Current Hp of the minion
    float getHP();

    //Max Hp of the minion
    float getMaxHp();

    //Get attack damage of the minion
    float getATK();

    /*
    Type of attack, among
      - Normal
      - Pierce
      - Seige
      - Chaos
      - Magic
      - Hero
    */
    AtkType getAttackType();

    //Get armor of the minion
    float getArmor();

    /*
    Types of Armor, among
      - No armor
      - Light Armor
      - Medium Armor
      - Heavy Armor
      - Fort Armor
      - Hero Armor
     */
    ArmorType getArmorType();

    //Get attack speed of the minion, measured in seconds; e.g. 1 second per attack or 1.8 second per attack
    float getAS();

    //0 for melee, 1 for ranged
    int range_melee();

    //movement sppeed of the minion
    float move_speed();

    //get health regen stats, usually 0
    float health_regen();


    /*
    Set Methods
     */

    //set static animation or image?
    void setStaticAnimation();

    //set attack animation
    void setAtkAnimation();

    //set projectileAnimation Only for Ranged
    void setProjectile();

    //speicial abilities like auras, etc
    void setSpecial();

}
