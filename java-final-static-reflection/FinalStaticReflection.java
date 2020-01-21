
import java.util.*;
import java.lang.reflect.*;

class FinalStaticReflection {
    final static boolean keepGoing = true;

    public static void main(String[] args) {
        try {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    try {
                        Field field = FinalStaticReflection.class.getDeclaredField("keepGoing");
                        field.setAccessible(true);

                        Field modifiersField = Field.class.getDeclaredField("modifiers");
                        modifiersField.setAccessible(true);
                        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

                        field.setBoolean(null, false);

                        System.out.print("Set keepGoing to false\n");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, 1500);

            int sum = 0;
            long start = System.currentTimeMillis(), current = start, expectedTime = 5 * 1000;
            for (int i = 0; current - start < expectedTime; i++, current = System.currentTimeMillis()) {
                sum += 1;
                if (i % 1000000 == 0) {
                    System.out.print("sum = " + sum + "\n");
                }
                if (!FinalStaticReflection.keepGoing) {
                    System.out.print("keepGoing = false, stop!\n");
                }
            }

            timer.cancel();

            if (current - start >= expectedTime) {
                System.out.print("Failed to react to the change of value of keepGoing\n");
                System.out.print("   keepGoing (direct)     = " + FinalStaticReflection.keepGoing + "\n");
                System.out.print("   keepGoing (reflection) = " + FinalStaticReflection.class.getDeclaredField("keepGoing").getBoolean(null) + "\n");
                System.exit(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}