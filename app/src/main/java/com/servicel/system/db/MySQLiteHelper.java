package com.servicel.system.db;


import android.content.*;
import android.database.*;
import android.database.sqlite.*;
import android.util.*;
import android.widget.*;
import com.servicel.system.*;
import com.servicel.system.clientsrecycler.*;
import java.io.*;
import java.util.*;

/**
 * Created by admin on 7/18/2016.
 */
public class MySQLiteHelper
{

    static final String DATABASE_NAME = "serviciocelular.db";
    String ok="OK";
    static final int DATABASE_VERSION = 1;
    public  static String getName="";
    public static final int NAME_COLUMN = 1;
    static final String DATABASE_CREATE = "create table CLIENTES( ID integer primary key autoincrement,DATE  text,TIME  text,NAME text,PHONE text,EMAIL text,IMEI text,BRAND text,MODEL text,COLOR text,CONDITIONS text,PASSWORD text,KINDOFSERVICE text,TOTALPRICE text,DEPOSIT text,PARTSPRICE text,DEADLINE text,POWERSON text,HASCOVER text,HASSIM text,HASMEMORY text,HASBATTERY text,HASSCREWS text,ISDELIVERED text); ";
    public static SQLiteDatabase db;
    private final Context context;
    private static SQLiteOpenHelper dbHelper;
    private static String TABLE_NAME = "CLIENTES";
    public  MySQLiteHelper(Context _context)
    {
        context = _context;
        dbHelper = new SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    public String insertEntry(String dat, String tim, String nam, 
							  String phon, String email_, String imei_, String brand_, 
							  String model_, String color_, String conditions_, String password_, 
							  String kindOfService_, String totalPrice_, String deposit_, 
							  String partsPrice_, String deadline_, Boolean powersOn_, Boolean hasCover_, 
							  Boolean hasSim_, Boolean hasMemory_, Boolean hasBattery_, Boolean hasScrews_, String isDelivered_)
    {
		try
		{

            ContentValues newValues = new ContentValues();
            // Assign values for each column. allof them have to be right 
            newValues.put("DATE", dat);
            newValues.put("TIME", tim);
            newValues.put("NAME", nam);
            newValues.put("PHONE", phon);
			newValues.put("EMAIL", email_);
			newValues.put("IMEI", imei_);
			newValues.put("BRAND", brand_);
			newValues.put("MODEL", model_);
			newValues.put("COLOR", color_);
			newValues.put("CONDITIONS", conditions_);
			newValues.put("PASSWORD", password_);
			newValues.put("KINDOFSERVICE", kindOfService_);
			newValues.put("TOTALPRICE", totalPrice_);
			newValues.put("DEPOSIT", deposit_);
			newValues.put("PARTSPRICE", partsPrice_);
			newValues.put("DEADLINE", deadline_);
			newValues.put("POWERSON", powersOn_);
			newValues.put("HASCOVER", hasCover_);
			newValues.put("HASSIM", hasSim_);
			newValues.put("HASMEMORY", hasMemory_);
			newValues.put("HASBATTERY", hasBattery_);
			newValues.put("HASSCREWS", hasScrews_);
			newValues.put("ISDELIVERED", isDelivered_);



            // Insert the row into your table
            db = dbHelper.getWritableDatabase();
            long result=db.insert("CLIENTES", null, newValues);
            System.out.print(result);
            Toast.makeText(context, "Orden guardada", Toast.LENGTH_LONG).show();


        }
		catch (Exception ex)
		{
            System.out.println("Exceptions " + ex);
            Log.e("Note", "One row entered");
        }
		return ok;
    }

    // Method to openthe Database
    public  MySQLiteHelper open() throws SQLException
    {
        db = dbHelper.getWritableDatabase();        return this;
    }

    // Method to close the Database
    public void close()
    {
        db.close();
    }

    // method returns an Instance of the Database
    public  SQLiteDatabase getDatabaseInstance()
    {
        return db;
    }






	//Made for clients list recycler
	public List<recyclerRowModel> getColumnStrings(String columnNAME, String status)
    {
		// DataModel dataModel = new DataModel();
        List<recyclerRowModel> data=new ArrayList<>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " ;", null);
		StringBuffer stringBuffer = new StringBuffer();
        recyclerRowModel dataModel = null; 
        while (cursor.moveToNext())
		{
            dataModel = new recyclerRowModel("", status, columnNAME);
            String name = cursor.getString(cursor.getColumnIndexOrThrow(columnNAME));
			String st = cursor.getString(cursor.getColumnIndexOrThrow(status));
            dataModel.setName(name);
			dataModel.setstatus(st);
            stringBuffer.append(dataModel);
            data.add(dataModel);
        }

        return data;
    }

	
	
	
	
	

