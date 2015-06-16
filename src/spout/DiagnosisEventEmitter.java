package spout;

import model.DiagnosisEvent;
import storm.trident.operation.TridentCollector;
import storm.trident.spout.ITridentSpout.Emitter;
import storm.trident.topology.TransactionAttempt;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class DiagnosisEventEmitter implements Emitter<Long>, Serializable {
    private static final long serialVersionUID = 1L;
    AtomicInteger successfulTransactions = new AtomicInteger(0);

    public void emitBatch(TransactionAttempt tx, Long coordinatorMeta, TridentCollector collector) {
        for (int i = 0; i < 10000; i++) {
            List<Object> events = new ArrayList<Object>();
            double lat = new Double(-30 + (int) (Math.random() * 75));
            double lng = new Double(-120 + (int) (Math.random() * 70));
            long time = System.currentTimeMillis();
    		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
    		String str = dayTime.format(new Date(time));
            
            String diag = new Integer(320 + (int) (Math.random() * 7)).toString();
            DiagnosisEvent event = new DiagnosisEvent(lat, lng, str, diag);
            events.add(event);
            collector.emit(events);
        }
    }

    public void success(TransactionAttempt tx) {
        successfulTransactions.incrementAndGet();
    }

    public void close() {
    }

}
