package ndn.livestream.apis;

import ndn.livestream.entities.CategoryEntity;
import ndn.livestream.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CategoryAPI {
    @Autowired
    private CategoryService categoryService;


    @GetMapping("/guest/category/getAll")
    public List<CategoryEntity> getAll() {
        return categoryService.findAll();
    }

    @GetMapping("/guest/category/getTopCatgory")
    public List<CategoryEntity> getTopCategory() {
        return categoryService.findTopCategory();
    }

    @GetMapping("/guest/category/findById/{id}")
    public CategoryEntity findById(@PathVariable Long id) {
       return categoryService.findById(id);
    }

    @PostMapping("/admin/category/delete/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }

    @PostMapping("/admin/category/save")
    public void save(@RequestBody CategoryEntity categoryEntity) {
        categoryService.save(categoryEntity);
    }

}
