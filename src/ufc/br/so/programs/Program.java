package ufc.br.so.programs;

import java.util.List;

import ufc.br.so.memory.Page;
import ufc.br.so.memory.PageList;

public abstract class Program {

	private String name;
	private int size;
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public abstract void execute();

	public List<Page> setBusyPages() {
		
		int p = (int) Math.ceil( ( (double) this.getSize() / (double) PageList.totalPageSize ) );
		
		List<Page> usedPages = PageList.requestPages(p);
		if(usedPages == null) System.err.println("OUT OF MEMORY!!");
		else{			
			System.out.println("Requesting " + p + " pages... ");
			for (Page pag : usedPages) {
				pag.setBusy(true);
			}
		}
		return usedPages;
	}

	public void freePages(List<Page> usedPages) {
		if(usedPages == null){ System.err.println("OUT OF MEMORY!!!"); return; }
		
		System.out.println("Freeing " + usedPages.size() + " pages... ");
		for (Page pag : usedPages) {
			pag.setBusy(false);
		}
	}

}
