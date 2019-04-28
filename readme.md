# sunlife-jp2046

Question: write a Singleton class in Java that will be used by multiple threads concurrently. 

Write a test to use the Singleton class. 

Put the compatible code in GitHub and send the link.**** 

# Code Description
## TheSingleton.java
Thread-safe Singleton class with 2 methods.

`put(String)` sets the class variable and makes the instance "available"

`pull()` consumes the class variable and makes the instance "unavailable"

## SingletonThreadTest.java
Main class to test the Singleton class.

Test based on classic producer consumer example:
* Instantiate multiple Producer and Consumer threads, and assign a Singleton to each
* Producer is responsible for "putting", but can only do so if the instance is available.
* Consumer is responsible for "pulling", but can only do so if the instance is not available.
* Producers will continously (try to) put items into the Singleton instance
* Consumers will continously (try to) pull items from the Singleton instance

Test Output:
* Singleton hashcodes are print to console when they are assigned to Producers and Consumers, to show that a single instance is being used. 
* successful and failed attempts to put and pull are printed to console to show that the code functions correctly while multiple threads are running concurrently.

The test will run indefinitely, until user manually terminates the application.
