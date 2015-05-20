import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import javax.servlet.annotation.WebServlet;
import org.json.simple.*;
import java.sql.*;


@WebServlet("/GetPhoneCus")
public class GetPhoneCus extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String msg = request.getParameter("msg");
		String cusId = request.getParameter("cusId");
		String driId = request.getParameter("driId");
		String itemInfo = request.getParameter("itemInfo");
		String phoneCus = "";
		PrintWriter out = response.getWriter();
		String phoneDri="";
		Statement statement= null;
		Connection con = null; 	
		ResultSet result = null;
		String db_url = "jdbc:mysql://localhost/TecTalk";
		
		String sql = "select phone_id from GCMCUS where cus_id = '"+cusId+"';";
		String sql2 = "select phone_id from GCMDRI where dri_id = '"+driId+"';";
		JSONArray jArray  = new JSONArray();
		JSONObject jObject = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(db_url,"root","tlszps13");
			statement = con.createStatement();
			result = statement.executeQuery(sql);
			if(result!=null){
		        	if(result.next()){
					phoneCus = result.getString("phone_id");
					result = statement.executeQuery(sql2);
					if(result!=null){
						if(result.next()){
							phoneDri = result.getString("phone_id");
					
							statement.close();
							con.close();
		  					response.sendRedirect("/TecTalk/PushToCus?msg="+msg+"&phoneCus="+phoneCus+"&phoneDri="+phoneDri+"&itemInfo="+itemInfo);
						}
					}
				}
			}else{
			}
			statement.close();
			con.close();

		}catch(SQLException e){
			out.println("sql error : "+e);
		}catch(Exception e){
			out.println("error : "+e);
		}
                
	}
}



