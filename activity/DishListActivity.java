package es.kronox.foodinggest.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import es.kronox.foodinggest.R;
import es.kronox.foodinggest.model.Dish;
import es.kronox.foodinggest.model.Dishes;
import es.kronox.foodinggest.model.Table;
import es.kronox.foodinggest.model.Tables;


public class DishListActivity extends AppCompatActivity  {

    public static final String EXTRA_INDEX_TABLE = "EXTRA_INDEX_TABLE";

    protected LinkedList<Dish> mDishes;
    int indexTableActive;

    String urlString = "http://www.mocky.io/v2/593be1031000009b0ec47774";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_dishes);
        setSupportActionBar(toolbar);

        // Recibimos la mesa a mostrar (por defecto 0)
        indexTableActive = getIntent().getIntExtra(EXTRA_INDEX_TABLE, 0);

        // Accedemos a la lista
        final ListView list = (ListView) findViewById(R.id.view_dish_list);

        // Le asigno un Listener a la lista para saber qué fila se selecciona
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Dish dish = mDishes.get(position);
                OnDishSelected(dish);
            }
        });

        // Descarga asincrona de datos a mostrar en la lista
        if (mDishes == null) {

            AsyncTask<String, Integer, Dishes> dishDownloader = new AsyncTask<String, Integer, Dishes>() {
                @Override
                protected Dishes doInBackground(String... params) {
                    // Aqui estamos en otro hilo
                    return downLoadDishList(urlString);
                }

                @Override
                protected void onPostExecute(Dishes dishes) {
                    super.onPostExecute(dishes);

                    // Actualizo mDishes
                    // Lo hago manualmente porque el ArrayAdapter no me carga el mDishes directamente
                    mDishes = new LinkedList<>();
                    Dish dish0 = dishes.getDish(1);
                    Dish dish1 = dishes.getDish(1);
                    Dish dish2 = dishes.getDish(2);
                    Dish dish3 = dishes.getDish(3);
                    Dish dish4 = dishes.getDish(4);
                    Dish dish5 = dishes.getDish(5);
                    Dish dish6 = dishes.getDish(6);
                    Dish dish7 = dishes.getDish(7);
                    Dish dish8 = dishes.getDish(0);

                    mDishes.add(dish0);
                    mDishes.add(dish1);
                    mDishes.add(dish2);
                    mDishes.add(dish3);
                    mDishes.add(dish4);
                    mDishes.add(dish5);
                    mDishes.add(dish6);
                    mDishes.add(dish7);
                    mDishes.add(dish8);

                    loadAdapter(list);
                }
            };

            // Ejecuto dishDownloader
            dishDownloader.execute(urlString);

            return;
        }

        loadAdapter(list);
    }


    private void loadAdapter(ListView list) {

        // MODELO DE PRUEBA
        LinkedList<Table> tables = new LinkedList<>();
        tables.add(new Table("Mesa 1"));
        tables.add(new Table("Mesa 2"));
        tables.add(new Table("Mesa 3"));


        // Creamos adapter con la lista de platos
        ArrayAdapter<Dish> adapter = new ArrayAdapter<Dish>(
                this,
                android.R.layout.simple_list_item_1,
                mDishes
        );

        // Le pasamos el adapter al ListView para que rellene la lista
        list.setAdapter(adapter);

        return;


    }

    private Dishes downLoadDishList(String urlString) {

        URL url = null;
        InputStream input = null;

        try {
            url = new URL(urlString);
            // Descargamos el json con la lista
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.connect();
            byte data[] = new byte[1024];
            int downloadedBytes;
            input = con.getInputStream();
            // Metemos el json descargado en un StringBuilder
            StringBuilder sb = new StringBuilder();
            while ((downloadedBytes = input.read(data)) != -1) {
                sb.append(new String(data, 0, downloadedBytes));
            }

            // Convertimos JSON en mDish
            JSONArray jsonArray = new JSONArray(sb.toString());

            Dishes dishes = new Dishes();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonDish = jsonArray.getJSONObject(i);

                String name = jsonDish.getString("dishName");
                String imageName = jsonDish.getString("imageName");
                String ingredients = jsonDish.getString("ingredients");
                Double price = jsonDish.getDouble("price");

                Dish dish = new Dish(name, ingredients, price, imageName);

                dishes.add(dish);
            }
            return dishes;

        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;

    }


    public void OnDishSelected(Dish dish) {

        Intent intent = new Intent(this, DishDetailActivity.class);
        // intent.putExtra(DishDetailActivity.EXTRA_DISH_SELECT, dish);
        intent.putExtra(DishDetailActivity.EXTRA_DISH_SELECT, dish);
        intent.putExtra(DishDetailActivity.EXTRA_INDEX_TABLES, indexTableActive);
        startActivity(intent);

    }

}

