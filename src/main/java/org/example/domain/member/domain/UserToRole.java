package org.example.domain.member.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.domain.model.BaseEntity;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"user_id", "role_id"}
                )
        }
)
public class UserToRole extends BaseEntity {
    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private User user;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Role role;

    public static UserToRole relate(User user, Role role) {
        UserToRole userToRole = new UserToRole();
        userToRole.setUser(user);
        userToRole.setRole(role);
        return userToRole;
    }

    public void setUser(User user) {
        if (this.user != null) {
            this.user.getUserToRoles().remove(this);
        }
        this.user = user;
        this.user.getUserToRoles().add(this);
    }

    public void setRole(Role role) {
        if (this.role != null) {
            this.role.getUserToRoles().remove(this);
        }
        this.role = role;
        this.role.getUserToRoles().add(this);
    }

    public String getRoleName() {
        return this.role.getValue().name();
    }
}