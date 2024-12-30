package com.rige.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
public class RoleEntity implements GrantedAuthority{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<UserEntity> users;

    @Override
    public String getAuthority() {
        return name;
    }
}
