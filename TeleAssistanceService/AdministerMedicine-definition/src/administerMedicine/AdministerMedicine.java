package administerMedicine;

import java.util.HashMap;

public interface AdministerMedicine {
	public HashMap<String, HashMap<String, Integer>> admMedicine(int analysisResult, String Strategy);
}
