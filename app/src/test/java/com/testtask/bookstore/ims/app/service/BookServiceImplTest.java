package com.testtask.bookstore.ims.app.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import com.testtask.bookstore.ims.app.dto.BookFullTransferDto;
import com.testtask.bookstore.ims.app.dto.BookUpdateResponseDto;
import com.testtask.bookstore.ims.app.dto.CreateNewBookRequestDto;
import com.testtask.bookstore.ims.app.mapper.BookDtoEntityMapper;
import com.testtask.bookstore.ims.app.model.Book;
import com.testtask.bookstore.ims.app.repository.BookRepository;
import java.util.UUID;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {
    private static UUID mockExistingId = UUID.fromString("3a67852c-2d5c-4b3a-a073-8e894be75567");
    private static Book returnBookModel;
    private static BookFullTransferDto resultDto;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private BookDtoEntityMapper bookMapper;

    @InjectMocks
    private BookServiceImpl bookService;

    @BeforeAll
    static void setUpBeforeClass() {
        returnBookModel = new Book();
        returnBookModel.setId(mockExistingId);
        returnBookModel.setTitle("Brave New World");
        returnBookModel.setAuthor("Aldous Huxley");

        resultDto = new BookFullTransferDto(mockExistingId, returnBookModel.getTitle(),
                returnBookModel.getAuthor(), null, null, null);
    }

    @Test
    @DisplayName("Verify save() method works")
    void save_BookRequestDto_ReturnBookFullTransferDto() {
        CreateNewBookRequestDto newBookDto = new CreateNewBookRequestDto("Brave New World",
                "Aldous Huxley");
        Book bookModel = new Book();
        bookModel.setTitle(newBookDto.title());
        bookModel.setAuthor(newBookDto.author());
        when(bookMapper.dtoToModel(newBookDto)).thenReturn(bookModel);
        when(bookRepository.save(bookModel)).thenReturn(Mono.just(returnBookModel));
        when(bookMapper.modelToDto(returnBookModel)).thenReturn(resultDto);

        Mono<BookFullTransferDto> actual = bookService.save(newBookDto);

        assertNotNull(actual);
        StepVerifier.create(actual).consumeNextWith(book -> {
            assertEquals(mockExistingId, book.id());
            assertEquals(newBookDto.title(), book.title());
            assertEquals(newBookDto.author(), book.author());
        }).verifyComplete();
    }

    @Test
    @DisplayName("Verify update() method works, retrieve response with isUpdated=true value")
    void update_existingId_SuccessResponse() {
        BookUpdateResponseDto successUpdate = new BookUpdateResponseDto(mockExistingId, true);
        BookFullTransferDto dtoToUpdate = new BookFullTransferDto(mockExistingId, "Brave New World",
                "Aldous Huxley", "e-154612ns", 10, "test data");
        when(bookRepository.existsById(mockExistingId)).thenReturn(Mono.just(true));
        Book updateBookModel = new Book();
        updateBookModel.setId(dtoToUpdate.id());
        updateBookModel.setAuthor(dtoToUpdate.author());
        updateBookModel.setDescription(dtoToUpdate.description());
        updateBookModel.setIsbn(dtoToUpdate.isbn());
        updateBookModel.setQuantity(dtoToUpdate.quantity());
        updateBookModel.setTitle(dtoToUpdate.title());
        when(bookMapper.dtoWithIdToModel(dtoToUpdate)).thenReturn(updateBookModel);
        when(bookRepository.save(updateBookModel)).thenReturn(Mono.just(updateBookModel));
        when(bookMapper.toUpdatedDto(mockExistingId, true)).thenReturn(successUpdate);

        Mono<BookUpdateResponseDto> actual = bookService.update(dtoToUpdate);

        assertNotNull(actual);
        StepVerifier.create(actual).consumeNextWith(updatedBook -> {
            assertEquals(mockExistingId, updatedBook.id());
            assertTrue(updatedBook.isUpdated());
        }).verifyComplete();
    }

    @Test
    @DisplayName("""
            Verify update(), given not existing id, retrieve response with isUpdated=false value""")
    void update_nonExistingId_FailResponse() {
        UUID mockNotExistingId = UUID.fromString("e13a23d0-34e8-4e78-b848-6d78d6f0df69");
        BookFullTransferDto dtoToUpdate = new BookFullTransferDto(mockNotExistingId,
                "Brave New World", "Aldous Huxley", "e-154612ns", 10, "test data");
        BookUpdateResponseDto failUpdate = new BookUpdateResponseDto(mockNotExistingId, false);
        when(bookRepository.existsById(mockNotExistingId)).thenReturn(Mono.just(false));
        when(bookMapper.toUpdatedDto(mockNotExistingId, false)).thenReturn(failUpdate);

        Mono<BookUpdateResponseDto> actual = bookService.update(dtoToUpdate);

        assertNotNull(actual);
        StepVerifier.create(actual).consumeNextWith(notUpdatedBook -> {
            assertEquals(mockNotExistingId, notUpdatedBook.id());
            assertFalse(notUpdatedBook.isUpdated());
        }).verifyComplete();
    }

    @Test
    @DisplayName("Given valid id, retrieve the ReturnBookFullTransferDto")
    void findById_WithValidId_ReturnBookFullTransferDto() {
        when(bookRepository.findById(mockExistingId)).thenReturn(Mono.just(returnBookModel));
        when(bookMapper.modelToDto(returnBookModel)).thenReturn(resultDto);

        Mono<BookFullTransferDto> actual = bookService.findById(mockExistingId);

        assertNotNull(actual);
        StepVerifier.create(actual).consumeNextWith(book -> assertEquals(mockExistingId, book.id()))
                .verifyComplete();
    }

    @Test
    @DisplayName("Given null id, retrieve the IllegalArgumentException exception")
    void findById_Null_IllegalArgumentException() {
        UUID mockNotExistingId = null;
        when(bookRepository.findById(mockNotExistingId)).thenThrow(new IllegalArgumentException());

        assertThrows(IllegalArgumentException.class, () -> bookService.findById(mockNotExistingId));
    }
}
