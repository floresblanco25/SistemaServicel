package com.servicel.system;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.os.*;
import android.support.annotation.*;
import android.support.design.widget.*;
import android.support.v7.widget.*;
import android.text.*;
import android.view.*;
import android.view.animation.*;
import android.widget.*;
import com.libizo.*;
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
    RecyclerAdapter adapter;
    List<DbEntryModel> datamodel;
	Boolean click = false;
	CustomEditText edSearch;

	
	
	
	
	
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
		edSearch = v.findViewById(R.id.edSearch);
		
		
		
		
//Typeface
		MainActivity.overrideFonts(getActivity(),v,"GoogleSans-Regular.ttf");
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
		adapter = new RecyclerAdapter(datamodel);
		LinearLayoutManager mLayoutManager =new LinearLayoutManager(this.getActivity());
		mLayoutManager.setReverseLayout(true);
		mLayoutManager.setStackFromEnd(true);
		recyclerView.setLayoutManager(mLayoutManager);
		recyclerView.setItemAnimator(new DefaultItemAnimator());
		recyclerView.setHasFixedSize(true);
		recyclerView.setAdapter(adapter);
		
		
		
		
		
		



		
		
		
		
		
		
			
			
			
			
			
//search edittext
		edSearch.addTextChangedListener(new TextWatcher() {
				@Override
				public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

				}

				@Override
				public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

				}

				@Override
				public void afterTextChanged(Editable editable) {
					filter(editable.toString());
				}
			});

			
			
			





        return v;
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//filter
	private void filter(String text) {
        ArrayList<DbEntryModel> filterdList = new ArrayList<>();
		for (DbEntryModel model : datamodel)
		{
            if (model.getColumnNAME().toLowerCase().contains(text.toLowerCase())
				
				){
                filterdList.add(model);
            }
        }

        adapter.filterList(filterdList);
    }

}









//|
//				model.getColumnKINDOFSERVICE().toLowerCase().contains(text.toLowerCase())|
//				model.getColumnBRAND().toLowerCase().contains(text.toLowerCase())|
//				model.getColumnCOLOR().toLowerCase().contains(text.toLowerCase())|
//				model.getColumnCONDITIONS().toLowerCase().contains(text.toLowerCase())|
//				model.getColumnEMAIL().toLowerCase().contains(text.toLowerCase())|
//				model.getColumnMODEL().toLowerCase().contains(text.toLowerCase())|
//				model.getColumnPHONE().toLowerCase().contains(text.toLowerCase())|
//				model.getColumnDEADLINE().toLowerCase().contains(text.toLowerCase())
	
