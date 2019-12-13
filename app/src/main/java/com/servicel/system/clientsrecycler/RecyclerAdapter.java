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

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>
{


//constructor
	List<DbEntryModel> recyclerRowModelList;
    LinearLayout row;






    public RecyclerAdapter(List<DbEntryModel> recyclerRowModelList)
    {
        this.recyclerRowModelList = recyclerRowModelList;
    }







//filter
	public void filterList(List<DbEntryModel> filterdNames)
	{
        this.recyclerRowModelList = filterdNames;
        notifyDataSetChanged();
    }






//inflate
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.clientsrow, parent, false);//Inflates the xml row
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }






//bind
    @Override
    public void onBindViewHolder(ViewHolder xmlRow, int position)
    {
		DbEntryModel object = recyclerRowModelList.get(position);
		String name = object.getColumnNAME();
		String date = object.getColumnDATE();
		String brand = object.getColumnBRAND();
		String model = object.getColumnMODEL();
		String deadline = object.getColumnDEADLINE();
		String isDelivered = object.getColumnIsDelivered();


		
		
//set row texts
        xmlRow.rowTitle.setText(name);
		xmlRow.rowdetails.setText(brand + " " + model + ", " + date + ". ");
		xmlRow.rowstatus.setText(isDelivered + " " + deadline + ".");
		
		
		
		
		
		
//set progress bar 
		if (object.getColumnIsDelivered().contains("Entregado"))
		{
			xmlRow.c3.setMax(100);
			xmlRow.c3.setProgress(100);
			xmlRow.c3.fillCircle(true);
			xmlRow.c3.setProgressColor(xmlRow.fillColor);
			xmlRow.check.setImageResource(R.drawable.baseline_check_white_18);
		}else
		{
			xmlRow.check.setImageResource(R.drawable.baseline_build_black_18);
			xmlRow.c3.fillCircle(false);
			xmlRow.c3.setProgressColor(xmlRow.progressColor);
			xmlRow.c3.setProgress(45);
			xmlRow.c3.setBackColor("#26000000");

		}


    }






//count
    @Override
    public int getItemCount()
    {
        return recyclerRowModelList.size();
    }







//viewholder class
    public class ViewHolder extends RecyclerView.ViewHolder 
    {

//initialize
        private TextView rowTitle,rowdetails,rowstatus;
		private Context context;
		private ImageView check;
		private CircularProgressBar c3;
		private String progressColor;
		private int lockedColor;
		private String fillColor;



//view holder
        public ViewHolder(final View v)
		{
            super(v);
//initialize
            rowTitle = v.findViewById(R.id.clientNameRow);
			rowdetails = v.findViewById(R.id.info);
			rowstatus = v.findViewById(R.id.rowStatus);
			check = v.findViewById(R.id.statusImage);
			c3 = v.findViewById(R.id.circularProgressBar);
			progressColor = v.getContext().getResources().getString(R.color.circleProgressUnits);
			fillColor = v.getContext().getResources().getString(R.color.circleProgressFillUnits);
			lockedColor = v.getContext().getResources().getColor(R.color.circleProgressLockedUnits);
			context = v.getContext();
			
			
			
			
			
			
			MainActivity.overrideFonts(v.getContext(),v,"GoogleSans-Regular.ttf");
			MainActivity.overrideFonts(context,rowTitle,"GoogleSans-Medium.ttf");





//view click
			v.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View p1)
					{
						final Intent i = new Intent(p1.getContext(), LauncherActivity.class);
						final Bundle b = new Bundle();
						Integer positionClicked = getPosition();
						DbEntryModel object = recyclerRowModelList.get(positionClicked);
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
