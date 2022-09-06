package com.josephrexrode.quotivationproject.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.josephrexrode.quotivationproject.models.ApiQuote;

@Service
public class RestService {

	@Autowired
	QuoteService qServ;
	
	private final RestTemplate restTemplate;
	
	public RestService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}
	
	// this returns the json array as a string
	public String getQuotesPlainJSON(String url) {
		return  this.restTemplate.getForObject(url, String.class);
	}
	
	// this parses the array and creates a new quote for each object in the array
	public void retrieveQuotes(String url) throws JsonMappingException, JsonProcessingException {
		String quotesJson = url;
		
		// mapper used to read json values
		ObjectMapper mapper = new ObjectMapper();
		
		// read and translate json array into a list of class ApiQuote (see model section)
		List<ApiQuote> quotes = Arrays.asList(mapper.readValue(quotesJson, ApiQuote[].class));
		
		// for each ApiQuote, create a new Quote object with the necessary data
		for (var i = 0; i < quotes.size(); i++) {
			ApiQuote quote = quotes.get(i);
			
			qServ.create(quote.getQ(), quote.getA());
		}
	}	
}
