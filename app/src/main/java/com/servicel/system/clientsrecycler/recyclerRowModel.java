package com.servicel.system.clientsrecycler;

public class recyclerRowModel
{

    public recyclerRowModel(String name,String status, String ColumnName)
    {
    this.name = name;
	this.ColumName = ColumnName;
	this.status = status;
    }

    public void setName(String name)
    {

	this.name = name;
    }
	public void setstatus(String stat)
    {

		this.status = stat;
    }
    public void setColumnName(String columnName)
    {

	this.ColumName = columnName;
    }

    private String name;
    private String ColumName;
    private String status;;

    public String getName()
    {
        return name;
    }
    public String getColumnName()
    {
        return ColumName;
    }
	public String getstatus()
    {
        return status;
    }


}
