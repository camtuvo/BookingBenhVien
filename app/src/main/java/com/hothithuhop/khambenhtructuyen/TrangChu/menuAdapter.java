package com.hothithuhop.khambenhtructuyen.TrangChu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class menuAdapter extends BaseAdapter {
    public menuAdapter(Context context, int layout, List<ItemMenu> list) {
        this.context = context;
        Layout = layout;
        this.list = list;
    }

    private Context context;
 private  int Layout;
 private List<ItemMenu>list;



    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int  position) {
        return 0;
    }
private  class ViewHolder{
        TextView tv_ten;
        ImageView img_menu;

}
    @Override
    public View getView(int position, View view, ViewGroup parent) {
//       ViewHolder viewHolder;

           LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           view=inflater.inflate(Layout,null);
//           viewHolder =new ViewHolder();
//
//           viewHolder.tv_ten =(TextView) view.findViewById(R.id.tv_ten);
//           viewHolder.img_menu=(ImageView) view.findViewById(R.id.img_view);
//           view.setTag(viewHolder);
//
//viewHolder.tv_ten.setText(list.get(position).itemName);
        return view;
    }}
