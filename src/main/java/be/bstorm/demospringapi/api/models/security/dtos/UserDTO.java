package be.bstorm.demospringapi.api.models.security.dtos;

import be.bstorm.demospringapi.dl.entities.User;
import be.bstorm.demospringapi.dl.enums.UserRole;

public record UserDTO(
        Long id,
        UserRole role,
        String fullName,
        String email // Ajout de l'email pour correspondre au Frontend
) {
    public static UserDTO fromUser(User user) {
        String fullName = user.getLastName() + " " + user.getFirstName();
        return new UserDTO(user.getId(), user.getRole(), fullName, user.getEmail());
    }
}
