package az.neotech.neoeats.menu.repository;

import az.neotech.neoeats.menu.domain.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
}
