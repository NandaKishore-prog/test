def bfs_(gph, s_node):
    visited = set() 
    queue = [s_node]  
    visited.add(s_node)

    while queue:
        node = queue.pop(0)  
        print(node, end=" ") 

        
        for neighbor in gph[node]:
            if neighbor not in visited:
                queue.append(neighbor)
                visited.add(neighbor)

gph= {0: [1, 2],1: [0,2,3],2: [0,1],3: [1]}
s_from=2 # we can change the searching vertex through change the value in this line(0-3)
print(f"Breadth-First Search starting at node {s_from}:")
bfs_(gph, s_from)

