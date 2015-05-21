import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;


@WebServlet("/SaveCusGCM")
public class SaveCusGCM extends HttpServlet{
        public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
                request.setCharacterEncoding("utf-8");
                response.setContentType("text/html;charset=utf-8");

                String cusId = request.getParameter("CUSID");
                String phoneId = request.getParameter("PHONEID");


		PrintWriter out = response.getWriter();

                Statement statement= null;
                Connection con = null;
                ResultSet result = null;
                String dbUrl = "jdbc:mysql://localhost/TecTalk";

                String sql = "insert into  GCMCUS(cus_id, phone_id) values (?,?)";

                try{
                        Class.forName("com.mysql.jdbc.Driver");
                        con = DriverManager.getConnection(dbUrl,"root","tlszps13");

			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1,cusId);
			pstmt.setString(2,phoneId);

			pstmt.executeUpdate();
	
			pstmt.close();
                        con.close();
		
			out.println("success");
                }catch(SQLException e){
                        out.println("sql error : "+e);
                }catch(Exception e){
                        out.println("error : "+e);
                }
        }
}
