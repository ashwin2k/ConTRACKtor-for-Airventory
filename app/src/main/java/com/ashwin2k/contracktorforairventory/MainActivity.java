package com.ashwin2k.contracktorforairventory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.alterac.blurkit.BlurKit;
import io.alterac.blurkit.BlurLayout;
import jp.wasabeef.blurry.Blurry;

public class MainActivity extends AppCompatActivity {
    RecyclerView contractlist;
    ArrayList<EquimentData> a=new ArrayList<>();
    FirebaseAuth mAuth;
    RecyclerAdapter adapter;
    RelativeLayout blur11;
    Context c;
    BlurLayout bl;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth=FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();
        BlurKit.init(this);

        c=this;
        bl=findViewById(R.id.blurLayout1);
        blur11=findViewById(R.id.blur_layout);



        adapter=new RecyclerAdapter(a, new RecyclerAdapter.clicklisten() {
            @Override
            public void doOnClick(final int pos) {
                Dialog d=new Dialog(c);

                Log.d("CLICKK",22+"");
                d.setContentView(R.layout.delivery_confirm);
                d.show();
                d.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                Log.d("ALPHA",bl.getAlpha()+"");
                bl.invalidate();
                bl.setVisibility(View.VISIBLE);
                d.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        bl.setVisibility(View.INVISIBLE);
                    }
                });
                AppCompatButton bconf=d.findViewById(R.id.confdeliv);
                bconf.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map mx=new HashMap();
                        mx.put("shelved",false);
                        mx.put("isAssigned",false);
                        db.collection("equipmentData").document(a.get(pos).getItem()).update(mx).addOnCompleteListener(new OnCompleteListener() {
                            @Override
                            public void onComplete(@NonNull Task task) {
                                Log.d("BTNFB",a.get(pos).getItem());
                                if(task.isSuccessful())

                                    Toast.makeText(c,"Reported successfully",Toast.LENGTH_LONG).show();

                            }
                        });
                    }
                });



            }
        });
        mAuth.signInWithEmailAndPassword("ashwin102000@gmail.com","ohn4n4n4").addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Log.d("Signin","success");
            }
        });
        contractlist=findViewById(R.id.contract_list);
        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(this);
        MyLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        contractlist.setLayoutManager(MyLayoutManager);
        db.collection("technicianData").document("Abigail Goyette").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot d=task.getResult();
                    ArrayList<Map> servicing=(ArrayList<Map>)d.get("servicing");
                    Log.d("Servicing",servicing.toString());
                    for(Map m: servicing){
                        a.add(new EquimentData(m.get("datebefore").toString(),m.get("timebefore").toString(),m.get("item").toString(),m.get("orgport").toString()));
                    }
                    adapter.notifyDataSetChanged();

                }
            }
        });


        contractlist.setAdapter(adapter);

    }

}
