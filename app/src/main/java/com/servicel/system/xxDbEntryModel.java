package com.servicel.system;

public class xxDbEntryModel
{

	private String id;
	private String columnDATE;
	private String columnTIME;
	private String columnNAME;
	private String columnIMEI;
	private String columnEMAIL;
	private String columnPHONE;
	private String columnBRAND;
	private String columnMODEL;
	private String columnCOLOR;
	private String columnCONDITIONS;
	private String columnPASSWORD;
	private String columnKINDOFSERVICE;
	private String columnTOTALPRICE;
	private String columnDEPOSIT;
	private String columnPARTSPRICE;
	private String columnDEADLINE;
	private String columnPOWERSON;
	private String columnHASCOVER;
	private String columnHASSIM;
	private String columnHASMEMORY;
	private String columnHASBATTERY;
	private String columnHASSCREWS;
	private String columnIsDelivered;

	//2----+--+--then go to recycler adapter
	public xxDbEntryModel()
	{}

	//a set all 
	public xxDbEntryModel(
		String columnID,
		String columnDATE, String columnTIME, String columnNAME,
		String columnPHONE, String columnEMAIL, String columnIMEI, String columnBRAND, String columnMODEL, String columnCOLOR,
		String columnCONDITIONS, String columnPASSWORD, String columnKINDOFSERVICE, String columnTOTALPRICE,
		String columnDEPOSIT, String columnPARTSPRICE, String columnDEADLINE, String columnPOWERSON, String columnHASCOVER,
		String columnHASSIM, String columnHASMEMORY, String columnHASBATTERY, String columnHASSCREWS,String columnIsDelivered2
	)
	{
		super();
		this.id = columnID;
		this.columnDATE = columnDATE;
		this.columnTIME = columnTIME;
		this.columnNAME = columnNAME;
		this.columnPHONE = columnPHONE;
		this.columnEMAIL = columnEMAIL;
		this.columnIMEI = columnIMEI;

		this.columnBRAND = columnBRAND;
		this.columnMODEL = columnMODEL;
		this.columnCOLOR = columnCOLOR;
		this.columnCONDITIONS = columnCONDITIONS;
		this.columnPASSWORD = columnPASSWORD;
		this.columnKINDOFSERVICE = columnKINDOFSERVICE;

		this.columnTOTALPRICE = columnTOTALPRICE;
		this.columnDEPOSIT = columnDEPOSIT;
		this.columnPARTSPRICE = columnPARTSPRICE;
		this.columnDEADLINE = columnDEADLINE;
		this.columnPOWERSON = columnPOWERSON;
		this.columnHASCOVER = columnHASCOVER;

		this.columnHASSIM = columnHASSIM;
		this.columnHASMEMORY = columnHASMEMORY;
		this.columnHASBATTERY = columnHASBATTERY;
		this.columnHASSCREWS = columnHASSCREWS;
		this.columnIsDelivered = columnIsDelivered2;
	}





	//get
	public String getID()
	{
		return id;
	};
	public String getColumnDATE()
	{
		return columnDATE;
	}
	public String getColumnTIME()
	{
		return columnTIME;
	}
	public String getColumnNAME()
	{
		return columnNAME;
	}
	public String getColumnIMEI()
	{
		return columnIMEI;
	}

	public String getColumnEMAIL()
	{
		return columnEMAIL;
	}

	public String getColumnPHONE()
	{
		return columnPHONE;
	}

	public String getColumnHASSCREWS()
	{
		return columnHASSCREWS;
	}

	public String getColumnHASBATTERY()
	{
		return columnHASBATTERY;
	}

	public String getColumnHASMEMORY()
	{
		return columnHASMEMORY;
	}

	public String getColumnHASSIM()
	{
		return columnHASSIM;
	}

	public String getColumnHASCOVER()
	{
		return columnHASCOVER;
	}

	public String getColumnPOWERSON()
	{
		return columnPOWERSON;
	}

	public String getColumnDEADLINE()
	{
		return columnDEADLINE;
	}

	public String getColumnPARTSPRICE()
	{
		return columnPARTSPRICE;
	}

	public String getColumnDEPOSIT()
	{
		return columnDEPOSIT;
	}

	public String getColumnTOTALPRICE()
	{
		return columnTOTALPRICE;
	}

	public String getColumnKINDOFSERVICE()
	{
		return columnKINDOFSERVICE;
	}

	public String getColumnPASSWORD()
	{
		return columnPASSWORD;
	}

	public String getColumnCONDITIONS()
	{
		return columnCONDITIONS;
	}

	public String getColumnCOLOR()
	{
		return columnCOLOR;
	}

	public String getColumnMODEL()
	{
		return columnMODEL;
	}

	public String getColumnBRAND()
	{
		return columnBRAND;
	}
	
	public String getColumnIsDelivered(){
		return columnIsDelivered;
	}


	//set

	public void setId(String columnID)
	{
		this.id = columnID;
	}
	public void setColumnDATE(String columnDATE)
	{
		this.columnDATE = columnDATE;
	}
	public void setColumnTIME(String columnTIME)
	{
		this.columnTIME = columnTIME;
	}
	public void setColumnNAME(String columnNAME)
	{
		this.columnNAME = columnNAME;
	}
	public void setColumnPHONE(String columnPHONE)
	{
		this.columnPHONE = columnPHONE;
	}

	public void setColumnEMAIL(String columnEMAIL)
	{
		this.columnEMAIL = columnEMAIL;
	}

	public void setColumnIMEI(String columnIMEI)
	{
		this.columnIMEI = columnIMEI;
	}
	public void setColumnHASSCREWS(String hasscrews)
	{
		this.columnHASSCREWS = hasscrews;
	}

	public void setColumnHASBATTERY(String hasbattery)
	{
		this.columnHASBATTERY = hasbattery;
	}

	public void setColumnHASMEMORY(String hasmemory)
	{
		this.columnHASMEMORY = hasmemory;
	}

	public void setColumnHASSIM(String hassim)
	{
		this.columnHASSIM = hassim;
	}

	public void setColumnHASCOVER(String hascover)
	{
		this.columnHASCOVER = hascover;
	}

	public void setColumnPOWERSON(String powerson)
	{
		this.columnPOWERSON = powerson;
	}

	public void setColumnDEADLINE(String deadline)
	{
		this.columnDEADLINE = deadline;
	}

	public void setColumnPARTSPRICE(String partsprice)
	{
		this.columnPARTSPRICE = partsprice;
	}

	public void setColumnDEPOSIT(String deposit)
	{
		this.columnDEPOSIT = deposit;
	}

	public void setColumnTOTALPRICE(String totalprice)
	{
		this.columnTOTALPRICE = totalprice;
	}

	public void setColumnKINDOFSERVICE(String kindofservice)
	{
		this.columnKINDOFSERVICE = kindofservice;
	}

	public void setColumnPASSWORD(String password)
	{
		this.columnPASSWORD = password;
	}

	public void setColumnCONDITIONS(String conditions)
	{
		this.columnCONDITIONS = conditions;
	}

	public void setColumnCOLOR(String color)
	{
		this.columnCOLOR = color;
	}

	public void setColumnMODEL(String model)
	{
		this.columnMODEL = model;
	}

	public void setColumnBRAND(String brand)
	{
		this.columnBRAND = brand;
	}
	public void setColumnIsDelivered(String isDelivered){
		this.columnIsDelivered = isDelivered;
	}




	@Override
	public String toString()
	{
		return "EntryModel [id=" + id + ", columnDATE=" + columnDATE + ", column2=" + columnTIME
			+ "]";
	}



}
