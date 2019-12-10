package com.servicel.system;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.os.*;
import android.support.design.widget.*;
import android.support.v7.widget.*;
import android.view.*;
import android.view.animation.*;
import android.widget.*;
import com.servicel.system.clientsrecycler.*;
import com.servicel.system.db.*;
import java.util.*;

import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;



public class ClientsListFragment extends Fragment 
{
//initialize
    MySQLiteHelper database;
    RecyclerView recyclerView;
    RecyclerAdapter recycler;
    List<DbEntryModel> datamodel;
	Boolean click = false;

	
	
	
	
	
//constructor
    public static ClientsListFragment newInstance()
    {
        ClientsListFragment fragment = new ClientsListFragment();
        return fragment;
    }



	
	
	
	
	
	
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
							 Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.clienstlistfr, container, false);
		
		
		
		
		
		
//Typeface
		MainActivity.overrideFonts(getActivity(),v);
		Typeface font = Typeface.createFromAsset(
		getActivity().getAssets(), 
			"facebook-letter-faces.ttf");
		TextView toolTit = v.findViewById(R.id.toolbar_title);
		toolTit.setTypeface(font);
			
			
		
		
		
		
		
		
//sqlite plus recyclerview
		MySQLiteHelper db = new MySQLiteHelper(this.getActivity());
		datamodel = new ArrayList<DbEntryModel>();
		datamodel=db.getEntries(
			MainActivity.COLUMNID,
			MainActivity.COLUMNNDATE, MainActivity.COLUMNTIME, MainActivity.COLUMNNAME,
			MainActivity.COLUMNNPHONE, MainActivity.COLUMNNEMAIL, MainActivity.COLUMNNIMEI,
			MainActivity.COLUMNBRAND, MainActivity.COLUMNMODEL, MainActivity.COLUMNCOLOR, MainActivity.COLUMNCONDITIONS,
			MainActivity.COLUMNPASSWORD, MainActivity.COLUMNKINDOFSERVICE, MainActivity.COLUMNTOTALPRICE,
			MainActivity.COLUMNDEPOSIT, MainActivity.COLUMNPARTSPRICE, MainActivity.COLUMNDEADLINE, MainActivity.COLUMNPOWERSON,
			MainActivity.COLUMNHASCOVER, MainActivity.COLUMNHASSIM, MainActivity.COLUMNHASMEMORY,
			MainActivity.COLUMNHASBATTERY, MainActivity.COLUMNHASSCREWS, MainActivity.COLUMNISDELIVERED
		);
		recyclerView = v.findViewById(R.id.recycler);
		recycler = new RecyclerAdapter(datamodel);
		LinearLayoutManager mLayoutManager =new LinearLayoutManager(this.getActivity());
		mLayoutManager.setReverseLayout(true);
		mLayoutManager.setStackFromEnd(true);
		recyclerView.setLayoutManager(mLayoutManager);
		recyclerView.setItemAnimator(new DefaultItemAnimator());
		recyclerView.setAdapter(recycler);



		
		
		
		
		
		
//add new entry button
		FloatingActionButton fab = v.findViewById(R.id.newOrder);
		fab.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view)
				{
					click = !click;
					if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP)
					{
						Interpolator interpolador = AnimationUtils.loadInterpolator(getActivity(),
																					android.R.interpolator.fast_out_slow_in);

						view.animate()
                            .rotation(click ? 45f : 0)
                            .setInterpolator(interpolador)
                            .start();
					}
						final Intent i = new Intent(getActivity(), LauncherActivity.class);
						final Bundle b = new Bundle();
						b.putString("id", "");
						b.putString("date", "");
						b.putString("time", "");
						b.putString("name", "");
						b.putString("phone", "");
						b.putString("email", "");
						b.putString("imei", "");
						b.putString("brand", "");
						b.putString("model", "");
						b.putString("color", "");
						b.putString("conditions", "");
						b.putString("password", "");
						b.putString("kindofservice", "");
						b.putString("totalprice", "");
						b.putString("deposit", "");
						b.putString("partsprice", "");
						b.putString("deadline", "");
						b.putString("powerson", "");
						b.putString("hascover", "");
						b.putString("hassim", "");
						b.putString("hasmemory", "");
						b.putString("hasbattery", "");
						b.putString("hasscrews", "");

						i.putExtras(b);
						getActivity().startActivity(i);
				}
			});

			
			
			





        return v;
    }

}
	
