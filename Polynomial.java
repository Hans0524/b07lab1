public class Polynomial{
    double[] coefficient;
    public Polynomial() {
        coefficient = new double[1];
        coefficient[0] = 0;
    }

    public Polynomial(double[] coef){
        coefficient = coef;
    }

    public double evaluate(double x){
        int coefsize = coefficient.length;
        double sum = coefficient[0];
        for (int i = 1; i < coefsize; i++){
            sum += coefficient[i] * Math.pow(x,i);
        }
        return sum;
    }

    public Polynomial add(Polynomial p){
        int asize = p.coefficient.length;
        int bsize = coefficient.length;
        int maxsize = Math.max(asize, bsize);
        double[] combine = new double[maxsize];
        for(int i = 0; i < maxsize; i++){
            if (i < asize){
                combine[i] += p.coefficient[i];
            }
            if (i < bsize){
                combine[i] += coefficient[i];
            }
        }
        Polynomial result = new Polynomial(combine);
        return result;
    }

    public boolean hasRoot(double d){
        boolean result = evaluate(d) == 0;
        return result;

    }

}
