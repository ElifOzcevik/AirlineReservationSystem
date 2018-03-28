

public class Client {

    private static int i;
    private final int id;

    Client() {
        this.id = i++;
    }

    @Override
    public String toString() {
        return Integer.toString(this.id);
    }

 //   @Override
  //  public boolean equals(Object o) {
   //     if (this == o) return true;
   //     if (o == null || getClass() != o.getClass()) return false;

   //     Client client = (Client) o;

  //      return id == client.id;
 //   }

    @Override
    public int hashCode() {
        return id;
    }
}
