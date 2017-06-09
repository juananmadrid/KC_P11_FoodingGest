package es.kronox.foodinggest.fragment;


import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import es.kronox.foodinggest.R;
import es.kronox.foodinggest.model.Table;
import es.kronox.foodinggest.model.Tables;


public class TableViewPagerFragment extends Fragment {

    private static final String ARG_INITIAL_TABLE_INDEX = "ARG_INITIAL_TABLE_INDEX";

    private int mInitialTableIndex = 0;
    private ViewPager mPager;
    private Tables mTables;

    public static TableViewPagerFragment newInstance(int  initial_table_index) {
        TableViewPagerFragment fragment = new TableViewPagerFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_INITIAL_TABLE_INDEX, initial_table_index);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        return root;

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


//    @Override
//    public CharSequence getPageTitle(int position) {
//        return super.getPageTitle(position);
//
//        Table table = mTables.getTable(position);
//        String tableName = table.getName();
//
//        return tableName;
//
//    }

}
