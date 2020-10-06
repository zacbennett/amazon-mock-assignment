import java.util.List;

public class User {
    private Session session; 
    private Cart cart;
    private String email;
    private String password;

    public User(String email, String password) {
        session = new Session();
        this.cart = new Cart();
        email = email;
        password = password;
    }

    private void handleUserInteraction() {
        this.handleSessionExpired();
        this.session.setLastInteractionTime();
    }

    private void handleSessionExpired() {
        if (this.session.isSessionExpired()) {
            this.cart.clearCart();
        }
    }

    public void addItem(Item item) {
        this.handleUserInteraction();
        // ...
        this.cart.
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

    public Cart getCart()
    {
        return this.Cart;
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
        return newUser;
    }

    public void deleteUser(String email) {
        this.db.deleteUserByEmail(email); 
    }
}

public interface DB {
    public void createUser(User user);
    public void deleteUserByEmail(String email);
}


public class Item {

    private float itemPrice;
    private String itemCategory;
    private int itemQuantity;
    private String itemName;
    private String itemDescription;
    private int itemSKU;
    private String itemType;

  public static void itemUpdate() {
    return this.itemQuantity++;
  }

  public static String getName() {
    return this.itemName;
  }

  public static float getPrice() {
    return this.itemPrice;
  }

  public static float getDescription() {
    return this.itemDescription;
  }

  public static int getItemSKU() {
    return this.itemSKU;
  }
}

class Cart {
    private List<Item> itemCart = new ArrayList<Item>();
    public void addItem(Item newItem) {
        for (int count = 0; count < itemCart.size(); count++) {
            item = itemCart.get(count);
            if (item.getItemSKU()) {
                item.itemUpdate();
            }
            else {
                itemCart.add(newItem);
            }
        }
    }
    public static void listItems() {
        for (int count = 0; count < itemCart.size(); count++) {
            item = itemCart.get(count);
            System.out.println(item.getName() + ": " + item.getPrice() + "Description: " + item.getDescription());
        }
    }

    public static void removeItem(int itemSKU) {
        itemCart.remove(itemSKU);
    }

    public static void clearCart(int itemSKU) {
        itemCart = new ArrayList<Item>();
    }

    public static Cart getInstance() {
        return instance;
    }
}