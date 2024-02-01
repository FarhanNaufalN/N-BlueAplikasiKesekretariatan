public class Barang {

    // Private instance variable
    private static Barang instance;

    // Private constructor to prevent instantiation from outside
    private Barang() {
        // Initialization code, if any
    }

    // Static method to obtain the instance
    public static synchronized Barang getInstance() {
        if (instance == null) {
            instance = new Barang();
        }
        return instance;
    }

    // Other methods and properties can be added as needed
}
