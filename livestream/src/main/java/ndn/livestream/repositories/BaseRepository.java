package ndn.livestream.repositories;

import ndn.livestream.entities.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;
@NoRepositoryBean
public interface BaseRepository<E extends BaseEntity> extends JpaRepository<E,Long> {
    Optional<E> findById(Long id);
}
