package vn.edu.ntu.votrungha.recycleview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import vn.edu.ntu.votrungha.controller.CartController;
import vn.edu.ntu.votrungha.controller.ICartController;
import vn.edu.ntu.votrungha.model.Product;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ICartController controller;
    // nguon du liệu
    ArrayList<Product> products;
    // adapter
    ProductAdapter adapter;
    // recycleView
    RecyclerView rvlistProduct;

    TextView txtsoluong;
    ImageView Imagemn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // nut back quay lại
        addView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {// tao menu trên thanh toolbar
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.mnu_cart,menu);
        // tim đối tượng menu chứa item.

        MenuItem cartmenu=menu.findItem(R.id.mnu_cart);//framlayout
        // lấy id trong menu
        txtsoluong=cartmenu.getActionView().findViewById(R.id.txtsoluong);
        Imagemn=cartmenu.getActionView().findViewById(R.id.Imagemn);

        txtsoluong.setText(controller.getCountCart());// set số lượng trong giỏ hàng khi không thêm xóa


        txtsoluong.setOnClickListener(this);
        Imagemn.setOnClickListener(this);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id =item.getItemId();// lấy ai id trong hệ thống
        switch (id)
        {
//            case  R.id.mnu_cart:
//                CallViewCart();
//                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void CallViewCart()// mở 1 activyti mới
    {
        Intent intent =new Intent(this,ViewCartctivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) { //sự kiện cho menu click vào giỏi hàng chuyển sang trang activity mới
        CallViewCart();//
    }





    private void addView()
    {
        controller=(ICartController) getApplication();
        rvlistProduct=findViewById((R.id.rvListProduct));
        rvlistProduct.setLayoutManager(new LinearLayoutManager(this));
        //context: actitvity, sovit,
        ICartController controller=(ICartController) getApplication();// phải khai bao name trong file mainifests
        products=controller.getListProduct();
        adapter=new ProductAdapter(products);
        rvlistProduct.setAdapter(adapter);

    }


    // lớp cài đặt cho việc hiển thị của 1 Product
    private class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        TextView txtName,txtPrice,txtDes;
        ImageButton imageButton;
        Product p;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName=this.itemView.findViewById(R.id.txtName);
            txtDes=this.itemView.findViewById(R.id.txtDesc);
            txtPrice=this.itemView.findViewById(R.id.txtPrice);
            imageButton=this.itemView.findViewById(R.id.imageButton);
             imageButton.setOnClickListener(this);

        }

        public void bind(Product p)
        {
            this.p=p;
            txtName.setText(p.getName());
            txtPrice.setText(new Integer(p.getPrice()).toString());
            txtDes.setText(p.getDesc());
            imageButton.setImageResource(R.drawable.ic_action_to_cart);

        }

        @Override
        public void onClick(View v) {

            if(!controller.addToShopping(p))
            {
                Toast.makeText(MainActivity.this,
                        "sp:"+p.getName()+" đã tồn tại",
                        Toast.LENGTH_SHORT).show();

            }
            else
            {
                Toast.makeText(MainActivity.this,
                        "đã thêm sản phẩm"+p.getName(),
                        Toast.LENGTH_SHORT).show();
                   txtsoluong.setText(controller.getCountCart());// mõi lần kích thêm số lượng hàng vào
            }

        }
    }

    //class adapter ket nối RecycleView và dữ liệu
    private class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder>
    {

        ArrayList<Product>products;

        public ProductAdapter(ArrayList<Product> products) {
            this.products = products;
        }

        @NonNull
        @Override
        // tạo một viewholder để hiển thị dữ liệu
        public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater=getLayoutInflater();// chuyển layout trong file xml thành 1 đối tượng.
            View view=layoutInflater.inflate(R.layout.product_item,parent,false);
            return new ProductViewHolder(view);// đưa giao diên trong view sang class product
        }
        //kết nối nguồn dũ liệu. trong danh sách với 1 viewholder.
        @Override
        public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
            // khi có 1 item trong produtes. nó sẽ đưa product hiển thị lên holder
            holder.bind(products.get(position));
        }

        @Override
        public int getItemCount() {// trả về số lương các product
            return products.size();
        }
    }
}
