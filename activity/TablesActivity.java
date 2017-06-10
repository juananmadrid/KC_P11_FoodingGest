package es.kronox.foodinggest.activity;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import es.kronox.foodinggest.R;
import es.kronox.foodinggest.fragment.TableListFragment;
import es.kronox.foodinggest.model.Table;
import es.kronox.foodinggest.model.Tables;

public class TablesActivity extends AppCompatActivity implements TableListFragment.OnTableSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tables);

        // Cargamos a mano el fragment
        FragmentManager fm = getFragmentManager();

        // Compruebo que no tengo ya añadido el fragment a la jerarquía comprobando
        // si el hueco creado en activity_tables (FrameLayout) está vacío
        if (fm.findFragmentById(R.id.tables_list_fragment) == null ) {

            // Instancio Tables
            Tables tables = Tables.getInstance();

            // Instancio nuevo fragment
            TableListFragment fragment = TableListFragment.newInstance(tables.getTables());

            // Usamos transacciones para añadir los fragment que nos interesen
            fm.beginTransaction()
                    .add(R.id.tables_list_fragment, fragment)
                    .commit();

        }

    }

    @Override
    public void onTableSelected(Table table, int position) {

        Intent intent = new Intent(this, TableViewPagerActivity.class);
        intent.putExtra(TableViewPagerActivity.EXTRA_TABLE_INDEX, position);
        startActivity(intent);

    }
}
