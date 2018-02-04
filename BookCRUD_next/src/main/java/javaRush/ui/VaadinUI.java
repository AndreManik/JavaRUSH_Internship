package javaRush.ui;

import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import javaRush.repository.BookRepository;
import javaRush.data.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringUI;

import java.util.ArrayList;
import java.util.List;

@SpringUI
public class VaadinUI extends UI {

    private final BookRepository repo;

    private final BookEditor editor;

    final Grid<Book> grid;

    final TextField filter;

    private final Button addNewBtn;



    private Button previewBtn = new Button("< Preview");
    private Button nextBtn = new Button("Next >");
    private ArrayList<Book> bookList = new ArrayList<>();
    private Button refreshTable = new Button("Refresh Table");
    private int iter = 0;
    private HorizontalLayout paging = new HorizontalLayout();
    private Boolean filtered = false;


    @Autowired
    public VaadinUI(BookRepository repo, BookEditor editor) {
        this.repo = repo;
        this.editor = editor;
        this.grid = new Grid<>(Book.class);
        this.filter = new TextField();
        this.addNewBtn = new Button("New Book", FontAwesome.PLUS);
        this.paging.addComponents(previewBtn, nextBtn);



    }

    @Override
    protected void init(VaadinRequest request) {

        HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn, refreshTable);
        VerticalLayout mainLayout = new VerticalLayout(actions, grid, paging, editor);
        setContent(mainLayout);

        grid.setHeight(420, Unit.PIXELS);
        grid.setWidth(800, Unit.PIXELS);
        grid.setColumns("id", "title", "description", "author", "isbn", "printYear", "readAlready");

        refreshTable.setStyleName(ValoTheme.BUTTON_PRIMARY);

        filter.setPlaceholder("Filter by title");


        filter.setValueChangeMode(ValueChangeMode.LAZY);
        filter.addValueChangeListener(e -> {
            filtered = true;
            listBooks(e.getValue());});


        grid.asSingleSelect().addValueChangeListener(e -> {
            editor.changeBook.setVisible(false);
            editor.editBook(e.getValue());
        });


        addNewBtn.addClickListener(e -> {
            editor.changeBook.setVisible(true);
            editor.editBook(new Book("", "", "", "", null));

        });


        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            listBooks(filter.getValue());
        });

        refreshTable.addClickListener(e ->{
                bookList = returnList(iter, repo);
                listBooks(null);});

        previewBtn.addClickListener(e -> {
            --iter;
            if (iter < 0) iter = 0;
            bookList = returnList(iter, repo);
            listBooks(null);
        });

        nextBtn.addClickListener(e -> {
            ++iter;
            if (iter > repo.findAll().size()/10) --iter;
            bookList = returnList(iter, repo);
            listBooks(null);
        });
        // Initialize listing
        listBooks(null);
    }

    // tag::listBooks[]
    public void listBooks(String filterText) {
        if (StringUtils.isEmpty(filterText)) {
            grid.setItems(getBookList());
            filtered = false;
        }
        else {
            filtered = true;
            grid.setItems(returnList(0, repo));
        }
    }
    // end::listBooks[]



    private ArrayList<Book> returnList(int pageNumber, BookRepository repository) {


        ArrayList<Book> list = new ArrayList<>();
        List<Book> temp;
        if (filtered) {
            temp = repository.findByTitleStartsWithIgnoreCase(filter.getValue());
        } else {
            temp = repository.findAll();
        }
        list.clear();

        if (pageNumber < 0) pageNumber = 0;

        if (pageNumber == (temp.size()/10)) {
            for (int i = (pageNumber * 10); i < temp.size(); i++) {
                list.add(temp.get(i));
            }
        } else if (pageNumber < (temp.size()/10) && pageNumber != 0) {
            for (int i = (pageNumber * 10); i < (pageNumber * 10) + 10; i++) {
                list.add(temp.get(i));
            }
        } else if (pageNumber == 0) {
            for (int i = 0; i < 10; i++) {
                list.add(temp.get(i));
            }
        }
        return list;
    }

    public ArrayList<Book> getBookList() {
        if (bookList.isEmpty()) {
            return returnList(0, repo);
        }
        return bookList;
    }

}