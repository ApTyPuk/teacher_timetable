package lv.kproject.teacher_timetable.core.requests.teacher;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Objects;

public class TeacherUpdateRequest {

    @NotEmpty
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String lastName;
    private String phoneNumber;
    private String email;
    private BigDecimal hourRate = BigDecimal.ZERO;


    public TeacherUpdateRequest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getHourRate() {
        return hourRate;
    }

    public void setHourRate(BigDecimal hourRate) {
        this.hourRate = hourRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeacherUpdateRequest that = (TeacherUpdateRequest) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(email, that.email) &&
                Objects.equals(hourRate, that.hourRate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, phoneNumber, email, hourRate);
    }

    @Override
    public String toString() {
        return "TeacherUpdateRequest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", hourRate=" + hourRate +
                '}';
    }
}
