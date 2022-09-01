package com.flyai.nfcmanager_source;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.TextView;

public class NFC_Reader extends AppCompatActivity {

    private NfcAdapter objNFCAdapter;
    private PendingIntent objPendingIntent;
    Intent objIntent;
    TextView objTV_TagID;
    TextView objTV_TagMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc_reader);

        objTV_TagID = (TextView) findViewById(R.id.txtTagID);
        objTV_TagMsg = (TextView) findViewById(R.id.txtTagMsg);

        objNFCAdapter = NfcAdapter.getDefaultAdapter(this);
        objIntent = new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        objPendingIntent = PendingIntent.getActivity(this, 0, objIntent, 0);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (objNFCAdapter != null) {
            objNFCAdapter.enableForegroundDispatch(this, objPendingIntent, null, null);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (objNFCAdapter != null) {
            objNFCAdapter.disableForegroundDispatch(this);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        //NFC Tag ID
        Tag objTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        if (objTag != null) {
            byte[] tagId = objTag.getId();
            objTV_TagID.setText("Tag ID : " + fnToHexString(tagId) + "\n");
        }

        //NFC Tag Message
        Parcelable[] rawMsgs = intent.getParcelableArrayExtra(objNFCAdapter.EXTRA_NDEF_MESSAGES);
        if (rawMsgs != null) {
            NdefMessage objMsgs = (NdefMessage) rawMsgs[0];
            NdefRecord[] objRec = objMsgs.getRecords();
            byte[] bData = objRec[0].getPayload();
            String strText = new String(bData);
            objTV_TagMsg.append("\n" + strText);
        }
    }

    public static final String CHARS = "0123456789ABCDEF";

    public static String fnToHexString(byte[] data) {
        StringBuilder objSB = new StringBuilder();
        for (int i = 0; i < data.length; ++i) {
            objSB.append(CHARS.charAt((data[i] >> 4) & 0x0f)).append(CHARS.charAt(data[i] & 0x0f));
        }
        return objSB.toString();
    }
}