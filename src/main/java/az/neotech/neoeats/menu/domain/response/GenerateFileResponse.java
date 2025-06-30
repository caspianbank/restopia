package az.neotech.neoeats.menu.domain.response;

import az.neotech.neoeats.commons.domain.enums.FileType;

public record GenerateFileResponse(
        String name,
        FileType fileType
) {
}
