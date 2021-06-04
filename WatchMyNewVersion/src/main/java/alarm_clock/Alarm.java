/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alarm_clock;

import watches.PanelAlarm;
import watches.TimeEvent;
import watches.WatchInterface;

/**
 *
 * @author megaa
 */
public class Alarm implements TimeEvent
{
    int h, m, s;
    
    public Alarm(int h, int m, int s)
    {
        this.h = h;
        this.m = m;
        this.s = s;
    }
    
    public void setHour(int h)
    {
        this.h = h;
    }
    
    public void setMin(int m)
    {
        this.m = m;
    }
    
    public void setSec(int s)
    {
        this.s = s;
    }
    
    public int getHour()
    {
        return this.h;
    }
    
    public int getMin()
    {
        return this.m;
    }
    
    public int getSec()
    {
        return this.s;
    }
    
    public String get_time_alarm()
    {
        String hour_tmp;
        String min_tmp;
        String sec_tmp;
        if(h / 10 == 0)
            hour_tmp = "0" + Integer.toString(h);
        else
            hour_tmp = Integer.toString(h);
        
        if(m / 10 == 0)
            min_tmp = "0" + Integer.toString(m);
        else
            min_tmp = Integer.toString(m);
        
        if(s / 10 == 0)
            sec_tmp = "0" + Integer.toString(s);
        else
            sec_tmp = Integer.toString(s);
        String result = hour_tmp + ":" + min_tmp + ":" + sec_tmp;
        return result;
    }
    
    @Override
    public void event(WatchInterface t)
    {
        if(t.get_hour() == h)
            if(t.get_minute() == m)
                if(t.get_second() == s)
                {
                    /*if(t.getMessage() == "")
                        t.setMessage("The alarm go off!!!");*/
                    PanelAlarm a = new PanelAlarm();
                    //System.out.println("The alarm go off!");
                }
    }
    
    @Override
    public boolean event2(WatchInterface t)
    {
        if(t.get_hour() == h)
            if(t.get_minute() == m)
                if(t.get_second() == s)
                {
                    return true;
                }
        return false;
    }
    
    @Override
    public boolean isFound(WatchInterface t)
    {
        if(t.get_hour() == h)
            if(t.get_minute() == m)
                if(t.get_second() == s)
                {
                    return true;
                }
        return false;
    }
    
    @Override
    public boolean isFound(int hours, int min, int sec)
    {
       if(hours == h)
            if(min == m)
                if(sec == s)
                {
                    return true;
                }
        return false;
    }
    
    /*@Override
    public int getH()
    {
        return h;
    }
    
    @Override
    public int getM()
    {
        return m;
    }*/   
}
