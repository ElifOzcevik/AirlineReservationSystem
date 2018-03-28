
/* = Writer = Producer */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Writer implements Runnable {

    private final static Random generator = new Random();
    private final Flight flight;
    private ArrayList<Integer> list;

    //constructor
    public Writer(Flight flight) {
        this.flight = flight;

        // random rezervasyon indeksi
        list = new ArrayList<>();
        for (int i=0; i<this.flight.seatCount(); i++) {
            list.add(i);
        }
        Collections.shuffle(list);
    }

  //thread'in run metodu
    @Override
    public void run() {
        for (int count=0; count<this.flight.seatCount(); count++) {
            try {
            	//makeReservation
                Thread.sleep(generator.nextInt(3000));
                Client newClient = new Client();
                displayState("makeReservation", list.get(count));
                this.flight.makeReservation(newClient, list.get(count));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i=0;i<this.flight.seatCount()/2;i++) {
            try {
            	//cancelReservetion
                Thread.sleep(generator.nextInt(3000));
                displayState("cancelReservation", list.get(i));
                this.flight.cancelReservation(list.get(i));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("\n\n\t\tcancelReservation and makeReservation done!\n");
    }

    //yazdýrma metodu
    private void displayState(String operation, int seatNo) {
        System.out.printf("\n operation: %16s | seatNo: %d ", operation, seatNo);
    }
}
