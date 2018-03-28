

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {

    	//thread havuzu oluþturulur
        ExecutorService application = Executors.newCachedThreadPool();

      //thread'ler tanýmlanýr ve çalýþtýrýlýr
        Flight flight = new SynchronizedFlight(15);
        application.execute(new Reader(flight));
        application.execute(new Reader(flight));
        application.execute(new Reader(flight));
        application.execute(new Reader(flight));
        application.execute(new Writer(flight));
        application.execute(new Writer(flight));
        application.execute(new Writer(flight));
        application.execute(new Writer(flight));
        application.execute(new Writer(flight));

        application.shutdown();
    }
}
