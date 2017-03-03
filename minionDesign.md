## Basic Minions:

### Minion Attributes:

- To implement special damage abilities or something else:  
  Overrides whatever method in MinionImpl should do the trick.
  Like a radiance effect could be achieved by adding a new function called `Radiance(double dmg, double radius)` which damages all enemy minions that are in the radius. 

  ```java
  public void Radiance(double dmg, double radius, MinionImpl) {
    for (each:enemies) {
      if (cal_distance(each) < radius) {
        each.hp -= dmg;
      }
    }
  }
  ```

  A `CritStrike(double chance, double critMultiplier) ` could be added in the `dmgCal` to modify the attack damage.

- Hit Points

- Movement Speed

- Attack Speed?

  I am thinking to implement attack speed in this manner:

  - The minions have its attack speed as `?` second per attack

  - Built in minions or somewhere else have a attackFlag;

  - The controller checks every 0.1 second to check thru all the unit whether any of them should attack, if attacked;

    ```java
    boolean attackFlag = False; //init in every minion
    float attackCounter = 0;
    //and a attack
    public void attack() {
      if (this.attackCounter == this.atkspeed){
        Minion target = this.chooseTarget();
        target.takeDmg(this.dmgCal(target));
      }
    }

    //this function called per 0.1 sec
    public void checkattack(ArrayList<Minion> MinionList) {
      for (Minion warriors: MinionList){
        warriors.attackConter += 0.1;
      }
    }
    ```

- TargetChoosing?

  So have to add world coordinates into minion class?

  And range minion and melee minion have different targeting, so two minion class of range/melee?

  ```java
  //Inside the map, the map should keep the minion list of all players like 
  ArrayList<Minion> player1Army;
  ArrayList<Minion> player2Army;

  and inside Minion, there will be a target system:
  public float cal_distance();

  public Minion TargetChoosing(ArrayList<Minion> MinionList) {
    float minDist = 9999999;
    for (Minion warrior: MinionList){
      float dist = cal_distance(this, warrior);
      if ( dist< minDist) {
        minDist = dist;
        target = warrior;
      }
    }
  }
  ```

  ​

- Types:

  - Melee
  - Ranged
  - Casters

- Weapon Type:

  - Normal
  - Pierce
  - Seige
  - Chaos
  - Magic
  - Hero

- Weapon Special Effect:

  - Cleave/Splash (AOE damage)

  - Mutli-Shot:

    Can Hit multiple targets

  - Penetrating?

- Armor Type:

  - No armor
  - Light Armor
  - Medium Armor
  - Heavy Armor
  - Fort Armor
  - Hero Armor

- Damage Chart:

  |            | Light Armor | Medium Armor | Heavy Armor | Fort Armor | Hero Armor | No Armor |
  | ---------- | ----------- | ------------ | ----------- | ---------- | ---------- | -------- |
  | **Normal** | 80%         | 130%         | 90%         | 90%        | 85%        | 100%     |
  | **Pierce** | 130%        | 100%         | 80%         | 80%        | 85%        | 100%     |
  | **Seige**  | 80%         | 90%          | 90%         | 130%       | 85%        | 100%     |
  | **Magic**  | 110%        | 80%          | 130%        | 70%        | 85%        | 100%     |
  | **Chaos**  | 110%        | 110%         | 110%        | 110%       | 110%       | 110%     |
  | **Hero**   | 100%        | 100%         | 100%        | 100%       | 100%       | 100%     |

###  Minion Category

- Melee
  - Ursa (Single Tank)
    - High HP & less damage tank
    - Normal Weapon
    - Heavy Armor
    - Atk speed increase by 10% per attack. (Max 100% atk speed)
  - Hounds (MultiTank)
    - Spawn as 3
    - Low Hp & High Dmg
    - Normal Weapon
    - Heavy Armor
    - Attack has chance (25%) to cause bleed: -3hp/sec, max 3 stacks.
  - Capital
    - Summon 3 lesser soldiers
    - Low Dmg, high hp & armor
    - Aura effect: soldiers gain +3 atk and 2 armor.
    - Normal Weapon
    - Heavy Armor
- Range
  - ​