package com.emergentes;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name = "CookieServlet", urlPatterns = {"/CookieServlet"})
public class CookieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { 
        String mensaje=null;
        boolean nuevaVisita=true;
        int contador;
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for(Cookie c: cookies){
                if((c.getName().equals("visitante"))&& c.getValue().equals("SI")){
                    nuevaVisita=false; 
                    break;  
                }
            }
        }             
        if(nuevaVisita){
            Cookie ck=new Cookie("visitante","SI");
            ck.setMaxAge(60);
            ck.setComment("Control de nuevos visitantes");
            response.addCookie(ck);
            mensaje="Gracias por visitar nuestra página";
            contador=1;
        }else{
            mensaje="Estamos agredecidos por tenerlo nuevamente";
            contador=1;
            contador++;
        }       
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        out.println("<h1>"+mensaje+"</h1>");
        out.println("<h2>Numero de visitas: "+contador+"</h2>");
        out.println("<a href='index.jsp'>Ir a inicio</a>");     
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
