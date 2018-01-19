package test.fandb.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import test.fandb.MainActivity;
import test.fandb.R;
import test.fandb.class_files.pojo.ProductList;

/**
 * Created by yellamobile-android on 19/01/18.
 */

public  class FoodCartRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    ArrayList<ProductList> productListArrayList=new ArrayList<>();

    public FoodCartRecyclerAdapter(Activity mainActivity, ArrayList<ProductList> productListArrayList) {
        this.productListArrayList=productListArrayList;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.food_cart_recycler_text_view,parent,false);
        FoodCartViewHolder homePageViewHolder=new FoodCartViewHolder(view);
        return homePageViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof FoodCartViewHolder)
        {
            FoodCartViewHolder foodCartViewHolder = (FoodCartViewHolder)holder;
            foodCartViewHolder.productNameTextView.setText(productListArrayList.get(position).getProductName()+" ("+productListArrayList.get(position).getQty()+")");
            foodCartViewHolder.productQtyTextView.setText(""+(productListArrayList.get(position).getQty()*productListArrayList.get(position).getProductPrice()));
        }
    }

    @Override
    public int getItemCount() {
        return productListArrayList.size();
    }

    public class FoodCartViewHolder extends RecyclerView.ViewHolder
    {
        TextView productNameTextView,productQtyTextView;

        public FoodCartViewHolder(View itemView) {
            super(itemView);

            productNameTextView = (TextView)itemView.findViewById(R.id.productNameTextView);
            productQtyTextView = (TextView)itemView.findViewById(R.id.productQtyTextView);

        }
    }
}
