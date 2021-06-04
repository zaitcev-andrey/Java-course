/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package watches;

import alarm_clock.Alarm;

/**
 *
 * @author megaa
 */
public class WatchHMS extends WatchHM implements WatchInterface
{
    int seconds;
    
    protected boolean evnt2()
    {
        boolean check = false;
        for(TimeEvent evnt : event_list)
        {
            check = evnt.event2(this);
            if(check == true)
                return true;
        }
        return false;
    }
    
    public WatchHMS(String brand, int price, int hours, int minutes, int seconds_)
    {
        super(brand, price, hours, minutes);
        seconds = seconds_;
    }
    
    @Override
    public void print()
    {
        super.print();
        
        System.out.println("Seconds: " + seconds);
    }
    
    @Override
    public void set_second(int seconds_)
    {
        seconds = seconds_;
        evnt();
    }
    @Override
    public void set_time(int hours_, int minutes_, int seconds_)
    {
        hours = hours_;
        minutes = minutes_;
        seconds = seconds_;
        evnt();
    }
    @Override
    public void move_time(int hours_, int minutes_, int seconds_)
    {       
        int add_minutes = (seconds + seconds_) / 60;
        int add_hours = (minutes + minutes_) / 60;
        hours = (hours + hours_ + add_hours) % 12;
        minutes = (minutes + minutes_ + add_minutes) % 60;
        seconds = (seconds + seconds_) % 60;
        evnt();
    }
    public boolean move_time_without_panel(int hours_, int minutes_, int seconds_)
    {       
        int add_minutes = (seconds + seconds_) / 60;
        int add_hours = (minutes + minutes_) / 60;
        hours = (hours + hours_ + add_hours) % 12;
        minutes = (minutes + minutes_ + add_minutes) % 60;
        seconds = (seconds + seconds_) % 60;
        return evnt2();
    }
    @Override
    public void move_second(int seconds_)
    {
        int add_minutes = (seconds + seconds_) / 60;
        int add_hours = (minutes + add_minutes) / 60;
        hours = (hours + add_hours) % 12;
        minutes = (minutes + add_minutes) % 60;
        seconds = (seconds + seconds_) % 60;
        evnt();
    }
    
    @Override
    public int get_second()
    {
        return seconds;
    }
    
    @Override
    public void print_time()
    {
        super.print_time();
        System.out.println("Seconds: " + seconds);
    }
    
    @Override
    public String toString()
    {
        return super.toString() + "^" + seconds;
    }
    
    public String get_time() // в виде 0:00:00
    {
        String hour_tmp;
        String min_tmp;
        String sec_tmp;
        if(hours / 10 == 0)
            hour_tmp = "0" + Integer.toString(hours);
        else
            hour_tmp = Integer.toString(hours);
        
        if(minutes / 10 == 0)
            min_tmp = "0" + Integer.toString(minutes);
        else
            min_tmp = Integer.toString(minutes);
        
        if(seconds / 10 == 0)
            sec_tmp = "0" + Integer.toString(seconds);
        else
            sec_tmp = Integer.toString(seconds);
        String result = hour_tmp + ":" + min_tmp + ":" + sec_tmp;
        return result;
    }
    
    public String get_alarms()
    {
        String result = "";
        for(int i = 0; i < event_list.size(); i++)
        {
            Alarm al = (Alarm) event_list.get(i);
            result += al.get_time_alarm() + "\n";
        }
        return result;
    }
}
