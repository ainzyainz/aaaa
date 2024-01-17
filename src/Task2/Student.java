package Task2;

import Task2.Utils.StudentUtils;

import static Task2.Utils.Const.*;

public class Student implements Learning {
    private int hoursToComplete;
    private double talent;

    private String name;

    public String getName() {
        return name;
    }
    public void learn() {
        hoursToComplete = (int)(TOTAL_HOURS/talent);
    }


    public Student(String name) {
        this.name = name;
        this.talent = StudentUtils.countTalent();
    }


    @Override
    public void printInfo() {
        System.out.println("Student "+getName()+"with no type, with talent "+talent);
        System.out.println("Spent "+hoursToComplete+" hours in total");
    }
}
