package jpabook.jpabook.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpabook.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor // lombok cho Constructor
public class MemberRepository {
    @PersistenceContext
    private final EntityManager em;


    // save
    public void save(Member member) {
        em.persist(member);
    }

    // findOne
    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    // findAll
    public List<Member> findAll() {
        return em.createQuery(
                "SELECT m from Member m", Member.class
        ).getResultList();
    }

    // findByName
    public List<Member> findByName(String name) {
        return em.createQuery(
                "SELECT m from Member m " +
                        "WHERE m.name = :name", Member.class
        ).setParameter("name", name)
                .getResultList();
    }
    // findAll
}
