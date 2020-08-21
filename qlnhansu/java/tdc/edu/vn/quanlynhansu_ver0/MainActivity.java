package tdc.edu.vn.quanlynhansu_ver0;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

import tdc.edu.vn.quanlynhansu_ver0.database_layer.MyDatabase;

public class MainActivity extends AppCompatActivity {
    private ArrayList<NhanSu> listMembers;
    //private ArrayAdapter<NhanSu> adapter;
    private MyAdapter adapter;
    public static String DH = "Dai Hoc";
    public static String CD = "Cao Dang";
    public static String TC = "Trung Cap";
    public static String KBC = "Khong Bang Cap";
    //database
    private MyDatabase DAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //database
        DAO = new MyDatabase(this);
        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnExit = findViewById(R.id.btnExit);
        final EditText edtName = findViewById(R.id.edtHoVaTen);
        final RadioGroup rdGroup = findViewById(R.id.GroupRadioButton);
        final CheckBox chkDocSach = findViewById(R.id.ckbDocSach);
        final CheckBox chkXemPhim = findViewById(R.id.ckbXemPhim);
        final EditText edtKhac = findViewById(R.id.edtKhac);

        ListView listView =  findViewById(R.id.ListMembers);
        listMembers = new ArrayList<NhanSu>();
        //Database
        DAO.getMembers(listMembers);
//       adapter = new ArrayAdapter<NhanSu>(this,android.R.layout.simple_list_item_1,listMembers);
        adapter = new MyAdapter(this,R.layout.list_item_layout,listMembers);
        listView.setAdapter(adapter);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //thu thap thong tin nhan su -> tao ra doi tuong nhan su -> ep' vao mang du lieu ->
                String Name = edtName.getText().toString();
                String degree = "";
                String hoppies= "";
                if(!edtName.getText().toString().isEmpty())
                {
                    if(chkDocSach.isChecked())
                    {
                        hoppies += chkDocSach.getText();
                        if(chkXemPhim.isChecked() || !edtKhac.getText().toString().isEmpty())
                        {
                            hoppies +=";";
                        }
                    }
                    if(chkXemPhim.isChecked())
                    {
                        hoppies += chkXemPhim.getText();
                        if(!edtKhac.getText().toString().isEmpty())
                        {
                            hoppies+=";";
                        }
                    }
                    if(!edtKhac.getText().toString().isEmpty())
                    {
                        hoppies += edtKhac.getText().toString();
                    }
                }
                //check radiobutton
                int radId = rdGroup.getCheckedRadioButtonId();
                switch (radId)
                {
                    case R.id.rdbTrungCap:degree = TC;
                        break;
                    case R.id.rdbCaoDang:degree = CD;
                        break;
                    case R.id.rdbDaiHoc:degree = DH;
                    default:
                        degree = KBC;
                }
                NhanSu nhanSu = new NhanSu(Name,degree,hoppies);
                listMembers.add(nhanSu);
                adapter.notifyDataSetChanged();

                //xoa cac du lieu bang dau danh vao
                edtName.setText("");
                rdGroup.clearCheck();
                chkDocSach.setChecked(false);
                chkXemPhim.setChecked(false);
                edtKhac.setText("");
                edtName.requestFocus();
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
    //cho thanh menu bên menu_layout hiển thị ra trong activity_main
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout,menu);
        return true;
    }
    // xử lý nút trong thanh menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuID = item.getItemId();
        switch (menuID){
            case R.id.menuItemRemove: remove();
                break;
            case R.id.menuItemUpdate: update();
                break;
            case R.id.menuItemSave: save();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //hàm xóa
    private  void remove(){
        Toast.makeText(this,"remove",Toast.LENGTH_SHORT).show();
        for( int i=0;i< listMembers.size(); i++)
        {
            if(listMembers.get(i).isCheck())
            {
                DAO.removeMember(listMembers.get(i));
                listMembers.remove(i);
                i--;
            }
        }
        adapter.notifyDataSetChanged();
    }
    //hàm sửa
    private  void update(){
        Toast.makeText(this,"update",Toast.LENGTH_SHORT).show();
    }
    //hàm lưu
    private  void save(){
        //hiển thị ra cho xem chơi vui thôi :))))
        Toast.makeText(this,"save",Toast.LENGTH_SHORT).show();
        //ghi toàn bộ giữa liệu vào data
        DAO.saveMembers(listMembers);
    }
}
