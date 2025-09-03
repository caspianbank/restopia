package az.restopia.order.controller;

import az.neotech.commons.Language;
import az.restopia.order.domain.response.ServiceActionPromptResponse;
import az.restopia.order.service.ServiceActionPromptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/service-prompts")
public class ServiceActionPromptController {

    private final ServiceActionPromptService serviceActionPromptService;

    @GetMapping
    public List<ServiceActionPromptResponse> getAllPrompts(
            @RequestHeader("X-Tenant-Code") String tenantCode,
            @RequestHeader("Accept-Language") String languageCode
    ) {
        return serviceActionPromptService.getAllActivePrompts(tenantCode, Language.valueOf(languageCode.toUpperCase()));
    }

}
