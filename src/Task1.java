package src;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static src.Utils.COLOR_CF;


public class Task1 {

    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<RobotPart> dump = new LinkedBlockingQueue<>();
        Dump myDump = new Dump(dump);
        for (int i = 0; i < 20; i++) {
            myDump.dumps();
        }

        Scientist sc1 = new Scientist("First Scientist", myDump, "[32m");
        Scientist sc2 = new Scientist("Second Scientist", myDump, "[36m");

        Thread sc1Thread = new Thread(sc1);
        Thread sc2Thread = new Thread(sc2);
        Thread dumpThread = new Thread(myDump);

        dumpThread.start();
        sc1Thread.start();
        sc2Thread.start();

        sc1Thread.join();
        sc2Thread.join();
        getResults(sc1,sc2);
    }

    public static void getResults(Scientist sc1, Scientist sc2)  {
        if (sc1.getCount() < sc2.getCount()){
            System.out.println(COLOR_CF + "[33m" + sc2.getName() +" won with " + sc2.getCount() + " robots" +
                    (char) 27 + "[0m"
            );
        } else if (sc1.getCount() > sc2.getCount()){
            System.out.println(COLOR_CF + "[33m" + sc1.getName() + " won with " + sc1.getCount() + " robots" +
                            COLOR_CF + "[0m"
                    );

        } else {
            System.out.println(COLOR_CF + "[31mIt's a tie! Nobody won with " + sc1.getCount() + " robots" +
                    COLOR_CF + "[0m"
            );
        }
    }
}
