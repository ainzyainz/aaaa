package src;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static src.Utils.COLOR_CF;
import static src.Utils.COUNT_OF_ROBOT_PART;

public class RobotMaker {

    private List<RobotPart> storage;
    private Set<RobotPart> tempRobot = new HashSet();
    private Scientist scientist;
    private int countOfRobots = 0;

    public int getCountOfRobots() {
        return countOfRobots;
    }

    public RobotMaker(Scientist scientist) {
        this.scientist = scientist;
        this.storage = scientist.getStorage();
    }

    public void checking() {
        for (int i = 0; i < storage.size() - 1; i++) {
            if (!tempRobot.contains(storage.get(i))) {
                tempRobot.add(storage.remove(i));
            }
            if (tempRobot.size() == COUNT_OF_ROBOT_PART) {
                countOfRobots++;
                System.out.println(COLOR_CF
                        + scientist.getMessage() + scientist.getName() + " created " +
                        countOfRobots + " robots" + COLOR_CF + "[0m"
                );
                tempRobot.clear();
            }
        }
    }
}
