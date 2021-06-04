/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.timer.watchmynewversion;

import alarm_clock.Alarm;
import java.util.Scanner;
import watches.NegativeValuesException;
import watches.Watch;
import watches.WatchHM;
import watches.WatchHMS;

/**
 *
 * @author megaa
 */
public class Main
{
    public static void main(String[] args)
    {
        Watch watch = new Watch("Casio", 20500);
        watch.print();

        System.out.println("");

        WatchHM watch1 = new WatchHM("Timex", 7990, 5, 35);
        watch1.print();

        System.out.println("");

        WatchHMS watch2 = new WatchHMS("Festina", 11200, 7, 42, 56);
        watch2.print();

        System.out.println("\nTime tests\n");

        watch1.print_time();
        watch1.set_time(8, 23);
        watch1.print_time();
        watch1.move_time(5, 50);
        watch1.print_time();

        System.out.println("");
        watch2.print_time();
        watch2.move_time(6, 20);
        watch2.print_time();

        try (Scanner in = new Scanner(System.in)) 
        {
            System.out.println("Move your time!\nInput hours: ");
            int move_h = in.nextInt();
            System.out.println("Input minutes: ");
            int move_m = in.nextInt();
            System.out.println("Input seconds: ");
            int move_s = in.nextInt();
            watch2.move_time(move_h, move_m, move_s);
            watch2.print_time();
        }

        System.out.println("\nTests for alarms\n");

        watch2.addEvent(new Alarm(5,15,0));
        watch2.addEvent(new Alarm(8,20,0));
        watch2.addEvent(new Alarm(2,2,0));
        //watch2.addEvent((timer)->{System.out.println(timer);}); // будет выводить время часов каждый раз при его изменении
        
        watch2.print_time();
        watch2.set_time(5, 15, 25);
        watch2.print_time();
                
        watch2.move_time(3, 5);
        watch2.print_time();
        watch2.move_time(0, 0, 5);
        watch2.print_time();
    }
}
