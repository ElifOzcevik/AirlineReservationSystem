

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


    @Override
    public int hashCode() {
        return id;
    }
}
