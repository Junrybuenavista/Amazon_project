import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JTextArea;

import org.openqa.selenium.WebDriver;

public class FindFileByExtension extends Thread{
	WebDriver driver;
	Statement stmt;
	JTextArea area;
    public static void main(String[] args) {

    }
    public FindFileByExtension(WebDriver driver,JTextArea area) {
    	this.driver=driver;
    	this.area=area;
    	setDataBaseConnection();
    }
   
    
    public void run() {
    	while(true) 
    	{	
    		   
		    	try {
		    		
		    		Thread.sleep(1000);
		            List<String> files = findFiles(Paths.get("D:"+File.separator+"xammmp7.4"+File.separator+"mysql"+File.separator+"data"+File.separator+"amazon"), "csv");            
		            
		            File f=new File(files.get(0));
		            //File fileName = new File("D:"+File.separator+"xammmp7.4"+File.separator+"mysql"+File.separator+"data"+File.separator+"amazon"+File.separator+"Amazon_data");    
					 f.renameTo(new File("D:"+File.separator+"xammmp7.4"+File.separator+"mysql"+File.separator+"data"+File.separator+"amazon"+File.separator+"Amazon_data.csv"));			            
					 System.out.println("File renamed");
					 
					 stmt.execute("LOAD DATA INFILE 'Amazon_data.csv' \r\n"
					 		+ "INTO TABLE amazon_data2 \r\n"
					 		+ "FIELDS TERMINATED BY ',' \r\n"
					 		+ "ENCLOSED BY '\"'\r\n"
					 		+ "LINES TERMINATED BY '\\n'\r\n"
					 		+ "IGNORE 1 ROWS;");
					 
					 driver.get("http://localhost/amazonCSV/query.php");
					 area.append("Process complete");
					 break;
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    	catch (IndexOutOfBoundsException ee) {
		           System.out.println("Waiting for file");
		        }
		    	catch (InterruptedException eee) {
		            eee.printStackTrace();
		        }
		    	catch (SQLException eeee) {
		            eeee.printStackTrace();
		        }
    	}
    }
    
    public void setDataBaseConnection() {
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/amazon","root","");  	
			stmt=con.createStatement();
			
		   }catch(Exception e){ e.printStackTrace();}  
	}
    
    public static List<String> findFiles(Path path, String fileExtension)
        throws IOException {

        if (!Files.isDirectory(path)) {
            throw new IllegalArgumentException("Path must be a directory!");
        }

        List<String> result;

        try (Stream<Path> walk = Files.walk(path)) {
            result = walk
                    .filter(p -> !Files.isDirectory(p))
                    // this is a path, not string,
                    // this only test if path end with a certain path
                    //.filter(p -> p.endsWith(fileExtension))
                    // convert path to string first
                    .map(p -> p.toString().toLowerCase())
                    .filter(f -> f.endsWith(fileExtension))
                    .collect(Collectors.toList());
        }

        return result;
    }

}