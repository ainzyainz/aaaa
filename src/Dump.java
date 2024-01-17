package src;

import java.util.concurrent.BlockingQueue;

import static src.Utils.*;

public class Dump implements Runnable {
    private final BlockingQueue<RobotPart> dump;
    private volatile boolean FLAG = false;

    public Dump(BlockingQueue<RobotPart> dump) {
        this.dump = dump;
    }

    public BlockingQueue<RobotPart> getDump() {
        return dump;
    }

    public boolean isFLAG() {
     synchronized (dump){
         return FLAG;
     }
    }

    @Override
    public void run() {
        int start = 0;
        while (start != Utils.COUNT_OF_NIGHT) {
            start++;
            System.out.println(COLOR_CF + "[34mNight " + start + " started" + COLOR_CF + "[0m");
            int countOfRobotParts = RANDOM.nextInt(MAX_NUMBER_OF_THROWS_ROBOT_PART) + 1;
            for (int l = 0; l < countOfRobotParts; l++) {
                dumps();
            }
            try {
                Thread.sleep(DELAY_FOR_THROW_DETAILS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        finishing();
    }

    private void finishing() {
        System.out.println("!!!Program finished!!!");
        FLAG = true;
    }

    public void dumps() {
        dump.add(RobotPart.values()
                [RANDOM.nextInt((RobotPart.values().length))]);
    }
}
