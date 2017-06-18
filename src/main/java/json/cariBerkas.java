/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

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
@WebServlet(name = "cariBerkas", urlPatterns = {"/cariBerkas.json"})
public class cariBerkas extends HttpServlet {
private HttpServletRequest req;
private HttpServletResponse res;
private org.json.simple.JSONObject one;
private util.Db d;
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
        one=new org.json.simple.JSONObject();try {
        showJSON();
        d.close();
    } catch (Exception ex) {
        util.Db.hindar(ex, req.getRemoteAddr());
    }one.writeJSONString(res.getWriter());
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

    private void showJSON() throws Exception {
        d=new util.Db();
        if(null!=req.getParameter("kunci")){
            String s=req.getParameter("kunci");
            loadWith(s);
        }else loadAll();
    }

    private void loadWith(String s) throws Exception {
    }

    private void loadAll() throws Exception {
        entity.dao.DAOBerkas dao=new entity.dao.DAOBerkas(d.genDB());
        util.RSA r=util.Work.loadRSA();
        org.json.simple.JSONArray a=new org.json.simple.JSONArray();
        java.util.List<entity.Berkas>l=dao.all();
        int batas=Integer.parseInt(req.getParameter("no"));
        one.put("size", (l.size()/30)+1);
        one.put("datas", a);
        one.put("no", batas);
    }
}
