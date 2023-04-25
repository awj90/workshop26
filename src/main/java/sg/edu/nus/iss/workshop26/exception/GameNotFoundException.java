package sg.edu.nus.iss.workshop26.exception;

public class GameNotFoundException extends Exception {
    
    public GameNotFoundException() {
        super();
    }

    public GameNotFoundException(String message) {
        super(message);
    }
}
