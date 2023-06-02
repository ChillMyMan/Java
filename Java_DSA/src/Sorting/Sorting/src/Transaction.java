package Sorting.Sorting.src;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;

public class Transaction implements Comparable<Transaction>{
    private final String who;
    private final Date when;
    private final double amount;

    public Transaction(String who, Date when, double amount){
        if(Double.isNaN(amount) || Double.isFinite(amount))
            throw new IllegalArgumentException("Amount cannot be NaN or infinite");
        this.who = who;
        this.when = when;
        this.amount = amount;
    }

    public Transaction(String transaction){
        String[] a = transaction.split("\\s+");
        who = a[0];
        when = new Date(a[1]);
        amount = Double.parseDouble(a[2]);
        if(Double.isNaN(amount) || Double.isFinite(amount))
            throw new IllegalArgumentException("Amount cannot be NaN or infinite");
    }

    public String who(){ return who; }
    public Date when(){ return when; }
    public double amount(){ return amount; }

    @Override
    public String toString(){
        return String.format("%-10s %10s %8.2f", who, when, amount);
    }

    public int compareTo(Transaction that){
        return Double.compare(this.amount, that.amount);
    }

    @Override
    public boolean equals(Object other){
        if(other == this)
            return true;
        if(other == null || other.getClass() != this.getClass())
            return false;
        Transaction that = (Transaction) other;
        return (this.amount == that.amount) && (this.who.equals(that.who));
    }

    public int hashCode(){
        int hash = 1;
        hash = 31 * hash + who.hashCode();
        hash = 31 * hash + when.hashCode();
        hash = 31 * hash + ((Double)amount).hashCode();
        return hash;
    }

    public static class WhoOrder implements Comparator<Transaction>{
        @Override
        public int compare(Transaction v, Transaction w){
            return v.who.compareTo(w.who);
        }
    }

    public static class WhenOrder implements Comparator<Transaction>{
        @Override
        public int compare(Transaction v, Transaction w){
            return v.when.compareTo(w.when);
        }
    }

    public static class HowMuchOrder implements Comparator<Transaction>{
        @Override
        public int compare(Transaction v, Transaction w){
            return Double.compare(v.amount, w.amount);
        }
    }

}
