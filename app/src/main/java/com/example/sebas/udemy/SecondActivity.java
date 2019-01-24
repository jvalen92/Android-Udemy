package com.example.sebas.udemy;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.TimedText;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.Toast.makeText;

public class SecondActivity extends AppCompatActivity {

    //Declara los elemtos de la interfaz
    EditText textPhone;
    EditText textWeb;

    ImageButton call;
    ImageButton web;
    ImageButton camera;

    private final int PHONE_CALL_CODE = 100;
    private final int PICTURE_FROM_CAMERA =50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textPhone = (EditText) findViewById(R.id.textPhone);
        textWeb = (EditText) findViewById(R.id.textWeb);

        call = (ImageButton) findViewById(R.id.btn_phone);
        web = (ImageButton) findViewById(R.id.btn_web);
        camera = (ImageButton) findViewById(R.id.btn_camera);

        //Listener para los botones

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent Camera
                Intent camera = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivity(camera);
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String phone = textPhone.getText().toString();
                if (phone != null) {
                    //capture the SDK version and call the right method
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                       // NewerVersion();

                        //comprobar si ha aceptado los permisos

                        if (checkPermission(Manifest.permission.CALL_PHONE)){
                            //ha aceptado
                            Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+phone));
                            if (ActivityCompat.checkSelfPermission(SecondActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                return;
                            }
                            startActivity(intent);
                        }else {
                            //primera vez que se le pregunta
                            if (!shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)){
                                //no se le ha preguntado
                                NewerVersion();
                            } else {
                                //ha denegado

                                makeText(SecondActivity.this,"por favor activa el permiso",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                intent.addCategory(Intent.CATEGORY_DEFAULT);
                                intent.setData(Uri.parse("package:"+ getPackageName()));

                                /*LOS FLAGS SIRVEN PARA VOLVER AL ESTADO EN EL QUE ESTABA
                                  LA APP JUSTO ANTES DE LANZAR EL INTENT */

                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                                startActivity(intent);
                            }
                        }
                    } else {
                        OlderVersion(phone);
                    }
                }
            }

            private void OlderVersion(String phone) {
                Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tell" + phone));
                if (checkPermission(Manifest.permission.CALL_PHONE)) {
                    startActivity(intentCall);
                } else {
                    makeText(SecondActivity.this, "You declined this permission", Toast.LENGTH_LONG).show();
                }
            }
            private void NewerVersion() {
                //el segundo parametro corresponde al codigo del permiso para hacer una llamada
                requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PHONE_CALL_CODE);
            }
        });

        //metodo para el navegador web
        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = textWeb.getText().toString();
                if (url != null && !url.isEmpty()){
                    Intent intentWeb = new Intent();
                    intentWeb.setAction(Intent.ACTION_VIEW);
                    intentWeb.setData(Uri.parse("http://"+url));
                }
                //Intent para contactos
                Intent intentContacts = new Intent(Intent.ACTION_VIEW,Uri.parse("content://contacts/people"));

                //Intent para email Rapido
                String email = "prueba@gmail.com";
                Intent intentEmail = new Intent(Intent.ACTION_SENDTO,Uri.parse("mailto:"+email));

                //Intent para email completo
                Intent intentEmailCompleto = new Intent(Intent.ACTION_SEND,Uri.parse(email));
                intentEmailCompleto.setType("plain/text");
                intentEmailCompleto.putExtra(Intent.EXTRA_SUBJECT,"Mail´s tittle");
                intentEmailCompleto.putExtra(Intent.EXTRA_TEXT,"hello world");
                intentEmailCompleto.putExtra(Intent.EXTRA_EMAIL,new String[]{"prueba@gmail.com","prueba123@gmail.com"});
                startActivity(Intent.createChooser(intentEmailCompleto,"elige tu correo"));

                //Intent Telefono 2 sin permisos
                Intent intentCall = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:2226568787"));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case PICTURE_FROM_CAMERA:
                if (requestCode == Activity.RESULT_OK ){
                    String result = data.toUri(0);
                    Toast.makeText(this,"result"+result,Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(this,"hubo un error",Toast.LENGTH_LONG).show();
                }
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PHONE_CALL_CODE:

                String permission = permissions[0];
                int result = grantResults[0];

                if (permission.equals(Manifest.permission.CALL_PHONE)) {
                    if (result == PackageManager.PERMISSION_GRANTED) {
                        //permiso aceptado
                        String phone = textPhone.getText().toString(); //capturar numero de telefono de la interfaz
                        Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
                        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }
                        startActivity(intentCall);
                    }else {
                        //permiso denegado
                        makeText(SecondActivity.this,"You declined this permission",Toast.LENGTH_LONG).show();
                    }
                }
                break;

            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }

    }

    private boolean checkPermission(String permission){
        int result = this.checkCallingOrSelfPermission(permission);
        return result == PackageManager.PERMISSION_GRANTED;
    }








}
