package javaRush;

import javaRush.data.Book;
import javaRush.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:application.properties")
@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner loadData(BookRepository repository) {
        return (args) -> {
            repository.save(new Book("Jack", "Bauer", "Author", "1234567890000", 1900));
            repository.save(new Book("Jack1", "Bauer1", "Author1", "1234567890001", 1901));
            repository.save(new Book("Jack2", "Bauer2", "Author2", "1234567890002", 1902));
            repository.save(new Book("Jack3", "Bauer3", "Author3", "1234567890003", 1903));
            repository.save(new Book("Jack4", "Bauer4", "Author4", "1234567890004", 1904));
            repository.save(new Book("Jack5", "Bauer5", "Author5", "1234567890005", 1905));
            repository.save(new Book("Jack6", "Bauer6", "Author6", "1234567890006", 1906));
            repository.save(new Book("Jack7", "Bauer7", "Author7", "1234567890007", 1907));
            repository.save(new Book("Jack8", "Bauer8", "Author8", "1234567890008", 1908));
            repository.save(new Book("Jack9", "Bauer9", "Author9", "1234567890009", 1909));
            repository.save(new Book("Jack10", "Bauer10", "Author10", "1234567890010", 1910));
            repository.save(new Book("Jack11", "Bauer11", "Author11", "1234567890011", 1911));
            repository.save(new Book("Jack12", "Bauer12", "Author12", "1234567890012", 1912));
            repository.save(new Book("Jack13", "Bauer13", "Author13", "1234567890013", 1913));
            repository.save(new Book("Jack14", "Bauer14", "Author14", "1234567890014", 1914));
            repository.save(new Book("Jack15", "Bauer15", "Author15", "1234567890015", 1915));
            repository.save(new Book("Jack16", "Bauer16", "Author16", "1234567890016", 1916));
            repository.save(new Book("Jack17", "Bauer17", "Author17", "1234567890017", 1917));
            repository.save(new Book("Jack18", "Bauer18", "Author18", "1234567890018", 1918));
            repository.save(new Book("Jack19", "Bauer19", "Author19", "1234567890019", 1919));
            repository.save(new Book("Jack20", "Bauer20", "Author20", "1234567890020", 1920));
            repository.save(new Book("Jack21", "Bauer21", "Author21", "1234567890021", 1921));
            repository.save(new Book("Jack22", "Bauer22", "Author22", "1234567890022", 1922));
            repository.save(new Book("Jack23", "Bauer23", "Author23", "1234567890023", 1923));
            repository.save(new Book("Jack24", "Bauer24", "Author24", "1234567890024", 1924));
            repository.save(new Book("Jack25", "Bauer25", "Author25", "1234567890025", 1925));
            repository.save(new Book("Jack26", "Bauer26", "Author26", "1234567890026", 1926));
            repository.save(new Book("Jack27", "Bauer27", "Author27", "1234567890027", 1927));
            repository.save(new Book("Jack28", "Bauer28", "Author28", "1234567890028", 1928));
            repository.save(new Book("Jack29", "Bauer29", "Author29", "1234567890029", 1929));
            repository.save(new Book("Jack30", "Bauer30", "Author30", "1234567890030", 1930));
            repository.save(new Book("Jack31", "Bauer31", "Author31", "1234567890031", 1931));
            repository.save(new Book("Jack32", "Bauer32", "Author32", "1234567890032", 1932));
            repository.save(new Book("Jack33", "Bauer33", "Author33", "1234567890033", 1933));
            repository.save(new Book("Jack34", "Bauer34", "Author34", "1234567890034", 1934));
            repository.save(new Book("Jack35", "Bauer35", "Author35", "1234567890035", 1935));


            log.info("Books found with findAll():");
            log.info("-------------------------------");
            for (Book book : repository.findAll()) {
                log.info(book.toString());
            }
            log.info("");

            Book book = repository.findOne(1L);
            log.info("Book found with findOne(1L):");
            log.info("--------------------------------");
            log.info(book.toString());
            log.info("");

            log.info("Book found with findByLastNameStartsWithIgnoreCase('Bauer'):");
            log.info("--------------------------------------------");
            for (Book bauer : repository
                    .findByTitleStartsWithIgnoreCase("Bauer")) {
                log.info(bauer.toString());
            }
            log.info("");
        };
    }

}