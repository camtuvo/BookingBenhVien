package com.hothithuhop.khambenhtructuyen.TrangChu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hothithuhop.khambenhtructuyen.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class imageSlideAdapter extends SliderViewAdapter<imageSlideAdapter.MyViewHolder>{
  List<Integer>imagerList;
  imageSlideAdapter(List<Integer>list)
  {
      this.imagerList=list;

  }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent) {
      View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
viewHolder.imageView.setImageResource(imagerList.get(position));

  }

    @Override
    public int getCount() {
        return imagerList.size();
    }

   class MyViewHolder extends SliderViewAdapter.ViewHolder
{
    ImageView imageView;
    public  MyViewHolder(View itemView)
    {
        super(itemView);
        imageView =itemView.findViewById(R.id.imageView);

    }

}
}
