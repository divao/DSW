package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.pojo.Locadora;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.apache.derby.shared.common.error.DerbySQLIntegrityConstraintViolationException;

public class LocadoraDAO extends GenericDAO<Locadora> {

    @Override
    public void save(Locadora locadora) {
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(locadora);
        tx.commit();
        em.close();
    }

    @Override
    public List<Locadora> getAll() {
        EntityManager em = this.getEntityManager();
        Query q = em.createQuery("select l from Locadora l", Locadora.class);
        List<Locadora> locadoras = q.getResultList();
        em.close();
        return locadoras;
    }

    @Override
    public void delete(Locadora locadora){
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        locadora = em.getReference(Locadora.class, locadora.getId());
        tx.begin();
        em.remove(locadora);
        tx.commit();
    }

    @Override
    public void update(Locadora locadora) {
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.merge(locadora);
        tx.commit();
        em.close();
    }

    @Override
    public Locadora get(Long id) {
        EntityManager em = this.getEntityManager();
        Locadora locadora = em.find(Locadora.class, id);
        em.close();
        return locadora;
    }

    public List<Locadora> getPorCidade(String cidade) {
        EntityManager em = this.getEntityManager();
        String s = "select p from Locadora p where p.cidade = :cidade";
        TypedQuery<Locadora> q = em.createQuery(s, Locadora.class);
        q.setParameter("cidade", cidade);
        return q.getResultList();
    }
    
    public Locadora getPorEmail(String email) {
        EntityManager em = this.getEntityManager();
        String s = "select l from Locadora l where l.email = :email";
        Query q = em.createQuery(s, Locadora.class);
        q.setParameter("email", email);
        return (Locadora) q.getSingleResult();
    }
    
    public List<Locadora> getAllPorCnpj(String cnpj) {
        EntityManager em = this.getEntityManager();
        Query q = em.createQuery("select c from Locadora c where c.cnpj = :cnpj", Locadora.class);
        q.setParameter("cnpj", cnpj);
        List<Locadora> locadoras = q.getResultList();
        em.close();
        return locadoras;
    }
    
    public List<Locadora> getAllPorEmail(String email) {
        EntityManager em = this.getEntityManager();
        Query q = em.createQuery("select c from Locadora c where c.email = :email", Locadora.class);
        q.setParameter("email", email);
        List<Locadora> locadoras = q.getResultList();
        em.close();
        return locadoras;
    }
}
