package es.kronox.foodinggest.model;

import java.util.LinkedList;


public class Tables {


    private static Tables mInstance;

    private Integer mNumber;
    private LinkedList<Table> mTables;


    // Instanciamos aqui las mesas y las hacemos accesibles desde fuera

    public static Tables getInstance() {
        if (mInstance == null) {
            mInstance = new Tables();
        }

        return mInstance;
    }

    // Tendremos un total de 10 mesas. Las inicializamos con su nombre y lista vacía de platos
    public Tables() {

        mTables = new LinkedList<>();
        mTables.add(new Table(1));
        mTables.add(new Table(12));
        mTables.add(new Table(3));
        mTables.add(new Table(4));
        mTables.add(new Table(5));
        mTables.add(new Table(6));
        mTables.add(new Table(7));
        mTables.add(new Table(8));
        mTables.add(new Table(9));
        mTables.add(new Table(10));

    }


}
