package comparators;

import java.util.Comparator;
import spo2fy.Album;

public class ArtistaAlbumComparator implements Comparator<Album>{

	@Override
	public int compare(Album album1, Album album2) {

		String artista1 = album1.getArtista();
		String artista2 = album2.getArtista();
		
		return artista1.compareTo(artista2);
	}
}
