package com.servicel.system.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.servicel.system.*;

public class SQLiteOpenHelper extends SQLiteOpenHelper {
    public SQLiteOpenHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // Called when no database exists in disk and the helper class needs
    // to create a new one.
    @Override
    public void onCreate(SQLiteDatabase _db) {
        try {
            _db.execSQL(MySQLiteHelper.DATABASE_CREATE);

        }catch(Exception er){

            Log.e("Androidruntime","exceptioin");
			
        }

    }

	@Override
	public void onConfigure(SQLiteDatabase db)
	{
		// TODO: Implement this method
		super.onConfigure(db);
		db.disableWriteAheadLogging();
	}
	
	

    // Called when there is a database version mismatch meaning that the version
    // of the database on disk needs to be upgraded to the current version.
    @Override
    public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion)
    {
        // Log the version upgrade.
        Log.w("TaskDBAdapter", "Upgrading from version " +_oldVersion + " to " +_newVersion + ", which will destroy all old data");


        // Upgrade the existing database to conform to the new version. Multiple
        // previous versions can be handled by comparing _oldVersion and _newVersion
        // values.
        // The simplest case is to drop the old table and create a new one.
        _db.execSQL("DROP TABLE IF EXISTS " + "CLIENTES");
        _db.execSQL("DROP TABLE IF EXISTS " + "SEMESTER1");
        // Create a new one.
        onCreate(_db);
    }


}
