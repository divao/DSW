/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.locadora.servlet;

import br.ufscar.dc.dsw.locadora.bean.Locadora;
import br.ufscar.dc.dsw.locadora.dao.LocadoraDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jackson Victor
 */
public class LocadoraController extends HttpServlet {

    private LocadoraDAO dao;

    @Override
    public void init() {
        dao = new LocadoraDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        String action = (String) request.getParameter("action");
        System.out.println("ACAO = " + action);
        try {
            if(action == null) {
                lista(request, response);
            }else if(action.equals("cadastro")){
                apresentaFormCadastro(request,response);
            }else if(action.equals("insercao")){
                insere(request, response);
            }else if(action.equals("remocao")){
                remove(request, response);
            }else if(action.equals("edicao")){
                apresentaFormEdicao(request, response);
            }else if(action.equals("atualizacao")){
                atualize(request, response);
            }else{
                lista(request, response);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(LocadoraController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void lista(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Locadora> listaLocadoras = dao.getAll();
        request.setAttribute("listaLocadoras", listaLocadoras);
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin/listaLocadora.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin/formularioLocadora.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Locadora locadora = dao.get(request.getParameter("cnpj"));
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin/formularioLocadora.jsp");
        request.setAttribute("locadora", locadora);
        dispatcher.forward(request, response);

    }

    private void insere(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        request.setCharacterEncoding("UTF-8");
        String cnpj = (String) request.getParameter("cnpj");
        String nome = (String) request.getParameter("nome");
        String email = (String) request.getParameter("email");
        String senha = (String) request.getParameter("senha");
        String cidade = (String) request.getParameter("cidade");
        // boolean ativo = Boolean.getBoolean(request.getParameter("ativo"));
        Locadora locadora = new Locadora(cnpj, nome, email, senha, cidade, true);
        dao.insert(locadora);
        response.sendRedirect("listaLocadora");
    }

    private void atualize(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        request.setCharacterEncoding("UTF-8");
        String cnpj = (String) request.getParameter("cpf");
        String nome = (String) request.getParameter("nome");
        String email = (String) request.getParameter("email");
        String senha = (String) request.getParameter("senha");
        String cidade = (String) request.getParameter("cidade");
        // boolean ativo = Boolean.getBoolean(request.getParameter("ativo"));
        Locadora locadora = new Locadora(cnpj, nome, email, senha, cidade, true);
        dao.update(locadora);
        response.sendRedirect("listaLocadora");
    }

    private void remove(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String cnpj = request.getParameter("cnpj");
        dao.delete(cnpj);
        response.sendRedirect("listaLocadora");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
