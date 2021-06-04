/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_and_client;

import alarm_clock.Alarm;
import java.util.ArrayList;
import java.util.List;
import watches.WatchHMS;

/**
 *
 * @author megaa
 */
public class Model
{
    WatchHMS w = new WatchHMS("Casio", 10000, 0, 0, 0);
    //ArrayList<String> all_msg = new ArrayList<>(); // все сообщения, которые отправят пользователи
    ArrayList<IObserver> all_o = new ArrayList<>(); // это список наблюдателей. Они смотрят за изменением модели
    
    Model m = this;
    boolean check = false;
    
    Thread t;
    
    Model() {
        if (t == null) {
            t = new Thread() {
                @Override
                public void run() {
                    while(true) {
                        try {
                            check = w.move_time_without_panel(0, 0, 1);
                            if(check == true) {
                                check = false;
                                get_alarm_window();
                            }
                            update_time();
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {}
                    }
                }
            };
            t.start();
        }
    }
    
    void get_alarm_window()
    {
        for (IObserver o : all_o)
        {
            o.get_alarm_window(m);
        }
    }
    
    void update_time()
    {
        for (IObserver o : all_o)
        {
            o.update_time(m);
        }
    }
    
    void update_alarm()
    {
        for (IObserver o : all_o)
        {
            o.update_alarm(m);
        }
    }
    
    public void add(Alarm a)
    {
        w.addEvent(a);
        update_alarm();
    }
    
    public String getTime()
    {
        return w.get_time();
    }
    
    public String getAlarms() {
        return w.get_alarms();
    }
    
    public void addO(IObserver o)
    {
        all_o.add(o);
    }
    
    /*public void add(String str)
    {
        all_msg.add(str);
        update();
    }

    public ArrayList<String> getAll_msg()
    {
        return all_msg;
    }
    
    public String last()
    {
        return all_msg.get(all_msg.size() - 1);
    }*/
}
