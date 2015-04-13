import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;


@WebServlet("/SaveDriInfo")
public class SaveDriInfo extends HttpServlet{
        public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
                request.setCharacterEncoding("utf-8");
                response.setContentType("text/html;charset=utf-8");

                String driver_id = request.getParameter("DRIVER_ID");
                String driver_pw = request.getParameter("DRIVER_PW");
		String driver_name = request.getParameter("DRIVER_NAME");
		String driver_phone = request.getParameter("DRIVER_PHONE");
		String driver_company = request.getParameter("DRIVER_COMPANY");

		PrintWriter out = response.getWriter();

                Statement statement= null;
                Connection con = null;
                ResultSet result = null;
                String db_url = "jdbc:mysql://localhost/TecTalk";

                String sql = "insert into Driver (dri_id, dri_pw, dri_name, dri_phone, dri_company) values (?,?,?,?,?)";

                try{
                        Class.forName("com.mysql.jdbc.Driver");
                        con = DriverManager.getConnection(db_url,"root","tlszps13");

			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1,driver_id);
			pstmt.setString(2,driver_pw);
			pstmt.setString(3,driver_name);
			pstmt.setString(4,driver_phone);
			pstmt.setString(5,driver_company);

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


