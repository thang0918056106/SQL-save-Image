package vovanthang.vvt.sqlsaveimage;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URI;

public class ThemDoVatActivitu extends AppCompatActivity {
    TextView txtThem;
    EditText edtTen , edtMota;
    ImageButton imgCamera , imgFolder;
    ImageView imgView;
    Button btnAdd , btnCancel;
    int REQUEST_CODE_CAMERA = 123;
    int REQUEST_CODE_FOLDER = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_do_vat_activitu);

        AnhXa();

        imgCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent , REQUEST_CODE_CAMERA);
            }
        });

        imgFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE_FOLDER );
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgView.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG , 100, byteArrayOutputStream);
                byte[] HinhAnh =  byteArrayOutputStream.toByteArray();

                MainActivity.database.INSERT_DOVAT(edtTen.getText().toString().trim(),
                        edtMota.getText().toString().trim(),
                        HinhAnh);
                Toast.makeText(ThemDoVatActivitu.this, "Đã thêm", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ThemDoVatActivitu.this,MainActivity.class));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK && data != null ){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgView.setImageBitmap(bitmap);
        }

        if(requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void AnhXa() {
        txtThem = (TextView) findViewById(R.id.txtThem);
        edtTen = (EditText) findViewById(R.id.edtTen);
        edtMota = (EditText)findViewById(R.id.edtMota);
        imgCamera = (ImageButton) findViewById(R.id.imgCamera);
        imgFolder = (ImageButton) findViewById(R.id.imgFolder);
        imgView = (ImageView) findViewById(R.id.imgView);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnCancel = (Button) findViewById(R.id.btnCancel);
    }
}
