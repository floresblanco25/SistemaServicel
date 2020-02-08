package com.servicel.system.db;

import android.content.*;
import android.database.*;
import android.database.sqlite.*;
import android.os.*;
import android.util.*;
import android.widget.*;
import com.servicel.system.*;
import java.io.*;
import java.nio.channels.*;
import java.util.*;

/**
 * Created by admin on 7/18/2016.
 */



public class MySQLiteHelper
{
    public static final String DATABASE_NAME = "serviciocelular.db";
    String ok="OK";
    static final int DATABASE_VERSION = 1;
    public  static String getName="";
    public static final int NAME_COLUMN = 1;
    static final String DATABASE_CREATE = "create table CLIENTES( ID integer primary key autoincrement,DATE  text,TIME  text,NAME text,PHONE text,EMAIL text,IMEI text,BRAND text,MODEL text,COLOR text,CONDITIONS text,PASSWORD text,KINDOFSERVICE text,TOTALPRICE text,DEPOSIT text,PARTSPRICE text,DEADLINE text,POWERSON text,HASCOVER text,HASSIM text,HASMEMORY text,HASBATTERY text,HASSCREWS text,ISDELIVERED text); ";
    public static SQLiteDatabase db;
    private final Context context;
    private static SQLiteOpenHelper dbHelper;
    private static String TABLE_NAME = "CLIENTES";

