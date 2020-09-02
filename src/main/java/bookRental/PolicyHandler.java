package bookRental;

import bookRental.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{

    @Autowired
    ConfirmRepository confirmRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverUnBooked_CancelConfirm(@Payload UnBooked unBooked){

        if(unBooked.isMe()){
            System.out.println("##### listener CancelConfirm : " + unBooked.toJson());


            Long bookingId = unBooked.getBookingId();
            System.out.println("##### unBooked.getBookingId : " + unBooked.getBookingId());

            //Payment payment = new Payment();
            //unbooked.setBookingStatus("canceled");  // bookingStatus 상태 변경은 booking policyHandler에서

            // Correlation id 는 'bookingId'
            confirmRepository.findById(Long.valueOf(unBooked.getBookingId())).ifPresent((confirm)->{
                confirm.setConfirmStatus("confirm canceled!!!");
                confirmRepository.save(confirm);
            });
           }
    }
}


