def run_mealy(table, start, input_str):
    current_state = start
    output = ""

    print("\nTraversing path: \n" + current_state)

    for symbol in input_str:
        if current_state not in table or symbol not in table[current_state]:
            return False

        transition = table[current_state][symbol]
        current_state = transition[0]
        output_symbol = transition[1]
        output += output_symbol
        print(" - " + symbol + " -> " + current_state + " (Output: " + output_symbol + ")")

    print("\nOutput: " + output)
    return True

def main():
    n = int(input("Enter the total number of states: "))

    states = input("\nEnter the states: ").split()

    start = input("\nEnter the start state: ")

    num_symbols = int(input("\nEnter the number of language symbols: "))

    language_symbols = input("\nEnter the language symbols: ").split()

    table = {}
    for state in states:
        table[state] = {}
        for symbol in language_symbols:
            print("\nTransition for state " + state + " and symbol " + symbol + " (nextState outputSymbol): ", end="")
            transition_info = input().split()
            next_state = transition_info[0]
            output_symbol = transition_info[1][0]
            table[state][symbol] = (next_state, output_symbol)

    input_str = input("\nEnter the string to check: ")

    if run_mealy(table, start, input_str):
        print("\nString is accepted")
    else:
        print("\nString is not accepted")

if __name__ == "__main__":
    main()
