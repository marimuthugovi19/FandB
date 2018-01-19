package test.fandb.adapter;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import test.fandb.R;
import test.fandb.class_files.pojo.ProductList;

/**
 * Created by yellamobile-android on 19/01/18.
 */

public class FoodPageRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    Activity activity;
    OnItemClickListener mListener;
    ArrayList<ProductList> productListArrayList = new ArrayList<>();

    public FoodPageRecyclerAdapter(Activity activity, ArrayList<ProductList> productListArrayList) {
        this.activity = activity;
        this.productListArrayList = productListArrayList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.food_recycler_adapter,parent,false);
        FoodViewHolder homePageViewHolder=new FoodViewHolder(view);
        return homePageViewHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof FoodViewHolder)
        {
            FoodViewHolder foodViewHolder = (FoodViewHolder)holder;

            foodViewHolder.productNameTextView.setText(productListArrayList.get(position).getProductName());
            foodViewHolder.productPriceTextView.setText(productListArrayList.get(position).getCurrency()+ " "+productListArrayList.get(position).getProductPrice());

            foodViewHolder.qtyTextView.setText(""+productListArrayList.get(position).getQty());

        }

    }


    @Override
    public int getItemCount() {
        return productListArrayList.size();
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        LinearLayout minusQtyLayout,plusQtyLayout;
        TextView qtyTextView,productNameTextView,productPriceTextView;

        public FoodViewHolder(View itemView) {
            super(itemView);

            plusQtyLayout = (LinearLayout)itemView.findViewById(R.id.plusQtyLayout);
            minusQtyLayout = (LinearLayout)itemView.findViewById(R.id.minusQtyLayout);

            qtyTextView = (TextView)itemView.findViewById(R.id.qtyTextView);
            productNameTextView = (TextView)itemView.findViewById(R.id.productNameTextView);
            productPriceTextView = (TextView)itemView.findViewById(R.id.productPriceTextView);

            plusQtyLayout.setOnClickListener(this);
            minusQtyLayout.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (mListener != null) {
                mListener.onItemClick(v, getPosition());
            }
        }
    }

    public interface OnItemClickListener
    {
        public void onItemClick(View view, int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener)
    {
        this.mListener = mItemClickListener;
    }
}
