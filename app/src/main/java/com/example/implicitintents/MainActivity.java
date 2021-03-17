package com.example.implicitintents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    //variable privada para obtener el objeto del EditText de la webpage
    private EditText mWebsiteEditText;
    //variable privada para obtener el objeto del EditText de la location
    private EditText mLocationEditText;
    //variable privada para obtener el objeto del EditText del share
    private EditText mShareTextEditText;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //obtenemos la referencia del Website de nuestro activity_main.xml mediante el findById
        mWebsiteEditText = findViewById(R.id.website_edittext);
        //obtenemos la referencia de la location de nuestro activity.xml mediante el findById
        mLocationEditText = findViewById(R.id.location_edittext);
        //otenemos la refrencia del share de nuestro activity_main.xml mediante el finById
        mShareTextEditText = findViewById(R.id.share_edittext);
    }//fin del método onCreate

    /**
     * Método para abrir una página web mediante un intent y un parametro que es la url del sitio al que queremos ir
     * @param view
     */
    public void openWebsite(View view) {
        //obtenemos el valor del editText del website y lo asignamos a nuestra variable url
        String url = mWebsiteEditText.getText().toString();
        //pareseamos nuestro string url anteriormente para tener un objeto de tipo Uri
        Uri webpage = Uri.parse(url);
        //Creamos una nueva intent la cual nos llevará al sitio web que tenemos definido
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

        //verificamos que haya alguna aplicacion la cual pueda realizar este intent de abrir nuestra webpage
        if(intent.resolveActivity(getPackageManager()) != null){
            //si hay alguna app que pueda realizar esto entonces ejecutamos nuestra intent
            startActivity(intent);
        }else{
            //si no se puede entonces mandamos por consola un mensaje que no se pudo lanzar este intent
            Log.d("Implicit Intents", "Can't handle this");
        }//fin del if/else
    }//fin del método openWebsite

    /**
     * Método para abrir el gps
     * @param view
     */
    public void openLocation(View view) {
        //obtenemos el valor del editText de la location y lo asignamos a nuestra variable loc
        String loc = mLocationEditText.getText().toString();
        //parsemos nuestro string loc anteriormente para tener un objeto de tipo Uri y la query geo search
        Uri addressUri = Uri.parse("geo:0,0?q=" + loc);
        //creamos una nueva intent la cual nos llevará hacia la localización o gps
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);

        //verificamos que haya alguna aplicacion la cual pueda realizar este intent de abrir la location o gps
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }else{
            //si no se puede entonces mandamos por consola un mensaje que no se pudo lanzar este intent
            Log.d("Implicit Intents", "Can't handle this");
        }//fin del if/else
    }//fin del método openLocation

    /**
     * M+etodo para abrir el share
     * @param view
     */
    public void shareText(View view) {
        //obtenemos el valor del editText del share y lo asignamos a nuestra variable txt
        String txt = mShareTextEditText.getText().toString();
        //definimos de tipo mime el texto que vamos a compartir
        String mimeType ="text/plain";
        //llamamos al método ShareCompact.IntentBuilder con los siguientes paramétros
        ShareCompat.IntentBuilder
                //para indicarle en que intent o activity debe lanzar esto, le indicamos que en esta activity con this
                .from(this)
                //indicamos el tipo de texto a compartir en este caso texto plano
                .setType(mimeType)
                //establecemos un titulo que dirá al abrir el share
                .setChooserTitle("Share this text with: ")
                //establecemos el texto que queremos compartir y en este caso será el txt
                .setText(txt)
                //iniciamos el share de nuestra activity
                .startChooser();
    }//fin del método shareText
}