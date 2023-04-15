package com.example.gdsetask;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String stringkey = "gdsctask.userinfo";
    public static final String imgkey = "gdsctask.userimage";
    private final int gallaryRequestCode = 1563 ;
    EditText name;
    EditText age;
    EditText email;
    EditText phone;
    RadioGroup radioGroup;
    RadioButton radioButton;
    ImageView image;
    String gender;

    Uri uri ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.nameEntry);
        age = findViewById(R.id.ageEntry);
        email = findViewById(R.id.emailEntry);
        phone = findViewById(R.id.phoneEntry);
        radioGroup = findViewById(R.id.radioGroup);
        image = findViewById(R.id.imageView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            if (requestCode == gallaryRequestCode){
                uri  = data.getData();
                image.setImageURI(uri);
            }
        }
    }

    public void submit(View V){
        Intent isubmit = new Intent(this, submitActivity.class);
        String info = createString();
        boolean run = true;
        String []arr = info.split(",");
        for (String s: arr){
            if (s.equals("")) {
                run = false;
            }
        }
        if (run){
            isubmit.putExtra(stringkey, info);
            isubmit.putExtra(imgkey, uri);
            startActivity(isubmit);
        }
        else{
            Toast.makeText(this, "fill all entries and upload photos", Toast.LENGTH_SHORT).show();
        }
    }

    public void selectImage(View V){
        Intent iGallery = new Intent(Intent.ACTION_PICK);
        iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(iGallery, gallaryRequestCode);
    }

    public void genderInput(View V){
        int selectedId = radioGroup.getCheckedRadioButtonId();
        if (selectedId == -1){
            System.out.println("nothing is selected");
        }
        else{
            radioButton = findViewById(selectedId);
            gender = radioButton.getText().toString();
        }
    }

    public void printarr(String []arr){
        for (String s: arr) System.out.print(s + " ");
        System.out.println("done");
    }

    public String createString(){
        return name.getText().toString() + "," + age.getText().toString() + "," + email.getText().toString() + "," + phone.getText().toString()
                 + "," + gender;
    }
}