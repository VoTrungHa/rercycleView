package vn.edu.ntu.votrungha.recycleview;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MyCartActivity extends AppCompatActivity {

    TextView txtSanpham;
    Button btnTrangChu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        addView();
    }

    public void addView()
    {
        Intent intent = getIntent();
        String s = intent.getStringExtra("bundle1");
        txtSanpham=findViewById(R.id.sanpham);
        txtSanpham.setText(s);
        btnTrangChu=findViewById(R.id.trangchu);
    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == 100) {
//            if(resultCode == MyCartActivity.RESULT_OK){
//                String result=data.getStringExtra("result");
//                Toast.makeText(MyCartActivity.this,result,Toast.LENGTH_SHORT).show();
//            }
//            if (resultCode == MyCartActivity.RESULT_CANCELED) {
//                //Write your code if there's no result
//            }
//        }
//    }//onActivityResult



}
