package com.servicel.system;
import android.app.*;
import android.content.*;
import android.graphics.*;
import android.os.*;
import android.support.annotation.*;
import android.support.design.widget.*;
import android.support.v4.app.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import com.github.angads25.toggle.*;
import com.servicel.system.db.*;
import java.text.*;
import java.util.*;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.app.DialogFragment;
import android.app.Fragment;
import android.net.*;


public class ShowOrCreateOrder extends Fragment
{


//initialize
	private TextView id;
	private boolean edition;
	LabeledSwitch powersOn;
    EditText date,time, name, phone, email, imei, brand, model, color,conditions, password, kindOfService, totalPrice, deposit, partsPrice, deadline; 
    LabeledSwitch hasCover, hasSim, hasMemory, hasBattery, hasScrews;
    Button setDateButton;
	ImageView back;
    String receieveOk, idStr,dateStr,timeStr, nameStr, phoneStr, emailStr, imeiStr, brandStr, modelStr, colorStr,conditionsStr, passwordStr, kindOfServiceStr, totalPriceStr, 
	depositStr, partsPriceStr, deadlineStr,powersOnStr_,hasCoverStr_,hasSimStr_, hasMemoryStr_, hasBatteryStr_, hasScrewsStr_;
    Boolean powersOnBool, hasCoverBool, hasSimBool, hasMemoryBool, hasBatteryBool, hasScrewsBool;
    TextView statusText;
    MySQLiteHelper databaseHelper;
    Boolean status = false;
    View v;
	boolean click = false;
	private String isDeliveredStr_="Pendiente";





//Constructor
	public static ShowOrCreateOrder newInstance()
	{
        ShowOrCreateOrder fragment = new ShowOrCreateOrder();
        return fragment;
    }






//call fragment and retrieve bundle
    public static ShowOrCreateOrder newInstance(
		String idBundle,
		String dateBundle, String timeBundle, String nameBundle, String phoneBundle, String emailBundle, String imeiBundle, String brandBundle, String modelBundle, String colorBundle,
		String conditionsBundle, String passwordBundle, String kindOfServiceBundle, String totalPriceBundle, String depositBundle, String partsPriceBundle, String deadlineBundle,
		String powersOnBundle, String hasCoverBundle, String hasSimBundle, String hasMemoryBundle, String hasBatteryBundle, String hasScrewsBundle, String isDeliveredBundle
	)
	{
		ShowOrCreateOrder fragment = new ShowOrCreateOrder();
		Bundle args = new Bundle();
		args.putString("id", idBundle);
		args.putString("date", dateBundle);
		args.putString("time", timeBundle);
		args.putString("name", nameBundle);
		args.putString("phone", phoneBundle);
		args.putString("email", emailBundle);
		args.putString("imei", imeiBundle);
		args.putString("brand", brandBundle);
		args.putString("model", modelBundle);
		args.putString("color", colorBundle);
		args.putString("conditions", conditionsBundle);
		args.putString("password", passwordBundle);
		args.putString("kindofservice", kindOfServiceBundle);
		args.putString("totalprice", totalPriceBundle);
		args.putString("deposit", depositBundle);
		args.putString("partsprice", partsPriceBundle);
		args.putString("deadline", deadlineBundle);
		args.putString("powerson", powersOnBundle);
		args.putString("hascover", hasCoverBundle);
		args.putString("hassim", hasSimBundle);
		args.putString("hasmemory", hasMemoryBundle);
		args.putString("hasbattery", hasBatteryBundle);
		args.putString("hasscrews", hasScrewsBundle);
		args.putString("isdelivered", isDeliveredBundle);
		fragment.setArguments(args);
		return fragment;
    }








