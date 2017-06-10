package es.kronox.foodinggest.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.lang.reflect.Array;
import java.util.LinkedList;
import es.kronox.foodinggest.R;
import es.kronox.foodinggest.model.Table;


public class TableListFragment extends Fragment {

    private final static String ARG_TABLES = "tables";

    protected LinkedList<Table> mTables;
    protected OnTableSelectedListener mOnTableSelectedListener;

    // Patrón para recibir argumentos
    public static TableListFragment newInstance(LinkedList<Table> tables) {
        TableListFragment fragment = new TableListFragment();

        // Enviamos lista de mesas vía Bundle
        Bundle arguments = new Bundle();
        arguments.putSerializable(ARG_TABLES, tables);
        fragment.setArguments(arguments);

        return fragment;

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Obtenemos lista de Tables vía Bundle
        if (getArguments() != null) {
            mTables = (LinkedList<Table>) getArguments().getSerializable(ARG_TABLES);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_table_list, container, false);

        // Accedemos a la lista
        ListView list = (ListView) root.findViewById(R.id.table_list);

        // Creamos Adapter con lista de Tables
        ArrayAdapter<Table> adapter = new ArrayAdapter<Table>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                mTables
                );

        // Le pasamos el adapter al ListView para que rellene la lista
        list.setAdapter(adapter);

        // Asigno listener a la lista para enterarme de cuando se ha pulsado una fila
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Avisamos al listener si lo tenemos que el usuario ha pulsado una fila
                if (mOnTableSelectedListener != null) {
                    Table tableSelected = mTables.get(position);
                    // Aviso al listener
                    mOnTableSelectedListener.onTableSelected(tableSelected, position);
                }
            }
        });


        return root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (getActivity() instanceof OnTableSelectedListener) {
            mOnTableSelectedListener = (OnTableSelectedListener) getActivity();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Compruebo que actividad que me están enganchando es del tipo OnTableSelectedListener
        if (getActivity() instanceof OnTableSelectedListener) {
            mOnTableSelectedListener = (OnTableSelectedListener) getActivity();
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();

        mOnTableSelectedListener = null;
    }

    public interface OnTableSelectedListener {
        void onTableSelected(Table table, int position);
    }


}
