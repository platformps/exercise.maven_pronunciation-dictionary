package com.github.perschola.linguistics;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static com.github.perschola.linguistics.CMUDictionary.DICTIONARY;
import static java.util.Collections.singletonList;

public enum CMUDictionaryDecision {
    GET_PRONUNCIATION((String string) ->
            singletonList(DICTIONARY.getPronunciation(string))),

    GET_SIMILAR_WORDS((string) ->
            singletonList(DICTIONARY.getWordsSimilarPronunciation(string))),

    GET_IDENTICAL_PRONUNCIATION((string) ->
            singletonList(DICTIONARY.getWordsIdenticalPronunciation(string))),

    GET_SPELLING((string) ->
            singletonList(singletonList(string))),

    GET_SIMILAR_WORDS_WITH_SUB_PHONEMES(DICTIONARY::getPhonemesRemoved),
    GET_SIMILAR_WORDS_WITH_PHONEMES(DICTIONARY::getPhonemesAdded);

    private final Function<String, List<List<String>>> fetchFunction;

    CMUDictionaryDecision(Function<String, List<List<String>>> fetchFunction) {
        this.fetchFunction = fetchFunction;
    }

    public static CMUDictionaryDecision getValueOf(String userInput) {
        return valueOf(userInput
                .replaceAll(" ", "_")
                .toUpperCase());
    }

    public static List<String> names() {
        List<String> names = new ArrayList<>();
        for (CMUDictionaryDecision decision : values()) {
            names.add(decision.name());
        }
        return names;
    }

    public List<List<String>> find(String word) {
        return fetchFunction.apply(word);
    }
}
