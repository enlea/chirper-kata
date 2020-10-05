package it.enlea.chirper.logic;

import java.util.SortedSet;

import it.enlea.chirper.repository.model.Post;

public interface ResponseFormatter {

	String formatReadPostList(SortedSet<Post> postList);

	String formatWallPostList(SortedSet<Post> postList);

}