public class GenerateUtils {
    public static int MIN_AGE = 15;
    public static int MAX_AGE = 30;
    public static String[] names = {"Jone","Mike","Ivan","Pavel","Liz","Artem","Kolya","Roma","Dima","Miki"};
    public static int generateAge(){
        return (int)(Math.random()*MIN_AGE+MIN_AGE);
    }
    public static String generate(){
        return names[(int)(Math.random()* names.length)];
    }


}
