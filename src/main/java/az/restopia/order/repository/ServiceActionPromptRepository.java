package az.restopia.order.repository;

import az.neotech.commons.Language;
import az.restopia.order.domain.entity.ServiceActionPrompt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceActionPromptRepository extends JpaRepository<ServiceActionPrompt, Long> {

    @Query(value = """
            SELECT DISTINCT sap FROM ServiceActionPrompt sap
            JOIN sap.translations t
            WHERE sap.tenantCode = :tenantCode
            AND KEY(t) = :language
            AND sap.deleteStatus = 0
            """)
    List<ServiceActionPrompt> findAllActivePromptsByTenantCodeAndLanguage(String tenantCode, Language language);
}
