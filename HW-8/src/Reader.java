

/* = Reader = Consumer */

import java.util.Random;

public class Reader implements Runnable {

    private final static Random generator = new Random();
    private final Flight flight; //thread'ler tanýmlanýr ve çalýþtýrýlýr

    //constructor tanýmlanýr
    public Reader(Flight flight) {
        this.flight = flight;
    }

  //thread'in genel run metodu
    @Override
    public void run() {
        for (int count=0; count<this.flight.seatCount()*1.5; count++) {
            try {
                Thread.sleep(generator.nextInt(3000));
                displaySeats(this.flight.queryReservation());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("\n\n\t\tqueryReservation done!\n");
    }

    // koltuklarýn bilgisinin dizisinin yazdýrýldýðý metot
    public static void displaySeats(Client[] arr) {
        System.out.printf("\n operation: %16s | seats: ", "queryReservation");
        for (int i=0;i<arr.length;i++) {
            System.out.printf("[%d|%s]", i, (arr[i] == null) ? "null": arr[i]);
        }
        System.out.printf("[%s|%s]", "seatNo", "clientNo");
    }
}
