package ufc.br.so.memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ufc.br.so.storage.HardDisk;

public class PageList {
	
	public static int totalPageSize;
	
	private static HashMap<Integer,Page> pageList = new HashMap<Integer, Page>();
	
	public static void startPageList(int pageSize){
		totalPageSize = pageSize;
		int hdSize = HardDisk.getHardDiskSize();
		for (int i = 0; i <  hdSize/pageSize; i++) {
			pageList.put(i, new Page());
		}
		
	}

	/*
	 * Return a list of Free Pages
	 */
	public static List<Page> requestPages(int numPages){
		ArrayList<Page> freePages = new ArrayList<Page>();
		int count = 0;
		for (Integer integer : pageList.keySet()) {
			if(!pageList.get(integer).isBusy()){
				
				freePages.add(pageList.get(integer));
				count++;
			}
			if(count == numPages) break;
			
		}
		
		return freePages;
	}
	
	
	public static HashMap<Integer,Page> getPageList() {
		return pageList;
	}

	public static void setPageList(HashMap<Integer,Page> pageList) {
		PageList.pageList = pageList;
	}
	
	
}
