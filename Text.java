import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Stream;

public class Text
{
    static String path_to_read = "C:\\Users\\ttuni\\OneDrive\\Documents\\read\\w.txt";
    static String path_to_write = "C:\\Users\\ttuni\\OneDrive\\Documents\\read\\w.txt";

    static String[] name = {"Nikita", "Sasha", "Polina", "Ivan", "Ksenija", "Anna", "Grigory", "Will", "Julia"};
    static int min_age = 18;
    static int max_age = 100;
    static int min_money = 1000;
    static int max_money = 1000000;

    public static void main(String[] args) throws IOException
    {
        //write(generate(2));
        print(read());
    }

    public static ArrayList<Person> generate(int n)
    {
        Random r = new Random();
        ArrayList<Person> result = new ArrayList<>();

        for(int i = 0; i < n; i++)
        {
            result.add(new Person(name[r.nextInt(name.length)],
                    min_age + r.nextInt(max_age - min_age),
                    min_money + r.nextInt(max_money - min_money)));
        }

        return result;
    }

    public static void print(ArrayList<Person> persons)
    {
        for(Person p : persons)
        {
            System.out.println("PERSON{ NAME: " + p.name + ", AGE: " + p.age + " years, MONEY: " + p.money + " $ }");
        }
    }

    public static void write(ArrayList<Person> persons) throws IOException
    {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path_to_write));

        for(Person p : persons)
        {
            writer.write(p.name + "," + p.age + "," + p.money + "\n");
        }

        writer.flush();
        writer.close();
    }

    public static ArrayList<Person> read() throws IOException
    {
        ArrayList<Person> result = new ArrayList<>();
        try(Stream<String> stream = Files.lines(Paths.get(path_to_read)))
        {
            stream.forEach(e ->
            {
                String[] strings = e.split(",");
                String name = strings[0];
                int age = Integer.parseInt(strings[1]);
                int money = Integer.parseInt(strings[2]);
                result.add(new Person(name, age, money));
            });
        }
        return result;
    }
}
