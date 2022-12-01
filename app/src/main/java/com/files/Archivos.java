package com.files;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Archivos extends AppCompatActivity {

    String ruta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        switch (currentNightMode) {
            case Configuration.UI_MODE_NIGHT_NO:
                setTheme(R.style.Dia);
                break;
            case Configuration.UI_MODE_NIGHT_YES:
                setTheme(R.style.Noche);
                break;
        }

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        String año = sharedPref.getString("año", "0000");

        if(año.equals("0000")){
            Calendar calendario = Calendar.getInstance();
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy");
            SimpleDateFormat format2 = new SimpleDateFormat("MM");
            SimpleDateFormat format3 = new SimpleDateFormat("dd");
            String saño = format1.format(calendario.getTime());
            String smes = format2.format(calendario.getTime());
            String sdia = format3.format(calendario.getTime());

            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("año", saño);
            editor.putString("mes", smes);
            editor.putString("dia", sdia);
            editor.apply();

            AsignarRuta();
        }
        if(!año.equals("0000")){
            String saño = sharedPref.getString("año", "");
            String smes = sharedPref.getString("mes", "");
            String sdia = sharedPref.getString("dia", "");
            int iaño = Integer.parseInt(saño);
            int imes = Integer.parseInt(smes);
            imes = imes - 1;
            int idia = Integer.parseInt(sdia);

            Calendar calendario = Calendar.getInstance();
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy");
            SimpleDateFormat format2 = new SimpleDateFormat("MM");
            SimpleDateFormat format3 = new SimpleDateFormat("dd");
            String sañoa = format1.format(calendario.getTime());
            String smesa = format2.format(calendario.getTime());
            String sdiaa = format3.format(calendario.getTime());
            int iañoa = Integer.parseInt(sañoa);
            int imesa = Integer.parseInt(smesa);
            imesa = imesa - 1;
            int idiaa = Integer.parseInt(sdiaa);

            String sfecha_primera_ejecucion_ennumero = saño + smes + sdia;
            int fecha_primera_ejecucion_ennumero = Integer.parseInt(sfecha_primera_ejecucion_ennumero);
            String sfecha_actual_ennumero = sañoa + smesa + sdiaa;
            int fecha_actual_ennumero = Integer.parseInt(sfecha_actual_ennumero);

            if (fecha_actual_ennumero > fecha_primera_ejecucion_ennumero) {
                Calendar fecha_primera_ejecucion = new GregorianCalendar();
                fecha_primera_ejecucion.set(iaño, imes, idia);

                Calendar fecha_actual = new GregorianCalendar();
                fecha_actual.set(iañoa, imesa, idiaa);

                Calendar resta_de_dias = Calendar.getInstance();
                resta_de_dias.setTimeInMillis(fecha_actual.getTime().getTime() - fecha_primera_ejecucion.getTime().getTime());
                String sresta_de_dias = "" + resta_de_dias.get(Calendar.DAY_OF_YEAR);
                double dresta_de_dias = Double.valueOf(sresta_de_dias).doubleValue();
                int iresta_de_dias = (int) dresta_de_dias;

                if (iresta_de_dias >= 3) {
                    //Aquí puedes poner tu código para solicitarle al usuario que escriba una reseña en Google Play
                    //Esta solicitud se le mostrará al usuario 3 días después de ejecutar la App por primera vez
                    Toast.makeText(getApplicationContext(), "Calificanos", Toast.LENGTH_SHORT).show();
                    AsignarRuta();
                }
                else{
                    AsignarRuta();
                }
            }
            else{
                AsignarRuta();
            }
        }


    }


    private void AsignarRuta() {

        Bundle parametros = this.getIntent().getExtras();
        if(parametros !=null){
            String id = parametros.getString("extraID");

            if(id != null){
                if(id.equals("fotos")){
                    ruta = "content://com.android.externalstorage.documents/tree/primary%3A/document/primary%3ADCIM";
                    AbrirRuta();
                }
                if(id.equals("imagenes")){
                    ruta = "content://com.android.externalstorage.documents/tree/primary%3A/document/primary%3APictures";
                    AbrirRuta();
                }
                if(id.equals("peliculas")){
                    ruta = "content://com.android.externalstorage.documents/tree/primary%3A/document/primary%3AMovies";
                    AbrirRuta();
                }
                if(id.equals("musica")){
                    ruta = "content://com.android.externalstorage.documents/tree/primary%3A/document/primary%3AMusic";
                    AbrirRuta();
                }
                if(id.equals("documentos")){
                    ruta = "content://com.android.externalstorage.documents/tree/primary%3A/document/primary%3ADocuments";
                    AbrirRuta();
                }
                if(id.equals("descargas")){
                    ruta = "content://com.android.externalstorage.documents/tree/primary%3A/document/primary%3ADownload";
                    AbrirRuta();
                }
                if(id.equals("alarmas")){
                    ruta = "content://com.android.externalstorage.documents/tree/primary%3A/document/primary%3AAlarms";
                    AbrirRuta();
                }
                if(id.equals("notificaciones")){
                    ruta = "content://com.android.externalstorage.documents/tree/primary%3A/document/primary%3ANotifications";
                    AbrirRuta();
                }
                if(id.equals("podcasts")){
                    ruta = "content://com.android.externalstorage.documents/tree/primary%3A/document/primary%3APodcasts";
                    AbrirRuta();
                }
                if(id.equals("ringtones")){
                    ruta = "content://com.android.externalstorage.documents/tree/primary%3A/document/primary%3ARingtones";
                    AbrirRuta();
                }
                if(id.equals("archivos")){
                    ruta = "content://com.android.externalstorage.documents/root/primary";
                    AbrirRuta();
                }
            }
            else{
                ruta = "content://com.android.externalstorage.documents/root/primary";
                AbrirRuta();
            }
        }
        else{
            ruta = "content://com.android.externalstorage.documents/root/primary";
            AbrirRuta();
        }

    }


    private void AbrirRuta() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                try {
                    Intent intent = new Intent();
                    intent.setComponent(ComponentName.createRelative("com.google.android.documentsui", "com.android.documentsui.files.FilesActivity"));
                    intent.setAction("android.intent.action.VIEW");
                    intent.setData(Uri.parse(ruta));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finishAndRemoveTask();
                        }
                    }, 600);
                }
                catch (Exception e){
                    try {
                        Intent intent = new Intent();
                        intent.setComponent(ComponentName.createRelative("com.android.documentsui", ".files.FilesActivity"));
                        intent.setAction("android.intent.action.VIEW");
                        intent.setData(Uri.parse(ruta));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finishAndRemoveTask();
                            }
                        }, 600);
                    }
                    catch (Exception e2){
                        try{
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse("content://com.android.externalstorage.documents/root/primary"));
                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            startActivity(intent);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    finishAndRemoveTask();
                                }
                            }, 600);
                        }
                        catch (Exception e3){
                            Toast.makeText(getApplicationContext(), R.string.error, Toast.LENGTH_SHORT).show();
                            finishAndRemoveTask();
                        }
                    }
                }


            }
        }, 400);

    }


}

