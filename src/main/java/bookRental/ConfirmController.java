package bookRental;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

 @RestController
 public class ConfirmController {

  @Autowired
  ConfirmService confirmService;

  //static final Long makePaymentSuccess = 1L;
  static final Long makeConfirmFail = 0L;

  // 결재 정보 생성  예약이 되었을때는 생성만 필요할듯
  @PostMapping("/confirms")
  public Long confirmInsert(@RequestBody Confirm confirm) {

   System.out.println("confirm.getBookingId :" + confirm.getBookingId());
   System.out.println("confirm.getConfirmId :" + confirm.getConfirmId());
   System.out.println("confirm.getConfirmStatus :" + confirm.getConfirmStatus());
   confirm.setConfirmStatus("cofirm succeeded");

   Long confirmId =  confirmService.confirmInsert(confirm);

   if (confirmId > 0) {
    return confirmId;     // 성공시 생성된 ID를 실패시 0 리턴
   } else {
    return makeConfirmFail;
   }
  }
 }
