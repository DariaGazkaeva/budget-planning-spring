package ru.kpfu.itis.dariagazkaeva.budgetplanning.entities;

import javax.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@Entity
@Table(name = "user_role")
public class UserAuthority implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Length(min = 2, max = 30)
    @Column(length = 30, nullable = false, unique = true)
    private String authority;

    @Override
    public String toString(){
        return authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
