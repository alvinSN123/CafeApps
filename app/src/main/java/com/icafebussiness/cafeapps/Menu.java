package com.icafebussiness.cafeapps;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.icafebussiness.cafeapps.R;
import com.icafebussiness.cafeapps.model.Model_Food;
import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class Menu extends AppCompatActivity {


    //Nama : Alvin Satria Nugraha
    //Nim : 10116063

    RecyclerView rvFood;
    private DatabaseReference userDatabase;
    private Button btn_cari;
    private EditText edt_cari;
    FirebaseRecyclerAdapter<Model_Food, UserViewHolder> dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        userDatabase = FirebaseDatabase.getInstance().getReference("Data");
        //setting firebase

        rvFood = findViewById(R.id.recycler_container);
        rvFood.setHasFixedSize(true);
        rvFood.setLayoutManager(new LinearLayoutManager(this));

        firebaseViewData(null);

        //implementasikan SearchButton
        btn_cari = findViewById(R.id.btn_cari);
        edt_cari = findViewById(R.id.edt_cari);

//        //impementasi login
//        btn_signin = (Button)findViewById(R.id.btn_signin);
//        btn_signup = (Button)findViewById(R.id.btn_signup);
//
//
//        btn_signin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//
//        btn_signup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//
//        btn_signin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent signIn = new Intent(Menu.this,SignIn.class);
//                startActivity(signIn);
//            }
//        });






        btn_cari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cari = edt_cari.getText().toString();
                firebaseViewData(cari);
            }
        });



    }







    private void firebaseViewData(String seacrhText) {

        Query firebaseSeacrh = userDatabase.orderByChild("title").startAt(seacrhText).endAt(seacrhText +"\uf8ff");


        dataAdapter = new FirebaseRecyclerAdapter<Model_Food, UserViewHolder>(

                Model_Food.class,
                R.layout.recycler_menu,
                UserViewHolder.class,
                firebaseSeacrh
        ) {
            @Override
            protected void populateViewHolder(UserViewHolder userViewHolder, Model_Food model_food, int i) {
                userViewHolder.setDetails(getApplicationContext(), model_food.getTitle(), model_food.getHarga(),
                        model_food.getNutrisi(), model_food.getImage());


                //Membuat userViewHolder
                userViewHolder.setItemClickListener(new itemClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Intent intent = new Intent(Menu.this, DetailCoffee.class);
                        intent.putExtra("DetailCoffe",dataAdapter.getRef(position).getKey());

                        startActivity(intent);

                    }
                });
            }
        };
        rvFood.setAdapter(dataAdapter);

    }

    public static class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        View view;
        //memanggil dari interface
        private com.icafebussiness.cafeapps.itemClickListener itemClickListener;

        //membuat method
        private void setItemClickListener(com.icafebussiness.cafeapps.itemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            view = itemView;

            //intent
            view.setOnClickListener(this);
        }


        public void setDetails(Context ctx, String title, String harga, String nutrisi, String image) {

            TextView txt_title = view.findViewById(R.id.txt_title_food);
            TextView txt_harga = view.findViewById(R.id.txt_harga_food);
            TextView txt_nutrisi = view.findViewById(R.id.txt_nutrisi_menu);
            ImageView img_food = view.findViewById(R.id.img_food);

            txt_title.setText(title);
            txt_harga.setText(harga);
            txt_nutrisi.setText(nutrisi);


            Glide.with(ctx).load(image).into(img_food);

        }


        //intent adapter
        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view, getAdapterPosition());

        }
    }

}
