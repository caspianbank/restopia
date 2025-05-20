package az.neotech.neoeats.menu.repository;

import az.neotech.neoeats.menu.domain.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    List<Menu> findByNameContainingIgnoreCase(String name);
}

