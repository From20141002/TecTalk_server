import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/MainPost")
public class MainPost extends HttpServlet{
 public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
   request.setCharacterEncoding("utf-8");
   response.setContentType("text/html;charset=utf-8");
   String driver_id = request.getParameter("DRIVER_ID");
   String customer_id = request.getParameter("CUSTOMER_ID");
   String item_name = request.getParameter("ITEM_NAME");
   String address = request.getParameter("ADDRESS");
   PrintWriter out = response.getWriter();
   out.println("<HTML>");
   out.println("<TITLE>Main Informain - 결과 화면</TITLE></HEAD>");
   out.println("<BODY>");
   out.println("택배기사 고유번호 :  " + driver_id + "<BR>");
   out.println("고객 고유번호 : " +  customer_id + "<BR>");
   out.println("상품이름 : " +  item_name + "<BR>");
   out.println("배송지 : " +  address+ "<BR>");
   out.println("저장되었습니다.");
   out.println("</BODY>");
   out.println("</HTML>");
  }
 }

       
