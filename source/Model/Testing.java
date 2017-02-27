package Model;

/**
 * Created by xingfanxia on 2/27/17.
 */
public class Testing {
    public static void main(String[] args) {
        CupCakeWarrior newcup1 = new CupCakeWarrior();
        CupCakeWarrior newcup2 = new CupCakeWarrior();

        System.out.println(newcup1.getHP());
        System.out.println(newcup1.hp);
        System.out.print(newcup1.dmgCal(newcup2));
    }
}