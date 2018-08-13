
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player
{
    int strength;
    int maxHealth;
    int health;
    public Player(int multiplyer)
    {
        strength = Utility.random(1, 5) * multiplyer;
        maxHealth = Utility.random(1, 10) * multiplyer;
        health = maxHealth;
    }
}
