package az.neotech.neoeats.order.service.impl;

import az.neotech.commons.Language;
import az.neotech.neoeats.order.domain.response.ServiceActionPromptResponse;
import az.neotech.neoeats.order.repository.ServiceActionPromptRepository;
import az.neotech.neoeats.order.service.ServiceActionPromptService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ServiceActionPromptServiceImpl implements ServiceActionPromptService {

    private final ServiceActionPromptRepository serviceActionPromptRepository;

    @Override
    public List<ServiceActionPromptResponse> getAllActivePrompts(String tenantCode, Language language) {
        return serviceActionPromptRepository.findAllActivePromptsByTenantCodeAndLanguage(tenantCode, language).stream()
                .map(prompt -> new ServiceActionPromptResponse(prompt.getPromptCode(), prompt.getPromptValue(language)))
                .toList();
    }
}
