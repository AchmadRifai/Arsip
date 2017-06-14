/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package page;

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
@WebServlet(name = "Index", urlPatterns = {"/"})
public class Index extends HttpServlet {
private HttpServletRequest req;
private HttpServletResponse res;
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
    try {
        initAwal();
        isiIndex();
        foot();
        d.close();
    } catch (Exception ex) {
        util.Db.hindar(ex,req.getRemoteAddr());
        res.sendRedirect("error.html?kode=505");
    }
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

    private void initAwal() throws Exception {
        d=new util.Db();
        page.util.Header.headIndex(res.getWriter(),"Daftar Pengguna");
        page.util.Header.navIndex(res.getWriter(),req.getSession().getAttribute("akun"),d);
    }

    private void isiIndex() throws Exception {
        res.getWriter().print("<div class=\"ui main text container\">");
        entity.dao.DAOAkun dao=new entity.dao.DAOAkun(d.genDB());
        page.util.DataList.akunCards(dao.all(),res.getWriter());
        res.getWriter().print("</div>");
    }

    private void foot() throws IOException {
        page.util.Footer.defaultFoot(res.getWriter());
    }
}
