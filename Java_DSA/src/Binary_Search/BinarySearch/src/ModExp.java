package Binary_Search.BinarySearch.src;

import java.math.BigInteger;

public class ModExp {
    public static void main(String[] args) {
        BigInteger b = new BigInteger("65");
        BigInteger e = new BigInteger("2000000");
        BigInteger n = new BigInteger("201");

        long start = System.currentTimeMillis();
        BigInteger ans = modExp(b, e, n);
        long end = System.currentTimeMillis();
        System.out.println("Took " + (end - start) + " ms.");
        BigInteger two = new BigInteger("2");

        start = System.currentTimeMillis();
        BigInteger ans2 = modExpRec(b, e, n);
        end = System.currentTimeMillis();
        System.out.println("Took " + (end - start) + " ms.");

        BigInteger ans3 = b.modPow(e, n);

        System.out.println("rec is " + ans2);
        System.out.println("real is " + ans3);

        if(ans2.equals(ans3))
            System.out.println("Calls agree.");
    }
    
    public static BigInteger modExp(BigInteger base, BigInteger exp, BigInteger n) {
        BigInteger index = new BigInteger("1");
        BigInteger answer = new BigInteger("1");
        while (index.compareTo(exp) <= 0) {
            answer = (answer.multiply(base)).mod(n);
            index = index.add(BigInteger.ONE);
        }
        return answer;
    }

    public static BigInteger modExpRec(BigInteger base, BigInteger exp, BigInteger n) {
        BigInteger zero = new BigInteger("0");
        BigInteger one = new BigInteger("1");
        BigInteger two = one.add(one);

        if(exp.equals(zero))
            return one;
        if(exp.equals(one))
            return base.mod(n);
        
        if (exp.mod(two).equals(zero)) {
            BigInteger ans = modExpRec(base, exp.divide(two), n);
            return (ans.multiply(ans)).mod(n);
        }
        return (base.multiply(modExpRec(base, exp.subtract(one), n))).mod(n);
    }
}
