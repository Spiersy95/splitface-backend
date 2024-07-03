package com.splitface.tattoo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table
@Getter
@Setter
public class Style {

//    public enum StyleEnum {
//        REALISM,
//        WATERCOLOUR,
//        WILDCARD
//    }

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String styleName;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "style_tattoo_mapping", joinColumns = @JoinColumn(name = "style_id"),
            inverseJoinColumns = @JoinColumn(name = "tattoo_id"))
    @JsonBackReference
    private List<Tattoo> tattoos;

}
