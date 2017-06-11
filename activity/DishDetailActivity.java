package es.kronox.foodinggest.activity;

import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import es.kronox.foodinggest.R;
import es.kronox.foodinggest.model.Dish;
import es.kronox.foodinggest.model.Tables;

public class DishDetailActivity extends AppCompatActivity {

    public static final String EXTRA_INDEX_TABLES = "EXTRA_INDEX_TABLES";
    public static final String EXTRA_DISH_SELECT = "EXTRA_DISH_SELECT";

    Dish mDish;
    Tables mTables;
    int mIndexTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_detail);

        // Recibo el index y el dish seleccionado que me pasa por Intent DishListActivity
        int mIndexTable = getIntent().getIntExtra(EXTRA_INDEX_TABLES, 0);
        mDish = (Dish) getIntent().getSerializableExtra(EXTRA_DISH_SELECT);

        // Accedemos a las vistas de la interfaz
        TextView dishName = (TextView) findViewById(R.id.dish_name);
        TextView ingredients = (TextView) findViewById(R.id.dish_ingredients);
        TextView price = (TextView) findViewById(R.id.dish_price);
        TextView variants = (TextView) findViewById(R.id.textInputEditText);
        ImageView image = (ImageView) findViewById(R.id.dish_image);

        // Accedemos al modelo de mesas
        mTables = Tables.getInstance();

        // Sincronizamos vistas con modelo de plato
        dishName.setText(mDish.getName());
        ingredients.setText(mDish.getIngredients());
        price.setText(mDish.getPrice().toString());

        // Convertimos texto imageString en recurso drawable
        String imageName = mDish.getImageName();
        int imageResource = R.drawable.ico_restaurant;

        switch (imageName) {
            case "almejas.jpg": imageResource = R.drawable.almejas; break;
            case "arroz_a_banda.jpg": imageResource = R.drawable.arroz_a_banda; break;
            case "arroz_a_horno.jpg": imageResource = R.drawable.arroz_a_horno; break;
            case "brascada.jpg": imageResource = R.drawable.brascada; break;
            case "chivito.jpg": imageResource = R.drawable.chivito; break;
            case "ensalada_col_gam.jpg": imageResource = R.drawable.ensalada_col_gam; break;
            case "ensaladilla_rusa.jpg": imageResource = R.drawable.ensaladilla_rusa; break;
            case "fideua.jpg": imageResource = R.drawable.fideua; break;
        }
        image.setImageResource(imageResource);

        // Accedemos a la vista de los botones Accept y Cancel
        findViewById(R.id.accept_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acceptButton();
            }
        });

        findViewById(R.id.cancel_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelButton();
            }
        });

    }


    private void cancelButton() {
        finish();
    }

    private void acceptButton() {

        // Añado plato a la lista de esa mesa

        // mTables.addDish(mDish);
        mTables.getTable(mIndexTable).addDish(mDish);
        finish();

    }


}
