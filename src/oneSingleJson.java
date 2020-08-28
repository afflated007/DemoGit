import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.commons.text.StringEscapeUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class oneSingleJson {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, JsonGenerationException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
Class.forName("com.mysql.cj.jdbc.Driver");
Connection conn=null;
JSONArray js=new JSONArray();

ArrayList<CustomerDetails> a=new ArrayList<CustomerDetails>();

conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/Business", "root", "root");


//object of statement class will help us to execute queries
Statement st=conn.createStatement();
ResultSet rs= st.executeQuery("select * from CustomerInfo where Location ='Asia';");
while(rs.next()) //setting the pointer to the particular row
{
	CustomerDetails c=new CustomerDetails();
	//3 different json files, 3 diff java objects
	c.setCourseName(rs.getString(1));
	c.setPurchasedDate(rs.getString(2));
	c.setAmount(rs.getInt(3));
	c.setLocation(rs.getString(4));
	a.add(c);
	
	
}
for(int i=0;i<a.size();i++)
{
	ObjectMapper o=new ObjectMapper();
	o.writeValue(new File("C:\\Home\\Learn\\Testing\\SDET\\project\\JsonJava\\customerInfo"+i+".json"),a.get(i));
	Gson g=new Gson();
	String jsonString=g.toJson(a.get(i));
	js.add(jsonString);

}

//Create json string from Java object

//Json simple
JSONObject jo=new JSONObject();
jo.put("data",js);
System.out.println(jo.toJSONString());
String unescapeString=StringEscapeUtils.unescapeJava(jo.toJSONString());
System.out.println(unescapeString);
String string1=unescapeString.replace("\"{", "{");
String finalstring=string1.replace("}\"", "}");
System.out.println(finalstring);
System.out.println("Jai Shiva");
System.out.println("ram ram");
try (FileWriter file = new FileWriter("C:\\Home\\Learn\\Testing\\SDET\\project\\JsonJava\\SingleJson.json")) {
	file.write(finalstring);
	
}

conn.close();
}
}