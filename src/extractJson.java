import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class extractJson {
	
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
		
//json will be the response of the API

	ObjectMapper o=new ObjectMapper();
	CustomerDetailsAppium c=o.readValue(new File("C:\\Home\\Learn\\Testing\\SDET\\project\\JsonJava\\customerInfo0.json"), CustomerDetailsAppium.class);
	
	System.out.println(c.getCourseName());
	System.out.println("Jai shri Krishna");
	c.getStudentName();
	//validate records in webtable are correct or not
	//getText() Appium
	
	
}

}