package com.servicel.system;


import android.content.*;
import android.os.*;
import android.support.design.widget.*;
import android.support.v7.widget.*;
import android.view.*;
import android.view.animation.*;
import android.widget.*;
import com.servicel.system.*;
import com.servicel.system.clientsrecycler.*;
import com.servicel.system.db.*;
import java.util.*;

import android.view.animation.AnimationUtils;
import android.app.*;

public class ClientsListFragment extends Fragment 
{

    private Button nOrder;
    MySQLiteHelper database;
    RecyclerView recyclerView;
    RecyclerAdapter recycler;
    List<recyclerRowModel> datamodel;

	Boolean click = false;

    public static ClientsListFragment newInstance()
    {
        ClientsListFragment fragment = new ClientsListFragment();
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
							 Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.mainpage, container, false);

		MySQLiteHelper db = new MySQLiteHelper(this.getActivity());
		datamodel = new ArrayList<recyclerRowModel>();
		datamodel =  db.getColumnStrings(MainActivity.COLUMNNAME, MainActivity.COLUMNISDELIVERED);//Column names returned as array list from mysqlitehelper.getcolumnstrings(string columnname)
		recyclerView = v.findViewById(R.id.recycler);
		recycler = new RecyclerAdapter(datamodel);//Passes a arraylist of recyclerrow model


		LinearLayoutManager mLayoutManager =new LinearLayoutManager(this.getActivity());
		mLayoutManager.setReverseLayout(true);
		mLayoutManager.setStackFromEnd(true);
		recyclerView.setLayoutManager(mLayoutManager);
		recyclerView.setItemAnimator(new DefaultItemAnimator());
		recyclerView.setAdapter(recycler);




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
	
