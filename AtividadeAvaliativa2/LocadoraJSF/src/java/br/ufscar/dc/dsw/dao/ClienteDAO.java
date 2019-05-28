package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.pojo.Cliente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class ClienteDAO extends GenericDAO<Cliente> {

    @Override
    public void save(Cliente cliente) {
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(cliente);
        tx.commit();
        em.close();
    }

    @Override
    public List<Cliente> getAll() {
        EntityManager em = this.getEntityManager();
        Query q = em.createQuery("select l from Cliente l", Cliente.class);
        List<Cliente> clientes = q.getResultList();
        em.close();
        return clientes;
    }

    @Override
    public void delete(Cliente cliente) {
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        cliente = em.getReference(Cliente.class, cliente.getId());
        tx.begin();
        em.remove(cliente);
        tx.commit();
    }

    @Override
    public void update(Cliente cliente) {
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.merge(cliente);
        tx.commit();
        em.close();
    }

    @Override
    public Cliente get(Long id) {
        EntityManager em = this.getEntityManager();
        Cliente cliente = em.find(Cliente.class, id);
        em.close();
        return cliente;
    }
    
    
    
    public Cliente getPorEmail(String email) {
        EntityManager em = this.getEntityManager();
        String s = "select c from Cliente c where c.email = :email";
        Query q = em.createQuery(s, Cliente.class);
        q.setParameter("email", email);
        return (Cliente) q.getSingleResult();
    }
    
    
}
