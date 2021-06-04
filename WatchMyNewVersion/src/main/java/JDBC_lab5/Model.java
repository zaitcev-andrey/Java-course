/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC_lab5;

import alarm_clock.Alarm;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    
    Connection c;
    
    Thread t;
    
    Model() {
        connect();
        ArrayList<String> tmp_alarms = listFromDB();
        for (String al:tmp_alarms)
        {
            try {
                Alarm a = new Alarm(0, 0, 0);
                a.setHour(Integer.parseInt(al.substring(0,2)));
                a.setMin(Integer.parseInt(al.substring(3,5)));
                a.setSec(Integer.parseInt(al.substring(6)));
                w.addEvent(a);
            } catch (UnsupportedOperationException e) {}
        }
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
    
    void connect()
    {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection ("jdbc:sqlite:D:\\SQLiteStudio\\clock.db");
            System.out.println("Opened database successfully");
        } catch (ClassNotFoundException ex) {
            System.out.println("Нет драйвера");
        } catch (SQLException ex) {
            System.out.println("Проблема с подключением к СУБД");
        }
    }
    
    ArrayList<String> listFromDB()
    {
        ArrayList<String> resList = new ArrayList<>();
        
        try {
            Statement st = c.createStatement();
            ResultSet res = st.executeQuery("select * from alarm_clocks");
            while (res.next()) {
                resList.add(res.getString("alarm"));
            }
            
        } catch (SQLException ex) {
            System.out.println("Проблема с подключением");
        }
        
        return resList;
    }
    
    public void saveAlarm(String a)
    {
        try {
            PreparedStatement pst = c.prepareStatement("INSERT INTO alarm_clocks(alarm) VALUES(?)");
            pst.setString(1, a);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Проблема с подключением");
        }
    }
    
    public void deleteAlarm(String a)
    {
        try {
            PreparedStatement pst = c.prepareStatement("DELETE FROM alarm_clocks WHERE alarm = (?)");
            pst.setString(1, a);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Проблема с подключением");
        }
    }
    
    void get_alarm_window()
    {
        w.deleteEvent(); // удаляем будильник в программе
        Alarm a = new Alarm(0, 0, 0);
        a.setHour(w.get_hour());
        a.setMin(w.get_minute());
        a.setSec(w.get_second());
        deleteAlarm(a.get_time_alarm()); // удаляем будильник в базе данных
        update_alarm();
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
        saveAlarm(a.get_time_alarm());
        update_alarm();
    }
    
    public void delete(Alarm a)
    {
        w.deleteEvent(a.getHour(), a.getMin(), a.getSec());
        deleteAlarm(a.get_time_alarm());
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
