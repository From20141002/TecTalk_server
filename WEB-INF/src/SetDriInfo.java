import javax.servlet.*;
import java.io.*;
import javax.servlet.annotation.WebServlet;
import org.json.simple.*;
import java.sql.*;
import javax.servlet.http.*;


@WebServlet("/SetDriInfo")
public class SetDriInfo extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String driId = request.getParameter("DRIVER_ID");
		PrintWriter out = response.getWriter();
		Statement statement= null;
		Connection con = null; 	
		ResultSet result = null;
		String db_url = "jdbc:mysql://localhost/TecTalk";
		
		String sql = "select * from Driver where dri_id = '"+driId+"';";
		JSONObject jObject = null;

		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(db_url,"root","tlszps13");
			statement = con.createStatement();
			result = statement.executeQuery(sql);
		
			while(result.next()){
			
				jObject = new JSONObject();
				jObject.put("dri_id",result.getString("dri_id"));
				jObject.put("dri_name",result.getString("dri_name"));
				jObject.put("dri_phone",result.getString("dri_phone"));
				jObject.put("dri_company",result.getString("dri_company"));;

			}
			statement.close();
			con.close();
			out.println(jObject.toString());

		}catch(SQLException e){
			out.println("sql error : "+e);
		}catch(Exception e){
			out.println("error : "+e);
		}
	}
}






