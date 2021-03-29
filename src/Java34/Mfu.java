package Java34;

import java.util.concurrent.atomic.AtomicInteger;

public class Mfu {
    Object mon = new Object();
    AtomicInteger id = new AtomicInteger(1);

    public void mfuScan () throws InterruptedException {
        synchronized (mon) {
            int currentID = id.getAndIncrement();
            System.out.printf("ID %d. Сканирую...\n", currentID);
            Thread.sleep(2000);
            System.out.printf("ID %d. Отсканировал\n", currentID);
        }
    }

    public void mfuPrint () throws InterruptedException {
            int currentID = id.getAndIncrement();
            System.out.printf("ID %d. Печатаю...\n", currentID);
            Thread.sleep(5000);
            System.out.printf("ID %d. Напечатал.\n", currentID);
    }

    public void mfuCopy () throws InterruptedException {
        synchronized (mon) {
            int currentID = id.getAndIncrement();
            System.out.printf("ID %d. Копирую...\n", currentID);
            Thread.sleep(3000);
            System.out.printf("ID %d. Скопировал.\n",currentID);
        }

    }

    public static void main(String[] args) throws InterruptedException {
        Mfu mfu = new Mfu();
       for (int i = 0; i < 3; i++) {
           new Thread(()-> {
               try {
                   mfu.mfuPrint();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }).start();
           new Thread(()-> {
               try {
                   mfu.mfuCopy();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }).start();
           new Thread(()-> {
               try {
                   mfu.mfuScan();
                   Thread.sleep(9000);
//                   mfu.mfuScan();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }).start();
           Thread.sleep(5000);
       }
    }
}
