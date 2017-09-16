package com.packtpub.felix.com.packtpub.felix.bookshelf_inventory_api1;

import java.util.Map;
import java.util.Set;

import Exceptions.BookAlreadyExistsException;
import Exceptions.BookNotFoundException;
import Exceptions.InvalidBookException;

public interface BookInventory {

	enum SearchCriteria
	{
		ISBN_LIKE,
		TITLE_LIKE,
		AUTHOR_LIKE,
		GROUP_LIKE,
		GRADE_GT,
		GRADE_LT
	}
	
	Set<String> getCategories();
	
	MutableBook createBook(String isbn) throws BookAlreadyExistsException;
	
	MutableBook loadBookForEdit(String isbn) throws BookNotFoundException;
	
	String storeBook(MutableBook book) throws InvalidBookException;
	
	Book loadBook(String isbn) throws BookNotFoundException;
	
	void removeBook(String isbn) throws BookNotFoundException;
	
	Set<String> searchBooks(Map<SearchCriteria, String> criteria);
}
