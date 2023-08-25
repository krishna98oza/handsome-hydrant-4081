package com.masai.DAO;

import java.util.List;

import org.hibernate.query.Query;

import com.masai.Entity.Vote;
import com.masai.Entity.Voter;
import com.masai.Exception.VoterNotFoundException;
//import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

//@Repository
@Transactional
public class VoterDAOImpl implements VoterDAO {

    @PersistenceContext
    private EntityManager entityManager;
    private final EntityManagerFactory entityManagerFactory;
    
    public VoterDAOImpl() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("ConnectVotingSystem");
        this.entityManager = entityManagerFactory.createEntityManager();
    }
    
    @Override
    public void saveVoter(Voter voter) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(voter);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw ex;
        }
    }
    @Override
    public boolean isEmailExists(String email) {
        jakarta.persistence.Query query = entityManager.createQuery("SELECT COUNT(v) FROM Voter v WHERE v.email = :email");
        query.setParameter("email", email);
        long count = (long) query.getSingleResult();
        return count > 0;
    }

    @Override
    public Voter getVoterById(Long id) throws VoterNotFoundException {
        Voter voter = entityManager.find(Voter.class, id);
        if (voter == null) {
            throw new VoterNotFoundException("Voter with ID " + id + " not found.");
        }
        return voter;
    }
    
    @Override
    public Voter getVoterByEmail(String email) {
        TypedQuery<Voter> query = entityManager.createQuery("SELECT v FROM Voter v WHERE v.email = :email", Voter.class);
        query.setParameter("email", email);
        try {
            return query.getSingleResult();
        } catch (Exception ex) {
            return null; // Return null if no matching email found
        }
    }

    @Override
    public void updateVoter(Voter voter) {
        entityManager.merge(voter);
    }

    @Override
    public void deleteVoter(Long id) throws VoterNotFoundException {
        Voter voter = getVoterById(id);
        entityManager.remove(voter);
    }
    @Override
    public List<Vote> getVotingHistory(Voter voter) {
        TypedQuery<Vote> query = entityManager.createQuery("SELECT v FROM Vote v WHERE v.voterId = :voterId", Vote.class);
        query.setParameter("voterId", voter.getId());
        return query.getResultList();
    }
}
