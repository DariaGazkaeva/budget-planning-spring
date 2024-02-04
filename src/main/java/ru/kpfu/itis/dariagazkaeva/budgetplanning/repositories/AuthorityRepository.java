package ru.kpfu.itis.dariagazkaeva.budgetplanning.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.entities.UserAuthority;

public interface AuthorityRepository extends CrudRepository<UserAuthority, Long> {
    UserAuthority findByAuthority(String authority);
}
