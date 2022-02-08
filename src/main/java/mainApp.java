public class mainApp {
    private static final Object mon = new Object();
    private static char currentLetter = 'A';

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            createThread();
        }
    }

    public static void createThread() {
        new Thread(() -> {
            synchronized (mon) {
                final char c = currentLetter;
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != c) {
                        try {
                            mon.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(currentLetter);
                    switch (currentLetter) {
                        case 'A': {
                            currentLetter = 'B';
                            break;
                        }
                        case 'B': {
                            currentLetter = 'C';
                            break;
                        }
                        case 'C': {
                            currentLetter = 'A';
                            break;
                        }
                    }
                        mon.notifyAll();
                }
            }
        }).start();
    }
}
