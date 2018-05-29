package chenangel.graduationdesign.generator.model;

public class Book {
    private Integer id;
    private String bookname;
    private String type;
    private String writer;
    private String press;
    private String pressdate;
    private String remark;
    private Double price;
    private String isbn;
    private String location;
    private Integer borrowacount;
    private Integer nowaccount;
    private String addtime;
    private Integer totalaccount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public String getPressdate() {
        return pressdate;
    }

    public void setPressdate(String pressdate) {
        this.pressdate = pressdate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getBorrowacount() {
        return borrowacount;
    }

    public void setBorrowacount(Integer borrowacount) {
        this.borrowacount = borrowacount;
    }

    public Integer getNowaccount() {
        return nowaccount;
    }

    public void setNowaccount(Integer nowaccount) {
        this.nowaccount = nowaccount;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public Integer getTotalaccount() {
        return totalaccount;
    }

    public void setTotalaccount(Integer totalaccount) {
        this.totalaccount = totalaccount;
    }
}
