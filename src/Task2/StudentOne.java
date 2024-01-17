package Task2;

import Task2.Utils.StudentUtils;

import static Task2.Utils.Const.FIRST;
import static Task2.Utils.Const.TOTAL_HOURS;

public class StudentOne extends Student implements Learning {
    private final int type = FIRST;
    private int hoursToComplete;
    private int hoursToPractice;
    private int hoursToLearnTheory;
    private int hoursInFlow;
    private double talent;

    public StudentOne(String name) {
        super(name);
        this.talent = StudentUtils.countTalent();
    }

    @Override
    public void learn() {
        hoursToComplete = (int)(TOTAL_HOURS/talent);
        hoursToLearnTheory = hoursToComplete/3;
        hoursToPractice = hoursToComplete/3;
        hoursInFlow = hoursToComplete/3;
    }

    @Override
    public void printInfo() {
        System.out.println("--------------------------------");
        System.out.println("Student "+super.getName()+" of type "+type+" with talent "+talent);
        System.out.println("Spent "+hoursToComplete+" hours in total, out of which:");
        System.out.println(hoursToPractice+ " practicing");
        System.out.println(hoursToLearnTheory+ " learning theory");
        System.out.println(hoursInFlow+ " in flow");
    }
}
