package com.servicel.system;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.servicel.system.db.MySQLiteHelper;




public class MainActivity extends AppCompatActivity

{



	MySQLiteHelper recolectDataAdapter;
	public static String COLUMNID = "ID";
	public static String TABLENAME = "CLIENTES";
	public static String COLUMNNDATE = "DATE";
	public static String COLUMNNAME = "NAME";
	public static String COLUMNTIME = "TIME";
	public static String COLUMNNPHONE = "PHONE";
	public static String COLUMNNEMAIL ="EMAIL";
	public static String COLUMNNIMEI ="IMEI";
	public static String COLUMNBRAND = "BRAND";
	public static String COLUMNMODEL = "MODEL";
	public static String COLUMNCOLOR = "COLOR";
	public static String COLUMNCONDITIONS = "CONDITIONS";
	public static String COLUMNPASSWORD = "PASSWORD";
	public static String COLUMNKINDOFSERVICE = "KINDOFSERVICE";
	public static String COLUMNTOTALPRICE = "TOTALPRICE";
	public static String COLUMNDEPOSIT = "DEPOSIT";
	public static String COLUMNPARTSPRICE = "PARTSPRICE";
	public static String COLUMNDEADLINE = "DEADLINE";
	public static String COLUMNPOWERSON = "POWERSON";
	public static String COLUMNHASCOVER = "HASCOVER";
	public static String COLUMNHASSIM = "HASSIM";
	public static String COLUMNHASMEMORY = "HASMEMORY";
	public static String COLUMNHASBATTERY = "HASBATTERY";
	public static String COLUMNHASSCREWS = "HASSCREWS";

	Fragment fragmentToOpen;
    FrameLayout container;
    private static Context mContext;
    public static String currentFragment="current";
	public static String tagShowOrder = "shororder";

	public static String COLUMNISDELIVERED="ISDELIVERED";

	private Toolbar mTopToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		
		mTopToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mTopToolbar);
		
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		mContext = this.getApplicationContext();

		
		
		
		if (savedInstanceState != null)
		{
			fragmentToOpen = getFragmentManager().getFragment(savedInstanceState, currentFragment);
		}
		else
		{
			FragmentManager manager = getFragmentManager();
			final FragmentTransaction transaction= manager.beginTransaction();


			// 3------ send bundle to ShowOrder.java
			fragmentToOpen = new bClientsListFragment();


			try
			{
				transaction.replace(R.id.container, fragmentToOpen, currentFragment);
				transaction.addToBackStack(currentFragment);
				transaction.commit();	}
			catch (Exception e)
			{
				e.printStackTrace();	}
		}

		
		recolectDataAdapter = new MySQLiteHelper(getApplicationContext());

		return;
	}
	
	public static void toast(String st){
		Toast.makeText(mContext,st,1).show();
	}


}

