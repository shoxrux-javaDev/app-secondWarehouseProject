package uz.spring.appanotherwarehouseproject.Service;

import org.springframework.stereotype.Service;
import uz.spring.appanotherwarehouseproject.Dto.CategoryDto;
import uz.spring.appanotherwarehouseproject.Dto.Response;
import uz.spring.appanotherwarehouseproject.Entity.Category;
import uz.spring.appanotherwarehouseproject.Repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Response addAll(List<CategoryDto> categoryDtoList) {
        for (CategoryDto categoryDto : categoryDtoList) {
            Optional<Category> categoryOptional = categoryRepository.findById(categoryDto.getParentId());
            if(categoryOptional.isEmpty()) return new Response("empty",false);
            Category category = new Category();
            category.setName(categoryDto.getName());
            category.setActive(categoryDto.isActive());
            category.setParent(categoryOptional.get());
            categoryRepository.save(category);
        }
        return new Response("added all",true);
    }

    public Response addOne(CategoryDto categoryDto) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryDto.getParentId());
        if(categoryOptional.isEmpty()) return new Response("empty",false);
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setActive(categoryDto.isActive());
        category.setParent(categoryOptional.get());
        categoryRepository.save(category);
        return new Response("added one",true);
    }

    public Response getAll() {
        List<Category> list = categoryRepository.findAll();
        if (list.isEmpty()) return new Response("empty",false);
        return new Response("success",true,list);
    }

    public Response getOne(Integer id) {
        Optional<Category> optional = categoryRepository.findById(id);
        if (optional.isEmpty()) return new Response("empty",false);
        return new Response("success",true,optional.get());
    }

    public Response editOne(Integer id, CategoryDto categoryDto) {
        Optional<Category> optional = categoryRepository.findById(categoryDto.getParentId());
        if (optional.isEmpty()) return new Response("empty",false);
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isEmpty()) return new Response("empty",false);
        Category category = optional.get();
        category.setParent(optional.get());
        category.setName(categoryDto.getName());
        category.setActive(categoryDto.isActive());
        categoryRepository.save(category);
        return new Response("edited",true);
    }

    public Response deleteAll() {
        categoryRepository.deleteAll();
        return new Response("deleted all",true);
    }

    public Response deleteOne(Integer id) {
        categoryRepository.deleteById(id);
        return new Response("deleted one",true);
    }
}
