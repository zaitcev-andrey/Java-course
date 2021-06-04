/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC_lab5;

import alarm_clock.Alarm;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 *
 * @author megaa
 */

// класс для взаимодействия клиента и сервера со стороны сервера
// во-первых наследуемся от потока, чтобы в отдельном потоке прослушивать то, что приходит от клиента
// во-вторых наблюдаем за моделью, и если там происходят измен-я, то он на них автоматически реагирует(implements)
public class WCS extends Thread implements IObserver
{
    Socket cs;
    Model m;
    
    InputStream is; // входной и выходной поток
    OutputStream os;
    DataInputStream dis; // обёртки над этими потоками
    DataOutputStream dos;
    
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public WCS(Socket cs, Model m) // создаём оболочку над выходным потоком
    {
        this.cs = cs;
        this.m = m;
        try
        {
            os = cs.getOutputStream();
            dos = new DataOutputStream(os);
        } catch (IOException ex) {
            Logger.getLogger(WCS.class.getName()).log(Level.SEVERE, null, ex);
        }
        m.addO(this);
        this.start(); // отсюда создаём новый поток для клиента
        
        //send("Hello!");
    }
    
    public void send(String s)
    {
        try {
            dos.writeUTF(s); // сервер отсылает клиенту сообщение
        } catch (IOException ex) {
            Logger.getLogger(WCS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() // прослушиваем, какие сообщения пришли от клиента
    {
        try
        {
            is = cs.getInputStream();
            dis = new DataInputStream(is);
            
            m.update_alarm();
            
            while(true)
            {
                // ожидаем приход строки со стороны клиента
                String obj = dis.readUTF(); // получаем строку от клиента
                // в msg будет хранится сообщение от клиента
                Msg msg = gson.fromJson(obj, Msg.class);
                
                //m.add(msg.getMsg());
                String al = msg.getMsg();
                try {
                    if(al.startsWith("a"))
                    {
                        Alarm a = new Alarm(0, 0, 0);
                        a.setHour(Integer.parseInt(al.substring(1,3)));
                        a.setMin(Integer.parseInt(al.substring(4,6)));
                        a.setSec(Integer.parseInt(al.substring(7)));
                        m.add(a);
                        //send("a" + a.AtoString());
                    }
                    else if (al.startsWith("d"))
                    {
                        Alarm a = new Alarm(0, 0, 0);
                        a.setHour(Integer.parseInt(al.substring(1,3)));
                        a.setMin(Integer.parseInt(al.substring(4,6)));
                        a.setSec(Integer.parseInt(al.substring(7)));
                        m.delete(a);
                    }
                } catch (UnsupportedOperationException e) {}
            }
        } catch (IOException ex) {
            Logger.getLogger(WCS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void get_alarm_window(Model m)
    {
        send("w");
    }
    
    @Override
    public void update_alarm(Model m)
    {
        // пришедшее сообщение(последнее) хотим отправить клиенту
        //send(m.last());
        send("a" + m.getAlarms());
    }
    
    @Override
    public void update_time(Model m)
    {
        send("t" + m.getTime());
    }
}
