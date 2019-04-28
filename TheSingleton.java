public class TheSingleton {

    // class-load initialization
    private static final TheSingleton instance = new TheSingleton();

    private String item = null;
    private boolean available = false;

    private TheSingleton() {}

    // no need for synchronized method since the instance created at load-time
    public static TheSingleton getInstance() {
        return instance;
    }

    public synchronized String pull() {
        String current_item = this.item;
        if (this.available) {
            System.out.println(" -- pulling item: [" + this.item + "]");
            this.available = false;
            this.item = null;
        }
        else {
            System.out.println(" -- __no item available to be pulled");
        }
        return current_item;
    }

    public synchronized void put(String new_item) {
        if (!this.available) {
            this.item = new_item;
            this.available = true;
            System.out.println(" -- putting item: [" + this.item + "]");
        }
        else {
            System.out.println(" -- __unable to put item [" + new_item + "]. another item already available.");
        }
    }

}