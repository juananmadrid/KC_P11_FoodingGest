package es.kronox.foodinggest.activity;

import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import es.kronox.foodinggest.R;
import es.kronox.foodinggest.model.Dish;
import es.kronox.foodinggest.model.Tables;

public class DishDetailActivity extends AppCompatActivity {

    public static final String EXTRA_INDEX_TABLES = "EXTRA_INDEX_TABLES";
    public static final String EXTRA_DISH_SELECT = "EXTRA_DISH_SELECT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_detail);

        Dish mDish;
        Tables mTables;

        // Recibo el index y el dish seleccionado que me pasa por Intent DishListActivity
        int indexTable = getIntent().getIntExtra(EXTRA_INDEX_TABLES, 0);
        mDish = (Dish) getIntent().getSerializableExtra(EXTRA_DISH_SELECT);

        // Modelo

        // mDish = new Dish("Pollo Braseado", "Polo, sal, pimienta, aderezos la pera, tomate...", (double) 10, "almejas.jpg");


        // Accedemos a las vistas de la interfaz
        TextView dishName = (TextView) findViewById(R.id.dish_name);
        TextView ingredients = (TextView) findViewById(R.id.dish_ingredients);
        TextView price = (TextView) findViewById(R.id.dish_price);
        TextView variants = (TextView) findViewById(R.id.textInputEditText);
        ImageView image = (ImageView) findViewById(R.id.dish_image);

        // Sincronizamos vistas con modelo
        dishName.setText(mDish.getName());
        ingredients.setText(mDish.getIngredients());
        price.setText(mDish.getPrice().toString());


        // Convertimos texto imageString en recurso drawable
        String imageName = mDish.getImageName();
        int imageResource = R.drawable.ico_restaurant;

        switch (imageName) {
            case "almejas.jpg": imageResource = R.drawable.almejas; break;
            case "arroz_a_banda.jpg": imageResource = R.drawable.arroz_a_banda; break;
            case "arroz_al_horno.jpg": imageResource = R.drawable.arroz_a_horno; break;
            case "brascada.jpg": imageResource = R.drawable.brascada; break;
            case "chivito.jpg": imageResource = R.drawable.chivito; break;
            case "ensalada_col_gam.jpg": imageResource = R.drawable.ensalada_col_gam; break;
            case "ensaladilla_rusa.jpg": imageResource = R.drawable.ensaladilla_rusa; break;
            case "fideua.jpg": imageResource = R.drawable.fideua; break;
        }
        image.setImageResource(imageResource);

    }
}
