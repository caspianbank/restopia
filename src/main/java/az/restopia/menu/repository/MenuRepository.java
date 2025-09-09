package az.restopia.menu.repository;

import az.restopia.menu.domain.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    List<Menu> findByNameContainingIgnoreCase(String name);

    @Query(value = """
            select * from menus m where m.tenant_code = :tenantCode and m.is_active = true
            limit 1
            """,
            nativeQuery = true)
    Optional<Menu> findActiveMenuByTenantCode(String tenantCode);
}

