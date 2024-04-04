package com.testtask.bookstore.ims.app.mapper;

import com.testtask.bookstore.ims.app.config.MapperConfig;
import com.testtask.bookstore.ims.app.dto.CreateNewBookRequestDto;
import com.testtask.bookstore.ims.app.dto.BookFullTransferDto;
import com.testtask.bookstore.ims.app.dto.BookUpdateResponseDto;
import com.testtask.bookstore.ims.proto.BookFullObject;
import com.testtask.bookstore.ims.proto.BookUpdateResponse;
import com.testtask.bookstore.ims.proto.CreateBookRequest;
import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, imports = UUID.class)
public interface BookDtoProtoMapper {
    CreateNewBookRequestDto protoCreateToDto(CreateBookRequest request);
    
    BookFullObject dtoToProto(BookFullTransferDto dto);
    
    BookFullTransferDto protoUpdateToDto(BookFullObject request);
    
    @Mapping(target="id.bookId", source="id")
    BookUpdateResponse dtoToUpdateProto(BookUpdateResponseDto dto);
}
