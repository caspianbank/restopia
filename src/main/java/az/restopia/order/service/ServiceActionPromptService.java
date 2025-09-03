package az.restopia.order.service;

import az.neotech.commons.Language;
import az.restopia.order.domain.response.ServiceActionPromptResponse;

import java.util.List;

public interface ServiceActionPromptService {

    List<ServiceActionPromptResponse> getAllActivePrompts(String tenantCode, Language language);
}
