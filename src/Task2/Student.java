package Task2;

import Task2.Utils.StudentUtils;

import static Task2.Utils.Const.*;

public class Student{
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
}