	private String foldername = "/Servicio Celular";








//Initialize dbhelper
    public  MySQLiteHelper(Context _context)
    {
        context = _context;
        dbHelper = new SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }










//Insert entry
    public String insertEntry(
		String mDate, String mTime, String mName, String mPhone, String mEmail, 
		String mImei, String mBrand, String mModel, String mColor, 
		String mConditions, String mPassword, String mKindOfService, 
		String mTotalPrice, String mDeposit, String mPartsPrice, String mDeadline, 
		Boolean mPowersOn, Boolean mHasCover, Boolean mHasSim, Boolean mHasMemory, 
		Boolean mHasBattery, Boolean mHasScrews, String mIsDelivered)
	{
		try
		{
            ContentValues newValues = new ContentValues();
            newValues.put("DATE", mDate);
            newValues.put("TIME", mTime);
            newValues.put("NAME", mName);
            newValues.put("PHONE", mPhone);
			newValues.put("EMAIL", mEmail);
			newValues.put("IMEI", mImei);
			newValues.put("BRAND", mBrand);
			newValues.put("MODEL", mModel);
			newValues.put("COLOR", mColor);
			newValues.put("CONDITIONS", mConditions);
			newValues.put("PASSWORD", mPassword);
			newValues.put("KINDOFSERVICE", mKindOfService);
			newValues.put("TOTALPRICE", mTotalPrice);
			newValues.put("DEPOSIT", mDeposit);
			newValues.put("PARTSPRICE", mPartsPrice);
			newValues.put("DEADLINE", mDeadline);
			newValues.put("POWERSON", mPowersOn);
			newValues.put("HASCOVER", mHasCover);
			newValues.put("HASSIM", mHasSim);
			newValues.put("HASMEMORY", mHasMemory);
			newValues.put("HASBATTERY", mHasBattery);
			newValues.put("HASSCREWS", mHasScrews);
			newValues.put("ISDELIVERED", mIsDelivered);

            db = dbHelper.getWritableDatabase();
            long result=db.insert("CLIENTES", null, newValues);
            System.out.print(result);
            Toast.makeText(context, "Orden guardada", Toast.LENGTH_SHORT).show();
			backupDb();


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

    public void close()
    {
        db.close();
    }
    public  SQLiteDatabase getDatabaseInstance()
    {
        return db;
    }


	
	








//Made for getting data from database 
	public List<DbEntryModel> getEntries(
		String columnID, String columnDATE, String columnTIME, String columnNAME,
		String columnPHONE, String columnEMAIL, String columnIMEI, String columnBRAND, String columnMODEL, String columnCOLOR,
		String columnCONDITIONS, String columnPASSWORD, String columnKINDOFSERVICE, String columnTOTALPRICE,
		String columnDEPOSIT, String columnPARTSPRICE, String columnDEADLINE, String columnPOWERSON, String columnHASCOVER,
		String columnHASSIM, String columnHASMEMORY, String columnHASBATTERY, String columnHASSCREWS, String columnIsDelivered
	)
	{
        List<DbEntryModel> listOfModels=new ArrayList<>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " ;", null);
        DbEntryModel entryModel = null; 

        while (cursor.moveToNext())
		{
            entryModel = new DbEntryModel(
				columnID, columnDATE, columnTIME, columnNAME,
				columnPHONE, columnEMAIL, columnIMEI, columnBRAND, columnMODEL, columnCOLOR,
				columnCONDITIONS, columnPASSWORD, columnKINDOFSERVICE, columnTOTALPRICE,
				columnDEPOSIT, columnPARTSPRICE, columnDEADLINE, columnPOWERSON, columnHASCOVER,
				columnHASSIM, columnHASMEMORY, columnHASBATTERY, columnHASSCREWS, columnIsDelivered);
				
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

			entryModel.setId(id);
            entryModel.setColumnDATE(date);
			entryModel.setColumnTIME(time);
			entryModel.setColumnNAME(name);
			entryModel.setColumnPHONE(phone);
			entryModel.setColumnEMAIL(email);
			entryModel.setColumnIMEI(imei);
			entryModel.setColumnBRAND(brand);
			entryModel.setColumnMODEL(model);
			entryModel.setColumnCOLOR(color);
			entryModel.setColumnCONDITIONS(conditions);
			entryModel.setColumnPASSWORD(password);
			entryModel.setColumnKINDOFSERVICE(kindofservice);
			entryModel.setColumnTOTALPRICE(totalprice);
			entryModel.setColumnDEPOSIT(deposit);
			entryModel.setColumnPARTSPRICE(partsprice);
			entryModel.setColumnDEADLINE(deadline);
			entryModel.setColumnPOWERSON(powerson);
			entryModel.setColumnHASCOVER(hascover);
			entryModel.setColumnHASSIM(hassim);
			entryModel.setColumnHASMEMORY(hasmemory);
			entryModel.setColumnHASBATTERY(hasbattery);
			entryModel.setColumnHASSCREWS(hasscrews);
			entryModel.setColumnIsDelivered(isdelivered);

            listOfModels.add(entryModel);
        }

        return listOfModels;
    }





	
	



//Update
	public int updateEntry(DbEntryModel entryModel)
	{
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues newValues = new ContentValues();
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
		
        int i = db.update(TABLE_NAME, //table
						  newValues, // column/value
						  "ID" + " = ?", // selections
						  new String[] { String.valueOf(entryModel.getID()) });
        db.close();
		Toast.makeText(context, "Cambios guardados", Toast.LENGTH_LONG).show();
        return i;

    }	


	
	
	
	



//delete one row
	public Boolean delete(String id, String columnname)
	{
		List<DbEntryModel> data=new ArrayList<>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " ;", null);
        DbEntryModel dataModel = null; 
        while (cursor.moveToNext())
		{
            dataModel = new DbEntryModel("","", "", columnname,"","","","","","","","","","","","","","","","","","","","");
            String name = cursor.getString(cursor.getColumnIndexOrThrow("NAME"));
            dataModel.setColumnNAME(name);
            data.add(dataModel);
        }
		DbEntryModel n = data.get(data.size()-1);
		Toast.makeText(context, n.getColumnNAME() + " borrado exitosamente", Toast.LENGTH_LONG).show();
		return db.delete(TABLE_NAME, "ID" + "=" + id, null) > 0;
	}
	
	
	
	
	
	
	
	
//export
	public void backupDb(){
		try {
			File sd = Environment.getExternalStorageDirectory();
			File data = Environment.getDataDirectory();
			File folder = new File(Environment.getExternalStorageDirectory() + foldername);
			folder.mkdir();

			if (sd.canWrite()) {
				String currentDBPath = DB_FILEPATH;
				String backupDBPath = foldername+"/"+DATABASE_NAME;
				File currentDB = new File(data, currentDBPath);
				File backupDB = new File(sd, backupDBPath);

				if (currentDB.exists())
				{
					FileChannel src = new FileInputStream(currentDB).getChannel();
					FileChannel dst = new FileOutputStream(backupDB).getChannel();
					dst.transferFrom(src, 0, src.size());
					src.close();
					dst.close();
					MainActivity.toast("Copia de seguridad guardada en  "+backupDBPath);
				}
			}
		} catch (Exception e) {
			MainActivity.toast(e.toString());
		}
	}
	
	
	public File ShareDb(){
			File sd = Environment.getExternalStorageDirectory();
			File data = Environment.getDataDirectory();
			File folder = new File(Environment.getExternalStorageDirectory() + foldername);
			folder.mkdir();

				String currentDBPath = DB_FILEPATH;
				String backupDBPath = foldername+"/"+DATABASE_NAME;
				File currentDB = new File(data, currentDBPath);
				File backupDB = new File(sd, backupDBPath);

		MainActivity.toast("Compartiendo");
		return backupDB;
		}
	
	
	
	
	
	
	
	
//restore
	public void restoreDb(){
		try {
			File sd = Environment.getExternalStorageDirectory();
			File data = Environment.getDataDirectory();

			if (sd.canWrite()) {
				String currentDBPath = DB_FILEPATH;
				String backupDBPath =  foldername+"/"+DATABASE_NAME;
				File currentDB = new File(data, currentDBPath);
				File backupDB = new File(sd, backupDBPath);

				if (currentDB.exists()) {
					FileChannel src = new FileInputStream(backupDB).getChannel();
					FileChannel dst = new FileOutputStream(currentDB).getChannel();
					dst.transferFrom(src, 0, src.size());
					src.close();
					dst.close();
					MainActivity.toast("Datos restaurados correctamente desde "+backupDBPath);
				}
			}
		} catch (Exception e) {
			MainActivity.toast(e.toString());
		}
	}





	public static String DB_FILEPATH = "//data/com.servicel.system/databases/serviciocelular.db"
	;

}

