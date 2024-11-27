package jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "STUDENT_TB")
public class Student {

    @Id
    @GeneratedValue
    private Long studentId;
    private String name;
    private String grade;

    @ManyToOne(fetch = FetchType.LAZY)// 관계구성
    @JoinColumn(name = "MAJORID") // 외래키 지정
    private Major major;

    public Student(String name, String grade) {
        this.name = name;
        this.grade = grade;
    }

}
