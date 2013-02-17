/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sessions.SongManager;

/**
 *
 * @author brunolarosa
 */
@WebServlet(name = "musicServlet", urlPatterns = {"/musicServlet"})
public class musicServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("audio/mpeg");
        ServletOutputStream stream = null;
        BufferedInputStream buffer = null;
        
        String mp3ID = request.getParameter("m");
        
        try {

            Context context = new InitialContext();
            SongManager songManager = (SongManager)context.lookup("java:global/iMuzik/iMuzik-ejb/SongManager");
            
            File mp3 = new File(songManager.getSong(Integer.valueOf(mp3ID)).getUrl());
        
            
            stream = response.getOutputStream();


            response.setContentLength((int) mp3.length());

            FileInputStream input = new FileInputStream(mp3);
            buffer = new BufferedInputStream(input);
            int readBytes = 0;

            //read from the file; write to the ServletOutputStream
            while ((readBytes = buffer.read()) != -1) {
                stream.write(readBytes);
            }


        } catch (Exception ioe) {
            
            throw new ServletException(ioe.getMessage());
            
        } finally {
            if (stream != null) {
                stream.close();
            }
            if (buffer != null) {
                buffer.close();
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
}
