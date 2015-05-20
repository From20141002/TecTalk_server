import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;
import java.net.URLEncoder;
import java.net.URLDecoder;
import org.json.simple.*;

@WebServlet("/DeletePhoneId")
public class DeletePhoneId extends HttpServlet{
        public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
                request.setCharacterEncoding("utf-8");
                response.setContentType("text/html;charset=utf-8");

                String cusId = request.getParameter("CUS_ID");
		String driId = request.getParameter("DRI_ID");


		PrintWriter out = response.getWriter();

                Statement statement= null;
                Connection con = null;
                ResultSet result = null;
                String db_url = "jdbc:mysql://localhost/TecTalk";
		String sql = "";

		if(cusId != null){
                sql = "delete from GCMCUS where cus_id = '";
		sql += cusId+"';" ;}

		if(driId != null){
                sql = "delete from GCMDRI where dri_id = '";
		sql += driId+"';" ;}

                try{
                        Class.forName("com.mysql.jdbc.Driver");
                        con = DriverManager.getConnection(db_url,"root","tlszps13");

			statement = con.createStatement();
			statement.executeUpdate(sql);
			statement.close();
                        con.close();
		
			out.println("success");
                }catch(SQLException e){
                        out.println("sql error : "+e);
                }catch(Exception e){
                        out.println("error : "+e);
                }
        }
}
