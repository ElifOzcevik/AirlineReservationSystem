
//buffer class'ý 

public interface Flight {

    public Client[] queryReservation() throws InterruptedException;
    public void cancelReservation(int seatNo) throws InterruptedException;
    public void makeReservation(Client client, int seatNo) throws InterruptedException;
    public int seatCount();
}
