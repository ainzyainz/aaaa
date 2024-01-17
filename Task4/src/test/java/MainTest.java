import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void NotEmpty() {
        assertNotEquals(0, Main.getSize());
    }

    @Test
    void PeopleInFileEqual() throws IOException, ClassNotFoundException {
        File file = new File("testFile.txt");
        List<Person> testPeople = new ArrayList<>();
        testPeople.add(new Person("TestName","TestSurname",99));
        Main.writeObj(testPeople,file);
        List<String> secondTestPeople = new ArrayList<>();
        Main.readObj(file,secondTestPeople,testPeople.size());
        assertEquals(testPeople.size(),secondTestPeople.size());
    }
    @Test
    void NamesInFileEqual() throws IOException, ClassNotFoundException {
        File file = new File("testFile.txt");

        List<Person> testPeople = new ArrayList<>();

        testPeople.add(new Person("TestName","TestSurname",99));

        testPeople.add(new Person("TestName1","TestSurname1",100));


        List<String> namesNsurnames = new ArrayList<>();

        testPeople.stream().forEach(q -> {
            String temp = q.getName()+" "+q.getSurname();
            namesNsurnames.add(temp);
        });


        List<String> secondTestPeople = new ArrayList<>();

        Main.writeObj(testPeople,file);
        Main.readObj(file,secondTestPeople,testPeople.size());

        for (int i = 0; i < testPeople.size(); i++) {
            assertEquals(secondTestPeople.get(i),namesNsurnames.get(i));
        }
    }
}