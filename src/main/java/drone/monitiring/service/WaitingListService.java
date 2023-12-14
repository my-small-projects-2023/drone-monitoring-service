package drone.monitiring.service;

import java.util.Map;

public interface WaitingListService {

	void setToWaitingList(Map<Long, Integer> medications);
	
	void executeFromList();
	
}
