package test.fandb;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

import test.fandb.adapter.FoodPageRecyclerAdapter;
import test.fandb.class_files.pojo.ProductList;

/**
 * Created by yellamobile-android on 19/01/18.
 */

public class FoodPagerFragment extends Fragment
{
    ProductList productList;
    ArrayList<ProductList> productListArrayList=new ArrayList<>();

    FoodPageRecyclerAdapter foodPageRecyclerAdapter;
    RecyclerView foodRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.food_fragment_layout,container,false);

        foodRecyclerView = (RecyclerView)view.findViewById(R.id.foodRecyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        foodRecyclerView.setLayoutManager(linearLayoutManager);

        productListArrayList.clear();

        productList = new ProductList("Mexican Rice with burger",15.0f,0,"AED");
        productListArrayList.add(productList);

        productList = new ProductList("Coke Combo",10.0f,0,"AED");
        productListArrayList.add(productList);

        foodPageRecyclerAdapter = new FoodPageRecyclerAdapter(getActivity(),productListArrayList);
        foodRecyclerView.setAdapter(foodPageRecyclerAdapter);

        foodPageRecyclerAdapter.SetOnItemClickListener(new FoodPageRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                switch (view.getId())
                {
                    case R.id.minusQtyLayout:

                        if(productListArrayList.get(position).getQty()>0)
                        {
                            productListArrayList.get(position).setQty(productListArrayList.get(position).getQty()-1);
                            foodPageRecyclerAdapter.notifyItemChanged(position);

                            addItems(productListArrayList);
                        }

                        break;

                    case R.id.plusQtyLayout:
                        productListArrayList.get(position).setQty(productListArrayList.get(position).getQty()+1);
                        foodPageRecyclerAdapter.notifyItemChanged(position);

                        addItems(productListArrayList);

                        break;
                }

            }
        });

        return view;
    }

    public void addItems(ArrayList<ProductList> productListArrayList)
    {
        Intent localIntent = new Intent();
        localIntent.setAction("ProductChanged");
        localIntent.putParcelableArrayListExtra("product_list",productListArrayList);
        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(localIntent);
    }
}
