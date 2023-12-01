package io.github.stjepanmamusa.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.stjepanmamusa.response.ApiResponse;
import io.github.stjepanmamusa.response.fact.Fact;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;

public class CatsRestClient {
    private final OkHttpClient client;
    private final String BASE_URI;

    public CatsRestClient(String baseUri) {
        this.client = new OkHttpClient();
        this.BASE_URI = baseUri;
    }

    public ApiResponse<List<Fact>> getFacts() {
        Request request = new Request.Builder()
                .url(BASE_URI + CatApiPaths.FACTS)
                .build();
        return callAndProcess(request, new TypeReference<List<Fact>>() {});
    }

    private <Res> ApiResponse<Res> callAndProcess(Request request, TypeReference<Res> responseType) {
        try (Response res = client.newCall(request).execute()) {
            ObjectMapper mapper = new ObjectMapper();
            Res data = mapper.readValue(res.body().string(), responseType);
            return new ApiResponse<>(data);
        } catch (IOException e) {
            return new ApiResponse<>(e);
        }
    }
}
