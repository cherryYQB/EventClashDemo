package yqb.com.example.eventclashdemo;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
    //布局标识集合
    private final List<Integer> typeList;

    //设置常量
    private static final int TYPE_IMG_THREE = 2;
    private static final int TYPE_IMG_RECYCLER_Two = 3;
    private static final int TYPE_IMG_RECYCLER_ONE = 1;
    private static final int TYPE_IMG_RECYCLER_FOUR = 4;
    private static final int TYPE_IMG_RECYCLER_Five = 5;

    public RecyclerViewAdapter(Context context, List<Integer> typeList) {
        this.context = context;
        this.typeList = typeList;
    }

    /**
     * 根据不同的position，设置不同的ViewType
     * position表示当前是第几个Item，通过position拿到当前的Item对象，然后判断这个item对象需要那种视图
     */
    @Override
    public int getItemViewType(int position) {
        if (typeList.get(position) == 2) {
            return TYPE_IMG_THREE;
        } else if (typeList.get(position) == 3) {
            return TYPE_IMG_RECYCLER_Two;
        } else if (typeList.get(position) == 1) {
            return TYPE_IMG_RECYCLER_ONE;
        } else if (typeList.get(position) == 4) {
            return TYPE_IMG_RECYCLER_FOUR;
        } else if (typeList.get(position) == 5) {
            return TYPE_IMG_RECYCLER_Five;
        }
        return 0;
    }

    //重写onCreateViewHolder方法，返回一个自定义的ViewHolder(当RecyclerView需要一个ViewHolder时会回调该方法，如果有可复用的View不会回调)
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_IMG_THREE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview1, parent, false);
            ThreeViewHolder threeViewHolder = new ThreeViewHolder(view);
            return threeViewHolder;
        } else if (viewType == TYPE_IMG_RECYCLER_Two) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview2_hrecyclerview, parent, false);
            hRecyclerViewHolder recyclerViewHolder = new hRecyclerViewHolder(view);
            return recyclerViewHolder;
        } else if (viewType == TYPE_IMG_RECYCLER_ONE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview3, parent, false);
            RecycleViewHolderOne recycleViewHolderOne = new RecycleViewHolderOne(view);
            return recycleViewHolderOne;
        } else if (viewType == TYPE_IMG_RECYCLER_FOUR) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview4, parent, false);
            RecycleViewHolderFour recycleViewHolderFour = new RecycleViewHolderFour(view);
            return recycleViewHolderFour;
        } else if (viewType == TYPE_IMG_RECYCLER_Five) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview5_vrecyclerview, parent, false);
            vRecyclerViewHolder recyclerViewHolder = new vRecyclerViewHolder(view);
            return recyclerViewHolder;
        }
        return null;
    }

    //填充onCreateViewHolder方法返回的holder中的控件(当一个View需要出现在屏幕上时，该方法会被回调，我们需要再该方法中根据数据来更改视图)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ThreeViewHolder) {
            setFloorTwo((ThreeViewHolder) holder, position);
        } else if (holder instanceof hRecyclerViewHolder) {
            setFloorThree((hRecyclerViewHolder) holder);
        } else if (holder instanceof RecycleViewHolderOne) {
            setOnePic((RecycleViewHolderOne) holder);
        } else if (holder instanceof RecycleViewHolderFour) {
            setFourPic((RecycleViewHolderFour) holder);
        } else if (holder instanceof vRecyclerViewHolder) {
            setFloorFive((vRecyclerViewHolder) holder);
        }
    }


    //获取数据的数量(告诉RecyclerView有多少个视图需要显示)
    @Override
    public int getItemCount() {
        return typeList.size();
    }

    private void setFourPic(RecycleViewHolderFour holder) {
        holder.iv_one.setImageResource(R.color.colorAccent);
        holder.iv_two.setImageResource(R.color.colorPrimary);
    }

    private void setOnePic(RecycleViewHolderOne holderOne) {
        holderOne.imageView.setImageResource(R.color.colorPrimary);
    }

    //设置二楼数据（显示3张图片）
    private void setFloorTwo(ThreeViewHolder holder, int position) {
        holder.tvTitle.setText("布局1");
    }

    //设置二楼数据（显示N张图片）
    private void setFloorThree(hRecyclerViewHolder holder) {
        setHRecyclerView(holder.RecyclerView);
    }

    private void setHRecyclerView(RecyclerView hRecyclerView) {

        HRecyclerViewAdapter hRecyclerViewAdapter = new HRecyclerViewAdapter(context);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        hRecyclerView.setLayoutManager(layoutManager);
        hRecyclerView.setHasFixedSize(false);
        hRecyclerView.setAdapter(hRecyclerViewAdapter);

        hRecyclerViewAdapter.setOnItemClickListener(new HRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(context, "你点击了" + position + "条", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //设置五楼数据（显示N张图片）
    private void setFloorFive(vRecyclerViewHolder holder) {
        setVRecyclerView(holder.RecyclerView);
    }

    private void setVRecyclerView(RecyclerView vRecyclerView) {
        VRecyclerViewAdapter vRecyclerViewAdapter = new VRecyclerViewAdapter(context);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        vRecyclerView.setLayoutManager(layoutManager);
        vRecyclerView.setHasFixedSize(false);
        vRecyclerView.setAdapter(vRecyclerViewAdapter);

        vRecyclerViewAdapter.setOnItemClickListener(new VRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(context, "你点击了" + position + "条", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //三张图片
    public class ThreeViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTitle;
        public ImageView ivOne, ivTwo, ivThree;

        public ThreeViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            ivOne = (ImageView) itemView.findViewById(R.id.iv_one);
            ivTwo = (ImageView) itemView.findViewById(R.id.iv_two);
            ivThree = (ImageView) itemView.findViewById(R.id.iv_three);
        }

    }

    //横向/纵向的RecyclerView
    public class hRecyclerViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTitle;
        public MyHRecyclerView RecyclerView;

        public hRecyclerViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            RecyclerView = (MyHRecyclerView) itemView.findViewById(R.id.recyclerview);
        }
    }

    public class vRecyclerViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTitle;
        public MyVRecyclerView RecyclerView;

        public vRecyclerViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            RecyclerView = (MyVRecyclerView) itemView.findViewById(R.id.recyclerview);
        }
    }


    public class RecycleViewHolderOne extends RecyclerView.ViewHolder {

        ImageView imageView;

        public RecycleViewHolderOne(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv_show_one);
        }
    }

    public class RecycleViewHolderFour extends RecyclerView.ViewHolder {
        ImageView iv_one, iv_two;

        public RecycleViewHolderFour(View itemView) {
            super(itemView);
            iv_one = (ImageView) itemView.findViewById(R.id.iv_one);
            iv_two = (ImageView) itemView.findViewById(R.id.iv_two);
        }
    }

    public class RecycleViewHolderFive extends RecyclerView.ViewHolder {
        ImageView iv_one, iv_two;

        public RecycleViewHolderFive(View itemView) {
            super(itemView);
            iv_one = (ImageView) itemView.findViewById(R.id.iv_one);
            iv_two = (ImageView) itemView.findViewById(R.id.iv_two);
        }
    }
}