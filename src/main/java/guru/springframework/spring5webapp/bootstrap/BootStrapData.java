package guru.springframework.spring5webapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;

@Component
public class BootStrapData implements CommandLineRunner {
	
	private final AuthorRepository authorRepository;
	private final BookRepository bookRepository;
	private final PublisherRepository publisherRepository;
	
	

	public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
		
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
	}



	@Override
	public void run(String... args) throws Exception {
		System.out.println("Started in BootStrapData");
		
		Publisher publisher= new Publisher();
		publisher.setName("ABC Publishing Company");
		publisher.setCity("St Petersberg");
		publisher.setState("FL");
		
		publisherRepository.save(publisher);
		System.out.println("Publisher Count: " + publisherRepository.count());
		
		
		Author eric = new Author("Eric", "Evans");
		Book ddd = new Book("Domain Driven Design", "123123");
		eric.getBooks().add(ddd);
		ddd.getAuthors().add(eric);
		
		ddd.setPublisher(publisher);
		publisher.getBooks().add(ddd);
		
		
		authorRepository.save(eric);
		bookRepository.save(ddd);
		publisherRepository.save(publisher);
		
		Author rod = new Author("Rod", "Johnson");
		Book noEjb = new Book("J2EE Development without EJB", "39393939");
		rod.getBooks().add(noEjb);
		noEjb.getAuthors().add(rod);
		
		noEjb.setPublisher(publisher);
        publisher.getBooks().add(noEjb);
		
		authorRepository.save(rod);
		bookRepository.save(noEjb);
		publisherRepository.save(publisher);
		
		
		System.out.println("Number of Books:"+bookRepository.count());
		System.out.println("Publisher Number of Books: " + publisher.getBooks().size());

	}

}
