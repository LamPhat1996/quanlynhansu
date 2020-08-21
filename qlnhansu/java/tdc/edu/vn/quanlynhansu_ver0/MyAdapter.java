package tdc.edu.vn.quanlynhansu_ver0;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.RequiresApi;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter<NhanSu> {
    private Activity context;
    private int layoutID;
    private ArrayList<NhanSu> listMembers;

    public MyAdapter(Activity context, int resource, ArrayList<NhanSu> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutID = resource;
        this.listMembers = objects;
    }
//tao viewholder để túm lấy các phần tử của convertview bên ggetView
    static class Viewholder
    {
        ImageView imgDegree ;
        TextView lblName;
        TextView lblHoppies ;
        CheckBox chkListItem;
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
//    convertView có tố chất di chuyển phần tử.
    {
        final Viewholder viewholder;
        //kiểm tra convertView rổng thì tạo mới
        if ( convertView==null )
        {
            //tạo phần tử mới cho listView
            viewholder = new Viewholder();
            convertView =context.getLayoutInflater().inflate(layoutID,parent,false);
            viewholder.imgDegree= convertView.findViewById(R.id.imgDegree);
            viewholder.lblName  = convertView.findViewById(R.id.lblName);
            viewholder.lblHoppies = convertView.findViewById(R.id.lblHoppies);
            viewholder.chkListItem = convertView.findViewById(R.id.chkListItem);
            //gắn viewholder vào convertView tương thích ( link the viewholder with the coresponding convertview )
            convertView.setTag(viewholder);
        }
        else
        {
            viewholder =(Viewholder)convertView.getTag();
            //xóa cái check khi cuộn thanh tab bên hông// chỉ về vị trí ban đầu của check
            viewholder.chkListItem.setChecked(listMembers.get(position).isCheck());
        }
        NhanSu nhanSu = listMembers.get(position);
        viewholder.lblName.setText(nhanSu.getName());
        viewholder.lblHoppies.setText(nhanSu.getHoppies());
        if(nhanSu.getDegree().equalsIgnoreCase(MainActivity.TC))
        {
            viewholder.imgDegree.setBackground(context.getResources().getDrawable(R.mipmap.trungcap));
        }
        else if (nhanSu.getDegree().equalsIgnoreCase(MainActivity.CD))
        {
            viewholder.imgDegree.setBackground(context.getResources().getDrawable(R.mipmap.caodang));
        }
        else if (nhanSu.getDegree().equalsIgnoreCase(MainActivity.DH))
        {
            viewholder.imgDegree.setBackground(context.getResources().getDrawable(R.mipmap.daihoc));
        }
        else
        {
            viewholder.imgDegree.setBackground(context.getResources().getDrawable(R.mipmap.khongbangcap));
        }
//nút check để xử lý sự kiện xóa of sửa.
        viewholder.chkListItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(viewholder.chkListItem.isChecked())
                    {
                        //tại thời điểm postion
                        listMembers.get(position).setCheck(true);
                    }
                    else
                    {
                        listMembers.get(position).setCheck(false);
                    }
                }
        });
        return convertView;
    }
}
