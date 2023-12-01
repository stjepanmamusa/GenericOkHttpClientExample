package io.github.stjepanmamusa.service;

import io.github.stjepanmamusa.client.CatsRestClient;
import io.github.stjepanmamusa.response.ApiResponse;
import io.github.stjepanmamusa.response.error.ErrorConsts;
import io.github.stjepanmamusa.response.fact.Fact;

import java.util.Arrays;
import java.util.List;

public class CatService {
    private static final String BASE_URL = "https://cat-fact.herokuapp.com";
    private final CatsRestClient client;

    public CatService() {
        this.client = new CatsRestClient(BASE_URL);
    }

    public CatService(String url) {
        this.client = new CatsRestClient(url);
    }

    public String getSingleCatFact() {
        ApiResponse<List<Fact>> factsApiResponse = client.getFacts();
        if(factsApiResponse.isSuccess()) {
            return factsApiResponse.getData().stream().findAny().get().getText();
        }
        else return ErrorConsts.PROCESSING_ERROR;

    }

    public List<String> getAFewCatFacts() {
        ApiResponse<List<Fact>> factsApiResponse = client.getFacts();
        if(factsApiResponse.isSuccess()) {
            return factsApiResponse.getData().stream().map(Fact::getText).toList();
        }
        else return Arrays.asList(ErrorConsts.PROCESSING_ERROR);
    }
}
