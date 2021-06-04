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
public interface WatchInterface
{
    public void set_brand(String brand_);
    public void set_price(int price_) throws NegativeValuesException;
    public void set_brand_and_price(String brand_, int price_) throws NegativeValuesException;
    public String get_brand();
    public int get_price();
    public void print();
    
    public default void set_hour(int hours_) {
        throw new UnsupportedOperationException("The class does not support this function");
    }
    public default void set_minute(int minutes_) {
        throw new UnsupportedOperationException("The class does not support this function");
    }
    public default void set_time(int hours_, int minutes_) {
        throw new UnsupportedOperationException("The class does not support this function");
    }
    public default void move_time(int hours_, int minutes_) {
        throw new UnsupportedOperationException("The class does not support this function");
    }
    public default void move_hour(int hours_) {
        throw new UnsupportedOperationException("The class does not support this function");
    }
    public default void move_minute(int minutes_) {
        throw new UnsupportedOperationException("The class does not support this function");
    }
    public default int get_hour() {
        throw new UnsupportedOperationException("The class does not support this function");
    }
    public default int get_minute() {
        throw new UnsupportedOperationException("The class does not support this function");
    }
    public default void print_time() {
        throw new UnsupportedOperationException("The class does not support this function");
    }
    
    public default void set_second(int seconds_) {
        throw new UnsupportedOperationException("The class does not support this function");
    }
    public default void set_time(int hours_, int minutes_, int seconds_) {
        throw new UnsupportedOperationException("The class does not support this function");
    }
    public default void move_time(int hours_, int minutes_, int seconds_) {
        throw new UnsupportedOperationException("The class does not support this function");
    }
    public default void move_second(int seconds_) {
        throw new UnsupportedOperationException("The class does not support this function");
    }
    public default int get_second() {
        throw new UnsupportedOperationException("The class does not support this function");
    }
    
    public default String getMessage() {
        throw new UnsupportedOperationException("The class does not support this function");
    }
    public default void setMessage(String str) {
        throw new UnsupportedOperationException("The class does not support this function");
    }
    public default void resetMessage() {
        throw new UnsupportedOperationException("The class does not support this function");
    }
    
    public default void addEvent(TimeEvent t) {
        throw new UnsupportedOperationException("The class does not support this function");
    }
    
    public default void deleteEvent() {
        throw new UnsupportedOperationException("The class does not support this function");
    }
    
    public default void deleteEvent(int h, int m, int s) {
        throw new UnsupportedOperationException("The class does not support this function");
    }
    /*public default String printLastAlarm() {
        throw new UnsupportedOperationException("The class does not support this function");
    }*/
}
