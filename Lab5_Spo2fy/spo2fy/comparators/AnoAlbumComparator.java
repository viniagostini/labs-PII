package comparators;

import java.util.Comparator;

import spo2fy.Album;

public class AnoAlbumComparator implements Comparator<Album>{

	@Override
	public int compare(Album primeiroAlbum, Album segundoAlbum) {
		
		int anoPrimeiroAlbum = primeiroAlbum.getAno();
		int anoSegundoAlbum = segundoAlbum.getAno();
		
		return anoPrimeiroAlbum - anoSegundoAlbum;
	}
}
