package es.kronox.foodinggest.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.Serializable;
import java.util.LinkedList;

import es.kronox.foodinggest.R;
import es.kronox.foodinggest.model.Dish;
import es.kronox.foodinggest.model.Table;
import es.kronox.foodinggest.model.Tables;


public class TableFragment extends Fragment {

    // private static final String ARG_TABLE_INDEX = "ARG_TABLE_INDEX";
    private static final String ARG_TABLE = "ARG_TABLE";

    // private int mTableIndex;
    private Table mTable;
    private LinkedList<Dish> mDishes;

    public static TableFragment newInstance(Table table) {
        TableFragment fragment = new TableFragment();

        Bundle arguments = new Bundle();
        arguments.putSerializable(ARG_TABLE, table);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        if (getArguments() != null) {
            mTable = (Table) getArguments().getSerializable(ARG_TABLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_table, container, false);

        // Accedemos a la vista
        ListView list = (ListView) root.findViewById(R.id.view_table_list);

        // Accedemos a la lista de platos de la mesa
        mDishes = mTable.getDishes();
        // Otra forma por si no me pasan el plato instanciado y me pasan el index
        // mDishes = Tables.getInstance().getTable(mTableIndex).getDishes();

        // Accedemos al viewSwitcher


        // Creamos Adapter con lista de platos

        ArrayAdapter<Dish> adapter = new ArrayAdapter<Dish>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                mDishes
        );

        // Le pasamos el adapter al ListView para que rellene la lista
        list.setAdapter(adapter);

        return root;

    }

}
