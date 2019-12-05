package com.servicel.system;
import android.app.*;
import android.content.*;
import android.os.*;
import android.support.v4.app.NavUtils;
import android.widget.*;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;


public class LauncherActivity extends Activity
{





//initialize
    Fragment fragmentToOpen;
    FrameLayout container;
    private static Context mContext;
    public static String currentFragment="current";
    String	strId,strDate,strTime, strName, strPhone, strEmail, strImei, strBrand, strModel, strColor,
    strConditions, strPassword, strKindOfService, strTotalPrice, strDeposit, strPartsPrice, strDeadLine, strIsDelivered;
    String booPowersOn, booHasCover, booHasSim, booHasMemory, booHasBattery, booHasScrews;
    private Bundle args;










    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);
		mContext = getApplicationContext();
		setContentView(R.layout.launcheractivity);




//receive bundle from adapter class
		try
		{
			args = getIntent().getExtras();
			strId = args.getString("id");
			strDate = args.getString("date");
			strTime =	args.getString("time");
			strName =	args.getString("name");
			strPhone =	args.getString("phone");
			strEmail =	args.getString("email");
			strImei =	args.getString("imei");
			strBrand =	args.getString("brand");
			strModel =	args.getString("model");
			strColor =	args.getString("color");
			strConditions =	args.getString("conditions");
			strPassword =	args.getString("password");
			strKindOfService = args.getString("kindofservice");
			strTotalPrice =	args.getString("totalprice");
			strDeposit =	args.getString("deposit");
			strPartsPrice =	args.getString("partsprice");
			strDeadLine =	args.getString("deadline");
			booPowersOn =	args.getString("powerson");
			booHasCover =	args.getString("hascover");
			booHasSim =	args.getString("hassim");
			booHasMemory =	args.getString("hasmemory");
			booHasBattery =	args.getString("hasbattery");
			booHasScrews =	args.getString("hasscrews");
			strIsDelivered = args.getString("isdelivered");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}








//saved instance
		if (savedInstanceState != null)
		{
			fragmentToOpen = getFragmentManager().getFragment(savedInstanceState, currentFragment);
		}
		else
		{
			FragmentManager manager = getFragmentManager();
			final FragmentTransaction transaction= manager.beginTransaction();

			fragmentToOpen = ShowOrCreateOrder.newInstance(
				strId,
				strDate, strTime, strName, strPhone, strEmail, strImei, strBrand, strModel, strColor,
				strConditions, strPassword, strKindOfService, strTotalPrice, strDeposit, strPartsPrice, strDeadLine,
				booPowersOn, booHasCover, booHasSim, booHasMemory, booHasBattery, booHasScrews, strIsDelivered
			);


			try
			{
				transaction.replace(R.id.container, fragmentToOpen, MainActivity.tagShowOrder);
				transaction.addToBackStack(currentFragment);
				transaction.commit();	}
			catch (Exception e)
			{
				e.printStackTrace();	}
		}
    }

	
	
	
	
	
	
	

	
	
	
//toast
    public static void toast(String p0)
    {
		Toast.makeText(getContext(), p0, Toast.LENGTH_SHORT).show();
    }

	
//cont3xt
    public static Context getContext()
    {
		return mContext;
    }

	
	
	
	
    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
		super.onSaveInstanceState(outState);
    }

	
	
	
//back pressed
	@Override
	public void onBackPressed()
	{
		Intent upIntent = NavUtils.getParentActivityIntent(this);
		NavUtils.navigateUpTo(this, upIntent);

		super.onBackPressed();
	}


}
