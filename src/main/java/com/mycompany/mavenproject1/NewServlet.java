/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import simplejdbc.CustomerEntity;
import simplejdbc.DAO;
import simplejdbc.DAOException;
import simplejdbc.DataSourceFactory;

/**
 *
 * @author pedago
 */
public class NewServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Servlet0</title>");            
            out.println("</head>");
            out.println("<body>");
            
            
            /* EXEMPLE : Récupération des paramètres */
            //String nom = request.getParameter("nom");  // param => /Servlet0?nom=Flo
            //out.println("Nom = " + nom);
            
            /* Affiche les informations de tous les clients habitant dans un état des USA transmis en paramètre */
            DataSource myDataSource = DataSourceFactory.getDataSource(); // La source de données à utiliser
            DAO myDAO = new DAO(myDataSource); // L'objet à tester
            DAO2 myDAO2 = new DAO2(myDataSource);
            List<String> res = myDAO2.allStates();
            String state = request.getParameter("state");
            List<CustomerEntity> result = myDAO.customersInState(state);  // Informations récupérées
            out.println("<form method=\"get\" >");   // On récupère la valeur choisie avec la méthode GET
            // Enregistrement de tous les états dans la liste HTML
            out.println("<select name=\"state\" >");
            
            for(String s : res){
                out.println("<option>"+s+"</option>");
            }
            out.println("</select>");
            
             out.print("  <input type=\"submit\" value=\"Valider\"  >");
            out.println("</form>");
            /* Création des colonnes du tableau */
            out.println("<table border=1 width=30% height=40%>");
            out.println("<tr>");
                out.println("<th>ID</th>");
                out.println("<th>Name</th>");
                out.println("<th>Address</th>");
            out.println("</tr>");
            
            System.out.println("result = " + result);
            
            /* Table HTML : Affichage des résultats */
            for (CustomerEntity ce : result) {
                out.println("<tr>");
               
                out.println("<td>" + ce.getCustomerId() + "</td>");
                out.println("<td>" + ce.getName() + "</td>");
                out.println("<td>" + ce.getAddressLine1() + "</td>");
                
                out.println("</tr>");
            }
            
           out.println("</table>");
            
            out.println("</body>");
            out.println("</html>");
        } catch (DAOException ex) {
            Logger.getLogger(NewServlet.class.getName()).log(Level.SEVERE, null, ex);
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
    }
    
}


