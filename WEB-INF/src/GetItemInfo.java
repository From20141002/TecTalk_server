import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import javax.servlet.annotation.WebServlet;
import org.json.simple.*;
import java.sql.*;


@WebServlet("/GetItemInfo")
public class GetItemInfo extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		

		String driId = request.getParameter("DRIID");
		String cusId = request.getParameter("CUSID");
		PrintWriter out = response.getWriter();
		Statement statement= null;
		Connection con = null; 	
		ResultSet result = null;
		String dbUrl = "jdbc:mysql://localhost/TecTalk";
		
		String sql = "select * from ItemInfo where ";
		if(driId!=null){
			sql += "dri_id='"+driId;
		}else if (cusId!=null){
			sql += "cus_id='"+cusId;
		}
		sql += "';";
		JSONArray jArray  = new JSONArray();
		JSONObject jObject = null;

		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(dbUrl,"root","tlszps13");
			statement = con.createStatement();
			result = statement.executeQuery(sql);
		
			while(result.next()){
			
				jObject = new JSONObject();
				jObject.put("CUSID",result.getString("cus_id"));
				jObject.put("DRIID",result.getString("dri_id"));
				jObject.put("ITEMINFO",result.getString("item_info"));
				jObject.put("ADDRESS",result.getString("item_address"));
				jObject.put("GETBYHAND",result.getString("item_getbyhand"));
				jArray.add(jObject);

			}
			statement.close();
			con.close();
			out.println(jArray.toString());

		}catch(SQLException e){
			out.println("sql error : "+e);
		}catch(Exception e){
			out.println("error : "+e);
		}
	}
}




