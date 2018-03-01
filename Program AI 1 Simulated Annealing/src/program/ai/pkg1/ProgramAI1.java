/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program.ai.pkg1;
import static java.lang.Math.exp;
import java.util.Random;
import java.util.Scanner;
/**
 *
 * @author Kukuh Rahingga P
 */
public class ProgramAI1{
    public static void main(String[] args) {        
        Scanner cinDoub = new Scanner(System.in);
        double newStateX1, newStateX2;
        double curEval, newEval, parm;
        double bsf, bsfX1, bsfX2, T;
        
        System.out.print("Masukkan nilai estimasi : "); parm = cinDoub.nextDouble();
        newStateX1 = 10;
        newStateX2 = -10;
        T = 10000;
        bsfX1 = 0;
        bsfX2 = 0;
        bsf = 0;       
        curEval = getNilai(newStateX1, newStateX2);        
        while (T >= 0.1) {           
            newStateX1 = ranGen();
            newStateX2 = ranGen();
            newEval = getNilai(newStateX1, newStateX2);
            if (newEval < curEval) {
                curEval = newEval;
                bsf = newEval;
                bsfX1 = newStateX1;
                bsfX2 = newStateX2;
            } else {
                double deltaEval = Math.pow(exp(1),(-(newEval - curEval)/T));
                double ran = Math.random();
                if ( deltaEval > ran ) {
                    curEval = newEval;
                }
            }
            T = 0.998*T;
        } 
        System.out.println("---- Hasil Simulated Annealing ----");
        System.out.println("x1 : " + bsfX1);
        System.out.println("x2 : " + bsfX2);
        System.out.println("f(x1,x2) = " + bsf);
        System.out.println("Akurasi = %" + cekAKurasi(bsf, parm));     
    }

    private static double getNilai(double x1, double x2) {
        double fungsi = (4-2.1*Math.pow(x1, 2)+Math.pow(x1,4)*Math.pow(x1, 2) + x1*x2 + (-4+4*Math.pow(x2, 2)*Math.pow(x2, 2)));
        return fungsi;
    }
    
    private static double ranGen(){
        Random rand = new Random();
        double number = rand.nextInt(20) + (-10) - rand.nextDouble();
        return  number;
    }
    
    public static double cekAKurasi(double Fa, double Fr) {
        double akurasi = (1 - ((Fa - Fr) / Fr)) * 100;
        return akurasi;
    }
}