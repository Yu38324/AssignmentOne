package com.example.assignmentone;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    private ImageView detailImage, overlayIcon;
    private TextView overlayText, contentTitle, descriptionText;
    private String[] titles, contents, imageNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // initialize
        Resources res = getResources();
        titles = res.getStringArray(R.array.string_array_titles);
        contents = res.getStringArray(R.array.string_array_content);
        imageNames = res.getStringArray(R.array.string_array_images);

        overlayIcon = findViewById(R.id.overlayIcon);
        overlayText = findViewById(R.id.overlayText);
        contentTitle = findViewById(R.id.titleText);
        descriptionText = findViewById(R.id.descriptionText);

        handleButtonClick();
    }

    private void handleButtonClick() {
        Intent intent = getIntent();
        int index = (intent != null && intent.hasExtra("INDEX"))
                ? intent.getIntExtra("INDEX", 0)
                : 0;

        // 确保索引在有效范围内
        if (index < 0 || index >= titles.length) {
            index = 0;
        }

        setupUI(index);
    }

    private void setupUI(int index) {
        // text
        overlayText.setText(getButtonTitle(index));
        contentTitle.setText(titles[index]);
        descriptionText.setText(contents[index]);

        // icon
        if (index < imageNames.length) {
            int iconResId = getResourceId(imageNames[index], "drawable");
            if (iconResId != 0) {
                overlayIcon.setImageResource(iconResId);
            }
        }
    }

    private String getButtonTitle(int index) {
        String[] buttonTitles = {"Facilities", "Events", "Clubs", "Support"};
        if (index >= 0 && index < buttonTitles.length) {
            return buttonTitles[index];
        }
        return "Welcome";
    }

    private int getResourceId(String resourceName, String resourceType) {
        return getResources().getIdentifier(resourceName, resourceType, getPackageName());
    }
}