    @Override
    public void onCreate(Bundle savedInstanceState)
    {
		idStr = getArguments().getString("id");
		dateStr = getArguments().getString("date");
		timeStr = getArguments().getString("time");
		nameStr = getArguments().getString("name");
		phoneStr = getArguments().getString("phone");
		emailStr = getArguments().getString("email");
		imeiStr = getArguments().getString("imei");
		brandStr = getArguments().getString("brand");
		modelStr = getArguments().getString("model");
		colorStr = getArguments().getString("color");
		conditionsStr = getArguments().getString("conditions");
		passwordStr = getArguments().getString("password");
		kindOfServiceStr = getArguments().getString("kindofservice");
		totalPriceStr = getArguments().getString("totalprice");
		depositStr = getArguments().getString("deposit");
		partsPriceStr = getArguments().getString("partsprice");
		deadlineStr = getArguments().getString("deadline");
		powersOnStr_ = getArguments().getString("powerson");
		hasCoverStr_ = getArguments().getString("hascover");
		hasSimStr_ = getArguments().getString("hassim");
		hasMemoryStr_ = getArguments().getString("hasmemory");
		hasBatteryStr_ = getArguments().getString("hasbattery");
		hasScrewsStr_ = getArguments().getString("hasscrews");
		isDeliveredStr_ = getArguments().getString("isdelivered");
		super.onCreate(savedInstanceState);
		this.setRetainInstance(false);
    }







	//OnCreate
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
							 Bundle savedInstanceState)
	{
        View v = inflater.inflate(R.layout.neworder, container, false);
		id = v.findViewById(R.id.idText);
		date = v.findViewById(R.id.edDate);
		time = v.findViewById(R.id.edHour);
		name = v.findViewById(R.id.edName);
		phone = v.findViewById(R.id.edPhone);
		email = v.findViewById(R.id.edEmail);
		imei = v.findViewById(R.id.edImei);
		brand = v.findViewById(R.id.edBrand);
		model = v.findViewById(R.id.edModel);
		color = v.findViewById(R.id.edColor);
		conditions = v.findViewById(R.id.edConditions);
		password = v.findViewById(R.id.edPassword);
		kindOfService = v.findViewById(R.id.edKindOfService);
		totalPrice = v.findViewById(R.id.edTotalPrice);
		deposit = v.findViewById(R.id.edDeposit);
		partsPrice = v.findViewById(R.id.edPartsPrice);
		deadline = v.findViewById(R.id.edDeadline);
		powersOn = v.findViewById(R.id.switchPowerOn);
		hasCover = v.findViewById(R.id.switchHasCover);
		hasSim = v.findViewById(R.id.switchHasSim);
		hasMemory = v.findViewById(R.id.switchHasMemory);
		hasBattery = v.findViewById(R.id.switchHasBatt);
		hasScrews = v.findViewById(R.id.switchMissingScrew);
		statusText = v.findViewById(R.id.status);
		setDateButton = v.findViewById(R.id.setDateButton);
		back = v.findViewById(R.id.backButton);








//bottom bar
		BottomNavigationView navigation = v.findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
		BottomNavigationViewHelper.removeShiftMode(navigation);








//Set saved data and disable fields
		if (dateStr.isEmpty())
		{
			SimpleDateFormat dateF = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
			SimpleDateFormat timeF = new SimpleDateFormat("hh:mm a", Locale.getDefault());
			String date_ = dateF.format(Calendar.getInstance().getTime());
			String time_ = timeF.format(Calendar.getInstance().getTime());
			date.setText(date_);
			time.setText(time_);
		}
		else
		{
//If data received from db
			date.setText(dateStr);
			time.setText(timeStr);
			name.setEnabled(false);
			phone.setEnabled(false);
			email.setEnabled(false);
			imei.setEnabled(false);
			brand.setEnabled(false);
			model.setEnabled(false);
			color.setEnabled(false);
			conditions.setEnabled(false);
			password.setEnabled(false);
			kindOfService.setEnabled(false);
			totalPrice.setEnabled(false);
			deposit.setEnabled(false);
			partsPrice.setEnabled(false);
			deadline.setEnabled(false);
			powersOn.setEnabled(false);
			hasCover.setEnabled(false);
			hasSim.setEnabled(false);
			hasBattery.setEnabled(false);
			hasMemory.setEnabled(false);
			hasScrews.setEnabled(false);
			setDateButton.setEnabled(false);
		}





//set text
		id.setText("ID " + idStr);
		name.setText(nameStr);
		phone.setText(phoneStr);
		email.setText(emailStr);
		imei.setText(imeiStr);
		brand.setText(brandStr);
		model.setText(modelStr);
		color.setText(colorStr);
		conditions.setText(conditionsStr);
		password.setText(passwordStr);
		kindOfService.setText(kindOfServiceStr);
		totalPrice.setText(totalPriceStr);
		deposit.setText(depositStr);
		partsPrice.setText(partsPriceStr);
		deadline.setText(deadlineStr);
		statusText.setText(isDeliveredStr_);
		isEdited(false);

		if (powersOnStr_.contains("1"))
		{
			powersOn.setOn(true);
			powersOn.setLabelOn("Si");
		}
		else
		{powersOn.setLabelOff("No");
		}
		if (hasCoverStr_.contains("1"))
		{
			hasCover.setOn(true);
			hasCover.setLabelOn("Si");
		}
		else
		{hasCover.setLabelOff("No");
		}
		if (hasSimStr_.contains("1"))
		{
			hasSim.setOn(true);
			hasSim.setLabelOn("Si");
		}
		else
		{hasSim.setLabelOff("No");}
		if (hasMemoryStr_.contains("1"))
		{
			hasMemory.setOn(true);
			hasMemory.setLabelOn("Si");
		}
		else
		{hasMemory.setLabelOff("No");}
		if (hasBatteryStr_.contains("1"))
		{
			hasBattery.setOn(true);
			hasBattery.setLabelOn("Si");
		}
		else
		{hasBattery.setLabelOff("No");}
		if (hasScrewsStr_.contains("1"))
		{
			hasScrews.setOn(true);
			hasScrews.setLabelOn("Si");
		}
		else
		{hasScrews.setLabelOff("No");}

		databaseHelper = new MySQLiteHelper(getActivity());
		databaseHelper = databaseHelper.open();





//calendar deadline button
		final Calendar cal = Calendar.getInstance(TimeZone.getDefault());
		setDateButton.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					DatePickerDialog datePicker = new DatePickerDialog(getActivity(),
																	   DialogFragment.STYLE_NORMAL, datePickerListener,
																	   cal.get(Calendar.YEAR), 
																	   cal.get(Calendar.MONTH),
																	   cal.get(Calendar.DAY_OF_MONTH));
					datePicker.setCancelable(false);
					datePicker.setTitle("Fecha de entrega");
					datePicker.show();
				}
				private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
					public void onDateSet(DatePicker view, int selectedYear,
										  int selectedMonth, int selectedDay)
					{
						String year1 = String.valueOf(selectedYear);
						String month1 = String.valueOf(selectedMonth + 1);
						String day1 = String.valueOf(selectedDay);
						deadline.setText(day1 + "/" + month1 + "/" + year1);


					}};
			});




















