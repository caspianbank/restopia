package az.neotech.neoeats.order.service;

import az.neotech.commons.Language;
import az.neotech.neoeats.order.domain.response.ServiceActionPromptResponse;

import java.util.List;

public interface ServiceActionPromptService {

    List<ServiceActionPromptResponse> getAllActivePrompts(String tenantCode, Language language);
}
