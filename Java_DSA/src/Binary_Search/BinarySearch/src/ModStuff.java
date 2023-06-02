package Binary_Search.BinarySearch.src;

import java.math.BigInteger;

public class ModStuff {
    final static BigInteger ZERO = new BigInteger("0");
    final static BigInteger ONE = new BigInteger("1");

    public static void main(String[] args) {
        BigInteger n = new BigInteger("225");
        BigInteger b = new BigInteger("119");
        BigInteger bInv = modInv(b, n);

        if (bInv != null) {
            System.out.println("The inverse is " + bInv);
            System.out.println("Java gets " + b.modInverse(n));
        } else
            System.out.println("No inverse");
    }
    
    public static BigInteger modInv(BigInteger value, BigInteger n) {
        BigInteger b0 = value;
        BigInteger n0 = n;
        BigInteger t0 = ZERO;
        BigInteger t = ONE;
        BigInteger q = n.divide(b0);
        BigInteger r = n0.subtract(q.multiply(b0));

        while (r.compareTo(ZERO) > 0) {
            BigInteger tmp = t0.subtract(q.multiply(t));

            if (tmp.compareTo(ZERO) > 0)
                tmp = tmp.mod(n);
            if (tmp.compareTo(ZERO) < 0)
                tmp = tmp.subtract(tmp.negate().mod(n));

            t0 = t;
            t = tmp;
            n0 = b0;
            b0 = r;
            q = n0.divide(b0);
            r = n0.subtract(q.multiply(b0));
        }
        
        System.out.println("GCD is " + b0);

        if(!b0.equals(ONE))
            return null;
        else
            return t.mod(n);
    }
}
