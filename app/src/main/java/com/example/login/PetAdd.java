package com.example.login;

import static com.example.login.R.layout.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import android.view.View.OnClickListener;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class PetAdd extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Button btnDisplay;
    private boolean gender;

    private static final String TAG = "PetAddActivity";
    private EditText etlocation, etdiscreption, etage, etPhone, etprice, etgender;
    private Spinner spkind;
    private ImageView ivPhoto;
    private FireBaseServices fbs;
    private RadioButton rd1, rd2;
    private Uri filePath;
    private StorageReference storageReference;
    private String refAfterSuccessfullUpload = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_pet_add);
        addListenerOnButton();
        getSupportActionBar().hide();
        connectComponents();
    }

    private void addListenerOnButton() {
        radioGroup = (RadioGroup) findViewById(R.id.radio);
        btnDisplay = (Button) findViewById(R.id.btnDisplay);

        btnDisplay.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                // get selected radio button from radioGroup
                int selectedId = radioGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioButton = (RadioButton) findViewById(selectedId);

                Toast.makeText(PetAdd.this,
                        radioButton.getText(), Toast.LENGTH_SHORT).show();

            }

        });

    }

    private void connectComponents() {
        etlocation = findViewById(R.id.etlocation);
        etprice = findViewById(R.id.etprice);
        etdiscreption = findViewById(R.id.etdisc);
        etage = findViewById(R.id.etage);
        etPhone = findViewById(R.id.etcontactnumber);
        spkind = findViewById(R.id.spkind);
        ivPhoto = findViewById(R.id.ivphotoPetAdd);
        fbs = FireBaseServices.getInstance();
        spkind.setAdapter(new ArrayAdapter<PetCategory>(this, android.R.layout.simple_list_item_1, PetCategory.values()));
        storageReference = fbs.getStorage().getReference();
        RadioGroup rg = (RadioGroup) findViewById(R.id.radio);
        btnDisplay = findViewById(R.id.btnDisplay);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioFemale:
                        gender = true;

                        break;
                    case R.id.radioMale:
                        gender = false;
                        break;

                }
            }
        });
    }


    public void add(View view) {
        // check if any field is empty
        String location, description, age, phone, kind, photo, price;
        boolean gender = false;
        location = etlocation.getText().toString();
        description = etdiscreption.getText().toString();
        price = etprice.toString();
        age = etage.getText().toString();
        phone = etPhone.getText().toString();
        kind = spkind.getSelectedItem().toString();
        if (ivPhoto.getDrawable() == null)
            photo = "no_image";
        else photo = storageReference.getDownloadUrl().toString();
        if (!rd1.isChecked() && !rd2.isChecked()) {
            Toast.makeText(this, R.string.err_fields_empty, Toast.LENGTH_SHORT).show();
            return;
        }

        if (location.trim().isEmpty() || description.trim().isEmpty() || age.trim().isEmpty() ||
                phone.trim().isEmpty() || kind.trim().isEmpty() || photo.trim().isEmpty()) {
            Toast.makeText(this, R.string.err_fields_empty, Toast.LENGTH_SHORT).show();
            return;
        }

        Pet pet = new Pet(phone, price, location, PetCategory.valueOf(kind), photo, gender, age, description);
        fbs.getFire().collection("restaurants")
                .add(pet)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    public void selectPhoto(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 40);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 40) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    try {
                        filePath = data.getData();
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
                        ivPhoto.setBackground(null);
                        ivPhoto.setImageBitmap(bitmap);
                        uploadImage();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void uploadImage() {
        if (filePath != null) {

            // Code for showing progressDialog while uploading
            ProgressDialog progressDialog
                    = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            // Defining the child of storageReference
            StorageReference ref
                    = storageReference
                    .child(
                            "images/"
                                    + UUID.randomUUID().toString());

            // adding listeners on upload
            // or failure of image
            ref.putFile(filePath)
                    .addOnSuccessListener(
                            new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                @Override
                                public void onSuccess(
                                        UploadTask.TaskSnapshot taskSnapshot) {

                                    // Image uploaded successfully
                                    // Dismiss dialog
                                    progressDialog.dismiss();
                                    Toast
                                            .makeText(PetAdd.this,
                                                    "Image Uploaded!!",
                                                    Toast.LENGTH_SHORT)
                                            .show();
                                }
                            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            // Error, Image not uploaded
                            progressDialog.dismiss();
                            Toast
                                    .makeText(PetAdd.this,
                                            "Failed " + e.getMessage(),
                                            Toast.LENGTH_SHORT)
                                    .show();
                        }
                    })
                    .addOnProgressListener(
                            new OnProgressListener<UploadTask.TaskSnapshot>() {

                                // Progress Listener for loading
                                // percentage on the dialog box
                                @Override
                                public void onProgress(
                                        UploadTask.TaskSnapshot taskSnapshot) {
                                    double progress
                                            = (100.0
                                            * taskSnapshot.getBytesTransferred()
                                            / taskSnapshot.getTotalByteCount());
                                    progressDialog.setMessage(
                                            "Uploaded "
                                                    + (int) progress + "%");
                                }
                            });
        }
    }
}