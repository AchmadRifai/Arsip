/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author janoko
 */
@WebServlet(name = "Berkas", urlPatterns = {"/berkas.data"})
public class Berkas extends HttpServlet {
private HttpServletRequest req;
private HttpServletResponse res;
private String alamat;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        req=request;
        res=response;
    if(null!=req.getSession().getAttribute("login"))try {
        generateAlamat();
        kirimBerkas();
    } catch (Exception ex) {
        util.Db.hindar(ex, req.getRemoteAddr());
        res.sendRedirect("error.html?kode=505");
    }else res.sendRedirect("login.html");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void generateAlamat() throws Exception{
        util.RSA r=util.Work.loadRSA();
        String s=req.getParameter("akses");
        alamat=r.decrypt(s);
    }

    private void kirimBerkas() throws IOException {
        java.io.File f=new java.io.File("berkas/"+alamat);
        java.io.FileInputStream i=new java.io.FileInputStream(f);
        java.io.OutputStream o=res.getOutputStream();
        int c;
        while((c=i.read())!=-1)o.write(c);
        i.close();
        o.close();
    }
}
