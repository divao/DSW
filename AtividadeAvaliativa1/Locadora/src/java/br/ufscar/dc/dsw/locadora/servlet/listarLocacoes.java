/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.locadora.servlet;

import br.ufscar.dc.dsw.locadora.bean.Locacao;
import br.ufscar.dc.dsw.locadora.dao.LocacaoDAO;
import br.ufscar.dc.dsw.locadora.dao.LocadoraDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Jackson Victor
 */
@WebServlet(name = "listarLocacoes", urlPatterns = {"/listarLocacoes"})
public class listarLocacoes extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        Object usuarioLogado = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
        String username; 
        if (usuarioLogado instanceof UserDetails ) { 
            username= ((UserDetails)usuarioLogado).getUsername(); 
        } else { 
            username = usuarioLogado .toString(); 
        }
        
        System.out.println(username);
        
        response.setContentType("text/html;charset=UTF-8");
        String cnpj = (new LocadoraDAO().getPorEmail(username)).getCnpj();
        List<Locacao> todasLocacoes;
        try {
            LocacaoDAO locacaoDAO = new LocacaoDAO();
            todasLocacoes = locacaoDAO.getPorCnpj(cnpj);
            request.setAttribute("listaLocacoes", todasLocacoes);
            request.getRequestDispatcher("/locadora/listaLocacoes.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", e.getLocalizedMessage());
            request.getRequestDispatcher("erro.jsp").forward(request, response);
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

}
