
import java.util.Random;

class RandomSum
{
    final static Random rand = new Random();

    public static void main(String args[])
    {
        int sum = 0;
        for (long start = System.currentTimeMillis(); System.currentTimeMillis() < start + 10 * 1000;)
        {
            sum += next();
        }
        System.out.println("sum = " + sum);
    }

    static int next()
    {
        return rand.nextInt(2);
    }
}
