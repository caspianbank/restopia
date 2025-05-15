package az.neotech.neoeats.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import az.neotech.neoeats.domain.entity.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    List<Menu> findByNameContainingIgnoreCase(String name);
}

