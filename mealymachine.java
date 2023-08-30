import java.util.*;

 class Main {
     public static void main(String[] args) {
         MealyMachineValidateString.main();
     }
 }

 class MealyMachineValidateString {
     public static void main() {
         String inputString, message;

         message = "Enter valid input symbols (comma-separated): ";
         inputString = getInput(message);
         Set<String> validInputs = new HashSet<>(Arrays.asList(inputString.split(",")));

         message = "Enter states (Q) (comma-separated): ";
         inputString = getInput(message);
         Set<String> states = new HashSet<>(Arrays.asList(inputString.split(",")));

         message = "Enter starting state : ";
         String startingState = getInput(message);

         Map<String, Map<String, String>> transitions = new HashMap<>();

         for (String state : states) {
             Map<String, String> transition = new HashMap<>();
             for (String validInput : validInputs) {
                 message = String.format("Enter next state for transition '%s' with input '%s': ", state, validInput);
                 String next_state = getInput(message);
                 message = String.format("Enter output for transition '%s' with input '%s': ", state, validInput);
                 String output = getInput(message);
                 transition.put(validInput, next_state + "," + output);
             }
             transitions.put(state, transition);
         }

         MealyMachine mealyMachine = new MealyMachine(startingState, transitions);

         while (true) {
             message = "Enter input sequence to be processed: ";
             String input_sequence = getInput(message);
             mealyMachine.process_input_sequence(input_sequence);

             System.out.print("Do you want to process another input sequence? (y/n): ");
             String choice = new Scanner(System.in).nextLine();
             if (choice.equalsIgnoreCase("n")) {
                 break;
             }
         }
     }

     public static String getInput(String message) {
         Scanner scanner = new Scanner(System.in);
         String inputString;

         do {
             System.out.print(message);
             inputString = scanner.nextLine();
             if (inputString.length() == 0) {
                 System.out.println("Input can not be empty");
             }
         } while (inputString.length() == 0);

         return inputString;
     }
 }

 class MealyMachine {
     private String startingState;
     private Map<String, Map<String, String>> transitions;

     public MealyMachine(String startingState, Map<String, Map<String, String>> transitions) {
         this.startingState = startingState;
         this.transitions = transitions;
     }

     public void process_input_sequence(String input_sequence) {
         String current_state = startingState;
         String output_sequence = "";
         List<String> traversal_path = new ArrayList<>();
         traversal_path.add(current_state);

         for (int i = 0; i < input_sequence.length(); i++) {
             String input_symbol = String.valueOf(input_sequence.charAt(i));
             Map<String, String> current_transitions = transitions.get(current_state);

             if (current_transitions.containsKey(input_symbol)) {
                 String next_state_and_output = current_transitions.get(input_symbol);
                 String[] next_state_and_output_arr = next_state_and_output.split(",");
                 String next_state = next_state_and_output_arr[0];
                 String output = next_state_and_output_arr[1];

                 output_sequence += output;
                 traversal_path.add(current_state + "(" + input_symbol + "," + output + ")->" + next_state);
                 current_state = next_state;
             } else {
                 showError("Invalid transition: State '" + current_state + "' with input '" + input_symbol + "'");
                 return;
             }
         }

         System.out.println("Output Sequence: " + output_sequence);
         System.out.println();
         System.out.println("Traversal Path: " + String.join(" -> ", traversal_path));
     }

     public static void showError(String message) {
         System.out.println("Output: Your string is not valid");
         System.out.println("Reason: " + message);
     }
 }
