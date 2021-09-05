package dev.hereos.firstproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.hereos.firstproject.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	
	
	
//	private Map<Long, Category> map = new HashMap<>();
//
//	public void save(Category obj) {
//		map.put(obj.getId(), obj);
//	}
//
//	public Category findById(Long id) {
//		return map.get(id);
//	}
//	
//	public List<Category> findAll() {
//		return new ArrayList<Category>(map.values());
//	}
}