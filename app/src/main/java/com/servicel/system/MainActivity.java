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
import android.text.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import com.libizo.*;
import com.servicel.system.*;
import com.servicel.system.clientsrecycler.*;
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
	MySQLiteHelper database;
    RecyclerView recyclerView;
    RecyclerAdapter adapter;
    List<DbEntryModel> datamodel;
	Boolean click = false;
	CustomEditText edSearch;
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
	private RequestPermissionHandler mRequestPermissionHandler;









    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		view = findViewById(R.id.drawer);
		backup= findViewById(R.id.backupMenu);
		restore = findViewById(R.id.restoremenu);
		edSearch = findViewById(R.id.edSearch);
		mRequestPermissionHandler = new RequestPermissionHandler();
		mCheckPermission();
		//bottom bar
		BottomNavigationView navigation = findViewById(R.id.navigationmain);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
		
		
		
		
		
		
		
		
		
		
//Recycler
		MySQLiteHelper db = new MySQLiteHelper(this);
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
		recyclerView =  findViewById(R.id.recycler);
		adapter = new RecyclerAdapter(datamodel);
		LinearLayoutManager mLayoutManager =new LinearLayoutManager(this);
		mLayoutManager.setReverseLayout(true);
		mLayoutManager.setStackFromEnd(true);
		recyclerView.setLayoutManager(mLayoutManager);
		recyclerView.setItemAnimator(new DefaultItemAnimator());
		recyclerView.setHasFixedSize(true);
		recyclerView.setAdapter(adapter);
		
		
		
		
		
		
		
		
		
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


		
		
		
		
		
		

//search
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
		TextView toolTit = findViewById(R.id.toolbar_title);
		toolTit.setTypeface(font);
		
		
		
		
	
		
		
		
		
//drawer
		drawerLayout = findViewById(R.id.drawer);
		drawerToggle = new DuoDrawerToggle(this, drawerLayout, toolbar,
														   R.string.navigation_drawer_open,
														   R.string.navigation_drawer_close);
		drawerLayout.setDrawerListener(drawerToggle);
		drawerToggle.syncState();
		
		
		
		







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

					case R.id.showpending:
					{
						filterPendings("pendiente");
					}
					return true;
					
				case R.id.showdelivered:
					{
						filterPendings("");
					}
					return true;


            }
            return false;
        }

    };
	
	









//toast
	public static void toast(String st)
	{
		Toast.makeText(mContext, st, 1).show();
	}


	
	
	
	
	
	
	
	
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
	
	
	
	
	
	
	
	
	
//Permissions
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
	
	
	
	
	
	
	
	
	
	
//Filter
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
	private void filterPendings(String text) {
        ArrayList<DbEntryModel> filterdList = new ArrayList<>();
		for (DbEntryModel model : datamodel)
		{
            if (model.getColumnIsDelivered().toLowerCase().contains(text.toLowerCase())

				){
                filterdList.add(model);
            }
        }

        adapter.filterList(filterdList);
    }

	
	
	
	
	



	
}

