package az.restopia.menu.repository;

import az.restopia.menu.domain.entity.MenuItem;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    @Query("SELECT mi FROM MenuItem mi")
    List<MenuItem> findMenuItemsSorted(Sort sort);
}
