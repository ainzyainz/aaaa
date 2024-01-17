package Task2;

import Task2.Utils.StudentUtils;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Student> school = new ArrayList<>();
        StudentUtils.genStudent(school, 1);
        StudentUtils.genStudent(school,2);
        StudentUtils.genStudent(school,3);

        for(Student temp: school){
            temp.learn();
            temp.printInfo();
        }
    }
}
