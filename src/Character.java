/**
 * Created by stevenburris on 9/20/16.
 */
public class Character {
    String name;
    int health;
    int damage;

    public void battle(Character enemy) {
        System.out.printf("A wild %s appears!\n", enemy.name);

        while (this.health > 0 && enemy.health >0) {
            this.health -= enemy.damage;
            enemy.health -= damage;
            System.out.printf("%s's health %s\n", this.name, this.health);
            System.out.printf("%s's health %s\n", enemy.name, enemy.health);
        }

        if (this.health <= 0) {
            System.out.printf("%s has died\n", this.name);
        }

        if (enemy.health <=0 ) {
            System.out.printf("%s has died\n", enemy.name);
        }
    }
}
