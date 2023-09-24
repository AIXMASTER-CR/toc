mport java.util.*;
import java.util.AbstractMap.SimpleEntry;

public class MealyMachine {
    public static boolean runMealy(Map<String, Map<Character, SimpleEntry<String, Character>>> table, String start, String str) {
        String currentState = start;
        StringBuilder output = new StringBuilder();

        System.out.print("\nTraversing path: \n" + currentState);

        for (char symbol : str.toCharArray()) {
            if (!table.containsKey(currentState) || !table.get(currentState).containsKey(symbol)) {
                return false;
            }

            SimpleEntry<String, Character> transition = table.get(currentState).get(symbol);
            currentState = transition.getKey();
            char outputSymbol = transition.getValue();
            output.append(outputSymbol);
            System.out.print(" - " + symbol + " -> " + currentState + " (Output: " + outputSymbol + ")");
        }

        System.out.println("\nOutput: " + output.toString());
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the total number of states: ");
        int n = sc.nextInt();
        sc.nextLine();

        List<String> states = new ArrayList<>();
        System.out.print("\nEnter the states: ");
        String[] statesArray = sc.nextLine().split(" ");
        states.addAll(Arrays.asList(statesArray));

        System.out.print("\nEnter the start state: ");
        String start = sc.nextLine();

        System.out.print("\nEnter the number of language symbols: ");
        int numSymbols = sc.nextInt();
        sc.nextLine();
        List<Character> language = new ArrayList<>();
        System.out.print("\nEnter the language symbols: ");
        String[] languageArray = sc.nextLine().split(" ");
        for (String symbol : languageArray) {
            language.add(symbol.charAt(0));
        }

        Map<String, Map<Character, SimpleEntry<String, Character>>> table = new HashMap<>();
        for (String state : states) {
            table.put(state, new HashMap<>());
            for (char symbol : language) {
                System.out.print("\nTransition for state " + state + " and symbol " + symbol + " (nextState outputSymbol): ");
                String[] transitionInfo = sc.nextLine().split(" ");
                String nextState = transitionInfo[0];
                char outputSymbol = transitionInfo[1].charAt(0);
                table.get(state).put(symbol, new SimpleEntry<>(nextState, outputSymbol));
            }
        }

        System.out.print("\nEnter the string to check: ");
        String str = sc.nextLine();

        if (runMealy(table, start, str)) {
            System.out.println("\nString is accepted");
        } else {
            System.out.println("\nString is not accepted");
        }
        sc.close();
    }
}
