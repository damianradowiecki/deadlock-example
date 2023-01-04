package pl.damianradowiecki;

public class Sample2 {

    private final static Object firstResource = new Object();
    private final static Object secondResource = new Object();

    public static void main(String[] args) {
        Thread firstThread = new Thread(new SampleThread(firstResource, secondResource));
        Thread secondThread = new Thread(new SampleThread(secondResource, firstResource));
        firstThread.start();
        waitFor(500);
        secondThread.start();
    }

    static class SampleThread implements Runnable{

        private Object resourceOne;
        private Object resourceTwo;

        public SampleThread(Object resourceOne, Object resourceTwo) {
            this.resourceOne = resourceOne;
            this.resourceTwo = resourceTwo;
        }

        @Override
        public void run() {
            synchronized (resourceOne){
                System.out.println("Locked resource one");
                waitFor(1000);
                synchronized (resourceTwo){
                    System.out.println("Locked resource two");
                    waitFor(1000);
                }
            }
        }
    }

    private static void waitFor(int millis) {
        try { Thread.sleep(millis);} catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
