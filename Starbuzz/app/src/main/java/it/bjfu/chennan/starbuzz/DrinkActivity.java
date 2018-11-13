package it.bjfu.chennan.starbuzz;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DrinkActivity extends Activity {
    public static final String EXTRA_DRINKID="drinkid";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);
        int drinkid = (Integer)getIntent().getExtras().get(EXTRA_DRINKID);

        SQLiteOpenHelper startbuzzDatabaseHelper = new MyDBHelper(this);

        try{
            SQLiteDatabase db = startbuzzDatabaseHelper.getReadableDatabase();
            Cursor cursor = db.query("DRINK",
                                    new String[] {"NAME","DESCRIPTION","IMAGE_RESOURCE_ID","FAVORITE"},
                        "_id=?",
                                    new String[]{Integer.toString(drinkid)},null,null,null);
            if(cursor.moveToFirst())
            {
                String nameText = cursor.getString(0);
                String descriptionText = cursor.getString(1);
                int photoId = cursor.getInt(2);
                Boolean isFavorite=(cursor.getInt(3)==1);
                TextView name= (TextView) findViewById(R.id.name);
                name.setText(nameText);

                TextView description= (TextView) findViewById(R.id.description);
                description.setText(descriptionText);

                ImageView photo = findViewById(R.id.photo);
                photo.setImageResource(photoId);
                photo.setContentDescription(nameText);

                CheckBox checkBox = findViewById(R.id.favorite);
                checkBox.setChecked(isFavorite);
            }
            cursor.close();
            db.close();
        }catch (SQLiteException e)
        {
            Toast toast= Toast.makeText(this,"Database unavailable",Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    public void onFavoriteClicked(View view){
        int drinkId = (Integer) getIntent().getExtras().get(EXTRA_DRINKID);
        CheckBox favorite= (CheckBox) findViewById(R.id.favorite);
        ContentValues drinkValues = new ContentValues();
        drinkValues.put("FAVORITE",favorite.isChecked());
        SQLiteOpenHelper starbuzzDatabaseHelper = new MyDBHelper(this);
        try{
            SQLiteDatabase db = starbuzzDatabaseHelper.getWritableDatabase();
            db.update("DRINK",drinkValues,"_id=?",new String[]{Integer.toString(drinkId)});
            db.close();
        }catch (SQLiteException e)
        {
            Toast toast= Toast.makeText(this,"Database unavailable",Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
