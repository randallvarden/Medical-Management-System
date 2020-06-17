package naartjie;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JTextArea;
import java.io.IOException;

public class Naartjie_log 
{
	private boolean append_to_file = true;
	private String filePath = "medicalLog.txt";
	Naartjie_admin r = new Naartjie_admin();
	
	public Naartjie_log(boolean append_value)
	{

		append_to_file = append_value;
	}
	
	public void writeToFile(String name, String action, String username, String time ) throws IOException
	{
		FileWriter write = new FileWriter(filePath, append_to_file);
		PrintWriter printLine = new PrintWriter(write);
		printLine.println(name +" " + action +" "+ username + " at " + time);
		printLine.close();

	}
	
	public String dateConverter()
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		Date date = Calendar.getInstance().getTime(); 
		return (dateFormat.format(date));
	}
	
	public void readfile(JTextArea textArea2)
	{
		String filePath = "C:\\Users\\User\\eclipse-workspace\\Blue Naartjie-20181017T102451Z-001\\medicalLog.txt";
		 File file1 = new File(filePath);
		 try 
			{
				FileReader reader = new FileReader(file1);
				BufferedReader br = new BufferedReader(reader);
				textArea2.setText("");
				
				Object[] lines = br.lines().toArray();
				for(int i = 0; i < lines.length; i++)
				{
					String line = lines[i].toString().trim();
					//String[] row = lines[i].toString().split(" ");
					textArea2.append(line);
					textArea2.append("\n");
				}
				br.close();
			
			} 
			
			catch (FileNotFoundException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
