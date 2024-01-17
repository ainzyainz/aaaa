import java.io.*;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static final List<String> PEOPLE2 = new ArrayList<>();
    public static final File FILE = createdFile();

    public static void main(String[] args) {

        List<Person> people = firstSort();
        try {
            writeObj(people, FILE);
            readObj(FILE, PEOPLE2,people.size());
        } catch (IOException|ClassNotFoundException e) {
            e.printStackTrace();
        }
        printResult(PEOPLE2);

    }

    public static void writeObj(List<Person> people, File file) throws IOException {
        try(FileOutputStream fos = new FileOutputStream(file); ObjectOutputStream oos = new ObjectOutputStream(fos)){
            for(Person temp: people){
                oos.writeObject(temp);
            }
        }
    }
    public static void readObj(File file, List<String> people2, int count) throws IOException, ClassNotFoundException {
        try(FileInputStream fis = new FileInputStream(file); ObjectInputStream ois = new ObjectInputStream(fis)){

            for(int i = 0; i < count; i++){
                Person temp = (Person) ois.readObject();
                String nameNsurname = temp.getName()+" "+temp.getSurname();
                people2.add(nameNsurname);
            }
        }
    }

    public static int getSize(){
        return PEOPLE2.size();
    }
    private static void printResult(List<String> people2) {
        System.out.println("Final list");
        System.out.println(people2);
    }

    private static ArrayList<Person> firstSort() {
        ArrayList<Person> people = (ArrayList<Person>) Stream
                .generate(()-> new Person(GenerateUtils.generate(),GenerateUtils.generate(),GenerateUtils.generateAge()))
                .limit(100)
                .filter(q -> q.getAge()<21)
                .peek(System.out::println)
                .sorted(Comparator.comparing(Person::getSurname).thenComparing(Person::getName))
                .distinct()
                .collect(Collectors.toList());
        return people;
    }


    private static File createdFile() {
        File file = new File("myFile.txt");
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.getStackTrace();
            }
        }
        return file;
    }
}
