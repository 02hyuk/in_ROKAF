package cal;

public class ImpeCalculator implements Calculator {
    @Override
    public long factorial(long n) {
        long result = 1;
        for(long i = 1; i <= n; i++) {
            result *= i;
        }

        return result;
    }
}
