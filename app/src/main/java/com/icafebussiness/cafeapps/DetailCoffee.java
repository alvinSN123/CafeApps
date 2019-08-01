package com.icafebussiness.cafeapps;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.icafebussiness.cafeapps.R;
import com.icafebussiness.cafeapps.model.Model_Food;
import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jcminarro.roundkornerlayout.RoundKornerRelativeLayout;

public class DetailCoffee extends AppCompatActivity {

    TextView txt_detail_judul;
    ImageView img_detail_coffee;
    RecyclerView recycler_container_kopi;
    RoundKornerRelativeLayout korner_detail_coffe;
    FirebaseDatabase coffeDatabase;
    DatabaseReference coffee;
    String coffeId= "";

    //Nama : Alvin Satria Nugraha
    //Nim : 10116063

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_coffee);


        coffeDatabase = FirebaseDatabase.getInstance();
        coffee = coffeDatabase.getReference("Data");
        txt_detail_judul = findViewById(R.id.txt_detail_judul);
        img_detail_coffee = findViewById(R.id.img_detail_coffee);

        if (getIntent() != null){
            coffeId = getIntent().getStringExtra("DetailCoffe");

            if (!coffeId.isEmpty()){
                getDetailCofee(coffeId);
            }

        }

    }

    private void getDetailCofee(String coffeId) {

        coffee.child(coffeId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Model_Food model_food = dataSnapshot.getValue(Model_Food.class);

                txt_detail_judul.setText(model_food.getTitle());

                Glide.with(getBaseContext()).load(model_food.getImage()).into(img_detail_coffee);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
