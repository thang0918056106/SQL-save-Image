package vovanthang.vvt.sqlsaveimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DovatAdapter extends BaseAdapter {
    private Context context;
    private  int layout;
    ArrayList<DoVat> doVatArrayList;

    public DovatAdapter(Context context, int layout, ArrayList<DoVat> doVatArrayList) {
        this.context = context;
        this.layout = layout;
        this.doVatArrayList = doVatArrayList;
    }

    @Override
    public int getCount() {
        return doVatArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        ImageView img;
        TextView txtTen , txtMota;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder ;
            if(view == null) {
                holder = new ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(context);
                view = inflater.inflate(layout, null);
                holder.txtTen = (TextView) view.findViewById(R.id.txtTen);
                holder.txtMota = (TextView) view.findViewById(R.id.txtMoTa);
                holder.img = (ImageView) view.findViewById(R.id.imgHinh);
                view.setTag(holder);
            }
            else {
                holder = (ViewHolder) view.getTag();
            }

            DoVat doVat = doVatArrayList.get(i);
            holder.txtTen.setText(doVat.getTen());
            holder.txtMota.setText(doVat.getTuoi());
            //chuyen mang byte sang kieu bitmap
        byte [] HinhAnh = doVat.getHinh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(HinhAnh, 0 , HinhAnh.length);

        holder.img.setImageBitmap(bitmap);
        return view;
    }
}
