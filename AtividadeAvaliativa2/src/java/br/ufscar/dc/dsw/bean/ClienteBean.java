package br.ufscar.dc.dsw.bean;

import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.dao.PapelDAO;
import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.pojo.Cliente;
import br.ufscar.dc.dsw.pojo.Papel;
import br.ufscar.dc.dsw.pojo.Usuario;
import java.sql.SQLException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@ManagedBean
@SessionScoped
public class ClienteBean {
    
    

    private Cliente cliente;

    public Cliente getCliente() {
        return cliente;
    }

    public String lista() {
        return "admin/indexCliente.xhtml";
    }

    public String cadastra() {
        cliente = new Cliente();
        return "formCliente.xhtml";
    }

    public String edita(Long id) {
        ClienteDAO dao = new ClienteDAO();
        cliente = dao.get(id);
        return "formCliente.xhtml";
    }

    public String salva() {
        ClienteDAO dao = new ClienteDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        PapelDAO papelDAO = new PapelDAO();
        Usuario u = new Usuario();
        u.setEmail(cliente.getEmail());
        u.setSenha(new BCryptPasswordEncoder().encode(cliente.getSenha()));
        u.setAtivo(true);
        
        Papel p = papelDAO.get(Long.valueOf(2));

        
        
        
        if (cliente.getId() == null) {
            dao.save(cliente);
            usuarioDAO.save(u);
            u.getPapel().add(p);
            usuarioDAO.update(u);
        } else {
            dao.update(cliente);
            u.getPapel().add(p);
            usuarioDAO.update(u);
        }
        return "indexCliente.xhtml";
    }

    public String delete(Cliente cliente) {
        ClienteDAO dao = new ClienteDAO();
        dao.delete(cliente);
        
        return "indexCliente.xhtml";
    }

    public String volta() {
        return "/index.xhtml?faces-redirect=true";
    }

    public List<Cliente> getClientes() throws SQLException {
        ClienteDAO dao = new ClienteDAO();
        return dao.getAll();
    }

    
}
