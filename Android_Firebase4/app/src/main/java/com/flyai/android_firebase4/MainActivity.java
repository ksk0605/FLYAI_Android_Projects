package com.flyai.android_firebase4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Currency;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    FirebaseDatabase myFirebase;
    DatabaseReference myDB_Reference =null;

    // HashMap <String, Object> childNode = null;
    HashMap<String, Object> Customer_Value = null;

    TextView txtFirebase;
    EditText edtCustomerName;
    Button btnInsert;
    String strHeader = "Customer Information";
    String strCName = null;

    String strCPhone_No = null;
    EditText edtCustomerPhone_No;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtFirebase = (TextView) findViewById(R.id.txtFirebase);
        edtCustomerName = (EditText) findViewById(R.id.edtCustomerName);
        edtCustomerPhone_No = (EditText) findViewById(R.id.edtCustomerPhoneNo);

        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnInsert.setOnClickListener(this);

        myFirebase = FirebaseDatabase.getInstance();
        myDB_Reference = myFirebase.getReference();

        Customer_Value = new HashMap<>();

        //mGet_FirebaseDatabase();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnInsert:
                strCName = edtCustomerName.getText().toString();
                strCPhone_No = edtCustomerPhone_No.getText().toString(); //mysol
                if (!strCName.equals("")) {

                    Customer_Value.put("Name", strCName);           // HashMap("Name" ,strCName)
                    Customer_Value.put("Phone_No", strCPhone_No);   // HashMap("Phone_No", strCPhone_No)

                    mSet_FirebaseDatabase(true);                 // App -> Firebase DB
                    mGet_FirebaseDatabase();                        // Firebase DB  -> App
                }
                edtCustomerName.setText("");
                edtCustomerPhone_No.setText("");
                break;
            default:
                edtCustomerName.setText("");
                edtCustomerPhone_No.setText("");
                break;
        }
    }

    private void mGet_FirebaseDatabase() {
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                txtFirebase.setText("Firebase Value : ");
                long iRecordCount = datasnapshot.getChildrenCount();
                int iCnt = 0;

                for (DataSnapshot postSnapshot : datasnapshot.getChildren()) {
                    iCnt++;
                    String strKey = postSnapshot.getKey();
                    //my sol
                    //Object strValue = postSnapshot.getValue(Object.class);
                    String strValue = postSnapshot.getValue().toString(); //teacher's sol

                    txtFirebase.append("\n" + iCnt + " : " + strKey);
                    txtFirebase.append("\n " + " \t = " + strValue);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("Tag: ", "Failed to read value", error.toException());
            }
        };
        Query sortnyName = FirebaseDatabase.getInstance().getReference().child(strHeader).orderByChild(strCName);
        sortnyName.addListenerForSingleValueEvent(postListener);

    }

    private void mSet_FirebaseDatabase(boolean b) {
        if(b){
            myDB_Reference.child(strHeader).child(strCPhone_No).setValue(Customer_Value);
            // child는 primary를 결정
            // * aasdfd
            //   - fsadfa
            //      $ sadff
        }
    }
}