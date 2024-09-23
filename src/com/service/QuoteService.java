package com.service;
import com.model.Quote;
import com.repository.QuoteRepository;

import java.time.LocalDate;

public class QuoteService {
    public void createQuote(LocalDate issueDate, LocalDate validityDate, Long lastProjectId) {
        Quote quote = new Quote();
        quote.setIssueDate(issueDate);
        quote.setValidityDate(validityDate);
        new QuoteRepository().createQuote(quote ,lastProjectId);
    }
    public Quote getQuote(Long projectId){
        return new QuoteRepository().getQuote(projectId);
    }
    public void updateQuote(String status, Long quoteId){
        new QuoteRepository().updateQuote(status, quoteId);
    }
}
