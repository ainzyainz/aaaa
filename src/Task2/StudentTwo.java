package Task2;

import Task2.Utils.StudentUtils;

import static Task2.Utils.Const.*;

public class StudentTwo extends Student implements Learning {
    private final int type = SECOND;
    private final double talent;
    private int hoursToComplete;
    private int hoursToPractice;
    private int hoursToLearnTheory;

    public StudentTwo(String name) {
        super(name);
        this.talent = StudentUtils.countTalent();
    }

    @Override
    public void learn() {
        hoursToComplete = (int)(TOTAL_HOURS/talent)*2;
        hoursToLearnTheory = hoursToComplete/2;
        hoursToPractice = hoursToComplete/2;
    }

    @Override
    public void printInfo() {
        System.out.println("--------------------------------");
        System.out.println("Student "+super.getName()+" of type "+type+" with talent "+talent);
        System.out.println("Spent "+hoursToComplete+" hours in total, out of which:");
        System.out.println(hoursToPractice+ " practicing");
        System.out.println(hoursToLearnTheory+ " learning theory");
    }
}
