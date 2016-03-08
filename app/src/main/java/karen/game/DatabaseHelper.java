package karen.game;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Myles Gray on 3/6/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "players.db";
    private static final String TABLE_NAME = "players";
    //private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_HSCORE = "hscore";
    SQLiteDatabase db;
    private static final String TABLE_CREATE = "create table players (id integer primary key not null, "+
            "name text not null, hscore text not null);";

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }

    public void insertPlayer(Player p)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from players";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        //values.put(COLUMN_ID, count);
        values.put(COLUMN_NAME, p.getName());
        values.put(COLUMN_HSCORE, p.getHscore());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<Player> getAll()
    {
        List<Player> dataList = new ArrayList<Player>();
        db = this.getReadableDatabase();
        String query = "select * from players";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst())
        {
            do
            {
                Player data = new Player();
                data.setName(cursor.getString(1));
                data.setHscore(Integer.parseInt(cursor.getString(2)));
                dataList.add(data);
            }
            while(cursor.moveToNext());
        }
        return dataList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS" + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
}
