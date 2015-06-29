package rog.rochlitz.salmed.objects;

public class Artikel {
	
	public Artikel(int artikelId, String artikelname, String vk,
			String subartikel) {
		super();
		this.artikelId = artikelId;
		this.artikelname = artikelname;
		this.vk = vk;
		if(subartikel!=null){
			this.subartikel = stringToIntArray( subartikel.trim() );
		}
		
		
		
	}
	
	public Artikel(int artikelId, String artikelname, String vk
			 ) {
		super();
		this.artikelId = artikelId;
		this.artikelname = artikelname;
		this.vk = vk;
		
	}
	
	private int artikelId; //TODO int
	private String artikelname;
	private String vk;//verkaufspreis
	private int[] subartikel;
	
 
	public int getArtikelId() {
		return artikelId;
	}
	public void setArtikelId(int artikelId) {
		this.artikelId = artikelId;
	}
	public String getArtikelname() {
		return artikelname;
	}
	public void setArtikelname(String artikelname) {
		this.artikelname = artikelname;
	}
	public String getVk() {
		return vk;
	}
	public void setVk(String vk) {
		this.vk = vk;
	}
	public int[] getSubartikel() {
		return subartikel;
	}
	public void setSubartikel(int[] subartikel) {
		this.subartikel = subartikel;
	}
	
	public int[] stringToIntArray(String in){
		String[] numberStrs = in.split(";");
		int[] numbers = new int[numberStrs.length];
		for(int i = 0;i < numberStrs.length;i++)
		{
		   numbers[i] = Integer.parseInt(numberStrs[i]);
		}
		return numbers;
	}

}
