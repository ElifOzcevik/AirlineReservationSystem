
import java.util.concurrent.locks.*;


class SynchronizedFlight implements Flight {
  
/* Bir ReadWriteLock uygulamasý aþaðýdaki davranýþlarý garanti eder:
   Verileri güncelleyen hiçbir iþ parçacýðý olmadýðý sürece birden çok iþ parçacýðý ayný anda verileri okuyabilir.
   Verileri bir defada güncelleyebilen yalnýzca bir iþ parçacýðý yazma kilidi serbest býrakýlýncaya kadar diðer iþ parçacýklarýna (hem okuyucular hem de yazarlar) blok yapmaya neden olur.
   Bir iþ parçacýðý diðer iþ parçacýklarý okurken verileri güncelleþtirmeye çalýþýrsa, iþ parçacýðý okuma kilidi serbest býrakýlana kadar da engeller. */
	
	
	
	private ReadWriteLock accessLock = new ReentrantReadWriteLock();
    private final Lock readLock = accessLock.readLock();
    private final Lock writeLock = accessLock.writeLock();
    private final Client[] seats; //client tipinde bir oturma yeri dizisi tanýmlanýr


    SynchronizedFlight() {
        this(10); //seatsCount olarak 10 döndürür
    }

    SynchronizedFlight(int seatsCount) {
        this.seats = new Client[seatsCount]; //seats dizisine boyut atanýr
    }

    //queryReservation() metodu tanýmlanýr.
    @Override
    public Client[] queryReservation() throws InterruptedException {
        readLock.lock();
        Client[] s;
        try {
            s = this.seats;
        } finally {
            readLock.unlock();
        }
        return s;
    }

  //cancelReservation() metodu tanýmlanýr
    @Override
    public void cancelReservation(int seatNo) throws InterruptedException {
        writeLock.lock();

        try {
            seats[seatNo] = null;
        } finally {
            writeLock.unlock();
        }
    }

    //makeReservation() metodu tanýmlanýr
    @Override
    public void makeReservation(Client client, int seatNo) throws InterruptedException {
        writeLock.lock();

        try {if (seats[seatNo]==null)  seats[seatNo] = client;
        		else System.out.println("\nYou can't choose it. You must choose another seat");
        	
       
           //seats[seatNo] = client
        } finally {
            writeLock.unlock();
        }
    }


  //seatCount() metodu tanýmlanýr
    @Override
    public int seatCount() {
        return this.seats.length;
    }
}
