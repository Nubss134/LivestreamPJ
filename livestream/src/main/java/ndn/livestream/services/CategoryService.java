package ndn.livestream.services;

import ndn.livestream.entities.CategoryEntity;
import ndn.livestream.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryEntity> findAll() {
        return categoryRepository.findAll();
    }
    public List<CategoryEntity> findTopCategory() {
        return categoryRepository.findTopCategory();
    }

    public CategoryEntity findById(Long id) {
        return categoryRepository.findById(id).orElseThrow();
    }

    public void delete(Long id) {
        if(categoryRepository.findById(id).isEmpty()) return;
        categoryRepository.deleteById(id);
    }

    public void save(CategoryEntity categoryEntity) {
        categoryEntity.setUpdatedTime(new Date());
        if(categoryEntity.getId() == null) {
            categoryEntity.setCreatedTime(new Date());
        }
        categoryRepository.save(categoryEntity);
    }

}
