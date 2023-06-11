import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Polynomial{
    double[] coefficient;
    int[] exponent;
    public Polynomial() {
        coefficient = new double[1];
        coefficient[0] = 0;
        exponent = new int[1];
        exponent[0] = 0;

    }

    public Polynomial(double[] coef, int[] expo){
        coefficient = coef;
        exponent = expo;
    }

    public double evaluate(double x){
        int coefsize = coefficient.length;
        //double sum = coefficient[0];
        double sum = 0;
        for (int i = 0; i < coefsize; i++){
            sum += coefficient[i] * Math.pow(x,exponent[i]);
        }
        return sum;
    }

    public Polynomial add(Polynomial p){
        int asize = p.coefficient.length;
        int bsize = coefficient.length;
        int maxsize = asize + bsize;
        double[] combine = new double[maxsize];
        int[] expcombine = new int[maxsize];
        for(int i = 0; i < maxsize; i++){
            if (i < asize){
                combine[i] += p.coefficient[i];
                expcombine[i] = p.exponent[i];

            }
            if (i < bsize){
                combine[asize+i] = coefficient[i];
                expcombine[asize+i] = exponent[i];
            }
        }
        Polynomial result = new Polynomial(combine,expcombine);
        return result;
    }


    public Polynomial multiply(Polynomial p){
        int asize = p.coefficient.length;
        int bsize = coefficient.length;
        int maxsize = asize + bsize;
        double[] resultcoef = new double[maxsize];
        int[] resultexp = new int[maxsize];
        int r = 0;
        for(int i = 0; i < asize; i++){
            for(int j = 0; j < bsize; j++){
                resultcoef[r] = p.coefficient[i] * coefficient[j];
                resultexp[r] = p.exponent[i] + exponent[j];
                r++;
            }
        }
        int[] finalexp = new int[1];
        double[] finalcoef = new double[1];
        int len = 0;
        for(int i = 0; i < maxsize; i++){
            if(resultcoef[i] != 0.0 && resultexp[i] != 0) {

                len++;
                finalexp = Arrays.copyOf(finalexp, len);
                finalcoef = Arrays.copyOf(finalcoef, len);
                finalcoef[len-1] = resultcoef[i];
                finalexp[len - 1] = resultexp[i];
                for(int j = i + 1; j < maxsize; j++){
                    if (resultexp[i] == resultexp[j]){
                        finalcoef[len - 1] += resultcoef[j];
                        resultcoef[j] = 0;
                        resultexp[j] = 0;

                    }
                }
            }
        }
        Polynomial result = new Polynomial(finalcoef, finalexp);
        /*System.out.println( Arrays.toString(finalcoef));
        System.out.println( Arrays.toString(finalexp));
        System.out.println( Arrays.toString(resultcoef));
        System.out.println( Arrays.toString(resultexp));

         */
        return result;
    }

    public Polynomial(File f) throws FileNotFoundException {

        Scanner input = new Scanner(f);
        String poly = input.nextLine();
        String[] parameters = poly.split("(?=[+-])"); //if meet + or -, it will split the term and keep +\-

        int len = parameters.length;
        double[] coef = new double[len];
        int[] expo = new int[len];
        for(int i = 0; i < len; i++){
            String p = parameters[i];
            int coefsign = 1;
            if(p.startsWith("-")){
                coefsign = -1;
            }
            String pnosign = p.replaceFirst("[+-]","");
            String[] s = pnosign.split("x");
            coef[i] = Double.parseDouble(s[0])*coefsign;
            if(s.length > 1){
                expo[i] =  Integer.parseInt(s[1]);
            }
            else{
                expo[i] = 0;
            }
        }
        coefficient = coef;
        exponent = expo;
    }

    public void saveToFile(String filename){
        FileWriter filewriter = null;
        try {
            filewriter = new FileWriter(filename);
            String poly = "";
            for(int i = 0; i < coefficient.length; i++){
                if(i > 0 && coefficient[i] > 0){
                    poly += "+";
                }
                poly += coefficient[i];
                if(exponent[i] > 0){
                    poly += "x";
                    poly +=exponent[i];
                }

            }
            filewriter.write(poly);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                filewriter.close();
            } catch (Exception e) {
                e.printStackTrace();;
            }
        }
    }


    public boolean hasRoot(double d){
        boolean result = evaluate(d) == 0;
        return result;

    }

}

