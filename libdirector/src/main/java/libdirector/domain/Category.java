package libdirector.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


@Table(name = "tbl_categories")
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(nullable = false, length = 80)
    private String name;


    @Column(nullable = false)
    private Boolean builtIn=false;



    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer sequence;


    @OneToMany(mappedBy = "bookCategory")
    private List<Book> categoryBooks=new ArrayList<>();

}