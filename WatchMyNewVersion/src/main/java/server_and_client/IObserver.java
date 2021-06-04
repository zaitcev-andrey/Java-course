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
public interface IObserver
{
    void update_time(Model m);
    void update_alarm(Model m);
    void get_alarm_window(Model m);
}
