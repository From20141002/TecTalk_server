<%@page contentType="text/html; charset=utf-8"%>
<%@page import="java.io.*"
	import="java.util.Date"
        import="java.sql.*"
	import="java.net.URLEncoder"%>
<%

 request.setCharacterEncoding("utf-8");
 String driver_name = request.getParameter("DRIVER.NAME");
 String client_name = request.getParameter("CLIENT.NAME");
 String item_name = request.getParameter("ITEM.NAME");
 String address = request.getParameter("ADDRESS");

 
String enco_driver = URLEncoder.encode(request.getParameter("DRIVER.NAME"));
String enco_client = URLEncoder.encode(request.getParameter("CLIENT.NAME"));
String enco_item = URLEncoder.encode(request.getParameter("ITEM.NAME"));
String enco_address = URLEncoder.encode(request.getParameter("ADDRESS"));

 try {
       String DB_URL = "jdbc:mysql://localhost/TecTalk";
       Class.forName("com.mysql.jdbc.Driver");
       Connection con = DriverManager.getConnection(DB_URL,"root","tlszps13");
       String sql = "INSERT INTO ItemInfo(cus_id, dri_id, item_info, item_address) VALUES(?,?,?,?)";
       
       PreparedStatement pstmt = con.prepareStatement(sql);
       pstmt.setString(1,client_name);
       pstmt.setString(2,driver_name);
       pstmt.setString(3,item_name);
       pstmt.setString(4,address);

       pstmt.executeUpdate();

       pstmt.close();
       con.close();
}
 catch(ClassNotFoundException e){
  }

 catch(SQLException e){
  }
 
response.sendRedirect("/TecTalk/MainPost?DRIVER_NAME="+enco_driver+"&CLIENT_NAME="+enco_client+"&ITEM_NAME="+enco_item+"&ADDRESS="+enco_address+"");

%>

