package ndn.livestream.repositories;

import ndn.livestream.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    List<CategoryEntity> findByNameContains(String name);
    @Query(nativeQuery = true, value = "select * from category limit 0,8")
    List<CategoryEntity> findTopCategory();
}
