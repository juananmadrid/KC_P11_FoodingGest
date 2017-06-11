package es.kronox.foodinggest.activity;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import es.kronox.foodinggest.R;
import es.kronox.foodinggest.fragment.TableViewPagerFragment;

public class TableViewPagerActivity extends AppCompatActivity implements TableViewPagerFragment.OnAddDishButtonListener {

    public static final String EXTRA_TABLE_INDEX = "EXTRA_TABLE_INDEX";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_view_pager);

        // Creamos Toolbar personalizada
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_pager);
        toolbar.setLogo(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);

        // Recibimos la mesa a mostrar (por defecto 0)
        int tableIndex = getIntent().getIntExtra(EXTRA_TABLE_INDEX, 0);

        // Añadimos TablePagerFragment a nuestra jerarquía
        // comprobando si hay hueco para meter el fragment
        FragmentManager fm = getFragmentManager();
        if (fm.findFragmentById(R.id.view_pager_fragment) == null) {
            // Metemos fragment en hueco
            TableViewPagerFragment fragment = TableViewPagerFragment.newInstance(tableIndex);
            fm.beginTransaction()
                    .add(R.id.view_pager_fragment, fragment)
                    .commit();

        }
    }

    // Se ejecutará al pulsar botón "Add Dish"

    public void OnAddDishButton (int position) {

        Intent intent = new Intent(this, DishListActivity.class);
        intent.putExtra(DishListActivity.EXTRA_INDEX_TABLE, position);
        startActivity(intent);

    }

}