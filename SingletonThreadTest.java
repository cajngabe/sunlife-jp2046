public class SingletonThreadTest {

    public static void main(String[] args) {
        test1();
    }

    /***
     * TEST: Testing that the Singleton class is thread safe
     *  - Test based on classic producer consumer example:
     *      - Instantiate multiple Producers and Consumers, and assign a singleton to each
     *      - Producer is responsible for "putting", but can only do so if the instance is available.
     *      - Consumer is responsible for "pulling", but can only do so if the instance is not available.
     */
    protected static void test1() {

        String[] items = {"oak", "maple", "cedar", "birch", "ash", "spruce", "pine"};


        for (String item: items) {

            // get a TheSingleton instance for each Producer
            Producer p = new Producer(TheSingleton.getInstance(), item);
            p.start();
        }

        for (int i=0; i<10; i++) {
            // get a TheSingleton instance for each Consumer
            Consumer c = new Consumer(TheSingleton.getInstance(), Integer.toString(i));
            c.start();
        }

    }

    public static class Producer extends Thread {

        private TheSingleton ts = null;
        private String item;

        Producer(TheSingleton singleton, String item) {
            super("Producer-" + item);
            this.ts = singleton;
            this.item = item;

        }

        @Override
        public void run() {
            // first print out the hashcode for the Singleton instance to show that a single instance is being used
            // for all thread instantiations.
            System.out.println(getName() + " -- SINGLETON: " + ts.toString());
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ts.put(item);
            }
        }
    }

    public static class Consumer extends Thread {

        private TheSingleton ts = null;

        Consumer(TheSingleton singleton, String name) {
            super("Consumer-" + name);
            this.ts = singleton;

        }

        @Override
        public void run() {
            // first print out the hashcode for the Singleton instance to show that a single instance is being used
            // for all thread instantiations.
            System.out.println(getName() + " -- SINGLETON: " + ts.toString());
            while(true) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ts.pull();

            }
        }
    }
}
