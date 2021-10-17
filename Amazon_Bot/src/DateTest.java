
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTest {
	
	public DateTest() {
		 try {
				SimpleDateFormat dateformatDay = new SimpleDateFormat("EEEEE");
				
				SimpleDateFormat dateformat3 = new SimpleDateFormat("yyyy-MM-dd");
				
				Date date1 = dateformat3.parse("2021-10-15");

				Date date = new Date();
		        String todate = dateformat3.format(date);

		        Calendar cal = Calendar.getInstance();
		        cal.add(Calendar.DATE, -7);
		        Date todate1 = cal.getTime();    
		        String fromdate = dateformat3.format(todate1);
				
				System.out.println("From:"+fromdate);
				System.out.println("to:"+todate);
				System.out.println("Today is:"+dateformatDay.format(new Date()));
				
				
				File folder = new File("C:"+File.separator+"xampp"+File.separator+"mysql"+File.separator+"data"+File.separator+"amazon");
		        File fList[] = folder.listFiles();

		        for (File f : fList) {
		            if (f.getName().endsWith(".csv")) {
		                f.delete(); 
		            }}
				
		 }catch(ParseException e) {e.printStackTrace();}
	}
	public static void main(String args[]) {new DateTest();}
}