//back buttom 
		back.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					final AlertDialog dialog2 = new AlertDialog.Builder(getActivity()).create();
					dialog2.setTitle("Regresar");
					dialog2.setMessage("¿Seguro que desea regresar y cancelar la edición? No se guardaran los datos.");
					dialog2.setButton(AlertDialog.BUTTON_POSITIVE, "Si",
						new DialogInterface.OnClickListener(){

							@Override
							public void onClick(DialogInterface p1, int p2)
							{
								goBack();

							}

						});
					dialog2.setButton(AlertDialog.BUTTON_NEGATIVE, "No", 
						new DialogInterface.OnClickListener(){

							@Override
							public void onClick(DialogInterface p1, int p2)
							{
								dialog2.dismiss();
							}
						});
					dialog2.show();
				}
			});














//Hide keyboard
		getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);










		return v;
    }











//------------------------------------------METHODS------------------------------------//**
	private void enableall()
	{
		name.setEnabled(true);
		phone.setEnabled(true);
		email.setEnabled(true);
		imei.setEnabled(true);
		brand.setEnabled(true);
		model.setEnabled(true);
		color.setEnabled(true);
		conditions.setEnabled(true);
		password.setEnabled(true);
		kindOfService.setEnabled(true);
		totalPrice.setEnabled(true);
		deposit.setEnabled(true);
		partsPrice.setEnabled(true);
		deadline.setEnabled(true);
		powersOn.setEnabled(true);
		hasCover.setEnabled(true);
		hasSim.setEnabled(true);
		hasBattery.setEnabled(true);
		hasMemory.setEnabled(true);
		hasScrews.setEnabled(true);
		setDateButton.setEnabled(true);
	}







//edited
	private void isEdited(boolean p0)
	{
		edition = p0;
	}








