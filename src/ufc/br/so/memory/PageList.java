package ufc.br.so.memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ufc.br.so.storage.HardDisk;
import ufc.br.so.storage.RAM;

public class PageList {
	
	public static int totalPageSize;
	
	private static HashMap<Integer,Page> pageListRAM = new HashMap<Integer, Page>();
	private static HashMap<Integer,Page> pageListHD = new HashMap<Integer, Page>();
	
	public static void startPageList(int pageSize){
		totalPageSize = pageSize;
		
		int ramSize = RAM.getRamSize();
		int hdSize = HardDisk.getHardDiskSize();
		/*
		 * RAM Page List
		 */
		for (int i = 0; i <   (int)Math.ceil((double)ramSize/(double)pageSize); i++) {
			pageListRAM.put(i, new Page());
		}
		
		/*
		 * Hard Disk Page List 
		 */
		for (int i = 0; i <  (int)Math.ceil((double)hdSize/(double)pageSize); i++) {
			pageListHD.put(i, new Page());
		}
		
	}

	/*
	 * Returns a list of Free Pages, these pages are from
	 * RAM or HD. 
	 */
	public static List<Page> requestPages(int numPages){
		ArrayList<Page> freePages = new ArrayList<Page>();
		int count = 0;
		
		
		int pHD = HardDisk.getHardDiskSize()/totalPageSize;
		int pRAM = RAM.getRamSize()/totalPageSize;
		int usedRAM = Math.abs(pRAM - numPages);
		
		if(numPages <= pRAM ){
			
			System.out.println("Allocating in the RAM");
			count = 0;
			for (Integer integer : pageListRAM.keySet()) {
				if(!pageListRAM.get(integer).isBusy()){
					freePages.add(pageListRAM.get(integer));
					count++;
				}
				if(count == numPages) break;
				
			}
			
		}else{
			//Use HD Pages
			if( ( (numPages - pRAM) <= pHD  ) ){
				//Allocating in the Memory
				System.out.println("Allocating in the RAM");
				System.out.println("Allocating in the RAM "+(pRAM)+" pages" );
				
				for (Integer integer : pageListRAM.keySet()) {
					if(!pageListRAM.get(integer).isBusy()){
						freePages.add(pageListRAM.get(integer));
						count++;
					}
					if(count == pRAM) break;
					
				}
				
				
				//Allocating in the HD
				System.out.println("Allocating in the HD "+(numPages - usedRAM)+" pages" );
				for (Integer integer : pageListHD.keySet()) {
					if(!pageListHD.get(integer).isBusy()){
						freePages.add(pageListHD.get(integer));
						count++;
					}
					if(count == pHD + pRAM) break;
					
				}
			}
			//Not enough pages available
			if(( numPages > pHD + pRAM  ) ){
				System.out.println("Not enough pages available!");
				return null;
			}
		}
		
		return freePages;
	}
	
	
	public static HashMap<Integer,Page> getPageList() {
		return pageListRAM;
	}

	public static void setPageList(HashMap<Integer,Page> pageList) {
		PageList.pageListRAM = pageList;
	}
	
	
}
