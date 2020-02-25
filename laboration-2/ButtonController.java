public interface ButtonController {
    void turboOff();

    void turboOn();

    void liftBed();

    void lowerBed();

    void startAll();

    void stopAll();

    // Calls the gas method for each car once
    void gas(int ticker_value);

    void brake(int ticker_value);
}
