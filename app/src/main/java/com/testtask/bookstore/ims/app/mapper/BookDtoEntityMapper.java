package com.testtask.bookstore.ims.app.mapper;

import com.testtask.bookstore.ims.app.config.MapperConfig;
import com.testtask.bookstore.ims.app.dto.BookRequestDto;
import com.testtask.bookstore.ims.app.dto.BookResponseDto;
import com.testtask.bookstore.ims.app.dto.BookUpdateResponseDto;
import com.testtask.bookstore.ims.app.model.Book;
import java.util.UUID;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class, imports = UUID.class)
public interface BookDtoEntityMapper {
    BookResponseDto modelToDto(Book model);

    Book dtoToModel(BookRequestDto dto);
    
    Book dtoWithIdToModel(BookResponseDto dto);
    
    BookUpdateResponseDto toUpdatedDto(UUID id, Boolean isUpdated);
}
