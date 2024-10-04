def linear_search(arr, target):
    for i in range(len(arr)):
        if arr[i] == target:
            return i
    return -1

arr = []
n = int(input("Enter the number of elements: "))                # 5getting input

print("Enter the elements one by one:")
for _ in range(n):
    num = int(input())
    arr.append(num)

target = int(input("Enter the target number to search: "))

result = linear_search(arr, target)                                
                                                                 # search operation
if result != -1:
    print("Element found at index", result)
else:
    print("Element not found")
