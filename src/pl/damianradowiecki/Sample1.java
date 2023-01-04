package pl.damianradowiecki;

public class Sample1 {

    private final static Object firstResource = new Object();
    private final static Object secondResource = new Object();

    public static void main(String[] args) {
        Thread firstThread = new Thread(() -> {
            synchronized (firstResource) {
                System.out.println("First thread has locked the firstResource");
                waitFor(1000);
                synchronized (secondResource) {
                    System.out.println("First thread has locked the secondResource");
                }
            }
        });

        Thread secondThread = new Thread(() -> {
            synchronized (secondResource) {
                System.out.println("Second thread has locked the secondResource");
                waitFor(1000);
                synchronized (firstResource) {
                    System.out.println("Second thread has locked the firstResource");
                }
            }
        });
        firstThread.start();
        secondThread.start();
    }

    private static void waitFor(int millis) {
        try { Thread.sleep(millis);} catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
