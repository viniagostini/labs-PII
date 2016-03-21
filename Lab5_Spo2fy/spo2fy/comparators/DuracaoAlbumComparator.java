package comparators;

import java.util.Comparator;

import spo2fy.Album;

public class DuracaoAlbumComparator implements Comparator<Album>{

	@Override
	public int compare(Album album1, Album album2) {

		int duracao1 = album1.getDuracaoTotal();
		int duracao2 = album2.getDuracaoTotal();
		
		return duracao1 - duracao2;
	}	
}
