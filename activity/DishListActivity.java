package es.kronox.foodinggest.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import java.util.LinkedList;

import es.kronox.foodinggest.R;
import es.kronox.foodinggest.model.Dish;


public class DishListActivity extends AppCompatActivity {

    public static final String EXTRA_TABLE_INDEX = "EXTRA_TABLE_INDEX";

    protected LinkedList<Dish> mDishes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // Accedemos a la lista
        ListView list = (ListView) findViewById(R.id.view_dish_list);


    }

}
