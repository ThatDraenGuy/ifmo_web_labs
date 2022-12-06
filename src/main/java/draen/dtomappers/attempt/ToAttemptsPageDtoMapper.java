package draen.dtomappers.attempt;

import draen.domain.users.UserAttempt;
import draen.dto.attempt.AttemptsPageDto;
import draen.dto.attempt.UserAttemptDto;
import draen.dtomappers.ToDtoMapper;
import draen.storage.PageOfUserAttemptInfo;
import lombok.Setter;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper (
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = ToUserAttemptDtoMapper.class,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
@Component
public abstract class ToAttemptsPageDtoMapper implements ToDtoMapper<PageOfUserAttemptInfo, AttemptsPageDto> {
    @Autowired
    @Setter
    private ToUserAttemptDtoMapper attemptInfoDtoMapper;
    @Override
    @Mapping(target = "totalLength", source = "page")
    @Mapping(target = "pagesAmount", source = "page")
    @Mapping(target = "attempts", source = "page")
    public abstract AttemptsPageDto toDto(PageOfUserAttemptInfo item);

    public long mapTotalLength(Page<UserAttempt> page) {
        return page.getTotalElements();
    }
    public int mapPagesAmount(Page<UserAttempt> page) {
        return page.getTotalPages();
    }
    public List<UserAttemptDto> mapAttempts(Page<UserAttempt> page) {
        return page.stream().map(attemptInfoDtoMapper::toDto).toList();
    }
}
