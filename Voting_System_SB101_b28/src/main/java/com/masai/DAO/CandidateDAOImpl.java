package com.masai.DAO;

import com.masai.Entity.Candidate;
import com.masai.Exception.CandidateNotFoundException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class CandidateDAOImpl implements CandidateDAO {

    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    public CandidateDAOImpl() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("ConnectVotingSystem");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public void saveCandidate(Candidate candidate) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(candidate);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw ex;
        }
    }

    @Override
    public void updateCandidate(Candidate candidate) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(candidate);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw ex;
        }
    }

    @Override
    public void deleteCandidate(int candidateId) {
        try {
            entityManager.getTransaction().begin();
            Candidate candidate = entityManager.find(Candidate.class, candidateId);
            if (candidate != null) {
                entityManager.remove(candidate);
            }
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw ex;
        }
    }

    @Override
    public Candidate getCandidateById(int candidateId) throws CandidateNotFoundException {
        Candidate candidate = entityManager.find(Candidate.class, candidateId);
        if (candidate == null) {
            throw new CandidateNotFoundException("Candidate with ID " + candidateId + " not found.");
        }
        return candidate;
    }

    @Override
    public List<Candidate> getAllCandidates() {
        List<Candidate> candidates;

        try {
            entityManager.getTransaction().begin();
            TypedQuery<Candidate> query = entityManager.createQuery("SELECT c FROM Candidate c", Candidate.class);
            candidates = query.getResultList();
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw ex;
        }

        return candidates;
    }
}
