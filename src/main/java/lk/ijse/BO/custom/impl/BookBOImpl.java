package lk.ijse.BO.custom.impl;

import lk.ijse.BO.custom.BookBO;
import lk.ijse.DTO.BookDTO;

import java.util.List;

public class BookBOImpl implements BookBO {
    @Override
    public List<BookDTO> getAll() {
        return null;
    }

    @Override
    public boolean saveBook(BookDTO bookDTO) {
        return false;
    }

    @Override
    public boolean updateBook(BookDTO bookDTO) {
        return false;
    }

    @Override
    public BookDTO getBook(String bookId) {
        return null;
    }

    @Override
    public boolean deleteBook(String bookId) {
        return false;
    }

    @Override
    public String getNextId() {
        return null;
    }
}
