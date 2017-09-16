package com.packtpub.felix.com.packtpub.felix.bookshelf_inventory_api1;

public interface MutableBook extends Book {
	void setIsbn(String isbn);
	void setTitle(String title);
	void setAuthor(String author);
	void setCategory(String category);
	void setRating(String rating);
}
