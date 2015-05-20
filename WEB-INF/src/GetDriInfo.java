import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import javax.servlet.annotation.WebServlet;
import org.json.simple.*;
import java.sql.*;


@WebServlet("/GetDriInfo")
public class GetDriInfo extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String driver_id = request.getParameter("DRIVER_ID");
		String customer_id = request.getParameter("CUSTOMER_ID");
                String item_info = request.getParameter("ITEM_INFO");
		PrintWriter out = response.getWriter();
		Statement statement= null;
		Connection con = null; 	
		ResultSet result = null;
		String db_url = "jdbc:mysql://localhost/TecTalk";
		
		String sql = "select * from ItemInfo where ";
		if(driver_id!=null){
			sql += "dri_id='"+driver_id;
		}else if (customer_id!=null){
			sql += "cus_id='"+customer_id; 
                        sql += "' and item_info='" + item_info;
		}
		sql += "';";
		JSONArray jArray  = new JSONArray();
		JSONObject jObject = null;

		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(db_url,"root","tlszps13");
			statement = con.createStatement();
			result = statement.executeQuery(sql);
		
			while(result.next()){
			
				jObject = new JSONObject();
				jObject.put("cus_id",result.getString("cus_id"));
				jObject.put("dri_id",result.getString("dri_id"));
				jObject.put("item_info",result.getString("item_info"));
				jObject.put("item_address",result.getString("item_address"));
				jObject.put("item_getbyhand",result.getString("item_getbyhand"));
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






