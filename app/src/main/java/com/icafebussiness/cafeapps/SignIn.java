package com.icafebussiness.cafeapps;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.icafebussiness.cafeapps.R;
import com.icafebussiness.cafeapps.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignIn extends AppCompatActivity {

    EditText edt_phone,edt_pass;
    Button btn_signin;

    //Nama : Alvin Satria Nugraha
    //Nim : 10116063

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edt_phone = findViewById(R.id.edt_phone);
        edt_pass = findViewById(R.id.edt_pass);
        btn_signin = findViewById(R.id.btn_signin);


        //Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ProgressDialog mDialog = new ProgressDialog(SignIn.this);
                mDialog.setMessage("Please Waiting....");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {


                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        //cek database jika kluar database
                        if (dataSnapshot.child(edt_phone.getText().toString()).exists()) {

                            //get user informasi
                            mDialog.dismiss();
                            User user = dataSnapshot.child(edt_phone.getText().toString()).getValue(User.class);
                            if (user.getPassword().equals(edt_pass.getText().toString())) {
                                Toast.makeText(SignIn.this, "Sign In Successfully !", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(SignIn.this, "Sign In Failed!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        else
                        {
                            mDialog.dismiss();
                            Toast.makeText(SignIn.this, "User Not exits", Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });


    }
}
