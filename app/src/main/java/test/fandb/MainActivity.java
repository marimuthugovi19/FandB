package test.fandb;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.media.Image;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import test.fandb.adapter.FoodCartRecyclerAdapter;
import test.fandb.adapter.PagerAdapter;
import test.fandb.class_files.pojo.ProductList;
import test.fandb.class_files.sliding_tabs.SlidingTabLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    PagerAdapter pagerAdapter;

    SlidingTabLayout foodCategorySlidingTabs;
    ViewPager foodsViewPagers;
    String[] array;

    TextView foodTotalPriceTextView;
    RecyclerView foodCartRecyclerView;
    FoodCartRecyclerAdapter foodCartRecyclerAdapter;

    ImageView rotationImageView;
    LinearLayout clickToShowLayout,expandedLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        array = getResources().getStringArray(R.array.home_sliders);

        foodCategorySlidingTabs = (SlidingTabLayout)findViewById(R.id.foodCategorySlidingTabs);
        foodsViewPagers = (ViewPager)findViewById(R.id.foodsViewPagers);

        foodCategorySlidingTabs.setSlidingTabView(R.layout.sliding_tab_view,R.id.slidingTextView,array,R.id.selectView);

        pagerAdapter = new test.fandb.adapter.PagerAdapter(getSupportFragmentManager());
        foodsViewPagers.setAdapter(pagerAdapter);

        foodCategorySlidingTabs.setSelectedIndicatorColors(Color.parseColor("#EEEEEE"));
        foodCategorySlidingTabs.setViewPager(foodsViewPagers);

        clickToShowLayout=(LinearLayout)findViewById(R.id.clickToShowLayout);
        expandedLayout=(LinearLayout)findViewById(R.id.expandedLayout);

        foodTotalPriceTextView = (TextView)findViewById(R.id.foodTotalPriceTextView);
        foodCartRecyclerView = (RecyclerView)findViewById(R.id.foodCartRecyclerView);

        rotationImageView = (ImageView)findViewById(R.id.rotationImageView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        foodCartRecyclerView.setLayoutManager(linearLayoutManager);


        clickToShowLayout.setOnClickListener(this);

        IntentFilter filter = new IntentFilter();
        filter.addAction("ProductChanged");
        LocalBroadcastManager.getInstance(MainActivity.this).registerReceiver(br,filter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(MainActivity.this).unregisterReceiver(br);
    }

    private BroadcastReceiver br = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            if(intent.getAction().equalsIgnoreCase("ProductChanged"))
            {
                ArrayList<ProductList> productListArrayList = intent.getParcelableArrayListExtra("product_list");

                ArrayList<ProductList> productListArrayList1=new ArrayList<>();

                for (int i=0;i<productListArrayList.size();i++)
                {
                    if(productListArrayList.get(i).getQty()>0)
                    {
                        productListArrayList1.add(productListArrayList.get(i));
                    }
                }

                if(!productListArrayList1.isEmpty())
                {
                    foodCartRecyclerAdapter = new FoodCartRecyclerAdapter(MainActivity.this,productListArrayList1);
                    foodCartRecyclerView.setAdapter(foodCartRecyclerAdapter);

                    int totalAmount=0;
                    for (int i=0;i<productListArrayList.size();i++)
                    {
                        totalAmount = totalAmount+ (productListArrayList.get(i).getQty()*Math.round(productListArrayList.get(i).getProductPrice())) ;
                    }

                    foodTotalPriceTextView.setText(productListArrayList.get(0).getCurrency()+" "+totalAmount);
                }
                else
                {
                    foodCartRecyclerView.setVisibility(View.GONE);
                    foodTotalPriceTextView.setText(productListArrayList.get(0).getCurrency()+" 0");
                }
            }



        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.clickToShowLayout:

                if(expandedLayout.getVisibility() == View.VISIBLE)
                {
                    rotationImageView.setRotation(90);
                    expandedLayout.setVisibility(View.GONE);
                }
                else
                {
                    rotationImageView.setRotation(270);
                    expandedLayout.setVisibility(View.VISIBLE);
                }

                break;
        }

    }
}
