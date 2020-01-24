package com.servicel.system;
import android.*;
import android.app.*;
import android.content.*;
import android.graphics.*;
import android.os.*;
import android.support.annotation.*;
import android.support.design.widget.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import com.servicel.system.*;
import com.servicel.system.db.*;
import java.util.*;
import nl.psdcompany.duonavigationdrawer.views.*;
import nl.psdcompany.duonavigationdrawer.widgets.*;

import android.support.v7.widget.Toolbar;
import com.servicel.system.LauncherActivity;
import com.servicel.system.R;

public class MainActivity extends AppCompatActivity

{

//initialize
	MySQLiteHelper dbHelper;
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
	LinearLayout backup,restore;
	String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/";
	List<DbEntryModel> datamodel;
	private RequestPermissionHandler mRequestPermissionHandler;









    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		view = findViewById(R.id.drawer);
		backup= findViewById(R.id.backupMenu);
		restore = findViewById(R.id.restoremenu);
		mRequestPermissionHandler = new RequestPermissionHandler();
		mCheckPermission();
		//bottom bar
		BottomNavigationView navigation = findViewById(R.id.navigationmain);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
		
		
		
		
		
		
//scroll listener
		CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigation.getLayoutParams();
		layoutParams.setBehavior(new BottomNavigationViewBehavior());







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
		overrideFonts(this,view,"GoogleSans-Medium.ttf");
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
		dbHelper = new MySQLiteHelper(getApplicationContext());
		dbHelper = dbHelper.open();
		
		
		
		
		
		
		
//backup
		backup.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
				dbHelper.backupDb();
				}


			});
		restore.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					dbHelper.restoreDb();
					recreate();
				}
			});
		
		
		

		return;
	}

	
	
	
	
	
	
	
	
	
	
	
	//bottom nav view
	private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
	= new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item)
		{
			switch (item.getItemId())
			{
                case R.id.addPerson:
					{
						final Intent i = new Intent(getApplicationContext(), LauncherActivity.class);
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
						startActivity(i);
					}
                    return true;




				case R.id.showMenu:{

					}
            }
            return false;
        }

    };
	
	











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
	public static void overrideFonts(final Context context, final View v, String f) {
		try {
			if (v instanceof ViewGroup) {
				ViewGroup vg = (ViewGroup) v;
				for (int i = 0; i < vg.getChildCount(); i++) {
					View child = vg.getChildAt(i);
					overrideFonts(context, child,f);
				}
			} else if (v instanceof TextView ) {
				((TextView) v).setTypeface(Typeface.createFromAsset(context.getAssets(), f));
			}
		} catch (Exception e) {
		}
		
	}
	
	
	
	
	
	
	
	
	
		private void mCheckPermission(){
			mRequestPermissionHandler.requestPermission(this, new String[] {
					Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE
				}, 123, new RequestPermissionHandler.RequestPermissionListener() {
					@Override
					public void onSuccess() {
					}

					@Override
					public void onFailed() {
					}
				});

		}
		
	@Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
										   @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mRequestPermissionHandler.onRequestPermissionsResult(requestCode, permissions,
															 grantResults);
    }
	
	
	
	
	



	
}

