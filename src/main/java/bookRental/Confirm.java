package bookRental;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Confirm_table")
public class Confirm {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long confirmId;
    private Long bookingId;
    private String confirmStatus;

    @PostPersist
    public void onPostPersist() {
        ConfirmSucceeded confirmSucceeded = new ConfirmSucceeded();
        BeanUtils.copyProperties(this, confirmSucceeded);
        confirmSucceeded.publishAfterCommit();
    }

    @PostUpdate
    public void onPostUpdate(){
        ConfirmCanceled confirmCanceled = new ConfirmCanceled();
        BeanUtils.copyProperties(this, confirmCanceled);
        confirmCanceled.publishAfterCommit();


    }


    public Long getConfirmId() {
        return confirmId;
    }

    public void setConfirmId(Long confirmId) {
        this.confirmId = confirmId;
    }
    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }
    public String getConfirmStatus() {
        return confirmStatus;
    }

    public void setConfirmStatus(String confirmStatus) {
        this.confirmStatus = confirmStatus;
    }




}
