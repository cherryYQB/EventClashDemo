package yqb.com.example.eventclashdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class VRecyclerViewAdapter extends RecyclerView.Adapter<VRecyclerViewAdapter.HImageHolder>{

    private final Context context;
    private OnItemClickListener onItemClickListener;

    public VRecyclerViewAdapter(Context context) {
        this.context=context;
    }

    @Override
    public HImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_vrecyclerview, parent, false);
        HImageHolder hImageHolder = new HImageHolder(view);
        return hImageHolder;
    }

    @Override
    public void onBindViewHolder(final HImageHolder holder, int position) {

        holder.tvName.setText("aaa"+position);
        holder.tvDiscount.setText("bbb"+position);
        holder.tvPrice.setText("ccc"+position);
        if (onItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(holder.getLayoutPosition());
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class HImageHolder extends RecyclerView.ViewHolder{

        public ImageView imageView;
        // 折扣 名字 价格
        public TextView tvDiscount,tvName,tvPrice;

        public HImageHolder(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.imageview);
            tvDiscount= (TextView) itemView.findViewById(R.id.tv_discount);
            tvName= (TextView) itemView.findViewById(R.id.tv_name);
            tvPrice= (TextView) itemView.findViewById(R.id.tv_price);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
}