	//Made for getting data from database 
	//1-----this---then----DBENTRYMODEL.JAVA
	//pedimos una lista de dbenteymodel objects que tenga datos cada uno 
	public List<xxDbEntryModel> getDBENTRYMODELColumnStrings(String columnID, String columnDATE, String columnTIME, String columnNAME,
															 String columnPHONE, String columnEMAIL, String columnIMEI, String columnBRAND, String columnMODEL, String columnCOLOR,
															 String columnCONDITIONS, String columnPASSWORD, String columnKINDOFSERVICE, String columnTOTALPRICE,
															 String columnDEPOSIT, String columnPARTSPRICE, String columnDEADLINE, String columnPOWERSON, String columnHASCOVER,
															 String columnHASSIM, String columnHASMEMORY, String columnHASBATTERY, String columnHASSCREWS, String columnIsDelivered
															 )//a
    {
        List<xxDbEntryModel> listOfDBENTRYMODEL=new ArrayList<>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " ;", null);
		//vaciamos el objeto
        xxDbEntryModel DBENTRYMODELobject = null; 
        while (cursor.moveToNext())
		{
            DBENTRYMODELobject = new xxDbEntryModel(columnID, columnDATE, columnTIME, columnNAME,
													columnPHONE, columnEMAIL, columnIMEI, columnBRAND, columnMODEL, columnCOLOR,
													columnCONDITIONS, columnPASSWORD, columnKINDOFSERVICE, columnTOTALPRICE,
													columnDEPOSIT, columnPARTSPRICE, columnDEADLINE, columnPOWERSON, columnHASCOVER,
													columnHASSIM, columnHASMEMORY, columnHASBATTERY, columnHASSCREWS, columnIsDelivered);//b
			//c     add another string
			//obtiene la columna deseada mientras se mueve al proximo el cursor
			String id = cursor.getString(cursor.getColumnIndexOrThrow(columnID));
			String date = cursor.getString(cursor.getColumnIndexOrThrow(columnDATE));
			String time = cursor.getString(cursor.getColumnIndexOrThrow(columnTIME));
			String name = cursor.getString(cursor.getColumnIndexOrThrow(columnNAME));
			String phone = cursor.getString(cursor.getColumnIndexOrThrow(columnPHONE));
			String email = cursor.getString(cursor.getColumnIndexOrThrow(columnEMAIL));
			String imei = cursor.getString(cursor.getColumnIndexOrThrow(columnIMEI));
			String brand = cursor.getString(cursor.getColumnIndexOrThrow(columnBRAND));
			String model = cursor.getString(cursor.getColumnIndexOrThrow(columnMODEL));
			String color = cursor.getString(cursor.getColumnIndexOrThrow(columnCOLOR));
			String conditions = cursor.getString(cursor.getColumnIndexOrThrow(columnCONDITIONS));
			String password = cursor.getString(cursor.getColumnIndexOrThrow(columnPASSWORD));
			String kindofservice = cursor.getString(cursor.getColumnIndexOrThrow(columnKINDOFSERVICE));
			String totalprice = cursor.getString(cursor.getColumnIndexOrThrow(columnTOTALPRICE));
			String deposit = cursor.getString(cursor.getColumnIndexOrThrow(columnDEPOSIT));
			String partsprice = cursor.getString(cursor.getColumnIndexOrThrow(columnPARTSPRICE));
			String deadline = cursor.getString(cursor.getColumnIndexOrThrow(columnDEADLINE));
			String powerson = cursor.getString(cursor.getColumnIndexOrThrow(columnPOWERSON));
			String hascover = cursor.getString(cursor.getColumnIndexOrThrow(columnHASCOVER));
			String hassim = cursor.getString(cursor.getColumnIndexOrThrow(columnHASSIM));
			String hasmemory = cursor.getString(cursor.getColumnIndexOrThrow(columnHASMEMORY));
			String hasbattery = cursor.getString(cursor.getColumnIndexOrThrow(columnHASBATTERY));
			String hasscrews = cursor.getString(cursor.getColumnIndexOrThrow(columnHASSCREWS));
			String isdelivered = cursor.getString(cursor.getColumnIndexOrThrow(columnIsDelivered));

			//D SET COLUMN
			//a cada dbentrymodel lo llenamos con los datos digamos unos 10 objetos llenados cada uno dependiendo cuantas entradas hay
			DBENTRYMODELobject.setId(id);
            DBENTRYMODELobject.setColumnDATE(date);
			DBENTRYMODELobject.setColumnTIME(time);
			DBENTRYMODELobject.setColumnNAME(name);
			DBENTRYMODELobject.setColumnPHONE(phone);
			DBENTRYMODELobject.setColumnEMAIL(email);
			DBENTRYMODELobject.setColumnIMEI(imei);
			DBENTRYMODELobject.setColumnBRAND(brand);
			DBENTRYMODELobject.setColumnMODEL(model);
			DBENTRYMODELobject.setColumnCOLOR(color);
			DBENTRYMODELobject.setColumnCONDITIONS(conditions);
			DBENTRYMODELobject.setColumnPASSWORD(password);
			DBENTRYMODELobject.setColumnKINDOFSERVICE(kindofservice);
			DBENTRYMODELobject.setColumnTOTALPRICE(totalprice);
			DBENTRYMODELobject.setColumnDEPOSIT(deposit);
			DBENTRYMODELobject.setColumnPARTSPRICE(partsprice);
			DBENTRYMODELobject.setColumnDEADLINE(deadline);
			DBENTRYMODELobject.setColumnPOWERSON(powerson);
			DBENTRYMODELobject.setColumnHASCOVER(hascover);
			DBENTRYMODELobject.setColumnHASSIM(hassim);
			DBENTRYMODELobject.setColumnHASMEMORY(hasmemory);
			DBENTRYMODELobject.setColumnHASBATTERY(hasbattery);
			DBENTRYMODELobject.setColumnHASSCREWS(hasscrews);
			DBENTRYMODELobject.setColumnIsDelivered(isdelivered);

			//llenamos la lista con el dbentrymodel object digamos le metimos 10 objetos
            listOfDBENTRYMODEL.add(DBENTRYMODELobject);
        }

