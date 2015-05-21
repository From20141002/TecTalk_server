import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;


@WebServlet("/LoginDri")
public class LoginDri extends HttpServlet{
        public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
                
		request.setCharacterEncoding("utf-8");
                response.setContentType("text/html;charset=utf-8");
                
		String driId = request.getParameter("DRIID");
                String driPasswd = request.getParameter("DRIPW");

		PrintWriter out = response.getWriter();
                Statement statement= null;
                Connection con = null;
                ResultSet result = null;
                String dbUrl = "jdbc:mysql://localhost/TecTalk";

                String sql = "select dri_pw from Driver where dri_id='"+driId+"';";

                try{
                        Class.forName("com.mysql.jdbc.Driver");
                        con = DriverManager.getConnection(dbUrl,"root","tlszps13");
                        statement = con.createStatement();
                        result = statement.executeQuery(sql);

			if(result.next()){
				String resultPasswd = result.getString("dri_pw");
				if(resultPasswd.equals(driPasswd)){
					out.println("success");
				}else{
					out.println("pw_error");
				}
			}else{
				out.println("id_error");
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

