package vovanthang.vvt.sqlsaveimage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnThem;
    public static Database database;
    ListView listView;
    DovatAdapter adapter;
    ArrayList<DoVat> doVatArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnThem = (Button) findViewById(R.id.btnThêm);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ThemDoVatActivitu.class));
            }
        });
        database = new Database(MainActivity.this,"Quanly.sqlite" , null,1);

        database.Query("CREATE TABLE IF NOT EXISTS DanhSachDoVat(Id INTEGER PRIMARY KEY AUTOINCREMENT , Ten VARCHAR(200), Mota VARCHAR(200),HinhAnh BLOB)");

        listView = (ListView) findViewById(R.id.listviewMain);
        doVatArrayList = new ArrayList<>();
        adapter = new DovatAdapter(MainActivity.this,R.layout.dong_dovat , doVatArrayList);

        Cursor cursor = database.GetData("SELECT * FROM DanhSachDoVat");
        while (cursor.moveToNext()){
            doVatArrayList.add(new DoVat(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getBlob(3)));
        }
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);
    }


}
