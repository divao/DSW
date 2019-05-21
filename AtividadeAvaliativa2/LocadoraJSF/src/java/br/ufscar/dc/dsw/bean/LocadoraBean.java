package br.ufscar.dc.dsw.bean;

import br.ufscar.dc.dsw.dao.LocadoraDAO;
import br.ufscar.dc.dsw.pojo.Locadora;
import java.sql.SQLException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class LocadoraBean {

    private Locadora locadora;
    private String nome;

    public String lista() {
        return "locadora/index.xhtml";
    }

    public String lista2() {
        return "locadora/lista.xhtml";
    }

    public String cadastra() {
        locadora = new Locadora();
        return "form.xhtml";
    }

    public String edita(Long id) {
        LocadoraDAO dao = new LocadoraDAO();
        locadora = dao.get(id);
        return "form.xhtml";
    }

    public String salva() {
        LocadoraDAO dao = new LocadoraDAO();
        if (locadora.getId() == null) {
            dao.save(locadora);
        } else {
            dao.update(locadora);
        }
        locadora = new Locadora();
        return "index.xhtml";
    }

    public String delete(Locadora locadora) {
        LocadoraDAO dao = new LocadoraDAO();
        dao.delete(locadora);
        return "index.xhtml";
    }

    public String volta() {
        return "/index.xhtml?faces-redirect=true";
    }

    public List<Locadora> getlocadoras() throws SQLException {
        LocadoraDAO dao = new LocadoraDAO();
        return dao.getAll();
    }

    public List<Locadora> getPorNome() throws SQLException {
        LocadoraDAO dao = new LocadoraDAO();

        if (nome == null || nome.equals("")) {
            return dao.getAll();
        } else {
            return dao.getPorNome(nome);
        }

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Locadora getLocadora() {
        return locadora;
    }
}
