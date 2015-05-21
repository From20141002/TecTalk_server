import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import javax.servlet.annotation.WebServlet;
import org.json.simple.*;
import java.sql.*;
import java.net.URLEncoder;
import java.net.URLDecoder;

@WebServlet("/GetPhoneId")
public class GetPhoneId extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String time = request.getParameter("TIME");
		String cusId = request.getParameter("CUSID");
		String driId = request.getParameter("DRIID");
		String itemInfo = URLDecoder.decode(request.getParameter("ITEMINFO"),"UTF-8");	
		String phoneCus = "";
		String phoneDri = "";
		

		PrintWriter out = response.getWriter();
		Statement statement= null;
		Connection con = null; 	
		ResultSet result = null;
		

		String db_url = "jdbc:mysql://localhost/TecTalk";

		String sql_phoneCus = "select phone_id from GCMCUS where cus_id = '"+cusId+"';";
		String sql_phoneDri = "select phone_id from GCMDRI where dri_id = '"+driId+"';";
		JSONArray jArray  = new JSONArray();
		JSONObject jObject = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(db_url,"root","tlszps13");
			statement = con.createStatement();
			result = statement.executeQuery(sql_phoneCus);
			if(result!=null){
		        	if(result.next()){
					phoneCus = result.getString("phone_id");
					result = statement.executeQuery(sql_phoneDri);
					if(result!=null){
						if(result.next()){
							phoneDri = result.getString("phone_id");
							out.println(itemInfo);
							statement.close();
							con.close();
							String en_itemInfo = URLEncoder.encode(itemInfo);
		  					response.sendRedirect("/TecTalk/PushToCus?TIME="+time+"&PHONECUS="+phoneCus+"&PHONEDRI="+phoneDri+"&ITEMINFO="+en_itemInfo+"");
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



