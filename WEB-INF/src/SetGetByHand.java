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

		String getByHand = request.getParameter("GETBYHAND");
		String phoneDri = request.getParameter("PHONEDRI");
		String cusId = request.getParameter("CUSID");
		String itemInfo = URLDecoder.decode(request.getParameter("ITEMINFO"),"UTF-8"); 
		String driId="";
                PrintWriter out = response.getWriter();
                Statement statementDriId= null;
                Connection conDriId = null;
		
		Statement statementGetByHand = null;
		Connection conGetByHand = null;
              
		ResultSet resultDriId = null;
		ResultSet resultGetByHand = null;


                String dbUrl = "jdbc:mysql://localhost/TecTalk";

                String sqlGetByHand = "update ItemInfo set item_getbyhand='"+getByHand+"' where cus_id = '"+cusId+"'and dri_id = '"+driId+"'and item_info = '"+itemInfo+"';";	
		String sqlDriId = "select dri_id from GCMDRI where phone_id='";
		sqlDriId += phoneDri+"';";
               
		try{
                        Class.forName("com.mysql.jdbc.Driver");
                        conDriId = DriverManager.getConnection(dbUrl,"root","tlszps13");
                        statementDriId = conDriId.createStatement();

                        resultDriId = statementDriId.executeQuery(sqlDriId);

                        if(resultDriId!=null){
                                if(resultDriId.next()){
					driId = resultDriId.getString("dri_id");
                                }
			}
                        statementDriId.close();
                        conDriId.close();

                }catch(SQLException e){
                        out.println("sql error : "+e);
                }catch(Exception e){
                        out.println("error : "+e);
                }
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
                        conGetByHand = DriverManager.getConnection(dbUrl,"root","tlszps13");
                        statementGetByHand = conGetByHand.createStatement();
			out.println(sqlGetByHand);
			statementGetByHand.executeUpdate(sqlGetByHand);
			conGetByHand.close();
			statementGetByHand.close();
			String enIteminfo = URLEncoder.encode(itemInfo);
			response.sendRedirect("/TecTalk/PushToDri?GETBYHAND="+getByHand+"&CUSID="+cusId+"&PHONEDRI="+phoneDri+"&ITEMINFO="+enIteminfo+"");        
			
		}catch(Exception e){
			out.println("error2: " + e);
		}
	}
}


