package lk.ijse.BO.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.BO.custom.BookBO;
import lk.ijse.DAO.DAOFactory;
import lk.ijse.DAO.custom.BookDAO;
import lk.ijse.DTO.BookDTO;
import lk.ijse.Entity.Book;

import java.util.ArrayList;
import java.util.List;

public class BookBOImpl implements BookBO {
    BookDAO bookDAO = (BookDAO) DAOFactory.getDaofactory().getDAO(DAOFactory.DAOTypes.BOOK);

    @Override
    public List<BookDTO> getAll() {
        List<BookDTO> bookDTOS = new ArrayList<>();
        List<Book> books = bookDAO.getAll();
        if (books != null) {
            for (Book book : books) {
                bookDTOS.add(new BookDTO(
                        book.getId(),
                        book.getTitle(),
                        book.getAuthor(),
                        book.getGenre()
                ));
            }
        }
        return bookDTOS;
    }

    @Override
    public boolean saveBook(BookDTO bookDTO) {
        String status = "Available";
        return bookDAO.save(new Book(
                bookDTO.getId(),
                bookDTO.getTitle(),
                bookDTO.getAuthor(),
                bookDTO.getGenre(),
                status
        ));
    }

    @Override
    public boolean updateBook(BookDTO bookDTO) {
        return bookDAO.update(new Book(
                bookDTO.getId(),
                bookDTO.getTitle(),
                bookDTO.getAuthor(),
                bookDTO.getGenre()
        ));
    }

    @Override
    public BookDTO getBook(String bookId) {
        Book book = bookDAO.getItem(bookId);
            if (book!=null) {
                return new BookDTO(book.getId(),book.getTitle(),book.getAuthor(),book.getGenre());
            } else {
                new Alert(Alert.AlertType.ERROR).show();
            }
            return null;
    }

    @Override
    public boolean deleteBook(String bookId) {
        return bookDAO.delete(bookId);
    }

    @Override
    public String getNextId() {
        String id = bookDAO.getNextId();
        Integer newId = Integer.parseInt(id.replace("B","")) + 1;
        return String.format("B%03d",newId);
    }
}
