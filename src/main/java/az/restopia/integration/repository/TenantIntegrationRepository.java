package az.restopia.integration.repository;

import az.restopia.integration.domain.entity.Integration;
import az.restopia.integration.domain.entity.TenantIntegration;
import az.restopia.integration.domain.enums.IntegrationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TenantIntegrationRepository extends JpaRepository<TenantIntegration, Long> {
    
    List<TenantIntegration> findByTenantCode(String tenantCode);
    
    Optional<TenantIntegration> findByIntegrationAndTenantCode(Integration integration, String tenantCode);
    
    Optional<TenantIntegration> findByTenantCodeAndIntegrationId(String tenantCode, Long integrationId);
    
    boolean existsByTenantCodeAndIntegration(String tenantCode, Integration integration);
    
    List<TenantIntegration> findByTenantCodeAndStatus(String tenantCode, IntegrationStatus status);
}