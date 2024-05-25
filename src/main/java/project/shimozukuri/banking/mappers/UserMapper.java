package project.shimozukuri.banking.mappers;

import org.mapstruct.Mapper;
import project.shimozukuri.banking.dtos.user.UserDto;
import project.shimozukuri.banking.entities.user.User;

@Mapper(componentModel = "spring")
public interface UserMapper extends Mappable<User, UserDto> {
}
