/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package watches;

import java.util.ArrayList;

/**
 *
 * @author megaa
 */
public class WatchHM extends Watch implements WatchInterface
{
    int hours;
    int minutes;
    
    String message = "";
    
    // храним всех наблюдателей часов
    ArrayList<TimeEvent> event_list = new ArrayList<>();
    
    protected void evnt()
    {
        for(TimeEvent evnt : event_list)
        {
            evnt.event(this);
        }
    }
    
    /*public void deleteEvent(TimeEvent t)
    {
        //int ind = event_list.indexOf(t);
        event_list.remove(t);
        
        /*for(int i = 0; i < event_list.size(); i++)
        {
            if(event_list.get(i).isFound(this))
            {
                ind = i;
                event_list.remove(ind);
                return;   
            }
        }
    }*/
    
    @Override
    public void deleteEvent()
    {
        int ind = 0;
        for(TimeEvent evnt : event_list)
        {
            if(evnt.isFound(this))
            {
                event_list.remove(ind);
                return;
            }
            ind++;
        }
    }
    
    @Override
    public void deleteEvent(int h, int m, int s)
    {
        int ind = 0;
        for(TimeEvent evnt : event_list)
        {
            if(evnt.isFound(h, m, s))
            {
                event_list.remove(ind);
                return;
            }
            ind++;
        }
    }
    
    @Override
    public void addEvent(TimeEvent t)
    {
        event_list.add(t);
    }

    /*@Override
    public String printLastAlarm()
    {
        String str = Integer.toString(event_list.get(event_list.size() - 1).getH()) + ":" + Integer.toString(event_list.get(event_list.size() - 1).getM());
        return str;
    }*/
    
    public WatchHM(String brand, int price, int hours_, int minutes_)
    {
        super(brand, price);

            hours = hours_;
            minutes = minutes_;
    }
    
    @Override
    public void print()
    {
        super.print();
        
        System.out.println("Hours: " + hours + "\nMinutes: " + minutes);
    }
    
    @Override
    public void set_hour(int hours_)
    {
        hours = hours_;
        evnt();
    }
    @Override
    public void set_minute(int minutes_)
    {
        minutes = minutes_;
        evnt();
    }
    @Override
    public void set_time(int hours_, int minutes_)
    {
        hours = hours_;
        minutes = minutes_;
        evnt();
    }
    @Override
    public void move_time(int hours_, int minutes_)
    {        
        int shift = (minutes + minutes_) / 60;
        hours = (hours + hours_ + shift) % 12;
        minutes = (minutes + minutes_) % 60;
        evnt();
    }
    @Override
    public void move_hour(int hours_)
    {
        hours = (hours + hours_) % 12;
        evnt();
    }
    @Override
    public void move_minute(int minutes_)
    {
        int shift = (minutes + minutes_) / 60;
        hours = (hours + shift) % 12;
        minutes = (minutes + minutes_) % 60;
        evnt();
    }
    
    @Override
    public int get_hour()
    {
        return hours;
    }
    @Override
    public int get_minute()
    {
        return minutes;
    }
    @Override
    public void print_time()
    {
        System.out.println("Hours: " + hours + "\nMinutes: " + minutes);
    }
    
    @Override
    public String toString()
    {
        return hours + ":" + minutes;
    }
    
    @Override
    public String getMessage()
    {
        return message;
    }
    
    @Override
    public void setMessage(String str)
    {
        message = str;
    }
    
    @Override
    public void resetMessage()
    {
        message = "";
    }
}
