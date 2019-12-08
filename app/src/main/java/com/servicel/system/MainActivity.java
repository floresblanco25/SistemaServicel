package com.servicel.system;
import android.app.*;
import android.content.*;
import android.os.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.view.*;
import android.widget.*;
import com.servicel.system.db.*;
import nl.psdcompany.duonavigationdrawer.views.*;
import nl.psdcompany.duonavigationdrawer.widgets.*;

import android.support.v7.widget.Toolbar;
import android.graphics.*;


public class MainActivity extends AppCompatActivity

{

//initialize
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
	private DuoDrawerLayout drawerLayout;
	private DuoDrawerToggle drawerToggle;
	private TextView menuTitle;

	private View view;









    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		view = findViewById(R.id.drawer);







//light statusbar
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
		{
			getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR); 
		}
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
		{
		}









//toolbar
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		mContext = this.getApplicationContext();
		
		
		
		
		
//typeface
		overrideFonts(this,view);
		Typeface font = Typeface.createFromAsset(
		getAssets(), 
		"facebook-letter-faces.ttf");
		menuTitle = findViewById(R.id.menuTitlev);
		menuTitle.setTypeface(font);
		
		
		
		
		
		
//drawer
		drawerLayout = findViewById(R.id.drawer);
		drawerToggle = new DuoDrawerToggle(this, drawerLayout, toolbar,
														   R.string.navigation_drawer_open,
														   R.string.navigation_drawer_close);

		drawerLayout.setDrawerListener(drawerToggle);
		drawerToggle.syncState();
		
		
		
		
		
//instance
		if (savedInstanceState != null)
		{
			fragmentToOpen = getFragmentManager().getFragment(savedInstanceState, currentFragment);
		}
		else
		{
			FragmentManager manager = getFragmentManager();
			final FragmentTransaction transaction= manager.beginTransaction();






//Show initial frgmnt
			fragmentToOpen = new ClientsListFragment();
			try
			{
				transaction.replace(R.id.container, fragmentToOpen, currentFragment);
				transaction.addToBackStack(currentFragment);
				transaction.commit();	}
			catch (Exception e)
			{
				e.printStackTrace();	
			}
		}





//Initialize sqlite
		recolectDataAdapter = new MySQLiteHelper(getApplicationContext());

		return;
	}












//toast
	public static void toast(String st)
	{
		Toast.makeText(mContext, st, 1).show();
	}


	
	
	
	//menu button
	public void openCloseMenu(View view){
		
		drawerLayout.openDrawer();
	};
	
	
	
	
	
	
	
//font
	public static void overrideFonts(final Context context, final View v) {
		try {
			if (v instanceof ViewGroup) {
				ViewGroup vg = (ViewGroup) v;
				for (int i = 0; i < vg.getChildCount(); i++) {
					View child = vg.getChildAt(i);
					overrideFonts(context, child);
				}
			} else if (v instanceof TextView ) {
				((TextView) v).setTypeface(Typeface.createFromAsset(context.getAssets(), "GoogleSans-Regular.ttf"));
			}
		} catch (Exception e) {
		}
	}



	
}

