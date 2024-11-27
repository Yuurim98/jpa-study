package jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "MAJOR_TB")
public class Major {

    @Id
    @GeneratedValue
    private Long majorId;
    private String name;
    private String category;

    public Major(String name, String category) {
        this.name = name;
        this.category = category;
    }

}
