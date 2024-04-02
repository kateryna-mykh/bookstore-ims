package com.testtask.bookstore.ims.app.mapper;

import com.testtask.bookstore.ims.app.config.MapperConfig;
import com.testtask.bookstore.ims.app.dto.CreateNewBookRequestDto;
import com.testtask.bookstore.ims.app.dto.BookFullTransferDto;
import com.testtask.bookstore.ims.app.dto.BookUpdateResponseDto;
import com.testtask.bookstore.ims.app.model.Book;
import java.util.UUID;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class, imports = UUID.class)
public interface BookDtoEntityMapper {
    BookFullTransferDto modelToDto(Book model);

    Book dtoToModel(CreateNewBookRequestDto dto);
    
    Book dtoWithIdToModel(BookFullTransferDto dto);
    
    BookUpdateResponseDto toUpdatedDto(UUID id, Boolean isUpdated);
}
