package lv.kproject.teacher_timetable.core.requests.id;

public class CheckIdRequest {

    private Long id;

    public CheckIdRequest() {
    }

    public CheckIdRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
