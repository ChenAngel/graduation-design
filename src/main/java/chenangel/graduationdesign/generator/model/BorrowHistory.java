package chenangel.graduationdesign.generator.model;

public class BorrowHistory {
    private Integer readers_id;
    private String bookname;
    private String addtime;
    private String returntime;
    private Integer books_id;

    public Integer getReaders_id() {
        return readers_id;
    }

    public void setReaders_id(Integer readers_id) {
        this.readers_id = readers_id;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getReturntime() {
        return returntime;
    }

    public void setReturntime(String returntime) {
        this.returntime = returntime;
    }

    public Integer getBooks_id() {
        return books_id;
    }

    public void setBooks_id(Integer books_id) {
        this.books_id = books_id;
    }
}
