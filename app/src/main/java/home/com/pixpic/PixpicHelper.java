package home.com.pixpic;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PixpicHelper extends SQLiteOpenHelper{
    public PixpicHelper(Context context){
        this(context,"Pixpic",null,1);
    }
    private PixpicHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE PIXPIC (_id INTEGER PRIMARY KEY NOT NULL,"+
                "name VARCHAR NOT NULL,"+
                "PX INTEGER,PY INTEGER,R INTEGER,G INTEGER,B INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
