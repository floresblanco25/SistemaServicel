package com.servicel.system.clientsrecycler;


import android.content.*;
import android.os.*;
import android.support.v7.widget.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import com.servicel.system.*;
import java.util.*;
import com.servicel.system.db.*;
import com.servicel.system.drawables.*;

/**
 * Created by gustavo.peiretti on 07/02/2017.
 */

public class cRecyclerAdapter extends RecyclerView.Adapter<cRecyclerAdapter.ViewHolder>
{
    static List<recyclerRowModel> recyclerRowModelList;
    LinearLayout row;
    public cRecyclerAdapter(List<recyclerRowModel> recyclerRowModelList)
    {
        this.recyclerRowModelList = recyclerRowModelList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.clientsrow, parent, false);//Inflates the xml row
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder xmlRow, int position)
    {
		// 1----- send bundle to LauncherActivity.java
		MySQLiteHelper database = new MySQLiteHelper(xmlRow.context);


		//3 job getting data then go to mysqlitehelper
		//desde la db
		//recibimos una lista con objetos que cada uno tienen datos colo fecha nombre etc y con get(id=posision) obtenemos un objeto de la lista 
		List listOfObjects = database.getDBENTRYMODELColumnStrings(aMainActivity.COLUMNID,
																   aMainActivity.COLUMNNDATE, aMainActivity.COLUMNTIME, aMainActivity.COLUMNNAME,
																   aMainActivity.COLUMNNPHONE, aMainActivity.COLUMNNEMAIL, aMainActivity.COLUMNNIMEI,
																   aMainActivity.COLUMNBRAND, aMainActivity.COLUMNMODEL, aMainActivity.COLUMNCOLOR, aMainActivity.COLUMNCONDITIONS,
																   aMainActivity.COLUMNPASSWORD, aMainActivity.COLUMNKINDOFSERVICE, aMainActivity.COLUMNTOTALPRICE,
																   aMainActivity.COLUMNDEPOSIT, aMainActivity.COLUMNPARTSPRICE, aMainActivity.COLUMNDEADLINE, aMainActivity.COLUMNPOWERSON,
																   aMainActivity.COLUMNHASCOVER, aMainActivity.COLUMNHASSIM, aMainActivity.COLUMNHASMEMORY,
																   aMainActivity.COLUMNHASBATTERY, aMainActivity.COLUMNHASSCREWS, aMainActivity.COLUMNISDELIVERED
																   );
		//con el objeto sacado segin la posision que clickeamos
		xxDbEntryModel object = (xxDbEntryModel) listOfObjects.get(position);
		//sacamos datos uno por uno del objeto que sacamos de la lista con getPosition();
		String id = object.getID();
		String date = object.getColumnDATE();
		String time = object.getColumnTIME();
		String name = object.getColumnNAME();
		String phone = object.getColumnPHONE();
		String email = object.getColumnEMAIL();
		String imei = object.getColumnIMEI();
		String brand = object.getColumnBRAND();
		String model = object.getColumnMODEL();
		String color = object.getColumnCOLOR();
		String conditions = object.getColumnCONDITIONS();
		String password = object.getColumnPASSWORD();
		String kindofservice = object.getColumnKINDOFSERVICE();
		String totalprice = object.getColumnTOTALPRICE();
		String deposit = object.getColumnDEPOSIT();
		String partsprice = object.getColumnPARTSPRICE();
		String deadline = object.getColumnDEADLINE();
		String powerson = object.getColumnPOWERSON();
		String hascover = object.getColumnHASCOVER();
		String hassim = object.getColumnHASSIM();
		String hasmemory = object.getColumnHASMEMORY();
		String hasbattery = object.getColumnHASBATTERY();
		String hasscrews = object.getColumnHASSCREWS();
		String isDelivered = object.getColumnIsDelivered();
		
		
		recyclerRowModel dataModel=recyclerRowModelList.get(position);
        xmlRow.rowTitle.setText(dataModel.getName());
		xmlRow.rowdetails.setText(brand+" "+model+", "+date+". ");
		xmlRow.rowstatus.setText(isDelivered+" "+deadline+".");
		if(dataModel.getstatus().contains("Entregado")){
			xmlRow.c3.setMax(100);
			xmlRow.c3.setProgress(100);
			xmlRow.c3.fillCircle(true);
			xmlRow.c3.setProgressColor(xmlRow.fillColor);
			
			
		xmlRow.check.setImageResource(R.drawable.baseline_check_white_18);
		}else{
			xmlRow.check.setImageResource(R.drawable.baseline_build_black_18);
			xmlRow.c3.fillCircle(false);
			xmlRow.c3.setProgressColor(xmlRow.progressColor);
			xmlRow.c3.setProgress(45);
			xmlRow.c3.setBackColor("#26000000");
			
		}
		
		
    }

    @Override
    public int getItemCount()
    {
        return recyclerRowModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder 
    {
        private TextView rowTitle,rowdetails,rowstatus;
		private Context context;
		private ImageView check;

		private CircularProgressBar c3;

		private String progressColor;

		private int lockedColor;

		private String fillColor;
        public ViewHolder(final View v)
		{
            super(v);
            rowTitle = v.findViewById(R.id.clientNameRow);
			rowdetails = v.findViewById(R.id.info);
			rowstatus = v.findViewById(R.id.rowStatus);
			check= v.findViewById(R.id.statusImage);
			c3 = v.findViewById(R.id.circularProgressBar);
			progressColor = v.getContext().getResources().getString(R.color.circleProgressUnits);
			fillColor = v.getContext().getResources().getString(R.color.circleProgressFillUnits);
			lockedColor = v.getContext().getResources().getColor(R.color.circleProgressLockedUnits);
			
			context = v.getContext();
			v.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View p1)
					{
						final Intent i = new Intent(p1.getContext(), cLauncherActivity.class);
						final Bundle b = new Bundle();
						Integer positionClicked = getPosition();
						// 1----- send bundle to LauncherActivity.java
						MySQLiteHelper database = new MySQLiteHelper(p1.getContext());


						//3 job getting data then go to mysqlitehelper
						//desde la db
						//recibimos una lista con objetos que cada uno tienen datos colo fecha nombre etc y con get(id=posision) obtenemos un objeto de la lista 
						List listOfObjects = database.getDBENTRYMODELColumnStrings(aMainActivity.COLUMNID,
																				   aMainActivity.COLUMNNDATE, aMainActivity.COLUMNTIME, aMainActivity.COLUMNNAME,
																				   aMainActivity.COLUMNNPHONE, aMainActivity.COLUMNNEMAIL, aMainActivity.COLUMNNIMEI,
																				   aMainActivity.COLUMNBRAND, aMainActivity.COLUMNMODEL, aMainActivity.COLUMNCOLOR, aMainActivity.COLUMNCONDITIONS,
																				   aMainActivity.COLUMNPASSWORD, aMainActivity.COLUMNKINDOFSERVICE, aMainActivity.COLUMNTOTALPRICE,
																				   aMainActivity.COLUMNDEPOSIT, aMainActivity.COLUMNPARTSPRICE, aMainActivity.COLUMNDEADLINE, aMainActivity.COLUMNPOWERSON,
																				   aMainActivity.COLUMNHASCOVER, aMainActivity.COLUMNHASSIM, aMainActivity.COLUMNHASMEMORY,
																				   aMainActivity.COLUMNHASBATTERY, aMainActivity.COLUMNHASSCREWS, aMainActivity.COLUMNISDELIVERED
																				   );
						//con el objeto sacado segin la posision que clickeamos
						xxDbEntryModel object = (xxDbEntryModel) listOfObjects.get(positionClicked);
						//sacamos datos uno por uno del objeto que sacamos de la lista con getPosition();
						String id = object.getID();
						String date = object.getColumnDATE();
						String time = object.getColumnTIME();
						String name = object.getColumnNAME();
						String phone = object.getColumnPHONE();
						String email = object.getColumnEMAIL();
						String imei = object.getColumnIMEI();
						String brand = object.getColumnBRAND();
						String model = object.getColumnMODEL();
						String color = object.getColumnCOLOR();
						String conditions = object.getColumnCONDITIONS();
						String password = object.getColumnPASSWORD();
						String kindofservice = object.getColumnKINDOFSERVICE();
						String totalprice = object.getColumnTOTALPRICE();
						String deposit = object.getColumnDEPOSIT();
						String partsprice = object.getColumnPARTSPRICE();
						String deadline = object.getColumnDEADLINE();
						String powerson = object.getColumnPOWERSON();
						String hascover = object.getColumnHASCOVER();
						String hassim = object.getColumnHASSIM();
						String hasmemory = object.getColumnHASMEMORY();
						String hasbattery = object.getColumnHASBATTERY();
						String hasscrews = object.getColumnHASSCREWS();
						String isDelivered = object.getColumnIsDelivered();

//												//3 job getting data then go to mysqlitehelper
						b.putString("id", id);
						b.putString("date", date);
						b.putString("time", time);
						b.putString("name", name);
						b.putString("phone", phone);
						b.putString("email", email);
						b.putString("imei", imei);
						b.putString("brand", brand);
						b.putString("model", model);
						b.putString("color", color);
						b.putString("conditions", conditions);
						b.putString("password", password);
						b.putString("kindofservice", kindofservice);
						b.putString("totalprice", totalprice);
						b.putString("deposit", deposit);
						b.putString("partsprice", partsprice);
						b.putString("deadline", deadline);
						b.putString("powerson", powerson);
						b.putString("hascover", hascover);
						b.putString("hassim", hassim);
						b.putString("hasmemory", hasmemory);
						b.putString("hasbattery", hasbattery);
						b.putString("hasscrews", hasscrews);
						b.putString("isdelivered", isDelivered);

						i.putExtras(b);
						p1.getContext().startActivity(i);
					}
				});
        }
    }

}