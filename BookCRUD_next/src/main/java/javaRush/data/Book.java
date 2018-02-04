package javaRush.data;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Book {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id", unique = true)
    private Long id;
    @Column(name = "title", unique = true, length = 100, nullable = false)
    private String title;
    @Column(name = "description", length = 255)
    private String description;
    @Column(name = "author", length = 100, nullable = false)
    private String author;
    @Column(name = "isbn", length = 20)
    private String isbn;
    @Column(name = "printYear")
    private Integer printYear;
    @Column(name = "readAlready")
    private Boolean readAlready = new Boolean(false);


    protected Book() {
    }

    public Book(String title, String description, String author, String isbn, Integer printYear) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.isbn = isbn;
        this.printYear = printYear;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
            this.title = title;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        if (this.author == null) {
            return "";
        }
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPrintYear() {
        if (printYear == null) {
            return "";
        }
        return String.valueOf(printYear);
    }

    public void setPrintYear(String printYear) {
        this.printYear = Integer.parseInt(printYear);
    }

    public Boolean getReadAlready() {
        return readAlready;
    }

    public void setReadAlready(Boolean readAlready) {
        this.readAlready = readAlready;
    }

    @Override
    public String toString() {
        return String.format("Book [id=%d, title = '%s', description = '%s', author = '%s', printYear = '%d']", id,
                title, description, author, printYear);
    }

}
