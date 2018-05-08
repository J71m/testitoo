package infosys;
import java.sql.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class InfosysData{

    static private String dbUser = "?user=user";
    static private String dbPW = "&password=password";
    static private String dbURN = "//localhost/database";

    static public String readContactsFromDB()throws Exception{
        int count = 0;
        String allContacts;
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();
        
        Connection cn=DriverManager.getConnection("jdbc:mysql:"+dbURN+dbUser+dbPW);
        Statement st=cn.createStatement();
        // Getting the total count of contacts, currently unused
        ResultSet countQuery=st.executeQuery("SELECT COUNT(id) AS cCount FROM smit_contacts"); 
        while (countQuery.next()){count = countQuery.getInt("cCount");}
        ResultSet rs=st.executeQuery("SELECT id, fname, lname, code, phone FROM smit_contacts ORDER BY id");
        while (rs.next()) { // Creating JSON object for each contact and storing it in JSON array
            JSONObject item = new JSONObject();
            item.put("id", rs.getInt("id"));
            item.put("fname", rs.getString("fname"));
            item.put("lname", rs.getString("lname"));
            item.put("code", rs.getString("code"));
            item.put("phone", rs.getString("phone"));
            array.add(item);
        }
        cn.close();
        json.put("query", array);
        json.put("count", count);
        allContacts = json.toString();
        return allContacts;
    }

    static public String addContact(String newContact)throws Exception{
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(newContact);

        Connection cn=DriverManager.getConnection("jdbc:mysql:"+dbURN+dbUser+dbPW);
        PreparedStatement st=cn.prepareStatement("INSERT INTO smit_contacts VALUES (DEFAULT, ?, ?, ?, ?)");
        st.setObject(1, json.get("fname"));
        st.setObject(2, json.get("lname"));
        st.setObject(3, json.get("code"));
        st.setObject(4, json.get("phone"));
        st.executeUpdate();
        cn.close();
        return "Contact added";
    }
}