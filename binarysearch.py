def binary_search(arr, target):
    low, high = 0, len(arr) - 1
    while low <= high:
        mid = (low + high) // 2
        if arr[mid] == target:
            return mid
        elif arr[mid] < target:
            low = mid + 1
        else:
            high = mid - 1
    return -1

def main():
    size = input("Enter the size of the array: ")
    while size == '':
        print("Input cannot be empty. Please enter a valid number.")
        size = input("Enter the size of the array: ")
    size = int(size)

    arr = []
    print("Enter the sorted array:")
    for i in range(size):
        arr.append(int(input(f"Element {i + 1}: ")))

    target = int(input("Enter search element : "))
    result = binary_search(arr, target)

    if result != -1:
        print(f"Binary Search: Target found at index {result}")
    else:
        print("Binary Search: Target not found in the array")

if __name__ == "__main__":
    main()
