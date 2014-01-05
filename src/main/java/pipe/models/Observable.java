package pipe.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pipe.models.interfaces.IObservable;
import pipe.models.interfaces.IObserver;

/**
 * Observable class can be used in classes that implement IObservable
 */
public class Observable implements IObservable, Serializable
{
    private final List<IObserver> observers = new ArrayList<IObserver>();

    public void registerObserver(IObserver observer)
    {
        if(!observers.contains(observer))
            observers.add(observer);
    }

    public void removeObserver(IObserver observer)
    {
        observers.remove(observer);
    }

    public void notifyObservers()
    {
        for(IObserver observer : observers)
            observer.update();
    }
}
