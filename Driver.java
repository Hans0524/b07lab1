import java.io.File;
import java.io.FileNotFoundException;

public class Driver {
    public static void main(String[] args) throws FileNotFoundException {
        Polynomial p = new Polynomial();
        System.out.println(p.evaluate(3));
        double[] c1 = {6,5};
        int[] e1 = {0,3};
        Polynomial p1 = new Polynomial(c1, e1);
        double[] c2 = {-2,-9};
        int[] e2 = {1,4};
        Polynomial p2 = new Polynomial(c2, e2);
        double e = p1.evaluate(0.1);
        System.out.println(e);
        Polynomial s = p1.add(p2);
        System.out.println("s(0.1) = " + s.evaluate(0.1));
        if (s.hasRoot(1))
            System.out.println("1 is a root of s");
        else
            System.out.println("1 is not a root of s");
        Polynomial m = p1.multiply(p2);
        System.out.println("m(2) = " + m.evaluate(2));

        //Test saveToFile
        double[] c_stf = {6,5,1};
        int[] e_stf = {0,3,2};
        Polynomial p_stf = new Polynomial(c_stf,e_stf);
        p_stf.saveToFile("Test_savetofile");
        //Test File
        File file = new File("Test_savetofile");
        Polynomial testp1 = new Polynomial(file);
        double teste = testp1.evaluate(2);
        System.out.println("testp1(2)=" + teste);
    }
}