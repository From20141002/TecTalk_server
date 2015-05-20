import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;


@WebServlet("/LoginCus")
public class LoginCus extends HttpServlet{
        public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
                
		request.setCharacterEncoding("utf-8");
                response.setContentType("text/html;charset=utf-8");
                
		String cus_id = request.getParameter("CUS_ID");
                String cus_passwd = request.getParameter("CUS_PW");

		PrintWriter out = response.getWriter();
                Statement statement= null;
                Connection con = null;
                ResultSet result = null;
                String db_url = "jdbc:mysql://localhost/TecTalk";

                String sql = "select cus_pw from Customer where cus_id='"+cus_id+"';";

                try{
                        Class.forName("com.mysql.jdbc.Driver");
                        con = DriverManager.getConnection(db_url,"root","tlszps13");
                        statement = con.createStatement();
                        result = statement.executeQuery(sql);

			if(result.next()){
				String result_pw = result.getString("cus_pw");
				if(result_pw.equals(cus_passwd)){
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

