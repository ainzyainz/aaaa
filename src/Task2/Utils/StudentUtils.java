package Task2.Utils;

import Task2.Student;
import Task2.StudentOne;
import Task2.StudentThree;
import Task2.StudentTwo;

import java.util.List;
import java.util.stream.Stream;

import static Task2.Utils.Const.*;

public class StudentUtils {

    public static double countTalent(){
        int temp = RANDOM.nextInt(10);
        return temp*0.1;
    }

    public static String[] names = {"Jone","Mike","Hannah","Danny","Liz","Artem","Johnny","Roma","Dima","Miki"};

    public static String genName(){
        return names[RANDOM.nextInt(names.length)];
    }
    public static void genStudent(List<Student> school, int x) {
        switch (x) {
            case 1:
                Stream.generate(() -> new StudentOne(StudentUtils.genName())).limit(TOTAL_STUDENTS / TOTAL_TYPES).forEach(school::add);
                break;
            case 2:
                Stream.generate(() -> new StudentTwo(StudentUtils.genName())).limit(TOTAL_STUDENTS / TOTAL_TYPES).forEach(school::add);
                break;
            case 3:
                Stream.generate(() -> new StudentThree(StudentUtils.genName())).limit(TOTAL_STUDENTS / TOTAL_TYPES).forEach(school::add);
                break;
        }
    }

}
