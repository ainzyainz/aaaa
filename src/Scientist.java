package src;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import static src.Utils.*;

public class Scientist implements Runnable {
    private final List<RobotPart> storage = new ArrayList<>();
    private final Dump mydump;
    private final BlockingQueue<RobotPart> dump;
    private final String name;
    private final String message;
    private final RobotMaker robotMaker;


    public List<RobotPart> getStorage() {
        return storage;
    }

    public Scientist(String name, Dump mydump, String message) {
        this.name = name;
        this.mydump = mydump;
        this.message = message;
        this.dump = mydump.getDump();
        this.robotMaker = new RobotMaker(this);
    }

    @Override
    public void run() {
        while (!mydump.isFLAG()) {
            int countOfParts = RANDOM.nextInt(MAX_NUMBER_OF_THROWS_ROBOT_PART) + 1;
            for (int l = 0; l < countOfParts; l++) {
                try {
                    removing();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                robotMaker.checking();
            }
        }
        System.out.println(COLOR_CF + message + name + " " + getCount());
    }

    public void removing() throws InterruptedException {
        RobotPart temp = dump.poll(DELAY_FOR_THROW_DETAILS, TimeUnit.MILLISECONDS);
        if (temp != null&&!mydump.isFLAG()) {
            storage.add(temp);
            System.out.println(name + " got detail " + temp);
            dump.remove(temp);
        }
    }

    public int getCount(){
        return robotMaker.getCountOfRobots();
    }

    public String getMessage() {
        return message;
    }

    public String getName() {
        return name;
    }
}
