package javaflix.mapper;

import javaflix.controller.request.StreamingRequest;
import javaflix.controller.response.StreamingResponse;
import javaflix.entity.Streaming;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StreamingMapper {

    public static Streaming toStreaming(StreamingRequest streamingRequest){
        return Streaming.builder()
                .nome(streamingRequest.nome())
                .build();
    }

    public static StreamingResponse toStreamingResponse(Streaming streaming){
        return StreamingResponse.builder()
                .id(streaming.getId())
                .nome(streaming.getNome())
                .build();
    }
}
