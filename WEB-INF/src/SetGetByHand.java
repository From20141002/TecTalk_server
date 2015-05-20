import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import javax.servlet.annotation.WebServlet;
import org.json.simple.*;
import java.sql.*;
import java.net.URLEncoder;
import java.net.URLDecoder;

@WebServlet("/SetGetByHand")
public class SetGetByHand extends HttpServlet{
        public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
                request.setCharacterEncoding("utf-8");
                response.setContentType("text/html;charset=utf-8");

		String getbyhand = request.getParameter("getbyhand");
		String phoneDri = request.getParameter("phoneDri");
		String cusId = request.getParameter("cusId");
		String itemInfo = URLDecoder.decode(request.getParameter("itemInfo"),"UTF-8"); 
		String driId="";
                PrintWriter out = response.getWriter();
                Statement statement= null;
                Connection con = null;
		
		Statement statement2 = null;
		Connection con2 = null;
              
		ResultSet result_driId = null;
		ResultSet result_getbyhand = null;


                String db_url = "jdbc:mysql://localhost/TecTalk";
		
		
		String sql_driId = "select dri_id from GCMDRI where phone_id='";
		sql_driId += phoneDri+"';";
		
               
		try{
                        Class.forName("com.mysql.jdbc.Driver");
                        con = DriverManager.getConnection(db_url,"root","tlszps13");
                        statement = con.createStatement();

                        result_driId = statement.executeQuery(sql_driId);

                        if(result_driId!=null){
                                if(result_driId.next()){
					driId = result_driId.getString("dri_id");
                                }
			}
                        statement.close();
                        con.close();

                }catch(SQLException e){
                        out.println("sql error : "+e);
                }catch(Exception e){
                        out.println("error : "+e);
                }
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
                        con2 = DriverManager.getConnection(db_url,"root","tlszps13");
                        statement2 = con2.createStatement();
                	String sql_getbyhand = "update ItemInfo set item_getbyhand='"+getbyhand+"' where cus_id = '"+cusId+"'and dri_id = '"+driId+"'and item_info = '"+itemInfo+"';";
			out.println(sql_getbyhand);
			statement2.executeUpdate(sql_getbyhand);
			con2.close();
			statement2.close();
			String en_iteminfo = URLEncoder.encode(itemInfo);
			response.sendRedirect("/TecTalk/PushToDri?getbyhand="+getbyhand+"&cusId="+cusId+"&phoneDri="+phoneDri+"&iteminfo="+en_iteminfo+"");        
			
		}catch(Exception e){
			out.println("error2: " + e);
		}
	}
}


