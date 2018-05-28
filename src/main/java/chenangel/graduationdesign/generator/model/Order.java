package chenangel.graduationdesign.generator.model;

public class Order {
    private Integer id;
    private String order_uuid;
    private Integer order_rid;
    private Integer order_aid;
    private String status;
    private String addtime;
    private String bookname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrder_uuid() {
        return order_uuid;
    }

    public void setOrder_uuid(String order_uuid) {
        this.order_uuid = order_uuid;
    }

    public Integer getOrder_rid() {
        return order_rid;
    }

    public void setOrder_rid(Integer order_rid) {
        this.order_rid = order_rid;
    }

    public Integer getOrder_aid() {
        return order_aid;
    }

    public void setOrder_aid(Integer order_aid) {
        this.order_aid = order_aid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }
}
