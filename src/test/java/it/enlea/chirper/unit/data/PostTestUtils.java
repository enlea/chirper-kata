package it.enlea.chirper.unit.data;

import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import it.enlea.chirper.repository.model.Post;

public class PostTestUtils {

	
	public static SortedSet<Post> filterPostsByUserName(SortedSet<Post> list, String username) {
		return list.stream()
							.filter(p -> p.getUserName().compareTo(username)==0)
							.filter(Objects::nonNull)
							.collect(Collectors.toCollection(TreeSet::new));
	}


	
	
}
