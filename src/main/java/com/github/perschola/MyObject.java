package com.github.perschola;

import com.github.perschola.linguistics.CMUDictionaryDecision;
import com.github.perschola.utils.Console;

import java.util.List;

public class MyObject implements Runnable {
    Console CONSOLE = Console.INSTANCE;

    public void setup() {
    }

    public void run() {
        String userInputWord;
        String userInputDecision;
        do {
            CONSOLE.println("Welcome to the word selection window");
            userInputWord = CONSOLE.getStringInput("What word would like to investigate?");
            do {
                CONSOLE.println("Welcome to the investigation selection window.");
                CONSOLE.println("How would you like to investigate the word?");
                userInputDecision = CONSOLE.getStringInput(CMUDictionaryDecision.names().toString());
                CMUDictionaryDecision decision = CMUDictionaryDecision.getValueOf(userInputDecision);
                List<List<String>> output = decision.find(userInputWord);
                CONSOLE.println(output.toString());
            } while (!userInputDecision.equals(userInputDecision));
        } while (!"exit application".equals(userInputWord));
    }
}
