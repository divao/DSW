package br.ufscar.dc.dsw.dao;
 
import br.ufscar.dc.dsw.pojo.Locacao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
 
public class LocacaoDAO extends GenericDAO<Locacao>{
    
    @Override
    public void save(Locacao locacao) {
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(locacao);
        tx.commit();
        em.close();
    }
    
    @Override
    public List<Locacao> getAll() {
        EntityManager em = this.getEntityManager();
        Query q = em.createQuery("select l from Locacao l", Locacao.class);
        List<Locacao> locacoes = q.getResultList();
        em.close();
        return locacoes;
    }
 
    @Override
    public void delete(Locacao locacao) {
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        locacao = em.getReference(Locacao.class, locacao.getId());
        tx.begin();
        em.remove(locacao);
        tx.commit();
    }
    
    @Override
    public void update(Locacao locacao) {
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.merge(locacao);
        tx.commit();
        em.close();
    }
 
    @Override
    public Locacao get(Long id) {
        EntityManager em = this.getEntityManager();
        Locacao locacao = em.find(Locacao.class, id);
        em.close();
        return locacao;
    }
}
