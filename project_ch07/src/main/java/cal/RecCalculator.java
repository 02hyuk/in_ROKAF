package cal;

public class RecCalculator implements Calculator {
    @Override
    public long factorial(long n) {
        if(n == 0) {
            return 1;
        }

        return n * factorial(n - 1);
    }
}
