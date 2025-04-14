package com.example.projectmanagementsystem;




import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class uploadListing extends AppCompatActivity {

    private Button uploadButton;
    private ImageView uploadImage;
    EditText title, rent, details;
    ProgressBar progressBar;
    private Uri imageUri;
    final  private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Images");
    final private StorageReference storageReference = FirebaseStorage.getInstance().getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_listing);


        uploadButton = findViewById(R.id.uploadButton);
        title = findViewById(R.id.title_listing);
        rent = findViewById(R.id.rent_listing);
        details = findViewById(R.id.details_listing);
        uploadImage = findViewById(R.id.uploadImage);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();
                            imageUri = data.getData();
                            uploadImage.setImageURI(imageUri);
                        } else {
                            Toast.makeText(uploadListing.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPicker = new Intent();
                photoPicker.setAction(Intent.ACTION_GET_CONTENT);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imageUri != null){
                    uploadToFirebase(imageUri);
                } else  {
                    Toast.makeText(uploadListing.this, "Please select image", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    //Outside onCreate
    private void uploadToFirebase(Uri uri){
        String titleListing = title.getText().toString();
        String rentListing = rent.getText().toString();
        String detailsListing = details.getText().toString();
        final StorageReference imageReference = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(uri));

        Log.d("UPLOAD", "Starting upload to Firebase Storage...");
        progressBar.setVisibility(View.VISIBLE);

        imageReference.putFile(uri)
                .addOnSuccessListener(taskSnapshot -> {
                    Log.d("UPLOAD", "File uploaded. Getting download URL...");
                    imageReference.getDownloadUrl().addOnSuccessListener(uri1 -> {
                        Log.d("UPLOAD", "Download URL retrieved: " + uri1.toString());

                        DataClass dataClass = new DataClass(uri1.toString(), titleListing, rentListing, detailsListing);
                        String key = databaseReference.push().getKey();

                        if (key != null) {
                            databaseReference.child(key).setValue(dataClass)
                                    .addOnSuccessListener(aVoid -> {
                                        Log.d("UPLOAD", "Data written to Realtime DB successfully.");
                                        progressBar.setVisibility(View.INVISIBLE);
                                        Toast.makeText(uploadListing.this, "Uploaded", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(uploadListing.this, Listings.class);
                                        startActivity(intent);
                                        finish();
                                    })
                                    .addOnFailureListener(e -> {
                                        Log.e("UPLOAD", "Failed to write data to DB", e);
                                        progressBar.setVisibility(View.INVISIBLE);
                                        Toast.makeText(uploadListing.this, "Failed to save data", Toast.LENGTH_SHORT).show();
                                    });
                        } else {
                            Log.e("UPLOAD", "Database key is null!");
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    }).addOnFailureListener(e -> {
                        Log.e("UPLOAD", "Failed to get download URL", e);
                        progressBar.setVisibility(View.INVISIBLE);
                    });
                })
                .addOnProgressListener(snapshot -> {
                    Log.d("UPLOAD", "Upload in progress... Bytes transferred: " + snapshot.getBytesTransferred());
                    progressBar.setVisibility(View.VISIBLE);
                })
                .addOnFailureListener(e -> {
                    Log.e("UPLOAD", "Upload failed", e);
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(uploadListing.this, "Failed to upload", Toast.LENGTH_SHORT).show();
                });
    }

    private String getFileExtension(Uri fileUri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(contentResolver.getType(fileUri));
    }
}