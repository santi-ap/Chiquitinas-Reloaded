/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package observer;

/**
 *
 * @author santialfonso
 */
public interface Subject {
    public abstract void registrarObserver(Observer observer);
    public abstract void removerObserver(Observer observer);
    public abstract void notificarObserver();
}
