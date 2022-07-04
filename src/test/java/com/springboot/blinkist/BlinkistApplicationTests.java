package com.springboot.blinkist;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

import com.springboot.blinkist.converter.AuthorConverter;
import com.springboot.blinkist.converter.BookConverter;
import com.springboot.blinkist.converter.LibraryConverter;
import com.springboot.blinkist.dao.*;
import com.springboot.blinkist.dto.AuthorDTO;
import com.springboot.blinkist.dto.BookDTO;
import com.springboot.blinkist.dto.LibraryDTO;
import com.springboot.blinkist.dto.LibraryDetailedDTO;
import com.springboot.blinkist.entity.*;
import com.springboot.blinkist.exception.NotFoundException;
import com.springboot.blinkist.service.AuthorService;


import com.springboot.blinkist.service.BookService;
import com.springboot.blinkist.service.CategoryService;
import com.springboot.blinkist.service.LibraryService;
import org.junit.jupiter.api.Test;

import org.mockito.MockedStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest

class BlinkistApplicationTests {


	@Autowired
	AuthorService authorService;

	@Autowired
	BookService bookService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	LibraryService libraryService;

	@MockBean
	StatusDAO statusDAO;

	@MockBean
	AuthorDAO authorDAO;

	@MockBean
	BookDAO bookDAO;
	@MockBean
	CategoryDAO categoryDAO;
	@MockBean
	LibraryDAO libraryDAO;

	@Test
	 void testAuthorFindAll(){

		when(authorDAO.findAll("1")).thenReturn(Stream.of(new Author(1,"john","k.carles")).collect(Collectors.toList()));
		authorService.findAll("1");
		assertEquals(1,authorService.findAll("1").size());

	}

	@Test
	 void testAuthorFindById(){

		Author author = new Author(1,"john","k.carles");
		int id=1;
		when(authorDAO.findById(id)).thenReturn(author);
		authorService.findById(id);
		verify(authorDAO).findById(id);
	}

	@Test()
	 void testAuthorNotFoundId(){
		when(authorDAO.findById(2)).thenThrow(new NotFoundException("not found"));
		boolean isTrue=false;
		try{
			authorService.findById(2);
		}
		catch (NotFoundException e)
		{
				isTrue =true;
		}

		assertTrue(isTrue);
	}

	@Test
	 void testAuthorSave(){

		MockedStatic<AuthorConverter> converter = mockStatic(AuthorConverter.class);
		AuthorDTO authorDTO = new AuthorDTO(1,"john","k.carles");
		Author author = new Author(1,"john","k.carles");
		converter.when(()->AuthorConverter.authorDTOtoEntity(authorDTO)).thenReturn(author);
		when(authorDAO.save(author)).thenReturn(author);
		authorService.save(authorDTO);
		verify(authorDAO).save(author);
		converter.close();
	}

	@Test
	 void testAuthorDeleted(){

		int id=1;
		doNothing().when(authorDAO).deleteById(id);
		authorService.deleteById(id);
		verify(authorDAO,atLeastOnce()).deleteById(id);
	}

	@Test
	void testBookFindAll(){
		Author author = new Author(1,"john","k.carles");
		Category category = new Category(1,"motivation");
		when(bookDAO.findAll("1","john","motivation")).thenReturn(Stream.of(new Book(1,"title",author,"https://image",category)).collect(Collectors.toList()));
		bookService.findByQuery("1","john","motivation");
		assertEquals(0,bookService.findByQuery("1","john","motivation").size());

	}
	@Test
	void testBookFindById(){

		Author author = new Author(1,"john","k.carles");
		Category category = new Category(1,"motivation");
		Book book = new Book(1,"title",author,"https://image",category);
		int id=1;
		when(bookDAO.findById(id)).thenReturn(book);
		bookService.findById(id);
		verify(bookDAO).findById(id);
	}

	@Test()
	void testBookNotFoundId(){
		when(bookDAO.findById(2)).thenThrow(new NotFoundException("not found"));
		boolean isTrue=false;
		try{
			bookService.findById(2);
		}
		catch (NotFoundException e)
		{
			isTrue =true;
		}

		assertTrue(isTrue);
	}

