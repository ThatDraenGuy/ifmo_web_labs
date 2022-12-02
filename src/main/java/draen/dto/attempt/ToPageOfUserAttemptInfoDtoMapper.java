package draen.dto.attempt;

import draen.domain.users.UserAttemptInfo;
import draen.dto.ToDtoMapper;
import draen.storage.PageOfUserAttemptInfo;
import lombok.AllArgsConstructor;
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
        uses = ToUserAttemptInfoDtoMapper.class,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
@Component
public abstract class ToPageOfUserAttemptInfoDtoMapper implements ToDtoMapper<PageOfUserAttemptInfo, PageOfUserAttemptInfoDto> {
    @Autowired
    @Setter
    private ToUserAttemptInfoDtoMapper attemptInfoDtoMapper;
    @Override
    @Mapping(target = "totalLength", source = "page")
    @Mapping(target = "pagesAmount", source = "page")
    @Mapping(target = "attempts", source = "page")
    public abstract PageOfUserAttemptInfoDto toDto(PageOfUserAttemptInfo item);

    public long mapTotalLength(Page<UserAttemptInfo> page) {
        return page.getTotalElements();
    }
    public int mapPagesAmount(Page<UserAttemptInfo> page) {
        return page.getTotalPages();
    }
    public List<UserAttemptInfoDto> mapAttempts(Page<UserAttemptInfo> page) {
        return page.stream().map(attemptInfoDtoMapper::toDto).toList();
    }
}
