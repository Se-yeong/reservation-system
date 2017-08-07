package kr.or.reservation.service;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import kr.or.reservation.domain.Category;

public interface CategoryService {

	public List<Category> selectList();

	public Long insert(Category category);

	public Long delete(Long id);

}