	@Test
	void testBookSave(){

		MockedStatic<BookConverter> converter = mockStatic(BookConverter.class);
		BookDTO bookDTO = new BookDTO(1,"motivation",1,1,"https:/image");
		Author author = new Author(1,"john","k.carles");
		Category category = new Category(1,"motivation");
		Book book = new Book(1,"title",author,"https://image",category);
		converter.when(()->BookConverter.bookDTOToBook(bookDTO)).thenReturn(book);
		when(bookDAO.save(book)).thenReturn(book);
		when(authorDAO.findById(1)).thenReturn(author);
		when(categoryDAO.findById(1)).thenReturn(category);
		bookService.save(bookDTO);
		verify(bookDAO).save(book);

	}

	@Test
	void testBookDeleted(){

		int id=1;
		doNothing().when(bookDAO).deleteById(id);
		bookService.deleteById(id);
		verify(bookDAO,atLeastOnce()).deleteById(id);
	}


	@Test
	void testCategoryFindAll(){

		when(categoryDAO.findAll()).thenReturn(Stream.of(new Category(1,"motivation")).collect(Collectors.toList()));

		assertEquals(1,categoryService.findAll().size());

	}

	@Test
	void testLibraryFindAll(){
		MockedStatic<LibraryConverter> converter = mockStatic(LibraryConverter.class);

		Author author = new Author(1,"john","k.carles");
		Category category = new Category(1,"motivation");
		Book book = new Book(1,"title",author,"https://image",category);
		Status status = new Status(1,"reading");
		Library library = new Library(1,"john",book,status);
		List<Library> libraries = Stream.of(library).collect(Collectors.toList());
		when(libraryDAO.findAll()).thenReturn(libraries);
		converter.when(()->LibraryConverter.entityToLibraryDetailedDTO(libraries)).thenReturn(Stream.of(new LibraryDetailedDTO(1,"john","title","john","motivation","reading")).collect(Collectors.toList()));

		assertEquals(1,libraryService.findByQuery("reading").size());
		converter.close();
	}

	@Test
	void testLibrarySave(){

		MockedStatic<LibraryConverter> converter = mockStatic(LibraryConverter.class);
		LibraryDTO libraryDTO = new LibraryDTO(1,"john",1,1);

		Author author = new Author(1,"john","k.carles");
		Category category = new Category(1,"motivation");
		Book book = new Book(1,"title",author,"https://image",category);
		Status status = new Status(1,"reading");

		Library library = new Library(1,"john",book,status);
		converter.when(()->LibraryConverter.libraryDTOtoEntity(libraryDTO)).thenReturn(library);
		when(libraryDAO.save(library)).thenReturn(library);
		when(bookDAO.findById(1)).thenReturn(book);
		when(statusDAO.findById(1)).thenReturn(status);


		libraryService.save(libraryDTO);
		verify(libraryDAO).save(library);
		converter.close();
	}

	@Test
	void testLibraryBookNotFound(){

		LibraryDTO libraryDTO = new LibraryDTO(1,"john",1,1);

		when(bookDAO.findById(1)).thenReturn(null);

		assertThrows(NotFoundException.class,()->libraryService.save(libraryDTO));


	}
	@Test
	void testLibraryStatusNotFound(){

		LibraryDTO libraryDTO = new LibraryDTO(1,"john",1,1);
		Author author = new Author(1,"john","k.carles");
		Category category = new Category(1,"motivation");
		Book book = new Book(1,"title",author,"https://image",category);
		when(bookDAO.findById(1)).thenReturn(book);

		when(statusDAO.findById(1)).thenReturn(null);
		assertThrows(NotFoundException.class,()->libraryService.save(libraryDTO));


	}

	@Test
	void testDeleteIdNotFoundLibrary(){


		when(libraryDAO.findById(1)).thenReturn(null);

	Exception exception=assertThrows(NotFoundException.class,()->libraryService.findById(1));

	String expected = "no library item found with id: 1";
	String actual = exception.getMessage();
	assertEquals(expected.toString(),actual.toString());
	}

	@Test
	void testDeleteLibrary(){
		Author author = new Author(1,"john","k.carles");
		Category category = new Category(1,"motivation");
		Book book = new Book(1,"title",author,"https://image",category);
		Status status = new Status(1,"reading");
		Library library = new Library(1,"john",book,status);
		doNothing().when(libraryDAO).deleteById(1);
		when(libraryDAO.findById(1)).thenReturn(library);
		libraryService.deleteById(1);
		verify(libraryDAO,atLeastOnce()).deleteById(1);
	}








}
