package Java34;

public class PrintStrABC {
    public String str = "";

    public synchronized void putA() {
            if (str.equals("")) str += "A";
            while (!str.endsWith("C")) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            str += "A";
            notifyAll();
    }

    public synchronized void putB() {
            if (str.equals("")) str += "A";
            while (!str.endsWith("A")) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            str += "B";
            notifyAll();
    }

    public synchronized void putC() {
            if (str.equals("")) str += "A";
            while (!str.endsWith("B")) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            str += "C";
            notifyAll();
    }


    public static void main(String[] args) {
        PrintStrABC abcString = new PrintStrABC();

        Thread t1 = new Thread(()-> {
            for (int i = 0; i <5; i++) {
            abcString.putA();}
        });
        Thread t2 = new Thread(()-> {
            for (int i = 0; i<5; i++){
            abcString.putB();}
        });
        Thread t3 = new Thread(()-> {
            for (int i =0; i < 5 ; i++) {
                abcString.putC();
            }
            System.out.println(abcString.str);
        });
        t1.start();
        t2.start();
        t3.start();
    }
}


