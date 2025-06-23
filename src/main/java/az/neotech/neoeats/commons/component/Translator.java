package az.neotech.neoeats.commons.component;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class Translator {

    private final MessageSource messageSource;

    public String translate(@NotNull String key) {
        return translate(key, Locale.getDefault().getLanguage());
    }

    public String translate(@NotNull String key, @NotNull String lang) {
        return messageSource.getMessage(key, null, null, Locale.of(lang));
    }

    public String translate(@NotNull String key, @NotNull String lang, Object... args) {
        return messageSource.getMessage(key, args, null, Locale.of(lang));
    }
}
