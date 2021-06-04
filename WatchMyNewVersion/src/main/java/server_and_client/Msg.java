/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_and_client;

/**
 *
 * @author megaa
 */

// Класс для сообщений от клиента
public class Msg
{
    String msg;

    public Msg(String msg)
    {
        this.msg = msg;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }
      
}
