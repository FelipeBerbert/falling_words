package br.felipeberbert.fallingwords.Data;

/**
 * Created by Felipe Berbert on 23/03/2017.
 */

public class DataSource {


    private static DataSource INSTANCE;

    // This class should not be instantiated.
    private DataSource() {}

    public static DataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DataSource();
        }
        return INSTANCE;
    }
}
