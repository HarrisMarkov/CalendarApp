package pt.ubi.di.projeto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class mySQLiteDBHandler extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHandler";

    public mySQLiteDBHandler(Context context) {

        super(context, "Event.db", null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable = "CREATE TABLE Event(Name TEXT primary key, Start TEXT, Stop TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Event");
    }

    /* ADD DATA TO THE DATABASE*/
    public boolean addData(String col_1, String col_2, String col_3){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", col_1);
        contentValues.put("Start",col_2);
        contentValues.put("Stop",col_3);

        long result = db.insert("Event", null, contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }

    /*  UPDATE DATABASE */
    public boolean updateData(String col_1, String col_2, String col_3){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Start",col_2);
        contentValues.put("Stop",col_3);

        Cursor cursor = db.rawQuery("SELECT * FROM Event where name = ?", new String[] {col_1});

        if(cursor.getCount() > 0){

            long result = db.update("Event", contentValues, "name=?", new String[] {col_1});

            if(result == -1)
                return false;
            else
                return true;
        }
        else return false;
    }

    /* DELETE DATA IN THE DATABASE */
    public boolean deleteData(String col_1){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM Event where name = ?", new String[] {col_1});

        if(cursor.getCount() > 0){

            long result = db.delete("Event", "name=?", new String[] {col_1});

            if(result == -1)
                return false;
            else
                return true;
        }
        else return false;
    }

    /* DELETE ALL DATA IN THE DATABASE */
    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("DROP TABLE Event", null);
        cursor = db.rawQuery("CREATE TABLE Event(Name TEXT primary key, Start TEXT, Stop TEXT)", null);
    }

    /* RETURN THE DATA FROM THE DATABASE */
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM Event", null);
        return cursor;
    }

    public Cursor getDatabyDate(String stop){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "Select Name, Stop from Event where Stop >= " + stop;

        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

}
