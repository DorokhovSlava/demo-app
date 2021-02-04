package org.dorokhov.demoapp.repositoryes;
import org.dorokhov.demoapp.entityes.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

}
