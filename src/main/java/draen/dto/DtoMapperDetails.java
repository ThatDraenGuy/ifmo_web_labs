package draen.dto;

import draen.domain.users.User;
import draen.domain.users.UserRepository;
import lombok.AllArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@Component
public class DtoMapperDetails {

    public Long userMapper(User user) {
        return user.getId();
    }
}
