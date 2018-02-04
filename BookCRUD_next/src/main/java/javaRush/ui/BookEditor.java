package javaRush.ui;

import com.vaadin.ui.*;
import javaRush.repository.BookRepository;
import javaRush.data.Book;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.themes.ValoTheme;


@SpringComponent
@UIScope
public class BookEditor extends VerticalLayout {

    private final BookRepository repository;

    private Book book;

    TextField title = new TextField("Title");
    TextField description = new TextField("Description");
    TextField author = new TextField("Author");
    TextField isbn = new TextField("ISBN");
    TextField printYear = new TextField("Print year");
    CheckBox readAlready = new CheckBox("Read Already");


    Button save = new Button("Save", FontAwesome.SAVE);
    Button refresh = new Button("Refresh editor");
    Button delete = new Button("Delete", FontAwesome.TRASH_O);
    Button change = new Button("Change");
    CssLayout actions = new CssLayout(save, change, refresh, delete);

    HorizontalLayout layoutMain;
    VerticalLayout layoutAddNew;
    VerticalLayout changeBook;


    Binder<Book> binder = new Binder<>(Book.class);

    @Autowired
    public BookEditor(BookRepository repository) {
        this.repository = repository;

        layoutMain = new HorizontalLayout();
        layoutAddNew = new VerticalLayout();
        changeBook = new VerticalLayout();
        changeBook.addComponents(title, description, isbn, printYear);
        layoutAddNew.addComponents(author);
        layoutMain.addComponents(layoutAddNew, changeBook) ;
        layoutMain.setSpacing(false);

        addComponents(actions, readAlready, layoutMain);
        layoutAddNew.setVisible(false);
        changeBook.setVisible(false);
        layoutMain.setVisible(true);

        binder.bindInstanceFields(this);

        setSpacing(true);
        actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        save.addClickListener(e -> {
            if (!title.getValue().isEmpty()) {
                save.isEnabled();
                repository.save(book);
            } else {
                save.isDisableOnClick();
            }
        });
        delete.addClickListener(e -> repository.delete(book));
        refresh.addClickListener(e -> editBook(book));

        change.addClickListener(e -> {
            editBook(book);
            toolBarVisible(changeBook);
            readAlready.setValue(false);
        });

        setVisible(false);
    }

    private void toolBarVisible(VerticalLayout layout) {
        boolean visible = !layout.isVisible();
        layout.setVisible(visible);
    }

    public interface ChangeHandler {
        void onChange();
    }

    public final void editBook(Book c) {
        if (c == null) {
            setVisible(false);
            return;
        }
        final boolean persisted = c.getId() != null;
        if (persisted) {

            book = repository.findOne(c.getId());
        }
        else {
            book = c;
        }
        if (book.getReadAlready()) {
            readAlready.setVisible(false);
        } else {
            readAlready.setVisible(true);
        }
        if (book.getAuthor().equals("")) {
            layoutAddNew.setVisible(true);
        } else {
            layoutAddNew.setVisible(false);
        }
        refresh.setVisible(persisted);
        binder.setBean(book);
        setVisible(true);
        save.focus();
        title.selectAll();
    }

    public void setChangeHandler(ChangeHandler h) {
        save.addClickListener(e -> h.onChange());
        delete.addClickListener(e -> h.onChange());
    }
}
