package com.whitespace.sleepcycle;

import android.content.Context;
import android.service.quicksettings.Tile;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.List;

public class SliderAdapted extends RecyclerView.Adapter<SliderAdapted.SliderViewHolder> {


    private OnItemClickListener listener;
    private Context mContext;

    private List<SliderItem> sliderItems;
    private ViewPager2 viewPager2;

    public SliderAdapted(List<SliderItem> sliderItems, ViewPager2 viewPager2) {
        this.sliderItems = sliderItems;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        holder.setText(sliderItems.get(position));
        if (position == sliderItems.size() - 2){
            viewPager2.post(runnable);
        }else if (position == sliderItems.size() - 0){
            viewPager2.post(runnable);
        }
    }

    @Override
    public int getItemCount() {
        return sliderItems.size();
    }


    class SliderViewHolder extends RecyclerView.ViewHolder {

        private TextView Title, Age, Hours;

        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            Title = itemView.findViewById(R.id.title);
            Age = itemView.findViewById(R.id.ageRange);
            Hours = itemView.findViewById(R.id.recommendedHours);

            mContext = itemView.getContext();

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null) {
//                        listener.onItemClick();
                    }
                }
            });


        }

        void setText(SliderItem sliderItem) {
            Title.setText(sliderItem.getType());
            Age.setText(sliderItem.getAge());
            Hours.setText(sliderItem.getHours());
        }

    }

    public interface OnItemClickListener {
        void onItemClick(long item);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            sliderItems.addAll(sliderItems);
            notifyDataSetChanged();
        }
    };

}
