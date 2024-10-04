def bubblesort(arr):
    n = len(arr)
    
    for i in range(n):
        swapped = False
        
        for j in range(n - i - 1):
            if arr[j] > arr[j + 1]:
                arr[j], arr[j + 1] = arr[j + 1], arr[j]
                swapped = True
        
        if not swapped:
            break

arr = []
n = int(input("Enter number of elements: "))
print("Enter the elements:")

for i in range(n):
    arr.append(int(input()))

bubblesort(arr)
print("Sorted array:", arr)
