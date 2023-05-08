package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GenerateIDProd {
	
	public String generateUniqueID() {
	    LocalDateTime now = LocalDateTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
	    return now.format(formatter);
	}

	
}
