package operator;

import model.DiagnosisEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import storm.trident.operation.BaseFunction;
import storm.trident.operation.TridentCollector;
import storm.trident.tuple.TridentTuple;

import java.util.ArrayList;
import java.util.List;

public class HourAssignment extends BaseFunction {
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = LoggerFactory.getLogger(HourAssignment.class);

    public void execute(TridentTuple tuple, TridentCollector collector) {
        DiagnosisEvent diagnosis = (DiagnosisEvent) tuple.getValue(0);
        String city = (String) tuple.getValue(1);
        LOG.debug("Key =  [" + city + ":" + diagnosis.time + "]");
        String key = city + ":" + diagnosis.diagnosisCode + ":" + diagnosis.time;

        List<Object> values = new ArrayList<Object>();
        values.add(diagnosis.time);
        values.add(key);
        collector.emit(values);
    }
}
