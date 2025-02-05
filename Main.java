import java.util.Random;

class NumberPrinter extends Thread {
    private int threadNumber;
    private static final int MAX_COUNT = 50;
    private static final Random random = new Random();

    public NumberPrinter(int threadNumber) {
        this.threadNumber = threadNumber;
    }

    @Override
    public void run() {
        for (int i = 1; i <= MAX_COUNT; i++) {
            System.out.println("Thread -" + threadNumber + "- : " + i);
            try {
                Thread.sleep(random.nextInt(100)); // หน่วงเวลาแบบสุ่มสูงสุด 100 มิลลิวินาที
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // สร้างและเริ่มต้นเธรดทั้งสามตัว
        NumberPrinter thread1 = new NumberPrinter(1);
        NumberPrinter thread2 = new NumberPrinter(2);
        NumberPrinter thread3 = new NumberPrinter(3);

        thread1.start();
        thread2.start();
        thread3.start();

        /*
         1. Before adding พฤติกรรมเริ่มต้น (ก่อนเพิ่ม Thread.sleep()):
         * - เธรดทำงานพร้อมกันโดยไม่มีการหน่วงเวลา ทำให้ผลลัพธ์ดูเป็นระเบียบมากขึ้น
         * - ลำดับการพิมพ์อาจยังคงสลับกันบ้าง แต่จะปรากฏเร็วขึ้น
         *
         2. After adding พฤติกรรมหลังจากเพิ่ม Thread.sleep():
         * - เธรดทำงานด้วยการหน่วงเวลาสุ่ม ทำให้ตัวเลขปรากฏในลำดับที่ไม่สามารถคาดเดาได้
         * - แต่ละเธรดหยุดชั่วคราวแบบสุ่ม ทำให้เกิดความหลากหลายในลำดับที่พิมพ์
         * - การจำลองสถานการณ์จริงที่การทำงานของเธรดไม่มีลำดับที่แน่นอน
         *
         3. คำอธิบายเกี่ยวกับมัลติเธรดใน Java:
         * - ใน Java, มัลติเธรดช่วยให้เธรดหลายตัวสามารถทำงานพร้อมกันได้
         * - ลำดับการทำงานถูกควบคุมโดย JVM และตัวจัดการเธรดของระบบปฏิบัติการ
         * - เนื่องจากการสลับบริบท (Context Switching) ลำดับการทำงานอาจเปลี่ยนแปลงไปในแต่ละครั้งที่รันโปรแกรม
         */
    }
}
