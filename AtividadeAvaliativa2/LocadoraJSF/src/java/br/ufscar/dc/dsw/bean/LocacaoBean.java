package br.ufscar.dc.dsw.bean;

import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.dao.LocacaoDAO;
import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.pojo.Locacao;
import br.ufscar.dc.dsw.pojo.Locadora;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class LocacaoBean {

    private Locacao locacao;

    public Locacao getLocacao() {
        return locacao;
    }

    public String lista() {
        return "locacao/index.xhtml";
    }
    
    public String lista2() {
        return "locadora/listaLocacao.xhtml";
    }

    public String lista2() {
        return "locadora/listaLocacao.xhtml";
    }

    public String cadastra() {
        locacao = new Locacao();
        return "form.xhtml";
    }

    public String edita(Long id) {
        LocacaoDAO dao = new LocacaoDAO();
        locacao = dao.get(id);
        return "form.xhtml";
    }

    public String salva() {
        LocacaoDAO dao = new LocacaoDAO();
        ClienteDAO daoCliente = new ClienteDAO();
        UsuarioDAO daoUsuario = new UsuarioDAO();
        locacao.setCliente(daoCliente.getPorEmail(daoUsuario.getEmailUsuarioLogado()));
        if (locacao.getId() == null) {
            dao.save(locacao);
        } else {
            dao.update(locacao);
        }
        return "index.xhtml";
    }

    public String delete(Locacao locacao) {
        LocacaoDAO dao = new LocacaoDAO();
        dao.delete(locacao);
        return "index.xhtml";
    }

    public String volta() {
        return "/index.xhtml?faces-redirect=true";
    }
    
    

    public List<Locacao> getLocacoes() throws SQLException {
        LocacaoDAO dao = new LocacaoDAO();
        return dao.getAll();
    }
    
    public List<Locacao> getPorCliente() throws SQLException {
        LocacaoDAO dao = new LocacaoDAO();
        return dao.getPorCliente();
    }
    
    public List<Locacao> getPorLocadora() throws SQLException {
        LocacaoDAO dao = new LocacaoDAO();
        return dao.getPorLocadora();
    }

    public List<Locacao> getPorCliente() throws SQLException {
        LocacaoDAO dao = new LocacaoDAO();
        return dao.getPorCliente();
    }

    public List<Locacao> getPorLocadora() throws SQLException {
        LocacaoDAO dao = new LocacaoDAO();
        return dao.getPorLocadora();
    }

    public Locacao getLivro() {
        return locacao;
    }

    public void validarData(FacesContext context,
            UIComponent toValidate,
            Object obj) {

        Date data = (Date) obj;

        try {
            if (data.before(new Date())) {
                ((UIInput) toValidate).setValid(false);
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Data Passada!");
                context.addMessage(toValidate.getClientId(context), message);
            }
        } catch (NullPointerException e) {
            ((UIInput) toValidate).setValid(false);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Data é obrigatório!");
            context.addMessage(toValidate.getClientId(context), message);
            e.printStackTrace();
        }
    }
}
