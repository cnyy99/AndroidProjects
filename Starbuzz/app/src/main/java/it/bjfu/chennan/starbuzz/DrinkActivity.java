package it.bjfu.chennan.starbuzz;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.Image;
import android.os.Bundle;
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
//        Drink drinks = Drink.drinks[drinkid];

//        TextView name= (TextView) findViewById(R.id.name);
//        name.setText(drinks.getName());
//        TextView description= (TextView) findViewById(R.id.description);
//        description.setText(drinks.getDescription());
//        ImageView photo = findViewById(R.id.photo);
//        photo.setImageResource(drinks.getImageResourceId());
//        photo.setContentDescription(drinks.getName());

        SQLiteOpenHelper startbuzzDatabaseHelper = new MyDBHelper(this);

        try{
            SQLiteDatabase db = startbuzzDatabaseHelper.getReadableDatabase();
            Cursor cursor = db.query("DRINK",
                                    new String[] {"NAME","DESCRIPTION","IMAGE_RESOURCE_ID"},
                        "_id=?",
                                    new String[]{Integer.toString(drinkid)},null,null,null);
            if(cursor.moveToFirst())
            {
                String nameText = cursor.getString(0);
                String descriptionText = cursor.getString(1);
                int photoId = cursor.getInt(2);
                TextView name= (TextView) findViewById(R.id.name);
                name.setText(nameText);

                TextView description= (TextView) findViewById(R.id.description);
                description.setText(descriptionText);

                ImageView photo = findViewById(R.id.photo);
                photo.setImageResource(photoId);

                photo.setContentDescription(nameText);
            }
            cursor.close();
            db.close();
        }catch (SQLiteException e)
        {
            Toast toast= Toast.makeText(this,"Database unavailable",Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
