package bookRental;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfirmService {
    @Autowired
    ConfirmRepository confirmRepository;

    // 예약에 대한  컨펌 정보 추가
    public Long confirmInsert(Confirm confirm) {
        try {
            Thread.currentThread().sleep((long) (400 + Math.random() * 220));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return confirmRepository.save(confirm).getConfirmId();

    }
}
