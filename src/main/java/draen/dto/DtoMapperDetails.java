package draen.dto;

import draen.domain.users.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@Component
public class DtoMapperDetails {

    public Long userMapper(User user) {
        return user.getId();
    }
}
