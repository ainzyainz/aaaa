package Task2;

import Task2.Utils.StudentUtils;

import static Task2.Utils.Const.*;

public class StudentThree extends Student implements Learning {
    private final int type = THIRD;
    private final double talent;
    private int hoursToComplete;
    private int hoursToPractice;

    public StudentThree(String name) {
        super(name);
        this.talent = StudentUtils.countTalent();
    }

    @Override
    public void learn() {
        hoursToComplete = (int)(TOTAL_HOURS/talent)*2;
        hoursToPractice = hoursToComplete;
    }

    @Override
    public void printInfo() {
        System.out.println("--------------------------------");
        System.out.println("Student "+super.getName()+" of type "+type+" with talent "+talent);
        System.out.println("Spent "+hoursToComplete+" hours in total, out of which:");
        System.out.println(hoursToPractice+ " practicing");
    }
}
