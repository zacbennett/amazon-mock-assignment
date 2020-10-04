import java.util.List;

public class User {
    private Session session; 
    private Cart cart;
    private String email;
    private String password;

    public User(String email, String password) {
        session = new Session();
        cart = new Cart();
        email = email;
        password = password;
    }

    private void handleUserInteraction() {
        this.handleSessionExpired();
        this.session.setLastInteractionTime();
    }

    private void handleSessionExpired() {
        if (this.session.isSessionExpired()) {
            this.cart.clearCart()
        }
    }

    public void addItem(Item item) {
        this.handleUserInteraction();
        // ...
    }

    public void removeItem(Item item) {
        this.handleUserInteraction();
        // ...

        // Note that its possible the item won't be in the cart due it being cleared from session inactivity

    }

    public List<Item> listItems() {
        this.handleUserInteraction();
        
        // ... 
    }
  
}

public class Session{
    private long sessionLength;
    private long lastInteractionTime;

    public Session() {
        // current timestamp in unix
        lastInteractionTime = getCurrentUnixTimeStamp();

        // set in seconds, total 10 minutes
        sessionLength = 600;
    }

    private static long getCurrentUnixTimeStamp() {
        return System.currentTimeMillis() / 1000L;
    }

    public boolean isSessionExpired() {
        long timeSinceLastInteraction = getCurrentUnixTimeStamp() - lastInteractionTime;
        if(timeSinceLastInteraction >= this.sessionLength) {
            return true;
        }
        return false;
    }

    public void setLastInteractionTime() {
        lastInteractionTime = getCurrentUnixTimeStamp();
    }
}

class MainAccountCreator{
    private DB db;
    private MainAccountCreator instance;
    static {
        instance = new MainAccountCreator();
    }
    
    public MainAccountCreator getInstance() {
        return instance;
    }

    public User addUser(String email, String password) {
        User newUser = new User(email, password);
        this.db.createUser(newUser); 
        return newUser
    }

    public void deleteUser(String email) {
        this.db.deleteUserByEmail(email); 
    }
}

public interface DB {
    public void createUser(User user);
    public void deleteUserByEmail(String email);
}