package com.luv2code.springboot.thymeleafdemo;

import com.luv2code.springboot.thymeleafdemo.controller.DemoController;
import com.luv2code.springboot.thymeleafdemo.controller.LoginController;
import com.luv2code.springboot.thymeleafdemo.dao.BookRepository;
import com.luv2code.springboot.thymeleafdemo.dao.CategoryRepository;
import com.luv2code.springboot.thymeleafdemo.dao.UserRepository;
import com.luv2code.springboot.thymeleafdemo.entity.Book;
import com.luv2code.springboot.thymeleafdemo.entity.Category;
import com.luv2code.springboot.thymeleafdemo.entity.User;
import com.luv2code.springboot.thymeleafdemo.service.BookService;
import com.luv2code.springboot.thymeleafdemo.service.CategoryService;
import com.luv2code.springboot.thymeleafdemo.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.mockito.Mockito.*;


@SpringBootTest
@RunWith(SpringRunner.class)
class ThymeleafDemoApplicationTests {
	@Autowired
	private BookService bookService;

	@MockBean
	private BookRepository bookRepository;

	@Autowired
	private UserService userService;

	@MockBean
	private UserRepository userRepository;

	@Autowired
	private CategoryService categoryService;

	@MockBean
	private CategoryRepository categoryRepository;


	@Test
	void showHome() {
		DemoController demoController = new DemoController(); //Arrange
		String response = demoController.showHome();          //Act
		Assertions.assertEquals("redirect:/login-page",response); //Assert
	}

	@Test
	void loginPage()
	{
		LoginController loginController = new LoginController();
		String response = loginController.showMyLoginPage();
		Assertions.assertEquals("new-login",response);
	}
	@Test
	void saveBook()
	{
		Book book = new Book("Math","1st");
		bookService.save(book);
		verify(bookRepository,times(1)).save(book);
	}
	@Test
	void saveCategory()
	{
		Category category=new Category("BIOMEDICAL");
		categoryService.save(category);
		verify(categoryRepository,times(1)).save(category);
	}

	@Test
	void saveUser()
	{
		User user =  new User("ekta", "password123");
		userService.save(user);
		verify(userRepository,times(1)).save(user);
	}
	@Test
	void findAllTestUser() {
		when(userRepository.findAll())
				.thenReturn(Stream.of(
								new User("karthik",
										"password123"),
								new User("sujay",
										"password123")).
						collect(Collectors.toList()));

		Assertions.assertEquals(2, userService.findAll().size());
	}
	@Test
	void findAllBooks() {
		when(bookRepository.findAll())
				.thenReturn(Stream.of(
								new Book("harry potter",
										"1st"),
								new Book("murder on the orient express",
										"9th")).
						collect(Collectors.toList()));

		Assertions.assertEquals(2, bookService.findAll().size());
	}
	@Test
	void findAllCategories() {
		when(categoryRepository.findAll())
				.thenReturn(Stream.of(
								new Category("horror"),
								new Category("psychothriller")).
						collect(Collectors.toList()));

		Assertions.assertEquals(2, categoryService.findAll().size());
	}
	@Test
	void deleteByIdTestBook(){
		Book book=new Book("book2", "50th");
		book.setId(0);
		bookService.deleteById(0);
		verify(bookRepository,times(1)).deleteById(0);
	}
	@Test
	void deleteByIdTestCategory(){
		Category category=new Category("cat1");
		category.setId(0);
		categoryService.deleteById(0);
		verify(categoryRepository,times(1)).deleteById(0);
	}

   @Test
	void updateByIdTestBook(){
	   int id = 0;
	   Book book = new Book("book17","17th");
	   book.setEdition("49th");
	   when(bookRepository.findById(id)).thenReturn(Optional.of(book));
	   Assertions.assertEquals("49th",book.getEdition());
   }
	@Test
	void findByIdTestBook() {
		int id = 0;
		Book book = new Book("book17","17th");
		when(bookRepository.findById(id)).thenReturn(Optional.of(book));
		Assertions.assertEquals(book, bookService.findById(0));

	}
	@Test
	void findByIdTestCategory() {
		int id = 0;
		Category category=new Category("psychothriller");
		when(categoryRepository.findById(id)).thenReturn(Optional.of(category));
		Assertions.assertEquals(category, categoryService.findById(0));

	}
	@Test
	void addTestCategory() {
		int id = 0;
		Category category=new Category("psychothriller");
		when(categoryRepository.findById(id)).thenReturn(Optional.of(category));
		Assertions.assertEquals(category, categoryService.findById(0));

	}



}