		//mandamos una lista con objetos que cada objeto lleva los datos como nombre fecha etc
        return listOfDBENTRYMODEL;
    }

	
	
	
	
	
	
	
	//Update
	public int updateEntry(xxDbEntryModel entryModel)
	{

    	// 1. get reference to writable DB
        SQLiteDatabase db = dbHelper.getWritableDatabase();

		// 2. create ContentValues to add key "column"/value
        ContentValues newValues = new ContentValues();
		//newValues.put("ID", entryModel.getID());
		newValues.put("DATE", entryModel.getColumnDATE());
		newValues.put("TIME", entryModel.getColumnTIME());
		newValues.put("NAME", entryModel.getColumnNAME());
		newValues.put("PHONE", entryModel.getColumnPHONE());
		newValues.put("EMAIL", entryModel.getColumnEMAIL());
		newValues.put("IMEI", entryModel.getColumnIMEI());
		newValues.put("BRAND", entryModel.getColumnBRAND());
		newValues.put("MODEL", entryModel.getColumnMODEL());
		newValues.put("COLOR", entryModel.getColumnCOLOR());
		newValues.put("CONDITIONS", entryModel.getColumnCONDITIONS());
		newValues.put("PASSWORD", entryModel.getColumnPASSWORD());
		newValues.put("KINDOFSERVICE", entryModel.getColumnKINDOFSERVICE());
		newValues.put("TOTALPRICE", entryModel.getColumnTOTALPRICE());
		newValues.put("DEPOSIT", entryModel.getColumnDEPOSIT());
		newValues.put("PARTSPRICE", entryModel.getColumnPARTSPRICE());
		newValues.put("DEADLINE", entryModel.getColumnDEADLINE());
		newValues.put("POWERSON", Boolean.valueOf(entryModel.getColumnPOWERSON()));
		newValues.put("HASCOVER", Boolean.valueOf(entryModel.getColumnHASCOVER()));
		newValues.put("HASSIM", Boolean.valueOf(entryModel.getColumnHASSIM()));
		newValues.put("HASMEMORY", Boolean.valueOf(entryModel.getColumnHASMEMORY()));
		newValues.put("HASBATTERY", Boolean.valueOf(entryModel.getColumnHASBATTERY()));
		newValues.put("HASSCREWS", Boolean.valueOf(entryModel.getColumnHASSCREWS()));
		newValues.put("ISDELIVERED", entryModel.getColumnIsDelivered());

        // 3. updating row
        int i = db.update(TABLE_NAME, //table
						  newValues, // column/value
						  "ID" + " = ?", // selections
						  new String[] { String.valueOf(entryModel.getID()) }); //selection args

        // 4. close
        db.close();
		Toast.makeText(context, "Cambios guardados", Toast.LENGTH_LONG).show();

        return i;

    }	
	
	
	
	
	
	//delete one row
	public Boolean delete(String id, String columnname){
		List<recyclerRowModel> data=new ArrayList<>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " ;", null);
		StringBuffer stringBuffer = new StringBuffer();
        recyclerRowModel dataModel = null; 
        while (cursor.moveToNext())
		{
            dataModel = new recyclerRowModel("","",columnname);
            String name = cursor.getString(cursor.getColumnIndexOrThrow("NAME"));
            dataModel.setName(name);
            stringBuffer.append(dataModel);
            data.add(dataModel);
        }
		recyclerRowModel n = data.get(0);
		Toast.makeText(context, n.getColumnName() + " borrado exitosamente", Toast.LENGTH_LONG).show();
		return db.delete(TABLE_NAME, "ID" + "=" + id, null) > 0;
	}

	
	
	
	
	
	public static String DB_FILEPATH = "/data/data/com.servicel.system/databases/serviciocelular.db";

	/**
	 * Copies the database file at the specified location over the current
	 * internal application database.
	 * */
	public boolean importDatabase(String dbPath) throws IOException {

		// Close the SQLiteOpenHelper so it will commit the created empty
		// database to internal storage.
		close();
		File newDb = new File(dbPath);
		File oldDb = new File(DB_FILEPATH);
		if (newDb.exists()) {
			FileUtils.copyFile(new FileInputStream(newDb), new FileOutputStream(oldDb));
			// Access the copied database so SQLiteHelper will cache it and mark
			// it as created.
			dbHelper.getWritableDatabase().close();
			return true;
		}
		return false;
	}

}

