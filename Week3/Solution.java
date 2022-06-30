import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Comparator;
import java.util.PriorityQueue;

//Ogrenciler icin olusturulan sinif
class Student {
    private int id;
    private String fname;
    private double cgpa;

    public Student(int id, String fname, double cgpa) {
        this.id = id;
        this.fname = fname;
        this.cgpa = cgpa;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return fname;
    }

    public double getCgpa() {
        return cgpa;
    }
}

//Enter ve served islemlerinin yapilacaği sinif
class Priorities {
    public List <Student> getStudents(List <String> events) {

        PriorityQueue <Student> studentQueue = new PriorityQueue(Comparator.comparing(Student::getCgpa).reversed().thenComparing(Student::getName).thenComparing(Student::getId));

        List <Student> sentStudents = new ArrayList <Student>();

        String[] part;
        String name;
        int id;
        double cgpa;

        //Gelen event'leri tek tek geziyoruz ve her biri icin istenen islemleri   gerceklestiriyoruz.
        for (String e: events) {
            //Bu kisimda gelen bir event'i partlara gore parcalara ayiriyoruz. Bunlar Part olarak kullanılmak uzere ataniyor.
            part = e.split(" ");

            //Gelen event "ENTER" ise student kuyruga ekleme islemi gerceklestiriliyor. Gelen event "SERVED" ise student kuyruktan cikariliyor.
            if (part[0].equals("ENTER")) {

                name = part[1];
                cgpa = Double.parseDouble(part[2]);
                id = Integer.parseInt(part[3]);

                studentQueue.add(new Student(id, name, cgpa));

            } else if (part[0].equals("SERVED")) {
                studentQueue.poll();
            }
        }

        //Son olarak kuyrukta olan kullanicilar students listine atilarak metottan donduruluyor.
        while (!studentQueue.isEmpty()) {
            sentStudents.add(studentQueue.poll());
        }

        return sentStudents;

    }
}

public class Solution {
    private final static Scanner scan = new Scanner(System.in);
    private final static Priorities priorities = new Priorities();

    public static void main(String[] args) {
        int totalEvents = Integer.parseInt(scan.nextLine());
        List<String> events = new ArrayList<>();
        //Events kullanicidan aliniyor
        while (totalEvents-- != 0) {
            String event = scan.nextLine();
            events.add(event);
        }
        //Eventleri metoda göndererek islemlerin gerceklestilmesi saglaniyor
        //En son kalan ogrenciler yazdirilmak uzere donduruluyor
        List<Student> students = priorities.getStudents(events);

        if (students.isEmpty()) {
            System.out.println("EMPTY");
        } else {
            for (Student st: students) {
                System.out.println(st.getName());
            }
        }
    }
}
