package naartjie;

public class Naartjie_drug 
{
	private String drugId;
	private String drugName;
	private String drugDescription;
	private int numberOfDrugs;

	protected Naartjie_drug(String drugId, String drugName, String drugDescription) 
	{
		super();
		this.drugId = drugId;
		this.drugName = drugName;
		this.drugDescription = drugDescription;
	}
	protected String getDrugId() 
	{
		return drugId;
	}
	protected void setDrugId(String drugId) 
	{
		this.drugId = drugId;
	}
	protected String getDrugName() 
	{
		return drugName;
	}
	protected void setDrugName(String drugName) 
	{
		this.drugName = drugName;
	}
	protected String getDrugDescription() 
	{
		return drugDescription;
	}
	protected void setDrugDescription(String drugDescription) 
	{
		this.drugDescription = drugDescription;
	}
	
	protected int getNumberOfDrugs() 
	{
		return numberOfDrugs;
	}
	protected void setNumberOfDrugs(int numberOfDrugs) 
	{
		this.numberOfDrugs = numberOfDrugs;
	}
	

}