//update db 
	public void updateRow(String idStr, String dateStr, String timeStr, String nameStr, String phoneStr, String emailStr, 
						  String imeiStr, String brandStr, String modelStr, String colorStr, String conditionsStr, String passwordStr, 
						  String kindOfServiceStr, String totalPriceStr, String depositStr, String partsPriceStr, String deadlineStr, 
						  Boolean powersOnBool, Boolean hasCoverBool, Boolean hasSimBool, Boolean hasMemoryBool, Boolean hasBatteryBool, 
						  Boolean hasScrewsBool, String delivered)
	{
		DbEntryModel e = new DbEntryModel(
			idStr,
			dateStr, 
			timeStr, nameStr, phoneStr, emailStr, imeiStr, brandStr, modelStr, colorStr,
			conditionsStr, passwordStr, kindOfServiceStr, totalPriceStr, depositStr, 
			partsPriceStr, deadlineStr, powersOnBool.toString(), hasCoverBool.toString(), hasSimBool.toString(), hasMemoryBool.toString(), 
			hasBatteryBool.toString(), hasScrewsBool.toString(), delivered);
		databaseHelper.updateEntry(e);
		goBack();
	}







//back
	private void goBack()
	{
		Intent upIntent = NavUtils.getParentActivityIntent(getActivity());
		NavUtils.navigateUpTo(getActivity(), upIntent);		
	}








//toast
	private void toast(String p0)
	{
		Toast.makeText(getActivity(), p0, Toast.LENGTH_LONG).show();
	}







//delete entry
	private void deleteEntry(String idStr, String columnname)
	{
		databaseHelper.delete(idStr, columnname);

	}







//clear
	private void clear()
	{
		SimpleDateFormat dateF = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
		SimpleDateFormat timeF = new SimpleDateFormat("hh:mm a", Locale.getDefault());
		String date_ = dateF.format(Calendar.getInstance().getTime());
		String time_ = timeF.format(Calendar.getInstance().getTime());
		date.setText(date_);
		time.setText(time_);
		name.setText("");
		phone.setText("");
		email.setText("");
		imei.setText("");
		brand.setText("");
		model.setText("");
		color.setText("");
		powersOn.setOn(false);
		hasCover.setOn(false);
		hasSim.setOn(false);
		hasMemory.setOn(false);
		hasBattery.setOn(false);
		hasScrews.setOn(false);
		conditions.setText("");
		password.setText("");
		kindOfService.setText("");
		totalPrice.setText("");
		deposit.setText("");
		partsPrice.setText("");
		deadline.setText("");
		enableall();
	}
	
	
	
	
	
	
	
	
	
	
	
//save
	private void save()
	{
			dateStr = date.getText().toString();
			timeStr = time.getText().toString();
			nameStr = name.getText().toString();
			phoneStr = phone.getText().toString();
			emailStr = email.getText().toString();
			imeiStr = imei.getText().toString();
			brandStr = brand.getText().toString();
			modelStr = model.getText().toString();
			colorStr = color.getText().toString();
			conditionsStr = conditions.getText().toString();
			passwordStr = password.getText().toString();
			kindOfServiceStr = kindOfService.getText().toString();
			totalPriceStr = totalPrice.getText().toString();
			depositStr = deposit.getText().toString();
			partsPriceStr = partsPrice.getText().toString();
			deadlineStr = deadline.getText().toString();
			powersOnBool = powersOn.isOn();
			hasCoverBool = hasCover.isOn();
			hasSimBool = hasSim.isOn();
			hasMemoryBool = hasMemory.isOn();
			hasBatteryBool = hasBattery.isOn();
			hasScrewsBool = hasScrews.isOn();

			if (
				name.getText().toString().isEmpty())
			{toast("Escriba el nombre del Cliente");}
			else
			{
				if (edition == false)
				{
					toast("edition false");
					receieveOk = databaseHelper.insertEntry(dateStr, timeStr, nameStr, phoneStr, emailStr, imeiStr, brandStr, modelStr, colorStr,
															conditionsStr, passwordStr, kindOfServiceStr, totalPriceStr, depositStr, 
															partsPriceStr, deadlineStr, powersOnBool, hasCoverBool, hasSimBool, hasMemoryBool, 
															hasBatteryBool, hasScrewsBool, "Pendiente");
					goBack();
				}
				if (edition == true)
				{
					toast("edition true");
					updateRow(idStr, dateStr, timeStr, nameStr, phoneStr, emailStr, imeiStr, brandStr, modelStr, colorStr,
							  conditionsStr, passwordStr, kindOfServiceStr, totalPriceStr, depositStr, 
							  partsPriceStr, deadlineStr, powersOnBool, hasCoverBool, hasSimBool, hasMemoryBool, 
							  hasBatteryBool, hasScrewsBool, isDeliveredStr_);
				}

			}
	}








