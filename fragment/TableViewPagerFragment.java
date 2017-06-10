package es.kronox.foodinggest.fragment;


import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import es.kronox.foodinggest.R;
import es.kronox.foodinggest.model.Table;
import es.kronox.foodinggest.model.Tables;

import static android.content.ContentValues.TAG;


public class TableViewPagerFragment extends Fragment {

    private static final String ARG_INITIAL_TABLE_INDEX = "ARG_INITIAL_TABLE_INDEX";

    private int mInitialTableIndex = 0;
    private ViewPager mPager;
    private Tables mTables;

    public static TableViewPagerFragment newInstance(int  initialTableIndex) {
        TableViewPagerFragment fragment = new TableViewPagerFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(ARG_INITIAL_TABLE_INDEX, initialTableIndex);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        if (getArguments() != null) {
            mInitialTableIndex = getArguments().getInt(ARG_INITIAL_TABLE_INDEX);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_table_view_pager, container, false);

        // Accedemos a la vista (ViewPager)
        mPager = (ViewPager) root.findViewById(R.id.view_pager);
        // Accedemos al modelo de mesas
        mTables = Tables.getInstance();
        // Creamos Adapter
        TableViewPagerAdapter adapter = new TableViewPagerAdapter(getFragmentManager(), mTables);
        // Asignamos Adapter
        mPager.setAdapter(adapter);

        // Nos enteramos de que cambia la pagina del ViewPager usando método addOnPageChangeListener
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                updateTable(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mPager.setCurrentItem(mInitialTableIndex);
        updateTable(mInitialTableIndex);

        return root;

    }

    public void updateTable (int tableIndex){
        String tableName = mTables.getTable(tableIndex).getName();
        if (getActivity() instanceof AppCompatActivity) {
            AppCompatActivity parentActivity = (AppCompatActivity) getActivity();
            ActionBar toolbar = parentActivity.getSupportActionBar();
            if (toolbar != null) {
                toolbar.setTitle(tableName);
            }
        }
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_table, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean superReturn = super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.menu_table_bill) {
            Log.v(TAG, "Pulsada opcion menu Bill");
            return true;
        }

        return superReturn;
    }
}

class TableViewPagerAdapter extends FragmentPagerAdapter {

    private Tables mTables;

    public TableViewPagerAdapter(FragmentManager fm, Tables tables) {
        super(fm);
        mTables = tables;
    }

    @Override
    public Fragment getItem(int position) {

        // TableFragment fragment = TableFragment.newInstance(mTables.getTable(position));
        TableFragment fragment = TableFragment.newInstance(mTables.getTable(position));
        return fragment;
    }

    @Override
    public int getCount() {
        return mTables.getCount();
    }


    @Override
    public CharSequence getPageTitle(int position) {
        super.getPageTitle(position);

        Table table = mTables.getTable(position);
        String tableName = table.getName();

        return tableName;

    }

}
