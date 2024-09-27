def dfs_(gph, s_node):
    visited = set()  # To track visited nodes
    stack = [s_node]  # Use a list as a stack

    while stack:
        node = stack.pop()  # Pop the last element (LIFO)
        if node not in visited:
            print(node, end=" ")  # Process the current node
            visited.add(node)

            # Push all adjacent nodes that have not been visited
            for neighbor in gph[node]:
                if neighbor not in visited:
                    stack.append(neighbor)

# Example Usage
gph = {0: [1, 2],1: [0,2,3],2: [0,1],3: [1]}
s_from=2 # we can change the searching vertex through change the value in this line(0-3)   
print(f"Depth-First Search starting from node {s_from}:")
dfs_(gph, s_from)
