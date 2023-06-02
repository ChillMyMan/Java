package Sorting.Sorting.src;

import edu.princeton.cs.algs4.StdOut;

public class Date implements Comparable<Date>{
    private static final int[] DAYS = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private final int day;
    private final int month;
    private final int year;

    public Date(int day, int month, int year){
        if(!isValid(day, month, year))
            throw new IllegalArgumentException("Invalid date");
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public Date(String date){
        String[] fields = date.split("/");
        if(fields.length != 3)
            throw new IllegalArgumentException("Invalid date");
        day = Integer.parseInt(fields[0]);
        month = Integer.parseInt(fields[1]);
        year = Integer.parseInt(fields[2]);
    }

    public int day(){ return day; }
    public int month(){ return month; }
    public int year(){ return year; }

    public static boolean isValid(int d, int m, int y){
        if(m < 1 || m > 12 || d < 1 || d > DAYS[m])
            return false;
        if(m == 2 && d == 29 && !isLeafYear(y))
            return false;
        return true;
    }

    private static boolean isLeafYear(int y){
        if(y % 400 == 0)
            return true;
        if(y % 100 == 0)
            return false;
        return y % 4 == 0;
    }

    public Date next(){
        if(isValid(day + 1, month, year))
            return new Date(day + 1, month, year);
        else if(isValid(1, month + 1, year))
            return new Date(1, month + 1, year);
        else return new Date(1, 1, year + 1);
    }

    public boolean isAfter(Date that){
        return compareTo(that) > 0;
    }

    public boolean isBefore(Date that){
        return compareTo(that) < 0;
    }

    @Override
    public int compareTo(Date that){
        if(this.year < that.year) return -1;
        if(this.year > that.year) return 1;
        if(this.month < that.month) return -1;
        if(this.month > that.month) return 1;
        if(this.day < that.day) return -1;
        if(this.day > that.day) return 1;
        return 0;
    }

    @Override
    public String toString(){
        return day + "/" + month + "/" + year;
    }

    @Override
    public boolean equals(Object other){
        if(other == this)
            return true;
        if(other == null)
            return false;
        if(other.getClass() != this.getClass())
            return false;
        Date that = (Date) other;
        return (this.day == that.day) && (this.month == that.month) && (this.year == that.year);
    }

    @Override
    public int hashCode(){
        int hash = 1;
        hash = 31 * hash + day;
        hash = 31 * hash + month;
        hash = 31 * hash + year;
        return hash;
    }


}
