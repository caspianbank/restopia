package az.restopia.menu.domain.response;

import az.restopia.commons.domain.enums.FileType;

public record GenerateFileResponse(
        String name,
        FileType fileType
) {
}
