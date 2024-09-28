package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Category;
import za.ac.cput.repository.CategoryRepository;
import za.ac.cput.service.interfaces.ICategoryService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category read(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category update(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findCategoryByName(String name) {
        return categoryRepository.findCategoryByName(name);
    }

}