package site.metacoding.white.domain;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Repository // IoC 등록
public class UserRepository {

    // DI
    private final EntityManager em;

    public User save(User user) {
        // Persistence Context에 영속화 시키기 -> 자동 flush (트랜잭션 종료시)
        em.persist(user);
        return user;
    }

    public Optional<User> findByUsername(String username) { // user를 Optional이란 박스에 담아서 user가 null인지 아닌지 모르니 박스에 담아서 서비스단에서 get을 해서 까서 알 수 있다 => null값 방지
        try {
            Optional<User> userOP = Optional.of(em
                    .createQuery(
                            "select u from User u where u.username=:username",
                            User.class)
                    .setParameter("username", username)
                    .getSingleResult());
            return userOP;
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<User> findById(Long id) {
        try {
            Optional<User> userOP = Optional.of(em
                    .createQuery(
                            "select u from User u where u.id=:id",
                            User.class)
                    .setParameter("id", id)
                    .getSingleResult());
            return userOP;
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public List<User> findAll() {
        List<User> userList = em.createQuery("select b from User b", User.class).getResultList();
        return userList;
    }

}