//bottom nav view
	private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
	= new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item)
		{
			switch (item.getItemId())
			{
                case R.id.save:
				{
					save();
				}
                    return true;





                case R.id.edit:
					if (name.getText().toString().isEmpty())
					{}
					else
					{
						enableall();
						isEdited(true);
					}
                    return true;





                case R.id.call:
					if ((phone.getText().toString()).matches(""))
					{
						toast("No hay número guardado");
					}
					else
					{Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone.getText().toString()));
						startActivity(intent);
					}

                    return true;







                case R.id.deleteOrder:
					{
						if (name.getText().toString().isEmpty())
						{toast("No existe orden que borrar");}
						else
						{

							final AlertDialog dialog2 = new AlertDialog.Builder(getActivity()).create();
							dialog2.setTitle("Eliminar Orden");
							dialog2.setMessage("¿Seguro que desea eliminar esta orden? Esto no se puede deshacer.");
							dialog2.setButton(AlertDialog.BUTTON_POSITIVE, "Si",
								new DialogInterface.OnClickListener(){

									@Override
									public void onClick(DialogInterface p1, int p2)
									{
										deleteEntry(idStr, nameStr);		
										goBack();

									}

								});
							dialog2.setButton(AlertDialog.BUTTON_NEGATIVE, "No", 
								new DialogInterface.OnClickListener(){

									@Override
									public void onClick(DialogInterface p1, int p2)
									{
										dialog2.dismiss();
									}
								});
							dialog2.show();
						}
					}
                    return true;





				case R.id.deliver:{
						{
							if (name.getText().toString().isEmpty())
							{toast("No existe orden que modificar");
							}
							else
							{
								dateStr = date.getText().toString();
								timeStr = time.getText().toString();
								nameStr = name.getText().toString();
								phoneStr = phone.getText().toString();
								emailStr = email.getText().toString();
								imeiStr = imei.getText().toString();
								brandStr = brand.getText().toString();
								modelStr = model.getText().toString();
								colorStr = color.getText().toString();
								conditionsStr = conditions.getText().toString();
								passwordStr = password.getText().toString();
								kindOfServiceStr = kindOfService.getText().toString();
								totalPriceStr = totalPrice.getText().toString();
								depositStr = deposit.getText().toString();
								partsPriceStr = partsPrice.getText().toString();
								deadlineStr = deadline.getText().toString();
								powersOnBool = powersOn.isOn();
								hasCoverBool = hasCover.isOn();
								hasSimBool = hasSim.isOn();
								hasMemoryBool = hasMemory.isOn();
								hasBatteryBool = hasBattery.isOn();
								hasScrewsBool = hasScrews.isOn();

								final AlertDialog dialog3 = new AlertDialog.Builder(getActivity()).create();
								dialog3.setMessage("¿Marcar como entregado?");
								dialog3.setButton(AlertDialog.BUTTON_POSITIVE, "Si",
									new DialogInterface.OnClickListener(){

										@Override
										public void onClick(DialogInterface p1, int p2)
										{
											updateRow(idStr, dateStr, timeStr, nameStr, phoneStr, emailStr, imeiStr, brandStr, modelStr, colorStr,
													  conditionsStr, passwordStr, kindOfServiceStr, totalPriceStr, depositStr, 
													  partsPriceStr, deadlineStr, powersOnBool, hasCoverBool, hasSimBool, hasMemoryBool, 
													  hasBatteryBool, hasScrewsBool, "Entregado");
										}

									});
								dialog3.setButton(AlertDialog.BUTTON_NEGATIVE, "No", 
									new DialogInterface.OnClickListener(){

										@Override
										public void onClick(DialogInterface p1, int p2)
										{
											dialog3.dismiss();
										}
									});
								dialog3.show();					
							}
						}
					}
					return true;





            }
            return false;
        }

    };

	







}
