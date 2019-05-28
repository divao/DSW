package br.ufscar.dc.dsw.bean;

import br.ufscar.dc.dsw.dao.LocadoraDAO;
import br.ufscar.dc.dsw.dao.PapelDAO;
import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.pojo.Locadora;
import br.ufscar.dc.dsw.pojo.Papel;
import br.ufscar.dc.dsw.pojo.Usuario;
import java.sql.SQLException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@ManagedBean
@SessionScoped
public class LocadoraBean {

    private Locadora locadora;
    private String cidade;

    public String lista() {
        return "admin/indexLocadora.xhtml";
    }

    public String lista2() {
        return "anonymous/lista.xhtml";
    }

    public String cadastra() {
        locadora = new Locadora();
        return "formLocadora.xhtml";
    }

    public String edita(Long id) {
        LocadoraDAO dao = new LocadoraDAO();
        locadora = dao.get(id);
        return "formLocadora.xhtml";
    }
    
    
    public String salva() {
        LocadoraDAO dao = new LocadoraDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        PapelDAO papelDAO = new PapelDAO();
        Usuario u = new Usuario();
        u.setEmail(locadora.getEmail());
        u.setSenha(new BCryptPasswordEncoder().encode(locadora.getSenha()));
        u.setAtivo(true);
        
        Papel p = papelDAO.get(Long.valueOf(3));

        
        
        
        if (locadora.getId() == null) {
            dao.save(locadora);
            usuarioDAO.save(u);
            u.getPapel().add(p);
            usuarioDAO.update(u);
        } else {
            dao.update(locadora);
            u.getPapel().add(p);
            usuarioDAO.update(u);
        }
        return "indexLocadora.xhtml";
    }

   

    public String delete(Locadora locadora) {
        LocadoraDAO dao = new LocadoraDAO();
        dao.delete(locadora);
        return "indexLocadora.xhtml";
    }

    public String volta() {
        return "/index.xhtml?faces-redirect=true";
    }

    public List<Locadora> getlocadoras() throws SQLException {
        LocadoraDAO dao = new LocadoraDAO();
        return dao.getAll();
    }

    public List<Locadora> getPorCidade() throws SQLException {
        LocadoraDAO dao = new LocadoraDAO();

        if (cidade == null || cidade.equals("")) {
            return dao.getAll();
        } else {
            return dao.getPorCidade(cidade);
        }

    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Locadora getLocadora() {
        return locadora;
    }
}
