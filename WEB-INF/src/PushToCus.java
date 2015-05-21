import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import javax.servlet.annotation.WebServlet;
import org.json.simple.*;
import java.sql.*;
import java.util.ArrayList;
import com.google.android.gcm.server.*;

@WebServlet("/PushToCus")
public class PushToCus extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		String time = request.getParameter("TIME");
		String phoneDri = request.getParameter("PHONEDRI");
		String itemInfo = request.getParameter("ITEMINFO");
		String phoneCus = request.getParameter("PHONECUS");


		JSONObject msg = new JSONObject();

		msg.put("TIME",time);
		msg.put("PHONEDRI",phoneDri);
		msg.put("ITEMINFO",itemInfo);


		String apiKey = "AIzaSyD6DTOM1ZnIw7gbmeS60GwoROz8n0vURXQ";
                String gcmURL = "https://android.googleapis.com/gcm/send";
                String messageId = String.valueOf(Math.random() % 100 + 1);
                Result result = null; 
                boolean showOn = true;

                int LIVETIME = 1;
                int RETRY= 2;
                
                try{
                      
                      Sender sender = new Sender(apiKey);
                      Message message = new Message.Builder().collapseKey(messageId).delayWhileIdle(showOn).timeToLive(LIVETIME).addData("test",msg.toString()).build();
                      result = sender.send(message,phoneCus,RETRY);
                }catch(Exception e)
		{ 
		      out.println(e.toString());         
                }
		
		if(result.getMessageId()!=null){
			out.println("success");
		}else{
			out.println("fail!");
		}
	}
}


