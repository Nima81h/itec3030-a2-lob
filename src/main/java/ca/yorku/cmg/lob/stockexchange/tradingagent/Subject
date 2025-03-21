package ca.yorku.cmg.lob.stockexchange.events;

import java.util.ArrayList;
import java.util.List;
import ca.yorku.cmg.lob.stockexchange.tradingagent.INewsObserver;


public abstract class Subject {
    protected List<INewsObserver> observers = new ArrayList<>();
    
    
    public void registerObserver(INewsObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }
    
    public void removeObserver(INewsObserver observer) {
        observers.remove(observer);
    }
    
    public void notifyObservers(Event event) {
        for (INewsObserver observer : observers) {
            observer.update(event);
        }
    }
}
