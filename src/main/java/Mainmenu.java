import java.util.Scanner;

public class Mainmenu {

    public static void main(String[] args) {

        while(true) {
            System.out.println("input number");

            Scanner sc=new Scanner(System.in);
            int in = sc.nextInt();

            switch (in) {
                case 1:
                    Delete d=new Delete();
                    String p=sc.next();
                    d.start(p);
                    break;
                case 2:
                    Copy c=new Copy();
                    String s=sc.next();
                    String de=sc.next();
                    c.start(s,de);
                    break;
            }
            if(in==3) break;
        }
    }
}
