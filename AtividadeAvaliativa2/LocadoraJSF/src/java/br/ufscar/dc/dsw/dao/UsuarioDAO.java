package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.pojo.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class UsuarioDAO extends GenericDAO<Usuario> {

    @Override
    public void save(Usuario usuario) {
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(usuario);
        tx.commit();
        em.close();
    }

    @Override
    public List<Usuario> getAll() {
        EntityManager em = this.getEntityManager();
        Query q = em.createQuery("select u from Usuario u", Usuario.class);
        List<Usuario> usuarios = q.getResultList();
        em.close();
        return usuarios;
    }

    @Override
    public void delete(Usuario usuario) {
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        usuario = em.getReference(Usuario.class, usuario.getId());
        tx.begin();
        em.remove(usuario);
        tx.commit();
    }

    @Override
    public void update(Usuario usuario) {
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.merge(usuario);
        tx.commit();
        em.close();
    }

    @Override
    public Usuario get(Long id) {
        EntityManager em = this.getEntityManager();
        Usuario usuario = em.find(Usuario.class, id);
        em.close();
        return usuario;
    }
    
    public Usuario getPorEmail(String email) {
        EntityManager em = this.getEntityManager();
        String s = "select u from Usuario u where u.email = :email";
        Query q = em.createQuery(s, Usuario.class);
        q.setParameter("email", email);
        return (Usuario) q.getSingleResult();
    }
    
    public List<Usuario> getAllPorEmail(String email) {
        EntityManager em = this.getEntityManager();
        Query q = em.createQuery("select u from Usuario u where u.email = :email", Usuario.class);
        q.setParameter("email", email);
        List<Usuario> usuarios = q.getResultList();
        em.close();
        return usuarios;
    }

    public String getEmailUsuarioLogado() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email;
        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        } else {
            email = principal.toString();
        }
        return email;
    }
}
