/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author benhur
 */
public class DoubleGenerator {
    public static double getDoubleNumber( double min, double max ,int ct)
{
  double diff = max - min;
  double ans = (min + Math.random( ) * diff);
  return round(ans,ct);
}
    
    public static double round(double value, int places) {
    if (places < 0) throw new IllegalArgumentException();

    long factor = (long) Math.pow(10, places);
    value = value * factor;
    long tmp = Math.round(value);
    return (double) tmp / factor;
}
}
