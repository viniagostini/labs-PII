package comparators;

import java.util.Comparator;

import spo2fy.Album;

public class TituloAlbumComparator implements Comparator<Album>{

	@Override
	public int compare(Album album1, Album album2) {
		
		String titulo1 = album1.getTitulo();
		String titulo2 = album2.getTitulo();
		
		return titulo1.compareTo(titulo2);
	}

}
