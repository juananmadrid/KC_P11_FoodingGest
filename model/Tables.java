package es.kronox.foodinggest.model;

import java.util.LinkedList;


public class Tables {


    // Singleton de mesas (instancio y expongo con método getInstance)

    private static Tables mInstance;

    private static LinkedList<Table> mTables;

    public static Tables getInstance() {
        if (mInstance == null) {
            mInstance = new Tables();
        }

        return mInstance;
    }


    // Inicializamos mesas con su nombre y lista vacía de platos
    public Tables() {
        mTables = new LinkedList<>();
        mTables.add(new Table("Mesa 1"));
        mTables.add(new Table("Mesa 2"));
        mTables.add(new Table("Mesa 3"));
        mTables.add(new Table("Mesa 4"));
        mTables.add(new Table("Mesa 5"));
        mTables.add(new Table("Mesa 6"));
        mTables.add(new Table("Mesa 7"));
        mTables.add(new Table("Mesa 8"));
        mTables.add(new Table("Mesa 9"));
        mTables.add(new Table("Mesa 10"));
        mTables.add(new Table("Mesa 11"));
        mTables.add(new Table("Mesa 12"));
        mTables.add(new Table("Mesa 13"));
        mTables.add(new Table("Mesa 14"));
        mTables.add(new Table("Mesa 15"));

    }

    public static LinkedList<Table> getTables() {
        return mTables;
    }

    public Table getTable(int index) {
        return mTables.get(index);
    }

    public int getCount() {
        return mTables.size();
    }


}
