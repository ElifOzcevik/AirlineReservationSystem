
import java.util.concurrent.locks.*;


class SynchronizedFlight implements Flight {
  
/* Bir ReadWriteLock uygulamas� a�a��daki davran��lar� garanti eder:
�� Verileri g�ncelleyen hi�bir i� par�ac��� olmad��� s�rece birden �ok i� par�ac��� ayn� anda verileri okuyabilir.
�� Verileri bir defada g�ncelleyebilen yaln�zca bir i� par�ac��� yazma kilidi serbest b�rak�l�ncaya kadar di�er i� par�ac�klar�na (hem okuyucular hem de yazarlar) blok yapmaya neden olur.
�� Bir i� par�ac��� di�er i� par�ac�klar� okurken verileri g�ncelle�tirmeye �al���rsa, i� par�ac��� okuma kilidi serbest b�rak�lana kadar da engeller. */
	
	
	
	private ReadWriteLock accessLock = new ReentrantReadWriteLock();
    private final Lock readLock = accessLock.readLock();
    private final Lock writeLock = accessLock.writeLock();
    private final Client[] seats; //client tipinde bir oturma yeri dizisi tan�mlan�r


    SynchronizedFlight() {
        this(10); //seatsCount olarak 10 d�nd�r�r
    }

    SynchronizedFlight(int seatsCount) {
        this.seats = new Client[seatsCount]; //seats dizisine boyut atan�r
    }

    //queryReservation() metodu tan�mlan�r.
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

  //cancelReservation() metodu tan�mlan�r
    @Override
    public void cancelReservation(int seatNo) throws InterruptedException {
        writeLock.lock();

        try {
            seats[seatNo] = null;
        } finally {
            writeLock.unlock();
        }
    }

    //makeReservation() metodu tan�mlan�r
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


  //seatCount() metodu tan�mlan�r
    @Override
    public int seatCount() {
        return this.seats.length;
    }
}
