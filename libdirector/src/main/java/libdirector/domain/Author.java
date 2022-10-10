package libdirector.domain;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_authors")
public class Author {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Boolean builtIn=false;

    @OneToMany(mappedBy = "bookAuthor")
    private List<Book> authorBooks=new ArrayList<>();

}