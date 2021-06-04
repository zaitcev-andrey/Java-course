/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package watches;

/**
 *
 * @author megaa
 */
public interface TimeEvent
{
    public void event(WatchInterface t);
    public boolean event2(WatchInterface t);
    public boolean isFound(WatchInterface t);
    public boolean isFound(int hours, int min, int sec);
    //public int getH();
    //public int getM();
}
