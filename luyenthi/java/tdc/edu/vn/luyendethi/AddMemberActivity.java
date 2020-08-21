package tdc.edu.vn.luyendethi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

public class AddMemberActivity extends AppCompatActivity {
    DataSource dataSource = new DataSource();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);
        Button back = findViewById(R.id.btnthoat);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            onBackPressed();
            }
        });

        final EditText hoten = findViewById(R.id.edtNhapHoTen);
        final RadioButton nam = findViewById(R.id.rbNam);
        RadioButton nu = findViewById(R.id.rbNu);
        final CheckBox docsach = findViewById(R.id.cbDocSach);
        final CheckBox xemphim = findViewById(R.id.cbXemPhim);
        final EditText diachi = findViewById(R.id.etNhapNoiSinh);
        Button add = findViewById(R.id.btnthem);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sothich = "";
                if(docsach.isChecked() && xemphim.isChecked()){
                    sothich = docsach.getText().toString() + "," + xemphim.getText().toString();
                }else if (docsach.isChecked()){
                    sothich = docsach.getText().toString();
                }else if(xemphim.isChecked())
                {
                    sothich = xemphim.getText().toString();
                }
                String gioitinh = "";
                if(nam.isChecked()){
                    gioitinh = "Nam";
                }
                else {
                    gioitinh = "Nu";
                }
                dataSource.them(getData(hoten.getText().toString(),gioitinh
                        ,diachi.getText().toString(),sothich));
                Toast.makeText(getApplicationContext(),"them thanh cong",Toast.LENGTH_LONG).show();
            }

        });
    }
    public Person getData(String hoten,String gioitinh,String noisinh,String sothich){
     return new Person(hoten,gioitinh,noisinh,sothich);
    }
}
