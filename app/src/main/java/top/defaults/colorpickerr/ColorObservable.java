package top.defaults.colorpickerr;

public interface ColorObservable {

    void subscribe(top.defaults.colorpickerr.ColorObserver observer);

    void unsubscribe(ColorObserver observer);

    int getColor();
}
