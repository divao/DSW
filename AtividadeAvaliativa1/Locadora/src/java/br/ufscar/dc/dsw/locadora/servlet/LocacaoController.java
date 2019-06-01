/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.locadora.servlet;

import br.ufscar.dc.dsw.locadora.bean.Locacao;
import br.ufscar.dc.dsw.locadora.dao.LocacaoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
@WebServlet(name = "LocacaoController", urlPatterns = {"/locacaoController"})
public class LocacaoController extends HttpServlet {

    private LocacaoDAO dao;

    @Override
    public void init() {
        dao = new LocacaoDAO();
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
        List<Locacao> listaLocacoes = dao.getPorCpf("1");
        request.setAttribute("listaLocacoes", listaLocacoes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/listaLocacao.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/formularioLocacao.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String cpf = request.getParameter("cpf");
        String cnpj = request.getParameter("cnpj");
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date data = null;
        try {
            data = formato.parse(request.getParameter("data"));
        } catch (ParseException ex) {
            Logger.getLogger(LocacaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        int horario = Integer.parseInt(request.getParameter("horario"));
        
        Locacao locacao = new Locacao(cpf, cnpj, data, horario);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/formularioLocacao.jsp");
        request.setAttribute("locacao", locacao);
        dispatcher.forward(request, response);

    }

    private void insere(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        request.setCharacterEncoding("UTF-8");
        String cpf = (String) request.getParameter("cpf");
        String cnpj = (String) request.getParameter("cnpj");
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date data = null;
        try {
            data = formato.parse(request.getParameter("data"));
        } catch (ParseException ex) {
            Logger.getLogger(LocacaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        int horario = Integer.parseInt(request.getParameter("horario"));
        Locacao locacao = new Locacao(cpf, cnpj, data, horario);
        dao.insert(locacao);
        response.sendRedirect("listaLocacao");
    }

    private void atualize(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        request.setCharacterEncoding("UTF-8");
        String cpf = (String) request.getParameter("cpf");
        String cnpj = (String) request.getParameter("cnpj");
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date data = null;
        try {
            data = formato.parse(request.getParameter("data"));
        } catch (ParseException ex) {
            Logger.getLogger(LocacaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        int horario = Integer.parseInt(request.getParameter("horario"));
        
        Locacao locacao = new Locacao(cpf, cnpj, data, horario);
        dao.update(locacao);
        response.sendRedirect("listaLocacao");
    }

    private void remove(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        
        String cpf = request.getParameter("cpf");
        String cnpj = request.getParameter("cnpj");
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date data = null;
        try {
            data = formato.parse(request.getParameter("data"));
        } catch (ParseException ex) {
            Logger.getLogger(LocacaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        int horario = Integer.parseInt(request.getParameter("horario"));
        
        Locacao locacao = new Locacao(cpf, cnpj, data, horario);
        dao.delete(locacao);
        response.sendRedirect("listaLocacao");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
