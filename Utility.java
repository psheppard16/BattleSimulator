/**
 * Write a description of class Utility here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Utility
{
    /**
     * Generates a random number with a given upper and lower bound
     * @return the random number
     */
    public static int random(int lowerBound, int upperBound)
    {
        int difference = upperBound - lowerBound;
        int randomNumber = ((int)((difference + 1)*Math.random() + lowerBound));  
        return randomNumber;
    }  
}


