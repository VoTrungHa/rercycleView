package vn.edu.ntu.votrungha.recycleview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import vn.edu.ntu.votrungha.controller.CartController;
import vn.edu.ntu.votrungha.controller.ICartController;
import vn.edu.ntu.votrungha.model.Product;

public class ViewCartctivity extends AppCompatActivity {

    TextView txtproduct;
    Button btnadd,btndelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cartctivity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        addView();
        addEvent();
    }

    private void addEvent()
    {
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ICartController controller=(ICartController) getApplication();
                controller.clearShoppingCart();
                txtproduct.setText("bạn đã xóa sảm phẩm");
            }
        });
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewCartctivity.this ,MyCartActivity.class);
//                Bundle myBun = new Bundle();
//                myBun.putString("sub1", "Trinh Thanh Do 1");
                intent.putExtra("bundle1", txtproduct.getText());
//                setResult(ViewCartctivity.RESULT_OK, intent);
                startActivity(intent);
                finish();
            }
        });
    }

    private void addView()
    {

        txtproduct=findViewById(R.id.txtPruduct);
        btnadd=findViewById(R.id.btnaddCart);
        btndelete=findViewById(R.id.btnDeleteCart);
        viewCartInfo();
    }

    private void viewCartInfo()
    {
        ICartController controller=(ICartController) getApplication();
        ArrayList<Product>ListProCart=controller.getShoppingCart();
        StringBuilder builder=new StringBuilder();
        for(Product item:ListProCart)
        {
            builder.append(item.getName() + " "+item.getPrice()+"vnd\n");
        }
         if(builder.toString().length()>0)
         {
             txtproduct.setText(builder.toString());
         }
         else
         {
             txtproduct.setText("Không có sản phẩm nào");
         }

    }
}
