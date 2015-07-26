package uk.vulcannetworks.phub.developers;

import uk.vulcannetworks.phub.VulcanCore;

public class PHubAPI {

	/** Not ready for usage **/
	
	private static VulcanCore vc;
	
	public PHubAPI(VulcanCore c){
		PHubAPI.vc = c;
	}
	
	public static String getPrefix(){
		return vc.getPrefix();
	}
	
	public static String getBeforeNameVariable(){
		return vc.getBeforeName();
	}
	
	public static String getAfterNameVariable(){
		return vc.getAfterName();
	}
	
}
