package com.contactos.Contactos;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MyActivity extends Activity {
    TextView contacto;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
         switch (requestCode){
             case (16):
                 if (resultCode == Activity.RESULT_OK){
                     Uri datosdelcontacto = data.getData();
                     Cursor registros = managedQuery(datosdelcontacto,null,null,null,null);
                     if (registros.moveToFirst()){
                         String nombre = registros.getString(
                                 registros.getColumnIndex(
                                         ContactsContract.Contacts.DISPLAY_NAME));
                         contacto.setText(nombre);
                     }
                 }
                 break;
         }
    }

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button btnmuestra = (Button)findViewById(R.id.btnmuestra);
        contacto = (TextView)findViewById(R.id.contacto);
        btnmuestra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent,16);
            }
        });


    }
}
